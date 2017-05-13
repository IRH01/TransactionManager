DROP TABLE IF EXISTS t_pos_counter;
DROP TABLE IF EXISTS t_branch_shift_report;
DROP TABLE IF EXISTS t_financial_report;
DROP TABLE IF EXISTS t_wx_transaction;
DROP TABLE IF EXISTS t_alipay_transaction;
DROP TABLE IF EXISTS t_order_counter;
DROP TABLE IF EXISTS t_order_invoice_record;
DROP TABLE IF EXISTS t_order_item_option;
DROP TABLE IF EXISTS t_order_item_discount;
DROP TABLE IF EXISTS t_order_item;
DROP TABLE IF EXISTS t_order_payment_record;
DROP TABLE IF EXISTS t_order;
DROP TABLE IF EXISTS t_order_queue_token;
DROP TABLE IF EXISTS t_vip_card_transaction;
DROP TABLE IF EXISTS t_vip_card;
DROP TABLE IF EXISTS t_product_discount_product;
DROP TABLE IF EXISTS t_product_discount;
DROP TABLE IF EXISTS t_product_product_option_group;
DROP TABLE IF EXISTS t_product_option;
DROP TABLE IF EXISTS t_product_option_group;
DROP TABLE IF EXISTS t_product_print_type;
DROP TABLE IF EXISTS t_category_product;
DROP TABLE IF EXISTS t_branch_product_status_record;
DROP TABLE IF EXISTS t_product;
DROP TABLE IF EXISTS t_print_type;
DROP TABLE IF EXISTS t_category;
DROP TABLE IF EXISTS t_role_permission;
DROP TABLE IF EXISTS t_account;
DROP TABLE IF EXISTS t_permission;
DROP TABLE IF EXISTS t_role;
DROP TABLE IF EXISTS t_branch_table;
DROP TABLE IF EXISTS t_branch;
DROP TABLE IF EXISTS t_branch_group;
DROP TABLE IF EXISTS t_headquarter;

CREATE TABLE t_headquarter (

  id      BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,

  name    VARCHAR(50)  NOT NULL,

  code    VARCHAR(50)  NOT NULL UNIQUE,

  logo    VARCHAR(255) NULL,

  enabled BIT          NOT NULL             DEFAULT TRUE
);

CREATE TABLE t_branch_group (

  id      BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,

  name    VARCHAR(50) NOT NULL,

  hq_id   BIGINT      NOT NULL,

  enabled BIT         NOT NULL             DEFAULT TRUE,

  CONSTRAINT FOREIGN KEY (hq_id) REFERENCES t_headquarter (id)
);

CREATE TABLE t_branch (

  id       BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,

  name     VARCHAR(50)  NOT NULL,

  number   INT          NOT NULL,

  hq_id    BIGINT       NOT NULL,

  group_id BIGINT       NOT NULL,

  phone    VARCHAR(20)  NOT NULL,

  province VARCHAR(20)  NULL,

  city     VARCHAR(50)  NULL,

  district VARCHAR(50)  NULL,

  address  VARCHAR(255) NULL,

  geo      POINT        NULL,

  enabled  BIT          NOT NULL             DEFAULT TRUE,

  CONSTRAINT FOREIGN KEY (hq_id) REFERENCES t_headquarter (id),

  CONSTRAINT FOREIGN KEY (group_id) REFERENCES t_branch_group (id),

  UNIQUE KEY (number, hq_id)
);

CREATE TABLE t_permission (

  id       BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,

  name     VARCHAR(255) NOT NULL,

  code     VARCHAR(50)  NOT NULL,

  category VARCHAR(10)  NOT NULL
);

CREATE TABLE t_role (

  id    BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,

  name  VARCHAR(255) NOT NULL,

  level VARCHAR(20)  NOT NULL,

  hq_id BIGINT       NOT NULL,

  UNIQUE INDEX t_role_name_hq_id (name, hq_id),

  CONSTRAINT FOREIGN KEY (hq_id) REFERENCES t_headquarter (id)
);

