DROP PROCEDURE IF EXISTS p_get_order_counter;
DROP PROCEDURE IF EXISTS p_update_vip_card_status;
DROP PROCEDURE IF EXISTS p_save_vip_card_transaction;
DROP PROCEDURE IF EXISTS p_stat_get_branch_sales_summary;
DROP PROCEDURE IF EXISTS p_stat_get_total_sales_details;
DROP PROCEDURE IF EXISTS p_stat_get_product_sales_summary;
DROP PROCEDURE IF EXISTS p_stat_get_product_sales_details;
DROP PROCEDURE IF EXISTS p_stat_get_category_sales_summary;
DROP PROCEDURE IF EXISTS p_stat_get_category_sales_details;
DROP PROCEDURE IF EXISTS p_stat_get_discount_summary;
DROP PROCEDURE IF EXISTS p_create_finance_report;
DROP PROCEDURE IF EXISTS p_get_vip_card_summary_reports;
DROP PROCEDURE IF EXISTS p_get_vip_card_branch_reports;
DROP PROCEDURE IF EXISTS p_get_vip_card_reports_total;
DROP PROCEDURE IF EXISTS p_get_pos_counter;

DELIMITER //
CREATE PROCEDURE p_get_order_counter(IN branchId BIGINT)
  BEGIN
    DECLARE counterId INT;
    DECLARE counterValue INT;
    DECLARE updatedAt DATE;
    DECLARE today DATE DEFAULT DATE(NOW());
    START TRANSACTION;
    SELECT
      id,
      updated_at,
      `value`
    FROM t_order_counter
    WHERE branch_id = branchId
    INTO counterId, updatedAt, counterValue FOR UPDATE;
    IF DATE(updatedAt) != today
    THEN
      SET counterValue = 1;
      SET updatedAt = today;
    END IF;
    UPDATE t_order_counter
    SET `value` = counterValue + 1, updated_at = updatedAt
    WHERE id = counterId;

    SELECT counterValue;
    COMMIT;
  END;
//

DELIMITER //
CREATE PROCEDURE p_save_vip_card_transaction(IN cardId    BIGINT, IN amount DECIMAL(8, 1), IN branchId BIGINT,
                                             IN bonus     DECIMAL(8, 1), IN invoiceAmount DECIMAL(8, 1),
                                             IN handlerId BIGINT, IN transactionType VARCHAR(20),
                                             IN payType   VARCHAR(20), IN i_point INT, IN i_number VARCHAR(50),
                                             IN createdAt DATETIME)
  BEGIN
    DECLARE v_balance_before DECIMAL(8, 1);
    DECLARE v_balance_after DECIMAL(8, 1);
    DECLARE v_invoice_quotar DECIMAL(8, 1);
    DECLARE v_row_count INT;
    DECLARE v_point INT;
    DECLARE v_transaction_count INT;

    START TRANSACTION;

    IF transactionType != 'INVOICE'
    THEN
      SELECT COUNT(*)
      FROM t_vip_card_transaction
      WHERE number = i_number
      INTO v_transaction_count;
    END IF;

    IF v_transaction_count IS NOT NULL AND v_transaction_count > 0
    THEN
      SELECT -2;
    ELSE
      SELECT
        balance,
        invoice_quota,
        point
      FROM t_vip_card
      WHERE id = cardId
      INTO v_balance_before, v_invoice_quotar, v_point FOR UPDATE;

      IF v_balance_before IS NULL
      THEN
        SELECT 0;
      ELSEIF amount IS NOT NULL AND v_balance_before < amount AND transactionType = 'CONSUME'
        THEN
          SELECT -1;
      ELSE

        IF amount IS NOT NULL
        THEN
          IF transactionType <> 'RECHARGE' AND transactionType <> 'REFUND'
          THEN
            SET v_balance_after = v_balance_before - amount;
          ELSE
            SET v_balance_after = v_balance_before + amount;
            IF bonus IS NOT NULL
            THEN
              SET v_balance_after = v_balance_after + bonus;
            END IF;
          END IF;
        ELSE
          SET v_balance_after = v_balance_before;
        END IF;

        IF invoiceAmount IS NOT NULL
        THEN
          IF transactionType <> 'RECHARGE'
          THEN
            SET v_invoice_quotar = v_invoice_quotar - invoiceAmount;
          ELSE
            SET v_invoice_quotar = v_invoice_quotar + amount - invoiceAmount;
          END IF;
        ELSEIF transactionType = 'RECHARGE'
          THEN
            SET v_invoice_quotar = v_invoice_quotar + amount;
        END IF;

        IF i_point IS NOT NULL
        THEN
          SET v_point = v_point + i_point;
        END IF;

        UPDATE t_vip_card
        SET balance = v_balance_after, invoice_quota = v_invoice_quotar, point = v_point
        WHERE id = cardId;

        IF transactionType = 'INVOICE'
        THEN
          SET v_balance_after = NULL;
          SET v_balance_before = NULL;
        END IF;

        INSERT INTO t_vip_card_transaction (vip_card_id, number, amount, bonus, balance_before, balance_after, created_at,
                                            pay_type, handler_id, branch_id, transaction_type, invoice_amount, point)
        VALUES (cardId, i_number, amount, bonus, v_balance_before, v_balance_after, createdAt, payType, handlerId,
                        branchId, transactionType, invoiceAmount, i_point);

        SELECT ROW_COUNT();
      END IF;
    END IF;
    COMMIT;
  END //

DELIMITER //
CREATE PROCEDURE p_update_vip_card_status(IN vipCardId BIGINT, IN in_status VARCHAR(20))
  BEGIN

    DECLARE v_status VARCHAR(20);

    SELECT status
    FROM t_vip_card
    WHERE id = vipCardId
    INTO v_status FOR UPDATE;

    IF v_status = 'DISABLED'
    THEN SELECT -1;
    ELSE
      UPDATE t_vip_card
      SET status = in_status
      WHERE id = vipCardId;
      SELECT ROW_COUNT();
    END IF;
  END //

