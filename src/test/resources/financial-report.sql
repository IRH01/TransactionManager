DELETE FROM t_financial_report;
DELETE FROM t_order_invoice_record;
DELETE FROM t_order_payment_record;
DELETE FROM t_order_item_discount;
DELETE FROM t_order_item_option;
DELETE FROM t_order_item;
DELETE FROM t_order;
DELETE FROM t_vip_card_transaction;
DELETE FROM t_vip_card;

INSERT INTO t_vip_card (id, hq_id, number, name, gender, mobile, password, branch_id, balance, invoice_quota, status,
                        point, grade, created_at, birth_date, id_card_number)
VALUES (1, 1, '1', 'card1', 'male', '1', 'password1', 1, 200, 100, 'ACTIVE', 1, 1, NOW(), '1986-07-01', '1');
INSERT INTO t_vip_card (id, hq_id, number, name, gender, mobile, password, branch_id, balance, invoice_quota, status,
                        point, grade, created_at, birth_date, id_card_number)
VALUES (2, 1, '2', 'card2', 'male', '2', 'password2', 2, 400, 200, 'ACTIVE', 2, 2, NOW(), '1986-07-02', '2');

INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (1, 1, 100, 25, 0, 125, '2016-01-01', 'CASH', 1, 1, 'RECHARGE', 50, '1');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (2, 1, 100, 25, 125, 250, '2016-01-01', 'UNI_PAY', 1, 1, 'RECHARGE', 50, '2');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (3, 2, 100, 25, 0, 125, '2016-01-01', 'CASH', 1, 1, 'RECHARGE', 50, '3');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (4, 2, 100, 25, 125, 250, '2016-01-01', 'UNI_PAY', 1, 1, 'RECHARGE', 50, '4');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (1, 1, 1, 1, '2016-01-01 01:00', '1', 280, 'CASH', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', 300,
NULL, 0.93, 1, NULL, 300, 20, 1);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (2, 1, 1, 2, '2016-01-01 02:00', '2', 560, 'UNI_PAY', 'IPAD', 'PACKAGE', 'COMPLETE', 2, NULL, 'remark', 600,
NULL, 0.93, 2, NULL, 600, 40, 2);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (3, 1, 1, 1, '2016-01-01 03:00', '3', 280, 'WECHAT', 'APP', 'SELF_SERVICE', 'COMPLETE', 1, NULL, 'remark',
300, NULL, 0.93, 3, NULL, 300, 20, 1);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (4, 1, 1, 2, '2016-01-01 04:00', '4', 560, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', 600,
NULL, 0.93, 4, NULL, 600, 40, 2);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (5, 2, 2, 1, '2016-01-01 05:00', '5', 280, 'COMBINED', 'IPAD', 'PACKAGE', 'COMPLETE', 1, NULL, 'remark', 300,
NULL, 0.93, 5, NULL, 300, 20, 1);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (6, 2, 2, 2, '2016-01-01 06:00', '6', 560, 'SIGNED', 'APP', 'SELF_SERVICE', 'COMPLETE', 2, 1, 'remark', 600,
NULL, 0.93, 6, NULL, 600, 40, 2);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (7, 2, 2, 1, '2016-01-01 07:00', '7', 280, 'VIPCARD', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', 300,
 1, 0.93, 7, NULL, 300, 20, 1);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (8, 2, 2, 2, '2016-01-01 08:00', '8', 560, 'CASH', 'IPAD', 'PACKAGE', 'COMPLETE', 2, NULL, 'remark', 600,
NULL, 0.93, 8, NULL, 600, 40, 2);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (9, 1, 1, 1, '2016-01-01 09:00', '9', -10, 'CASH', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', NULL,
NULL, NULL, 9, '1', NULL, NULL, NULL);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (10, 1, 1, 2, '2016-01-01 10:00', '10', -20, 'UNI_PAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark',
NULL, NULL, NULL, 10, '2', NULL, NULL, NULL);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (11, 1, 1, 1, '2016-01-01 11:00', '11', -30, 'WECHAT', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark',
NULL, NULL, NULL, 11, '3', NULL, NULL, NULL);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (12, 1, 1, 2, '2016-01-01 12:00', '12', -40, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark',
NULL, NULL, NULL, 12, '4', NULL, NULL, NULL);

INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (1, 'CASH', 140);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (1, 'COUPON', 140);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (2, 'UNI_PAY', 280);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (2, 'COUPON', 280);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (3, 'WECHAT', 140);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (3, 'COUPON', 140);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (4, 'ALIPAY', 280);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (4, 'COUPON', 280);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (5, 'CASH', 140);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (5, 'UNI_PAY', 140);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (6, 'SIGNED', 280);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (6, 'COUPON', 280);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (7, 'VIPCARD', 140);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (7, 'COUPON', 140);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (8, 'CASH', 280);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (8, 'COUPON', 280);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (9, 'CASH', -10);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (10, 'UNI_PAY', -20);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (11, 'WECHAT', -30);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (12, 'ALIPAY', -40);

INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (1, 1, 1, 1, 0, 70, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (2, 1, 2, 2, 140,  NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (3, 2, 1, 1, 0, 140, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (4, 2, 2, 2, 280, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (5, 3, 1, 1, 0, 70, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (6, 3, 2, 2, 140,  NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (7, 4, 1, 1, 0, 140, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (8, 4, 2, 2, 280,  NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (9, 5, 1, 1, 0, 70, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (10, 5, 2, 2, 140, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (11, 6, 1, 1, 0, 140, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (12, 6, 2, 2, 280, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (13, 7, 1, 1, 0, 70, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (14, 7, 2, 2, 140, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (15, 8, 1, 1, 0, 140, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (16, 8, 2, 2, 280, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (17, 9, 2, -1, 70, NULL, 'PACKAGE', 2);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (18, 10, 2, -2, 140, NULL, 'PACKAGE', 4);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (19, 11, 2, -1, 70, NULL, 'PACKAGE', 6);
INSERT INTO t_order_item (id, order_id, product_id, count, price, original_price, service_type, original_id)
VALUES (20, 12, 2, -2, 140, NULL, 'PACKAGE', 8);

INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (1, 280, '2016-01-01', 1);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (2, 560, '2016-01-01', 2);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (3, 280, '2016-01-01', 1);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (4, 560, '2016-01-01', 2);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (5, 280, '2016-01-01', 1);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (6, 560, '2016-01-01', 2);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (7, 280, '2016-01-01', 1);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (8, 560, '2016-01-01', 2);

INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, date, total_sales, wechat_sales, alipay_sales,
                                cash_sales, unipay_sales, coupons, signed_bills, vip_card_sales, discounts, total_refund, wechat_refund, alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders, gifts, invoices)
VALUES (1, 1, 1, 1, '2016-02-01', 10000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 100, 100, 25, 25, 25, 25, 1000,
        500, 500, 100, 100, 100);
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, date, total_sales, wechat_sales, alipay_sales,
                                cash_sales, unipay_sales, coupons, signed_bills, vip_card_sales, discounts, total_refund, wechat_refund, alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders, gifts, invoices)
VALUES (2, 1, 1, 1, '2016-02-02', 20000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 200, 200, 50, 50, 50, 50, 2000,
        1000, 1000, 200, 200, 200);
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, date, total_sales, wechat_sales, alipay_sales,
                                cash_sales, unipay_sales, coupons, signed_bills, vip_card_sales, discounts, total_refund, wechat_refund, alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders, gifts, invoices)
VALUES (3, 1, 1, 2, '2016-02-03', 30000, 3000, 3000, 3000, 3000, 3000, 3000, 3000, 300, 300, 75, 75, 75, 75, 3000,
        1500, 1500, 300, 300, 300);
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, date, total_sales, wechat_sales, alipay_sales,
                                cash_sales, unipay_sales, coupons, signed_bills, vip_card_sales, discounts, total_refund, wechat_refund, alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders, gifts, invoices)
VALUES (4, 1, 1, 2, '2016-02-04', 40000, 4000, 4000, 4000, 4000, 4000, 4000, 4000, 400, 400, 100, 100, 100, 100, 4000,
        2000, 2000, 400, 400, 400);
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, date, total_sales, wechat_sales, alipay_sales,
                                cash_sales, unipay_sales, coupons, signed_bills, vip_card_sales, discounts, total_refund, wechat_refund, alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders, gifts, invoices)
VALUES (5, 1, 1, NULL, '2016-02-05', 50000, 5000, 5000, 5000, 5000, 5000, 5000, 5000, 500, 500, 125, 125, 125, 125,
        5000, 2500, 2500, 500, 500, 500);
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, date, total_sales, wechat_sales, alipay_sales,
                                cash_sales, unipay_sales, coupons, signed_bills, vip_card_sales, discounts, total_refund, wechat_refund, alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders, gifts, invoices)
VALUES (6, 1, NULL, NULL, '2016-02-06', 60000, 6000, 6000, 6000, 6000, 6000, 6000, 6000, 600, 600, 150, 150, 150, 150,
        6000, 3000, 3000, 600, 600, 600);