CREATE TABLE t_role_permission (

  role_id       BIGINT NOT NULL,

  permission_id BIGINT NOT NULL,

  CONSTRAINT FOREIGN KEY (role_id) REFERENCES t_role (id),

  CONSTRAINT FOREIGN KEY (permission_id) REFERENCES t_permission (id)
);

CREATE TABLE t_account (

  id              BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,

  hq_id           BIGINT       NOT NULL,

  credential_id   VARCHAR(50)  NOT NULL,

  password        VARCHAR(255) NOT NULL,

  fullname        VARCHAR(50)  NOT NULL,

  mobile          VARCHAR(20)  NOT NULL,

  created_at      DATETIME     NOT NULL,

  enabled         BIT          NOT NULL,

  role_id         BIGINT       NOT NULL,

  branch_group_id BIGINT       NULL,

  branch_id       BIGINT       NULL,

  UNIQUE INDEX i_account_credential_id_hq_id (credential_id, hq_id),

  CONSTRAINT FOREIGN KEY (hq_id) REFERENCES t_headquarter (id),

  CONSTRAINT FOREIGN KEY (role_id) REFERENCES t_role (id),

  CONSTRAINT FOREIGN KEY (branch_group_id) REFERENCES t_branch_group (id),

  CONSTRAINT FOREIGN KEY (branch_id) REFERENCES t_branch (id)
);

CREATE TABLE t_category (

  id            BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,

  name          VARCHAR(10) NOT NULL,

  inter_name    VARCHAR(30),

  hq_id         BIGINT      NOT NULL,

  platform      VARCHAR(10) NULL,

  display_order SMALLINT    NOT NULL,

  enabled       BIT         NOT NULL             DEFAULT TRUE,

  CONSTRAINT FOREIGN KEY (hq_id) REFERENCES t_headquarter (id)
);

CREATE TABLE t_print_type (

  id        BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,

  branch_id BIGINT      NOT NULL,

  name      VARCHAR(20) NOT NULL,

  CONSTRAINT FOREIGN KEY (branch_id) REFERENCES t_branch (id)
);

CREATE TABLE t_branch_table (

  id        BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,

  code      VARCHAR(50) NOT NULL,

  zone      VARCHAR(10),

  branch_id BIGINT      NOT NULL,

  parent_id BIGINT      NULL,

  enabled   BIT         NOT NULL,

  UNIQUE INDEX i_branch_table_code_branch_id (code, branch_id),

  CONSTRAINT FOREIGN KEY (branch_id) REFERENCES t_branch (id),

  CONSTRAINT FOREIGN KEY (parent_id) REFERENCES t_branch_table (id)
    ON DELETE CASCADE
);

CREATE TABLE t_product (

  id          BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT,

  hq_id       BIGINT        NOT NULL,

  name        VARCHAR(50)   NOT NULL,

  inter_name  VARCHAR(50)   NULL,

  img_url     VARCHAR(255)  NULL,

  price       DECIMAL(8, 1) NOT NULL,

  status      VARCHAR(20)   NOT NULL,

  description VARCHAR(100)  NULL,

  label       VARCHAR(20)   NULL,

  CONSTRAINT FOREIGN KEY (hq_id) REFERENCES t_headquarter (id)
);

CREATE TABLE t_category_product (

  product_id    BIGINT   NOT NULL,

  category_id   BIGINT   NOT NULL,

  display_order SMALLINT NOT NULL,

  CONSTRAINT FOREIGN KEY (product_id) REFERENCES t_product (id),

  CONSTRAINT FOREIGN KEY (category_id) REFERENCES t_category (id)
);

CREATE TABLE t_product_print_type (

  product_id    BIGINT NOT NULL,

  print_type_id BIGINT NOT NULL,

  CONSTRAINT FOREIGN KEY (product_id) REFERENCES t_product (id),

  CONSTRAINT FOREIGN KEY (print_type_id) REFERENCES t_print_type (id)
);

CREATE TABLE t_product_option_group (

  id      BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,

  hq_id   BIGINT      NOT NULL,

  name    VARCHAR(50) NOT NULL,

  enabled BIT         NOT NULL,

  CONSTRAINT FOREIGN KEY (hq_id) REFERENCES t_headquarter (id)
);