DELIMITER //
CREATE PROCEDURE p_stat_get_branch_sales_summary(IN hqId     BIGINT, IN groupId BIGINT, IN branchId BIGINT,
                                                 IN dateFrom DATE,
                                                 IN dateTo   DATE)
  BEGIN

    DROP TEMPORARY TABLE IF EXISTS temp_sales;
    DROP TEMPORARY TABLE IF EXISTS temp_tables;

    CREATE TEMPORARY TABLE temp_sales (
      branch_id        BIGINT        NULL,
      sales            DECIMAL(8, 1) NULL,
      count            INT           NULL,
      count_with_table INT           NULL
    );

    CREATE TEMPORARY TABLE temp_tables (
      branch_id BIGINT NULL,
      tables    INT    NULL
    );

    INSERT INTO temp_sales (branch_id, sales, count, count_with_table)
      SELECT
        branch_id,
        SUM(price),
        COUNT(CASE WHEN o.original_bill IS NULL
          THEN 1
              ELSE NULL END),
        COUNT(CASE WHEN o.original_bill IS NULL AND o.table_id IS NOT NULL
          THEN 1
              ELSE NULL END)
      FROM t_order o
        LEFT JOIN t_branch b ON b.id = o.branch_id
      WHERE
        o.hq_id = hqId AND (groupId IS NULL OR b.group_id = groupId) AND
        (branchId IS NULL OR branch_id = branchId) AND (dateFrom IS NULL OR DATE(created_at) >= dateFrom) AND
        (dateTo IS NULL OR DATE(created_at) <= dateTo) AND status = 'COMPLETE'
      GROUP BY branch_id;

    INSERT INTO temp_tables (branch_id, tables)
      SELECT
        branch_id,
        COUNT(*)
      FROM t_branch_table
      GROUP BY branch_id;

    SELECT
      t1.branch_id                                                         AS b_id,
      b.name                                                               AS b_name,
      CONVERT(t1.sales, DECIMAL(10, 2))                                    AS sales,
      t1.count                                                             AS count,
      IF(t1.count = 0, NULL, CONVERT(t1.sales / t1.count, DECIMAL(10, 2))) AS average,
      IF(t2.tables = 0 OR t1.count_with_table = 0, NULL,
         CONVERT(t1.count_with_table * 100 / t2.tables, DECIMAL(10, 2)))   AS turn_rate
    FROM temp_sales t1
      LEFT JOIN temp_tables t2 ON t2.branch_id = t1.branch_id
      LEFT JOIN t_branch b ON b.id = t1.branch_id;
  END //

DELIMITER //
CREATE PROCEDURE p_stat_get_total_sales_details(IN hqId     BIGINT, IN groupId BIGINT, IN branchId BIGINT,
                                                IN dateFrom DATE,
                                                IN dateTo   DATE)
  BEGIN

    DECLARE v_tables INT;

    DROP TEMPORARY TABLE IF EXISTS temp_by_hour;
    DROP TEMPORARY TABLE IF EXISTS temp_by_service_type;
    DROP TEMPORARY TABLE IF EXISTS temp_by_pay_type;
    DROP TEMPORARY TABLE IF EXISTS temp_by_platform;

    SELECT COUNT(*)
    FROM t_branch_table t
      LEFT JOIN t_branch b ON b.id = t.branch_id
    WHERE b.hq_id = hqId AND (branchId IS NULL OR t.branch_id = branchId) AND
          (groupId IS NULL OR b.group_id = groupId)
    INTO v_tables;

    SELECT hqId AS hq_id;

    CREATE TEMPORARY TABLE temp_by_hour (
      `date`           DATE          NULL,
      hour             INT           NULL,
      sales            DECIMAL(8, 1) NULL,
      count            INT           NULL,
      count_with_table INT           NULL
    );

    INSERT INTO temp_by_hour (date, hour, sales, count, count_with_table)
      SELECT
        DATE(o.created_at),
        HOUR(o.created_at),
        SUM(o.price),
        COUNT(CASE WHEN o.original_bill IS NULL
          THEN 1
              ELSE NULL END),
        COUNT(CASE WHEN o.original_bill IS NULL AND o.table_id IS NOT NULL
          THEN 1
              ELSE NULL END)
      FROM t_order o
        LEFT JOIN t_branch b ON b.id = o.branch_id
      WHERE o.hq_id = hqId AND (branchId IS NULL OR o.branch_id = branchId) AND
            (groupId IS NULL OR b.group_id = groupId) AND o.status = 'COMPLETE' AND
            (dateFrom IS NULL OR DATE(o.created_at) >= dateFrom) AND
            (dateTo IS NULL OR DATE(o.created_at) <= dateTo)
      GROUP BY DATE(o.created_at), HOUR(o.created_at);

    SELECT
      CONVERT(sales, DECIMAL(10, 2))                                 AS sales,
      count                                                          AS count,
      date                                                           AS `key`,
      hour                                                           AS hour,
      IF(count_with_table = 0 OR v_tables = 0, NULL,
         CONVERT(count_with_table * 100 / v_tables, DECIMAL(10, 2))) AS turn_rate,
      IF(count = 0, NULL, CONVERT(sales / count, DECIMAL(10, 2)))    AS average
    FROM temp_by_hour;

    DROP TEMPORARY TABLE temp_by_hour;

    CREATE TEMPORARY TABLE temp_by_service_type (
      sales            DECIMAL(8, 1) NULL,
      count            INT           NULL,
      service_type     VARCHAR(30)   NULL,
      count_with_table INT           NULL
    );

    INSERT INTO temp_by_service_type (sales, count, count_with_table, service_type)
      SELECT
        SUM(price),
        COUNT(CASE WHEN o.original_bill IS NULL
          THEN 1
              ELSE NULL END),
        COUNT(CASE WHEN o.original_bill IS NULL AND o.table_id IS NOT NULL
          THEN 1
              ELSE NULL END),
        service_type
      FROM t_order o
        LEFT JOIN t_branch b ON b.id = o.branch_id
      WHERE o.hq_id = hqId AND (branchId IS NULL OR o.branch_id = branchId) AND
            (groupId IS NULL OR b.group_id = groupId) AND o.status = 'COMPLETE' AND
            (dateFrom IS NULL OR DATE(o.created_at) >= dateFrom) AND
            (dateTo IS NULL OR DATE(o.created_at) <= dateTo)
      GROUP BY service_type;

    SELECT
      CONVERT(sales, DECIMAL(10, 2))                                 AS sales,
      count                                                          AS count,
      service_type                                                   AS `key`,
      IF(count_with_table = 0 OR v_tables = 0, NULL,
         CONVERT(count_with_table * 100 / v_tables, DECIMAL(10, 2))) AS turn_rate,
      IF(count = 0, NULL, CONVERT(sales / count, DECIMAL(10, 2)))    AS average
    FROM temp_by_service_type;

    DROP TEMPORARY TABLE temp_by_service_type;

    CREATE TEMPORARY TABLE temp_by_pay_type (
      sales            DECIMAL(8, 1) NULL,
      count            INT           NULL,
      count_with_table INT           NULL,
      pay_type         VARCHAR(30)   NULL
    );

    INSERT INTO temp_by_pay_type (sales, count, count_with_table, pay_type)
      SELECT
        SUM(r.amount),
        COUNT(CASE WHEN o.original_bill IS NULL
          THEN 1
              ELSE NULL END),
        COUNT(CASE WHEN o.original_bill IS NULL AND o.table_id IS NOT NULL
          THEN 1
              ELSE NULL END),
        r.pay_type
      FROM t_order_payment_record r
        LEFT JOIN t_order o ON r.order_id = o.id
        LEFT JOIN t_branch b ON b.id = o.branch_id
      WHERE o.hq_id = hqId AND (branchId IS NULL OR o.branch_id = branchId) AND
            (groupId IS NULL OR b.group_id = groupId) AND o.status = 'COMPLETE' AND
            (dateFrom IS NULL OR DATE(o.created_at) >= dateFrom) AND
            (dateTo IS NULL OR DATE(o.created_at) <= dateTo)
      GROUP BY r.pay_type;

    SELECT
      CONVERT(sales, DECIMAL(10, 2))                                 AS sales,
      count                                                          AS count,
      pay_type                                                       AS `key`,
      IF(count_with_table = 0 OR v_tables = 0, NULL,
         CONVERT(count_with_table * 100 / v_tables, DECIMAL(10, 2))) AS turn_rate,
      IF(count = 0, NULL, CONVERT(sales / count, DECIMAL(10, 2)))    AS average
    FROM temp_by_pay_type;

    DROP TEMPORARY TABLE temp_by_pay_type;

    CREATE TEMPORARY TABLE temp_by_platform (
      sales            DECIMAL(8, 1) NULL,
      count            INT           NULL,
      platform         VARCHAR(30)   NULL,
      count_with_table INT           NULL
    );

    INSERT INTO temp_by_platform (sales, count, count_with_table, platform)
      SELECT
        SUM(price),
        COUNT(CASE WHEN o.original_bill IS NULL
          THEN 1
              ELSE NULL END),
        COUNT(CASE WHEN o.original_bill IS NULL AND o.table_id IS NOT NULL
          THEN 1
              ELSE NULL END),
        platform
      FROM t_order o
        LEFT JOIN t_branch b ON b.id = o.branch_id
      WHERE o.hq_id = hqId AND (branchId IS NULL OR o.branch_id = branchId) AND
            (groupId IS NULL OR b.group_id = groupId) AND o.status = 'COMPLETE' AND
            (dateFrom IS NULL OR DATE(o.created_at) >= dateFrom) AND
            (dateTo IS NULL OR DATE(o.created_at) <= dateTo)
      GROUP BY platform;

    SELECT
      CONVERT(sales, DECIMAL(10, 2))                                 AS sales,
      count                                                          AS count,
      platform                                                       AS `key`,
      IF(count_with_table = 0 OR v_tables = 0, NULL,
         CONVERT(count_with_table * 100 / v_tables, DECIMAL(10, 2))) AS turn_rate,
      IF(count = 0, NULL, CONVERT(sales / count, DECIMAL(10, 2)))    AS average
    FROM temp_by_platform;

    DROP TEMPORARY TABLE temp_by_platform;
  END //

DELIMITER //
CREATE PROCEDURE p_stat_get_product_sales_summary(IN hqId     BIGINT, IN groupId BIGINT, IN branchId BIGINT,
                                                  IN dateFrom DATE, IN dateTo DATE, IN categoryId BIGINT)
  BEGIN

    DECLARE v_orders INT;

    SELECT COUNT(*)
    FROM t_order o
      LEFT JOIN t_branch b ON b.id = o.branch_id
    WHERE o.hq_id = hqId AND (branchId IS NULL OR o.branch_id = branchId) AND
          (groupId IS NULL OR b.group_id = groupId) AND o.status = 'COMPLETE' AND
          (dateFrom IS NULL OR DATE(o.created_at) >= dateFrom) AND
          (dateTo IS NULL OR DATE(o.created_at) <= dateTo) AND o.original_bill IS NULL
    INTO v_orders;

    SELECT
      CONVERT(SUM(i.price * i.count), DECIMAL(10, 2))        AS sales,
      SUM(i.count)                                           AS count,
      CONVERT(SUM(i.count) * 100 / v_orders, DECIMAL(10, 2)) AS order_rate,
      p.id                                                   AS p_id,
      p.name                                                 AS p_name,
      p.inter_name                                           AS p_inter_name
    FROM t_order_item i
      LEFT JOIN t_order o ON o.id = i.order_id
      LEFT JOIN t_branch b ON b.id = o.branch_id
      LEFT JOIN t_product p ON p.id = i.product_id
      INNER JOIN (
                   SELECT DISTINCT (cp.product_id) AS product_id
                   FROM t_category_product cp
                   WHERE categoryId IS NULL OR cp.category_id = categoryId) sub ON sub.product_id = p.id
    WHERE o.hq_id = hqId AND (branchId IS NULL OR o.branch_id = branchId) AND
          (groupId IS NULL OR b.group_id = groupId) AND o.status = 'COMPLETE' AND
          (dateFrom IS NULL OR DATE(o.created_at) >= dateFrom) AND
          (dateTo IS NULL OR DATE(o.created_at) <= dateTo)
    GROUP BY i.product_id;
  END //

DELIMITER //
CREATE PROCEDURE p_stat_get_product_sales_details(IN hqId     BIGINT, IN groupId BIGINT, IN branchId BIGINT,
                                                  IN dateFrom DATE, IN dateTo DATE, IN productId BIGINT)
  BEGIN
    SELECT id
    FROM t_product
    WHERE id = productId;

    SELECT
      CONVERT(SUM(i.price * i.count), DECIMAL(10, 2)) AS sales,
      i.service_type                                  AS `key`
    FROM t_order_item i
      LEFT JOIN t_order o ON o.id = i.order_id
      LEFT JOIN t_branch b ON b.id = o.branch_id
    WHERE
           o.hq_id = hqId AND
           i.product_id = productId AND
          (branchId IS NULL OR o.branch_id = branchId) AND
          (groupId IS NULL OR b.group_id = groupId) AND o.status = 'COMPLETE' AND
          (dateFrom IS NULL OR DATE(o.created_at) >= dateFrom) AND
          (dateTo IS NULL OR DATE(o.created_at) <= dateTo)
    GROUP BY i.service_type;

    SELECT
      CONVERT(SUM(i.price * i.count), DECIMAL(10, 2)) AS sales,
      po.name                                         AS `key`
    FROM t_order_item i
      LEFT JOIN t_order o ON o.id = i.order_id
      LEFT JOIN t_branch b ON b.id = o.branch_id
      INNER JOIN t_order_item_option oi ON oi.order_item_id = i.id
      INNER JOIN t_product_option po ON po.id = oi.option_id
    WHERE
           o.hq_id = hqId AND
           i.product_id = productId AND
          (branchId IS NULL OR o.branch_id = branchId) AND
          (groupId IS NULL OR b.group_id = groupId) AND o.status = 'COMPLETE' AND
          (dateFrom IS NULL OR DATE(o.created_at) >= dateFrom) AND
          (dateTo IS NULL OR DATE(o.created_at) <= dateTo)
    GROUP BY oi.option_id
    ORDER BY SUM(i.price * i.count) DESC
    LIMIT 0, 5;


    SELECT
      CONVERT(SUM(i.price * i.count), DECIMAL(10, 2))       AS sales,
      SUM(i.count)                                          AS count,
      CONVERT(SUM(i.count) * 100 / c.count, DECIMAL(10, 2)) AS order_rate,
      DATE(o.created_at)                                    AS date
    FROM t_order_item i
      LEFT JOIN t_order o ON o.id = i.order_id
      LEFT JOIN t_branch b ON b.id = o.branch_id
      LEFT JOIN (SELECT
                   DATE(created_at) AS created_at,
                   COUNT(*)         AS count
                 FROM t_order o
                   LEFT JOIN t_branch b ON b.id = o.branch_id
                 WHERE o.hq_id = hqId AND (branchId IS NULL OR o.branch_id = branchId) AND
                       (groupId IS NULL OR b.group_id = groupId) AND o.status = 'COMPLETE' AND
                       (dateFrom IS NULL OR DATE(o.created_at) >= dateFrom) AND
                       (dateTo IS NULL OR DATE(o.created_at) <= dateTo) AND o.original_bill IS NULL
                 GROUP BY created_at) c ON c.created_at = DATE(o.created_at)
    WHERE o.hq_id = hqId AND (branchId IS NULL OR o.branch_id = branchId) AND
          (groupId IS NULL OR b.group_id = groupId) AND o.status = 'COMPLETE' AND
          (dateFrom IS NULL OR DATE(o.created_at) >= dateFrom) AND
          (dateTo IS NULL OR DATE(o.created_at) <= dateTo) AND i.product_id = productId
    GROUP BY DATE(o.created_at), c.count;
  END //