CREATE TABLE t_product_option (

  id         BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT,

  name       VARCHAR(50)   NOT NULL,

  inter_name VARCHAR(50)   NOT NULL,

  price      DECIMAL(5, 2) NOT NULL,

  group_id   BIGINT        NOT NULL,

  enabled    BIT           NOT NULL,

  CONSTRAINT FOREIGN KEY (group_id) REFERENCES t_product_option_group (id)
);

CREATE TABLE t_product_product_option_group (

  product_id BIGINT NOT NULL,

  group_id   BIGINT NOT NULL,

  CONSTRAINT FOREIGN KEY (product_id) REFERENCES t_product (id),

  CONSTRAINT FOREIGN KEY (group_id) REFERENCES t_product_option_group (id)
);

CREATE TABLE t_branch_product_status_record (

  id         BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,

  product_id BIGINT      NOT NULL,

  branch_id  BIGINT      NOT NULL,

  created_at DATETIME    NOT NULL,

  status     VARCHAR(20) NOT NULL,

  UNIQUE INDEX i_branch_product_status_record (product_id, branch_id),

  CONSTRAINT FOREIGN KEY (branch_id) REFERENCES t_branch (id),

  CONSTRAINT FOREIGN KEY (product_id) REFERENCES t_product (id)
);

CREATE TABLE t_product_discount (

  id             BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,

  hq_id          BIGINT      NOT NULL,

  name           VARCHAR(50) NOT NULL,

  discount_type  VARCHAR(20) NOT NULL,

  discount_value INT         NULL,

  start_date     DATE        NULL,

  end_date       DATE        NULL,

  start_time     INT         NULL,

  end_time       INT         NULL,

  CONSTRAINT FOREIGN KEY (hq_id) REFERENCES t_headquarter (id)
);

CREATE TABLE t_product_discount_product (

  discount_id BIGINT NOT NULL,

  product_id  BIGINT NOT NULL,

  CONSTRAINT FOREIGN KEY (discount_id) REFERENCES t_product_discount (id),

  CONSTRAINT FOREIGN KEY (product_id) REFERENCES t_product (id)
);

CREATE TABLE t_vip_card (

  id             BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT,

  hq_id          BIGINT        NOT NULL,

  number         VARCHAR(20)   NOT NULL,

  name           VARCHAR(20)   NOT NULL,

  gender         VARCHAR(10)   NOT NULL,

  birth_date     DATE          NULL,

  id_card_number VARCHAR(20)   NULL,

  mobile         VARCHAR(20)   NOT NULL,

  password       VARCHAR(20)   NULL,

  branch_id      BIGINT        NULL,

  balance        DECIMAL(8, 1) NOT NULL,

  invoice_quota  DECIMAL(8, 1) NOT NULL,

  status         VARCHAR(20)   NOT NULL,

  point          INT           NOT NULL,

  grade          INT           NOT NULL,

  created_at     DATETIME      NOT NULL,

  INDEX i_vip_card_status (status),

  UNIQUE INDEX i_vip_card_hq_id_number (hq_id, number),

  CONSTRAINT FOREIGN KEY (hq_id) REFERENCES t_headquarter (id),

  CONSTRAINT FOREIGN KEY (branch_id) REFERENCES t_branch (id)
);

CREATE TABLE t_vip_card_transaction (

  id               BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT,

  vip_card_id      BIGINT        NOT NULL,

  amount           DECIMAL(8, 1) NULL,

  bonus            DECIMAL(6, 2) NULL,

  balance_before   DECIMAL(8, 1) NULL,

  balance_after    DECIMAL(8, 1) NULL,

  created_at       DATETIME      NOT NULL,

  pay_type         VARCHAR(10)   NULL,

  handler_id       BIGINT        NULL,

  branch_id        BIGINT        NULL,

  transaction_type VARCHAR(20)   NOT NULL,

  invoice_amount   DECIMAL(8, 1) NULL,

  point            INT           NULL,

  number           VARCHAR(50)   NULL,

  INDEX i_vip_card_transaction_number (number),

  CONSTRAINT FOREIGN KEY (vip_card_id) REFERENCES t_vip_card (id),

  CONSTRAINT FOREIGN KEY (branch_id) REFERENCES t_branch (id),

  CONSTRAINT FOREIGN KEY (handler_id) REFERENCES t_account (id)
);