DELIMITER //

CREATE PROCEDURE p_stat_get_category_sales_summary(
  IN in_hqId     BIGINT,
  IN in_groupId  BIGINT,
  IN in_branchId BIGINT,
  IN in_dateFrom DATE,
  IN in_dateTo   DATE
)
  BEGIN
    DECLARE var_orderCount BIGINT;

    SELECT COUNT(*)
    FROM t_order a
      LEFT JOIN t_branch b ON a.branch_id = b.id
    WHERE status = 'COMPLETE'
          AND a.hq_id = in_hqId
          AND a.original_bill IS NULL
          AND (in_dateTo IS NULL OR DATE(a.created_at) <= in_dateTo)
          AND (in_dateFrom IS NULL OR DATE(a.created_at) >= in_dateFrom)
          AND (in_groupId IS NULL OR b.group_id = in_branchId)
          AND (in_branchId IS NULL OR a.branch_id = in_branchId)
    INTO var_orderCount;


    SELECT
      c.category_id                                                AS c_id,
      d.name                                                       AS c_name,
      d.inter_name                                                 AS c_inter_name,
      SUM(b.count)                                                 AS `count`,
      CONVERT(SUM(b.count * b.price), DECIMAL(10, 2))              AS sales,
      CONVERT(SUM(b.count) * 100 / var_orderCount, DECIMAL(10, 2)) AS order_rate
    FROM t_order a
      LEFT JOIN t_order_item b ON a.id = b.order_id
      INNER JOIN t_category_product c ON c.product_id = b.product_id
      LEFT JOIN t_category d ON c.category_id = d.id
      LEFT JOIN t_branch e ON e.id = a.branch_id
    WHERE
      a.status = 'COMPLETE'
      AND a.hq_id = in_hqId
      AND (in_dateTo IS NULL OR DATE(a.created_at) <= in_dateTo)
      AND (in_dateFrom IS NULL OR DATE(a.created_at) >= in_dateFrom)
      AND (in_groupId IS NULL OR e.group_id = in_groupId)
      AND (in_branchId IS NULL OR a.branch_id = in_branchId)
    GROUP BY c.category_id, d.name;

  END //

DELIMITER //

CREATE PROCEDURE p_stat_get_category_sales_details(
  IN in_hqId       BIGINT,
  IN in_groupId    BIGINT,
  IN in_branchId   BIGINT,
  IN in_dateFrom   DATE,
  IN in_dateTo     DATE,
  IN in_categoryId BIGINT
)
  BEGIN

    SELECT in_categoryId AS category_id;

    SELECT
      c.service_type                                  AS `key`,
      CONVERT(SUM(c.count * c.price), DECIMAL(10, 2)) AS sales
    FROM t_order a
      LEFT JOIN t_branch b ON a.branch_id = b.id
      LEFT JOIN t_order_item c ON a.id = c.order_id
      INNER JOIN t_category_product d ON c.product_id = d.product_id
    WHERE
      a.status = 'COMPLETE'
      AND a.hq_id = in_hqId
      AND (in_dateTo IS NULL OR DATE(a.created_at) <= in_dateTo)
      AND (in_dateFrom IS NULL OR DATE(a.created_at) >= in_dateFrom)
      AND (in_groupId IS NULL OR b.group_id = in_groupId)
      AND (in_branchId IS NULL OR a.branch_id = in_branchId)
      AND (in_categoryId IS NULL OR d.category_id = in_categoryId)
    GROUP BY c.service_type;


    SELECT
      e.name                                          AS `key`,
      CONVERT(SUM(c.count * c.price), DECIMAL(10, 2)) AS sales
    FROM t_order a
      LEFT JOIN t_branch b ON a.branch_id = b.id
      LEFT JOIN t_order_item c ON a.id = c.order_id
      INNER JOIN t_category_product d ON c.product_id = d.product_id
      LEFT JOIN t_product e ON e.id = c.product_id
    WHERE
      a.status = 'COMPLETE'
      AND a.hq_id = in_hqId
      AND (in_dateTo IS NULL OR DATE(a.created_at) <= in_dateTo)
      AND (in_dateFrom IS NULL OR DATE(a.created_at) >= in_dateFrom)
      AND (in_groupId IS NULL OR b.group_id = in_groupId)
      AND (in_branchId IS NULL OR a.branch_id = in_branchId)
      AND d.category_id = in_categoryId
    GROUP BY c.product_id
    ORDER BY SUM(c.count * c.price) DESC
    LIMIT 0, 5;

    SELECT
      DATE(a.created_at)                                    AS `date`,
      CONVERT(SUM(b.count) * 100 / e.count, DECIMAL(10, 2)) AS order_rate,
      SUM(b.count)                                          AS `count`,
      CONVERT(SUM(b.count * b.price), DECIMAL(10, 2))       AS sales
    FROM t_order a
      LEFT JOIN t_order_item b ON a.id = b.order_id
      LEFT JOIN t_branch c ON c.id = a.branch_id
      LEFT JOIN t_category_product d ON d.product_id = b.product_id
      LEFT JOIN (SELECT
                   DATE(created_at) AS created_at,
                   COUNT(*)         AS count
                 FROM t_order
                 WHERE status = 'COMPLETE'
                       AND hq_id = in_hqId
                       AND original_bill IS NULL
                       AND (in_dateTo IS NULL OR DATE(created_at) <= in_dateTo)
                       AND (in_dateFrom IS NULL OR DATE(created_at) >= in_dateFrom) AND
                       (in_branchId IS NULL OR branch_id = in_branchId)
                 GROUP BY created_at) e ON e.created_at = DATE(a.created_at)
    WHERE
      a.status = 'COMPLETE'
      AND a.hq_id = in_hqId
      AND (in_dateTo IS NULL OR DATE(a.created_at) <= in_dateTo)
      AND (in_dateFrom IS NULL OR DATE(a.created_at) >= in_dateFrom)
      AND (in_groupId IS NULL OR c.group_id = in_groupId)
      AND (in_branchId IS NULL OR a.branch_id = in_branchId)
      AND d.category_id = in_categoryId
    GROUP BY DATE(a.created_at), e.count;


  END //

DELIMITER //

CREATE PROCEDURE p_stat_get_discount_summary(IN hqId     BIGINT, IN groupId BIGINT,
                                             IN branchId BIGINT, IN dateFrom DATE, IN dateTo DATE)
  BEGIN
    SELECT
      COUNT(DISTINCT (o.id)) AS count,
      d.id                   AS d_id,
      d.name                 AS d_name
    FROM t_order_item_discount oid
      LEFT JOIN t_order_item oi ON oid.order_item_id = oi.id
      LEFT JOIN t_order o ON oi.order_id = o.id
      LEFT JOIN t_branch b ON b.id = o.branch_id
      LEFT JOIN t_product_discount d ON d.id = oid.discount_id
    WHERE o.status = 'COMPLETE'
          AND o.hq_id = hqId
          AND o.original_bill IS NULL
          AND (dateTo IS NULL OR DATE(o.created_at) <= dateTo)
          AND (dateFrom IS NULL OR DATE(o.created_at) >= dateFrom)
          AND (branchId IS NULL OR o.branch_id = branchId)
          AND (groupId IS NULL OR b.group_id = groupId)
    GROUP BY d.id, d.name;
  END //

DELIMITER //

DELIMITER //
CREATE PROCEDURE p_create_finance_report(IN hqId INT, IN v_date DATE)
  BEGIN
    DECLARE groupId INT;
    DECLARE branchId INT;
    DECLARE orderId INT;
    DECLARE groupEnd BIT DEFAULT FALSE;
    DECLARE hqTotalSales DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqTotalOrders INT DEFAULT 0;
    DECLARE groupTotalSales DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupTotalOrders INT DEFAULT 0;
    DECLARE branchTotalSales DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchTotalOrders INT DEFAULT 0;
    DECLARE hqWechatSales DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqAlipaySales DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqUnipaySales DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqCashSales DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqTotalSigned DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqTotalCoupon DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqVipCardSales DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqTotalRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqCashRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqUnipayRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqWechatRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqAlipayRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqTotalRecharge DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqCashSalesRecharge DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqUnipaySalesRecharge DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqTotalDiscount DECIMAL(10, 1) DEFAULT 0;
    DECLARE hqTotalGifts DECIMAL(8, 1) DEFAULT 0;
    DECLARE hqTotalInvoices DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupWechatSales DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupAlipaySales DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupUnipaySales DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupCashSales DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupTotalSigned DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupTotalCoupon DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupVipCardSales DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupTotalRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupCashRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupUnipayRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupWechatRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupAlipayRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupTotalRecharge DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupCashSalesRecharge DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupUnipaySalesRecharge DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupTotalDiscount DECIMAL(10, 1) DEFAULT 0;
    DECLARE groupTotalGifts DECIMAL(8, 1) DEFAULT 0;
    DECLARE groupTotalInvoices DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchWechatSales DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchAlipaySales DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchUnipaySales DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchCashSales DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchTotalSigned DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchTotalCoupon DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchVipCardSales DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchTotalRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchCashRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchUnipayRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchWechatRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchAlipayRefund DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchTotalRecharge DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchCashSalesRecharge DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchUnipaySalesRecharge DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchTotalDiscount DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchTotalGifts DECIMAL(8, 1) DEFAULT 0;
    DECLARE branchTotalInvoices DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchOrderInvoices DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchRechargeInvoices DECIMAL(10, 1) DEFAULT 0;
    DECLARE branchRowCount INT;
    DECLARE branchCounter INT DEFAULT 0;
    DECLARE orderTotalOriginalPrice DECIMAL(10, 2);
    DECLARE c_group CURSOR FOR SELECT id
                               FROM t_branch_group
                               WHERE hq_id = hqId;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET groupEnd = TRUE;
    DELETE FROM t_financial_report
    WHERE DATE(date) = v_date AND hq_id = hqId;
    OPEN c_group;
    loop_group: LOOP
      FETCH c_group
      INTO groupId;
      IF groupEnd
      THEN
        LEAVE loop_group;
      END IF;
      BEGIN
        DECLARE c_branch CURSOR FOR SELECT id
                                    FROM t_branch
                                    WHERE group_id = groupId;
        SELECT COUNT(*)
        FROM t_branch
        WHERE group_id = groupId
        INTO branchRowCount;
        SET groupTotalSales = 0;
        SET groupTotalOrders = 0;
        SET groupCashSales = 0;
        SET groupUnipaySales = 0;
        SET groupWechatSales = 0;
        SET groupAlipaySales = 0;
        SET groupTotalSigned = 0;
        SET groupTotalCoupon = 0;
        SET groupTotalDiscount = 0;
        SET groupVipCardSales = 0;
        SET groupTotalRecharge = 0;
        SET groupCashSalesRecharge = 0;
        SET groupUnipaySalesRecharge = 0;
        SET groupTotalRefund = 0;
        SET groupWechatRefund = 0;
        SET groupAlipayRefund = 0;
        SET groupCashRefund = 0;
        SET groupUnipayRefund = 0;
        SET groupTotalGifts = 0;
        SET groupTotalInvoices = 0;
        OPEN c_branch;
        loop_branch: LOOP
          FETCH c_branch
          INTO branchId;
          SET branchCounter = branchCounter + 1;
          IF branchCounter > branchRowCount
          THEN
            SET branchCounter = 0;
            LEAVE loop_branch;
          END IF;
          BEGIN
            SELECT
              SUM(price),
              SUM(IF(original_price IS NOT NULL, original_price - price, 0))
            FROM t_order
            WHERE branch_id = branchId AND DATE(created_at) = v_date AND status = 'COMPLETE'
            INTO branchTotalSales, branchTotalDiscount;

            SELECT COUNT(*)
            FROM t_order
            WHERE branch_id = branchId AND DATE(created_at) = v_date AND status = 'COMPLETE' AND original_bill IS NULL
            INTO branchTotalOrders;

            SELECT SUM(amount)
            FROM t_order_payment_record r LEFT JOIN t_order o ON o.id = r.order_id
            WHERE o.branch_id = branchId AND DATE(o.created_at) = v_date AND r.pay_type = 'CASH' AND status = 'COMPLETE'
            INTO branchCashSales;
            SELECT SUM(amount)
            FROM t_order_payment_record r LEFT JOIN t_order o ON o.id = r.order_id
            WHERE
              o.branch_id = branchId AND DATE(o.created_at) = v_date AND r.pay_type = 'UNI_PAY' AND status = 'COMPLETE'
            INTO branchUnipaySales;

            SELECT SUM(amount)
            FROM t_order_payment_record r LEFT JOIN t_order o ON o.id = r.order_id
            WHERE
              o.branch_id = branchId AND DATE(o.created_at) = v_date AND r.pay_type = 'WECHAT' AND status = 'COMPLETE'
            INTO branchWechatSales;

            SELECT SUM(amount)
            FROM t_order_payment_record r LEFT JOIN t_order o ON o.id = r.order_id
            WHERE
              o.branch_id = branchId AND DATE(o.created_at) = v_date AND r.pay_type = 'ALIPAY' AND status = 'COMPLETE'
            INTO branchAlipaySales;

            SELECT SUM(amount)
            FROM t_order_payment_record r LEFT JOIN t_order o ON o.id = r.order_id
            WHERE
              o.branch_id = branchId AND DATE(o.created_at) = v_date AND r.pay_type = 'SIGNED' AND status = 'COMPLETE'
            INTO branchTotalSigned;

            SELECT SUM(amount)
            FROM t_order_payment_record r LEFT JOIN t_order o ON o.id = r.order_id
            WHERE
              o.branch_id = branchId AND DATE(o.created_at) = v_date AND r.pay_type = 'COUPON' AND status = 'COMPLETE'
            INTO branchTotalCoupon;

            SELECT SUM(amount)
            FROM t_order_payment_record r LEFT JOIN t_order o ON o.id = r.order_id
            WHERE o.branch_id = branchId AND DATE(o.created_at) = v_date AND r.pay_type = 'VIPCARD'
                  AND status = 'COMPLETE'
            INTO branchVipCardSales;

            SELECT SUM(amount) * -1
            FROM t_order_payment_record r LEFT JOIN t_order o ON o.id = r.order_id
            WHERE
              o.branch_id = branchId AND DATE(o.created_at) = v_date AND r.pay_type = 'CASH' AND status = 'COMPLETE' AND
              r.amount < 0
            INTO branchCashRefund;

            SELECT SUM(amount) * -1
            FROM t_order_payment_record r LEFT JOIN t_order o ON o.id = r.order_id
            WHERE
              o.branch_id = branchId AND DATE(o.created_at) = v_date AND r.pay_type = 'UNI_PAY' AND status = 'COMPLETE'
              AND r.amount < 0
            INTO branchUnipayRefund;

            SELECT SUM(amount) * -1
            FROM t_order_payment_record r LEFT JOIN t_order o ON o.id = r.order_id
            WHERE
              o.branch_id = branchId AND DATE(o.created_at) = v_date AND r.pay_type = 'WECHAT' AND status = 'COMPLETE'
              AND
              r.amount < 0
            INTO branchWechatRefund;

            SELECT SUM(amount) * -1
            FROM t_order_payment_record r LEFT JOIN t_order o ON o.id = r.order_id
            WHERE
              o.branch_id = branchId AND DATE(o.created_at) = v_date AND r.pay_type = 'ALIPAY' AND status = 'COMPLETE'
              AND
              r.amount < 0
            INTO branchAlipayRefund;

            SELECT SUM(i.original_price * i.count)
            FROM t_order_item i
              LEFT JOIN t_order o ON o.id = i.order_id
            WHERE o.branch_id = branchId AND DATE(o.created_at) = v_date AND status = 'COMPLETE' AND i.price = 0 AND
                  i.original_price IS NOT NULL
            INTO branchTotalGifts;

            SELECT SUM(amount)
            FROM t_vip_card_transaction
            WHERE DATE(created_at) = v_date AND branch_id = branchId AND pay_type = 'CASH'
            INTO branchCashSalesRecharge;

            SELECT SUM(amount)
            FROM t_vip_card_transaction
            WHERE DATE(created_at) = v_date AND branch_id = branchId AND pay_type = 'UNI_PAY'
            INTO branchUnipaySalesRecharge;

            SELECT SUM(amount)
            FROM t_order_invoice_record r
              LEFT JOIN t_order o ON o.id = r.order_id
            WHERE branch_id = branchId AND DATE(r.created_at) = v_date AND status = 'COMPLETE' AND
                  original_bill IS NULL
            INTO branchOrderInvoices;

            SELECT SUM(invoice_amount)
            FROM t_vip_card_transaction
            WHERE branch_id = branchId AND DATE(created_at) = v_date AND transaction_type = 'RECHARGE' AND
                  invoice_amount IS NOT NULL
            INTO branchRechargeInvoices;

            IF branchTotalSales IS NULL
            THEN
              SET branchTotalSales = 0;
            END IF;
            IF branchTotalOrders IS NULL
            THEN
              SET branchTotalOrders = 0;
            END IF;
            IF branchCashSales IS NULL
            THEN
              SET branchCashSales = 0;
            END IF;
            IF branchUnipaySales IS NULL
            THEN
              SET branchUnipaySales = 0;
            END IF;
            IF branchWechatSales IS NULL
            THEN
              SET branchWechatSales = 0;
            END IF;
            IF branchAlipaySales IS NULL
            THEN
              SET branchAlipaySales = 0;
            END IF;
            IF branchTotalSigned IS NULL
            THEN
              SET branchTotalSigned = 0;
            END IF;
            IF branchTotalCoupon IS NULL
            THEN
              SET branchTotalCoupon = 0;
            END IF;
            IF branchVipCardSales IS NULL
            THEN
              SET branchVipCardSales = 0;
            END IF;
            IF branchTotalDiscount IS NULL
            THEN
              SET branchTotalDiscount = 0;
            END IF;
            IF branchCashSalesRecharge IS NULL
            THEN
              SET branchCashSalesRecharge = 0;
            END IF;
            IF branchUnipaySalesRecharge IS NULL
            THEN
              SET branchUnipaySalesRecharge = 0;
            END IF;
            IF branchCashRefund IS NULL
            THEN
              SET branchCashRefund = 0;
            END IF;
            IF branchUnipayRefund IS NULL
            THEN
              SET branchUnipayRefund = 0;
            END IF;
            IF branchWechatRefund IS NULL
            THEN
              SET branchWechatRefund = 0;
            END IF;
            IF branchAlipayRefund IS NULL
            THEN
              SET branchAlipayRefund = 0;
            END IF;
            IF branchOrderInvoices IS NULL
            THEN
              SET branchOrderInvoices = 0;
            END IF;
            IF branchRechargeInvoices IS NULL
            THEN
              SET branchRechargeInvoices = 0;
            END IF;
            IF branchTotalGifts IS NULL
            THEN
              SET branchTotalGifts = 0;
            END IF;

            SET branchTotalInvoices = branchOrderInvoices + branchRechargeInvoices;
            SET branchTotalRefund = branchAlipayRefund + branchWechatRefund + branchUnipayRefund +
                                    branchCashRefund;
            SET branchTotalRecharge = branchCashSalesRecharge + branchUnipaySalesRecharge;

            INSERT INTO t_financial_report (
              hq_id,
              group_id,
              branch_id,
              total_sales,
              orders,
              cash_sales,
              unipay_sales,
              wechat_sales,
              alipay_sales,
              signed_bills,
              vip_card_sales,
              coupons,
              discounts,
              total_refund,
              cash_refund,
              unipay_refund,
              wechat_refund,
              alipay_refund,
              total_recharge,
              cash_recharge,
              unipay_recharge,
              gifts,
              invoices,
              date
            ) VALUES (
              hqId,
              groupId,
              branchId,
              branchTotalSales,
              branchTotalOrders,
              branchCashSales,
              branchUnipaySales,
              branchWechatSales,
              branchAlipaySales,
              branchTotalSigned,
              branchVipCardSales,
              branchTotalCoupon,
              branchTotalDiscount,
              branchTotalRefund,
              branchCashRefund,
              branchUnipayRefund,
              branchWechatRefund,
              branchAlipayRefund,
              branchTotalRecharge,
              branchCashSalesRecharge,
              branchUnipaySalesRecharge,
              branchTotalGifts,
              branchTotalInvoices,
              v_date
            );

            SET groupTotalSales = groupTotalSales + branchTotalSales;
            SET groupTotalOrders = groupTotalOrders + branchTotalOrders;
            SET groupTotalDiscount = groupTotalDiscount + branchTotalDiscount;
            SET groupCashSales = groupCashSales + branchCashSales;
            SET groupUnipaySales = groupUnipaySales + branchUnipaySales;
            SET groupWechatSales = groupWechatSales + branchWechatSales;
            SET groupAlipaySales = groupAlipaySales + branchAlipaySales;
            SET groupTotalSigned = groupTotalSigned + branchTotalSigned;
            SET groupTotalCoupon = groupTotalCoupon + branchTotalCoupon;
            SET groupVipCardSales = groupVipCardSales + branchVipCardSales;
            SET groupCashRefund = groupCashRefund + branchCashRefund;
            SET groupUnipayRefund = groupUnipayRefund + branchUnipayRefund;
            SET groupWechatRefund = groupWechatRefund + branchWechatRefund;
            SET groupAlipayRefund = groupAlipayRefund + branchAlipayRefund;
            SET groupTotalRefund = groupCashRefund + groupUnipayRefund + groupWechatRefund +
                                   groupAlipayRefund;
            SET groupCashSalesRecharge = groupCashSalesRecharge + branchCashSalesRecharge;
            SET groupUnipaySalesRecharge = groupUnipaySalesRecharge + branchUnipaySalesRecharge;
            SET groupTotalRecharge = groupCashSalesRecharge + groupUnipaySalesRecharge;
            SET groupTotalGIfts = groupTotalGifts + branchTotalGifts;
            SET groupTotalInvoices = groupTotalInvoices + branchTotalInvoices;
          END;
        END LOOP;
        CLOSE c_branch;

        SET hqTotalSales = hqTotalSales + groupTotalSales;
        SET hqTotalOrders = hqTotalOrders + groupTotalOrders;
        SET hqTotalDiscount = hqTotalDiscount + groupTotalDiscount;
        SET hqCashSales = hqCashSales + groupCashSales;
        SET hqUnipaySales = hqUnipaySales + groupUnipaySales;
        SET hqWechatSales = hqWechatSales + groupWechatSales;
        SET hqAlipaySales = hqAlipaySales + groupAlipaySales;
        SET hqTotalSigned = hqTotalSigned + groupTotalSigned;
        SET hqTotalCoupon = hqTotalCoupon + groupTotalCoupon;
        SET hqVipCardSales = hqVipCardSales + groupVipCardSales;
        SET hqCashRefund = hqCashRefund + groupCashRefund;
        SET hqUnipayRefund = hqUnipayRefund + groupUnipayRefund;
        SET hqWechatRefund = hqWechatRefund + groupWechatRefund;
        SET hqAlipayRefund = hqAlipayRefund + groupAlipayRefund;
        SET hqTotalRefund = hqCashRefund + hqUnipayRefund + hqWechatRefund + hqAlipayRefund;
        SET hqCashSalesRecharge = hqCashSalesRecharge + groupCashSalesRecharge;
        SET hqUnipaySalesRecharge = hqUnipaySalesRecharge + groupUnipaySalesRecharge;
        SET hqTotalRecharge = hqCashSalesRecharge + hqUnipaySalesRecharge;
        SET hqTotalGifts = hqTotalGifts + groupTotalGifts;
        SET hqTotalInvoices = hqTotalInvoices + groupTotalInvoices;

        INSERT INTO t_financial_report (
          hq_id,
          group_id,
          branch_id,
          total_sales,
          orders,
          cash_sales,
          unipay_sales,
          wechat_sales,
          alipay_sales,
          signed_bills,
          vip_card_sales,
          coupons,
          discounts,
          total_refund,
          cash_refund,
          unipay_refund,
          wechat_refund,
          alipay_refund,
          total_recharge,
          cash_recharge,
          unipay_recharge,
          gifts,
          invoices,
          date
        ) VALUES (
          hqId,
          groupId,
          NULL,
          groupTotalSales,
          groupTotalOrders,
          groupCashSales,
          groupUnipaySales,
          groupWechatSales,
          groupAlipaySales,
          groupTotalSigned,
          groupVipCardSales,
          groupTotalCoupon,
          groupTotalDiscount,
          groupTotalRefund,
          groupCashRefund,
          groupUnipayRefund,
          groupWechatRefund,
          groupAlipayRefund,
          groupTotalRecharge,
          groupCashSalesRecharge,
          groupUnipaySalesRecharge,
          groupTotalGifts,
          groupTotalInvoices,
          v_date
        );
      END;
    END LOOP;
    CLOSE c_group;

    INSERT INTO t_financial_report (
      hq_id,
      group_id,
      branch_id,
      total_sales,
      orders,
      cash_sales,
      unipay_sales,
      wechat_sales,
      alipay_sales,
      signed_bills,
      vip_card_sales,
      coupons,
      discounts,
      total_refund,
      cash_refund,
      unipay_refund,
      wechat_refund,
      alipay_refund,
      total_recharge,
      cash_recharge,
      unipay_recharge,
      gifts,
      invoices,
      date
    ) VALUES (
      hqId,
      NULL,
      NULL,
      hqTotalSales,
      hqTotalOrders,
      hqCashSales,
      hqUnipaySales,
      hqWechatSales,
      hqAlipaySales,
      hqTotalSigned,
      hqVipCardSales,
      hqTotalCoupon,
      hqTotalDiscount,
      hqTotalRefund,
      hqCashRefund,
      hqUnipayRefund,
      hqWechatRefund,
      hqAlipayRefund,
      hqTotalRecharge,
      hqCashSalesRecharge,
      hqUnipaySalesRecharge,
      hqTotalGifts,
      hqTotalInvoices,
      v_date
    );
  END;