CREATE TABLE t_order (

  id               BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT,

  hq_id            BIGINT        NOT NULL,

  branch_id        BIGINT        NOT NULL,

  handler_id       BIGINT        NULL,

  created_at       DATETIME      NOT NULL,

  bill             VARCHAR(100)  NOT NULL UNIQUE,

  price            DECIMAL(8, 1) NOT NULL,

  original_price   DECIMAL(8, 1) NULL,

  discount         DECIMAL(3, 2) NULL,

  pay_type         VARCHAR(10)   NOT NULL,

  platform         VARCHAR(10)   NOT NULL,

  service_type     VARCHAR(20)   NOT NULL,

  status           VARCHAR(10)   NOT NULL,

  number_of_people INT           NULL,

  signer_id        BIGINT        NULL,

  remark           VARCHAR(100)  NULL,

  vip_card_id      BIGINT        NULL,

  device           INT           NULL,

  received         DECIMAL(8, 1) NULL,

  `change`         DECIMAL(3, 1) NULL,

  original_bill    VARCHAR(50),

  table_id         BIGINT        NULL,

  INDEX i_order_platform (platform),

  INDEX i_order_created_at_status (created_at, status),

  CONSTRAINT FOREIGN KEY (hq_id) REFERENCES t_headquarter (id),

  CONSTRAINT FOREIGN KEY (branch_id) REFERENCES t_branch (id),

  CONSTRAINT FOREIGN KEY (handler_id) REFERENCES t_account (id),

  CONSTRAINT FOREIGN KEY (signer_id) REFERENCES t_account (id),

  CONSTRAINT FOREIGN KEY (original_bill) REFERENCES t_order (bill)
    ON DELETE CASCADE,

  CONSTRAINT FOREIGN KEY (table_id) REFERENCES t_branch_table (id),

  CONSTRAINT FOREIGN KEY (vip_card_id) REFERENCES t_vip_card (id)
);

CREATE TABLE t_order_payment_record (

  order_id BIGINT        NOT NULL,

  amount   DECIMAL(8, 1) NOT NULL,

  pay_type VARCHAR(20)   NOT NULL,

  CONSTRAINT FOREIGN KEY (order_id) REFERENCES t_order (id)
);

CREATE TABLE t_order_item (

  id             BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT,

  order_id       BIGINT        NOT NULL,

  product_id     BIGINT        NOT NULL,

  count          INT           NOT NULL,

  price          DECIMAL(8, 1) NOT NULL,

  original_price DECIMAL(8, 1) NULL,

  service_type   VARCHAR(20)   NULL,

  original_id    BIGINT        NULL,

  CONSTRAINT FOREIGN KEY (order_id) REFERENCES t_order (id),

  CONSTRAINT FOREIGN KEY (product_id) REFERENCES t_product (id),

  CONSTRAINT FOREIGN KEY (original_id) REFERENCES t_order_item (id)
    ON DELETE CASCADE
);

CREATE TABLE t_order_item_discount (

  order_item_id BIGINT NOT NULL,

  discount_id   BIGINT NOT NULL,

  CONSTRAINT FOREIGN KEY (order_item_id) REFERENCES t_order_item (id),

  CONSTRAINT FOREIGN KEY (discount_id) REFERENCES t_product_discount (id)
);

CREATE TABLE t_order_item_option (

  order_item_id BIGINT NOT NULL,

  option_id     BIGINT NOT NULL,

  CONSTRAINT FOREIGN KEY (order_item_id) REFERENCES t_order_item (id),

  CONSTRAINT FOREIGN KEY (option_id) REFERENCES t_product_option (id)
);

CREATE TABLE t_order_invoice_record (

  order_id   BIGINT        NOT NULL,

  amount     DECIMAL(8, 1) NULL,

  created_at DATETIME      NOT NULL,

  handler_id BIGINT        NOT NULL,

  INDEX i_order_invoice_record_created_at (created_at),

  CONSTRAINT FOREIGN KEY (order_id) REFERENCES t_order (id),

  CONSTRAINT FOREIGN KEY (handler_id) REFERENCES t_account (id)
);

CREATE TABLE t_order_counter (

  id         BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,

  branch_id  BIGINT NOT NULL,

  `value`    INT    NOT NULL,

  updated_at DATE   NOT NULL,

  CONSTRAINT FOREIGN KEY (branch_id) REFERENCES t_branch (id)
);

CREATE TABLE `t_alipay_transaction` (
  `id`               INT(11) NOT NULL AUTO_INCREMENT,
  `trade_no`         VARCHAR(64)      DEFAULT NULL
  COMMENT '支付宝交易号',
  `out_trade_no`     VARCHAR(64)      DEFAULT NULL
  COMMENT '商户订单号',
  `out_biz_no`       VARCHAR(64)      DEFAULT NULL
  COMMENT '商户业务号',
  `notify_time`      VARCHAR(30)      DEFAULT NULL
  COMMENT '通知时间',
  `notify_type`      VARCHAR(64)      DEFAULT NULL
  COMMENT '通知类型',
  `notify_id`        VARCHAR(128)     DEFAULT NULL
  COMMENT '通知校验ID',
  `buyer_id`         VARCHAR(16)      DEFAULT NULL
  COMMENT '买家支付宝用户号',
  `buyer_logon_id`   VARCHAR(100)     DEFAULT NULL
  COMMENT '买家支付宝账号',
  `trade_status`     VARCHAR(32)      DEFAULT NULL
  COMMENT '交易状态',
  `total_amount`     VARCHAR(9)       DEFAULT NULL
  COMMENT '订单金额',
  `receipt_amount`   VARCHAR(9)       DEFAULT NULL
  COMMENT '实收金额',
  `invoice_amount`   VARCHAR(9)       DEFAULT NULL
  COMMENT '开票金额',
  `buyer_pay_amount` VARCHAR(9)       DEFAULT NULL
  COMMENT '付款金额',
  `point_amount`     VARCHAR(9)       DEFAULT NULL
  COMMENT '集分宝金额',
  `refund_fee`       VARCHAR(9)       DEFAULT NULL
  COMMENT '总退款金额',
  `send_back_fee`    VARCHAR(9)       DEFAULT NULL
  COMMENT '实际退款金额',
  `subject`          VARCHAR(256)     DEFAULT NULL
  COMMENT '订单标题',
  `body`             VARCHAR(400)     DEFAULT NULL
  COMMENT '商品描述',
  `gmt_create`       VARCHAR(30)      DEFAULT NULL
  COMMENT '交易创建时间',
  `gmt_payment`      VARCHAR(30)      DEFAULT NULL
  COMMENT '交易付款时间',
  `gmt_refund`       VARCHAR(30)      DEFAULT NULL
  COMMENT '交易退款时间',
  `gmt_close`        VARCHAR(30)      DEFAULT NULL
  COMMENT '交易结束时间',
  `fund_bill_list`   VARCHAR(512)     DEFAULT NULL
  COMMENT '支付金额信息',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE `t_wx_transaction` (
  `id`             INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT '微信交易流水id',
  `return_code`    VARCHAR(16) NOT NULL
  COMMENT '微信支付返回状态码',
  `return_msg`     VARCHAR(128)         DEFAULT NULL
  COMMENT '微信支付返回信息',
  `result_code`    VARCHAR(16)          DEFAULT NULL
  COMMENT '业务结果',
  `err_code`       VARCHAR(32)          DEFAULT NULL
  COMMENT '错误代码',
  `err_code_des`   VARCHAR(128)         DEFAULT NULL
  COMMENT '错误代码描述',
  `openid`         VARCHAR(128)         DEFAULT NULL
  COMMENT '用户标识',
  `is_subscribe`   VARCHAR(2)           DEFAULT NULL
  COMMENT '是否关注公众账号',
  `trade_type`     VARCHAR(16)          DEFAULT NULL
  COMMENT '交易类型',
  `bank_type`      VARCHAR(16)          DEFAULT NULL
  COMMENT '付款银行',
  `total_fee`      VARCHAR(32)          DEFAULT NULL
  COMMENT '总金额',
  `fee_type`       VARCHAR(8)           DEFAULT NULL
  COMMENT '货币种类',
  `cash_fee`       VARCHAR(32)          DEFAULT NULL
  COMMENT '现金支付金额',
  `cash_fee_type`  VARCHAR(16)          DEFAULT NULL
  COMMENT '现金支付货币类型',
  `coupon_fee`     VARCHAR(32)          DEFAULT NULL
  COMMENT '代金券或立减优惠金额',
  `transaction_id` VARCHAR(32)          DEFAULT NULL
  COMMENT '微信交易流水号',
  `out_trade_no`   VARCHAR(32)          DEFAULT NULL
  COMMENT '商户订单号',
  `time_end`       VARCHAR(14)          DEFAULT NULL
  COMMENT '交易结束时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE t_financial_report (

  id              BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT,

  hq_id           BIGINT         NOT NULL,

  group_id        BIGINT         NULL,

  branch_id       BIGINT         NULL,

  date            DATE           NOT NULL,

  total_sales     DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  wechat_sales    DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  alipay_sales    DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  cash_sales      DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  unipay_sales    DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  coupons         DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  signed_bills    DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  vip_card_sales  DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  discounts       DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  total_refund    DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  wechat_refund   DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  alipay_refund   DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  cash_refund     DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  unipay_refund   DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  total_recharge  DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  cash_recharge   DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  unipay_recharge DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  orders          INT            NOT NULL             DEFAULT 0,

  gifts           DECIMAL(8, 1)  NOT NULL             DEFAULT 0,

  invoices        DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  CONSTRAINT FOREIGN KEY (hq_id) REFERENCES t_headquarter (id),

  CONSTRAINT FOREIGN KEY (group_id) REFERENCES t_branch_group (id),

  CONSTRAINT FOREIGN KEY (branch_id) REFERENCES t_branch (id),

  INDEX i_financial_report_date (date)
);

CREATE TABLE t_branch_shift_report (

  id              BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT,

  branch_id       BIGINT         NOT NULL,

  account_id      BIGINT         NULL,

  start_time      DATETIME       NOT NULL,

  end_time        DATETIME       NOT NULL,

  total_sales     DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  wechat_sales    DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  alipay_sales    DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  cash_sales      DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  unipay_sales    DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  coupons         DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  signed_bills    DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  vip_card_sales  DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  discounts       DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  cash_recharge   DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  unipay_recharge DECIMAL(10, 1) NOT NULL             DEFAULT 0,

  device          INT            NOT NULL,

  CONSTRAINT FOREIGN KEY (branch_id) REFERENCES t_branch (id),

  CONSTRAINT FOREIGN KEY (account_id) REFERENCES t_account (id),

  UNIQUE INDEX i_branch_shift_report_branch_device_time (branch_id, device, start_time),

  INDEX i_branch_shift_report_start_time (start_time)
);

CREATE TABLE t_pos_counter (

  branch_id BIGINT NOT NULL PRIMARY KEY,

  counter   INT    NOT NULL DEFAULT 0,

  CONSTRAINT FOREIGN KEY (branch_id) REFERENCES t_branch (id)
);


/*CREATE TABLE t_order_statistic (

  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,

  hq_id INT NOT NULL FOREIGN KEY REFERENCES t_headquarter(id),

  area_id INT NULL FOREIGN KEY REFERENCES t_area(id),

  branch_id INT NULL FOREIGN KEY REFERENCES t_branch(id),

  sales DECIMAL(12,2) NOT NULL DEFAULT 0,

  count INT NOT NULL DEFAULT 0,

  date DATE NOT NULL,

  platform VARCHAR(10) NOT NULL,

  INDEX i_order_statistic_platform_date (platform, date)
);*/