//

DELIMITER //
CREATE PROCEDURE p_get_vip_card_summary_reports(IN hqId     BIGINT, IN groupId BIGINT, IN branchId BIGINT,
                                                IN dateFrom DATE, IN dateTo DATE, IN in_page INT, IN in_size INT)
  BEGIN
    DECLARE v_offset INT;

    IF in_page IS NOT NULL
    THEN
      SET v_offset = (in_page - 1) * in_size;
    END IF;

    DROP TEMPORARY TABLE IF EXISTS temp_vip_card_summary_report;

    CREATE TEMPORARY TABLE temp_vip_card_summary_report (
      vip_card_id     BIGINT,
      number          VARCHAR(50),
      name            VARCHAR(50),
      balance         DECIMAL(9, 1),
      invoice_quota   DECIMAL(9, 1),
      cash_recharge   DECIMAL(9, 1),
      unipay_recharge DECIMAL(9, 1),
      refund          DECIMAL(9, 1),
      consume         DECIMAL(9, 1),
      invoice         DECIMAL(9, 1)
    );

    IF in_page IS NULL
    THEN
      INSERT INTO temp_vip_card_summary_report (vip_card_id, number, name, balance, invoice_quota, cash_recharge,
                                                unipay_recharge, refund, consume, invoice)
        SELECT
          c.id,
          c.number,
          c.name,
          c.balance,
          c.invoice_quota,
          SUM(IF(t.transaction_type = 'RECHARGE' AND t.pay_type = 'CASH', t.amount, 0)),
          SUM(IF(t.transaction_type = 'RECHARGE' AND t.pay_type = 'UNI_PAY', t.amount, 0)),
          SUM(IF(t.transaction_type = 'REFUND', t.amount, 0)),
          SUM(IF(t.transaction_type = 'CONSUME', t.amount, 0)),
          SUM(IF(t.invoice_amount IS NOT NULL, t.invoice_amount, 0))
        FROM t_vip_card c
          LEFT JOIN t_vip_card_transaction t ON t.vip_card_id = c.id
          LEFT JOIN t_branch b ON b.id = c.branch_id
        WHERE c.hq_id = hqId AND
              (groupId IS NULL OR b.group_id = groupId) AND
              (branchId IS NULL OR b.id = branchId) AND
              (dateFrom IS NULL OR t.created_at >= dateFrom) AND
              (dateTo IS NULL OR t.created_at <= dateTo)
        GROUP BY c.id, c.number, c.name, c.balance, c.invoice_quota;
    ELSE
      INSERT INTO temp_vip_card_summary_report (vip_card_id, number, name, balance, invoice_quota, cash_recharge,
                                                unipay_recharge, refund, consume, invoice)
        SELECT
          c.id,
          c.number,
          c.name,
          c.balance,
          c.invoice_quota,
          SUM(IF(t.transaction_type = 'RECHARGE' AND t.pay_type = 'CASH', t.amount, 0)),
          SUM(IF(t.transaction_type = 'RECHARGE' AND t.pay_type = 'UNI_PAY', t.amount, 0)),
          SUM(IF(t.transaction_type = 'REFUND', t.amount, 0)),
          SUM(IF(t.transaction_type = 'CONSUME', t.amount, 0)),
          SUM(IF(t.invoice_amount IS NOT NULL, t.invoice_amount, 0))
        FROM t_vip_card c
          LEFT JOIN t_vip_card_transaction t ON t.vip_card_id = c.id
          LEFT JOIN t_branch b ON b.id = c.branch_id
        WHERE c.hq_id = hqId AND
              (groupId IS NULL OR b.group_id = groupId) AND
              (branchId IS NULL OR b.id = branchId) AND
              (dateFrom IS NULL OR t.created_at >= dateFrom) AND
              (dateTo IS NULL OR t.created_at <= dateTo)
        GROUP BY c.id, c.number, c.name, c.balance, c.invoice_quota
        LIMIT v_offset, in_size;
    END IF;

    SELECT
      vip_card_id                     AS c_id,
      name                            AS c_name,
      number                          AS c_number,
      balance                         AS c_balance,
      invoice_quota                   AS c_invoice_quota,
      cash_recharge,
      unipay_recharge,
      cash_recharge + unipay_recharge AS total_recharge,
      consume - refund                AS consume,
      invoice
    FROM temp_vip_card_summary_report;
  END //

DELIMITER //
CREATE PROCEDURE p_get_vip_card_branch_reports(IN hqId     BIGINT, IN groupId BIGINT, IN branchId BIGINT,
                                               IN dateFrom DATE, IN dateTo DATE)
  BEGIN

    DROP TEMPORARY TABLE IF EXISTS temp_vip_card_branch_report;

    CREATE TEMPORARY TABLE temp_vip_card_branch_report (
      branch_id       BIGINT,
      branch_name     VARCHAR(50),
      cash_recharge   DECIMAL(9, 1),
      unipay_recharge DECIMAL(9, 1),
      refund          DECIMAL(9, 1),
      consume         DECIMAL(9, 1),
      invoice         DECIMAL(9, 1)
    );

    INSERT INTO temp_vip_card_branch_report (branch_id, branch_name, cash_recharge, unipay_recharge, refund, consume, invoice)
      SELECT
        b.id,
        b.name,
        SUM(IF(t.transaction_type = 'RECHARGE' AND t.pay_type = 'CASH', t.amount, 0)),
        SUM(IF(t.transaction_type = 'RECHARGE' AND t.pay_type = 'UNI_PAY', t.amount, 0)),
        SUM(IF(t.transaction_type = 'REFUND', t.amount, 0)),
        SUM(IF(t.transaction_type = 'CONSUME', t.amount, 0)),
        SUM(IF(t.invoice_amount IS NOT NULL, t.invoice_amount, 0))
      FROM t_vip_card c
        LEFT JOIN t_vip_card_transaction t ON t.vip_card_id = c.id
        LEFT JOIN t_branch b ON b.id = c.branch_id
      WHERE c.hq_id = hqId AND
            (groupId IS NULL OR b.group_id = groupId) AND
            (branchId IS NULL OR b.id = branchId) AND
            (dateFrom IS NULL OR t.created_at >= dateFrom) AND
            (dateTo IS NULL OR t.created_at <= dateTo)
      GROUP BY b.id, b.name;

    SELECT
      branch_id                       AS b_id,
      branch_name                     AS b_name,
      cash_recharge,
      unipay_recharge,
      cash_recharge + unipay_recharge AS total_recharge,
      consume - refund                AS consume,
      invoice
    FROM temp_vip_card_branch_report;
  END //

DELIMITER //
CREATE PROCEDURE p_get_vip_card_reports_total (IN hqId     BIGINT, IN groupId BIGINT, IN branchId BIGINT,
                                               IN dateFrom DATE, IN dateTo DATE)
  BEGIN

    DROP TEMPORARY TABLE IF EXISTS temp_vip_card_report_total;

    CREATE TEMPORARY TABLE temp_vip_card_report_total (
      cash_recharge   DECIMAL(9, 1),
      unipay_recharge DECIMAL(9, 1),
      refund          DECIMAL(9, 1),
      consume         DECIMAL(9, 1),
      invoice         DECIMAL(9, 1)
    );

    INSERT INTO temp_vip_card_report_total (cash_recharge, unipay_recharge, refund, consume, invoice)
      SELECT
        SUM(IF(t.transaction_type = 'RECHARGE' AND t.pay_type = 'CASH', t.amount, 0)),
        SUM(IF(t.transaction_type = 'RECHARGE' AND t.pay_type = 'UNI_PAY', t.amount, 0)),
        SUM(IF(t.transaction_type = 'REFUND', t.amount, 0)),
        SUM(IF(t.transaction_type = 'CONSUME', t.amount, 0)),
        SUM(IF(t.invoice_amount IS NOT NULL, t.invoice_amount, 0))
      FROM t_vip_card c
        LEFT JOIN t_vip_card_transaction t ON t.vip_card_id = c.id
        LEFT JOIN t_branch b ON b.id = c.branch_id
      WHERE c.hq_id = hqId AND
            (groupId IS NULL OR b.group_id = groupId) AND
            (branchId IS NULL OR b.id = branchId) AND
            (dateFrom IS NULL OR t.created_at >= dateFrom) AND
            (dateTo IS NULL OR t.created_at <= dateTo);

    SELECT
      cash_recharge,
      unipay_recharge,
      cash_recharge + unipay_recharge AS total_recharge,
      consume - refund                AS consume,
      invoice
    FROM temp_vip_card_report_total;
  END //

DELIMITER //
CREATE PROCEDURE p_get_pos_counter(IN branchId BIGINT)
  BEGIN
    DECLARE v_counter INT;
    START TRANSACTION;

    SELECT counter
    FROM t_pos_counter
    WHERE branch_id = branchId
    INTO v_counter FOR UPDATE;

    IF v_counter IS NOT NULL
    THEN
      SET v_counter = v_counter + 1;
      UPDATE t_pos_counter
      SET counter = v_counter
      WHERE branch_id = branchId;

      SELECT v_counter;
    ELSE
      SELECT 0;
    END IF;
    COMMIT;
  END //




