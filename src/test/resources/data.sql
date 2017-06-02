DELETE FROM t_branch_shift_report;
DELETE FROM t_financial_report;
DELETE FROM t_order_invoice_record;
DELETE FROM t_order_payment_record;
DELETE FROM t_order_item_discount;
DELETE FROM t_order_item_option;
DELETE FROM t_order_item;
DELETE FROM t_order;
DELETE FROM t_vip_card_transaction;
DELETE FROM t_vip_card;
DELETE FROM t_product_discount_product;
DELETE FROM t_product_discount;
DELETE FROM t_branch_product_status_record;
DELETE FROM t_product_product_option_group;
DELETE FROM t_category_product;
DELETE FROM t_product_print_type;
DELETE FROM t_product;
DELETE FROM t_product_option;
DELETE FROM t_product_option_group;
DELETE FROM t_print_type;
DELETE FROM t_branch_table;
DELETE FROM t_category;
DELETE FROM t_account;
DELETE FROM t_branch;
DELETE FROM t_branch_group;
DELETE FROM t_role_permission;
DELETE FROM t_role;
DELETE FROM t_permission;
DELETE FROM t_headquarter;

INSERT INTO t_headquarter (id, name, code, logo, enabled) VALUES (1, '1', '1', 'logo1', TRUE);
INSERT INTO t_headquarter (id, name, code, logo, enabled) VALUES (2, '2', '2', 'logo2', TRUE);

INSERT INTO t_permission (id, name, code, category) VALUES (1, '实时数据', 'data', '查看权限');
INSERT INTO t_permission (id, name, code, category) VALUES (2, '财务报表', 'finance', '查看权限');
INSERT INTO t_permission (id, name, code, category) VALUES (3, '操作日志', 'audit', '查看权限');
INSERT INTO t_permission (id, name, code, category) VALUES (4, '营销管理', 'marketing', '操作后台');
INSERT INTO t_permission (id, name, code, category) VALUES (5, '菜单管理', 'product', '操作后台');
INSERT INTO t_permission (id, name, code, category) VALUES (6, '店铺管理', 'branch', '操作后台');
INSERT INTO t_permission (id, name, code, category) VALUES (7, '人员管理', 'account', '操作后台');
INSERT INTO t_permission (id, name, code, category) VALUES (8, '权限管理', 'permission', '操作后台');
INSERT INTO t_permission (id, name, code, category) VALUES (9, '打折', 'discount', 'POS权限');
INSERT INTO t_permission (id, name, code, category) VALUES (10, '其他', 'other', '其他权限');
INSERT INTO t_permission (id, name, code, category) VALUES (11, '订单管理', 'order', 'POS权限');
INSERT INTO t_permission (id, name, code, category) VALUES (12, '签单', 'sign', '其他权限');

INSERT INTO t_role (id, name, hq_id, level) VALUES (1, '总部管理员', 1, 'HQ');
INSERT INTO t_role (id, name, hq_id, level) VALUES (2, '总部财务', 1, 'HQ');
INSERT INTO t_role (id, name, hq_id, level) VALUES (3, '区域负责人', 1, 'BRANCH_GROUP');
INSERT INTO t_role (id, name, hq_id, level) VALUES (4, '区域财务', 1, 'BRANCH_GROUP');
INSERT INTO t_role (id, name, hq_id, level) VALUES (5, '店长', 1, 'BRANCH');
INSERT INTO t_role (id, name, hq_id, level) VALUES (6, '店内财务', 1, 'BRANCH');
INSERT INTO t_role (id, name, hq_id, level) VALUES (7, '店员', 1, 'BRANCH');
INSERT INTO t_role (id, name, hq_id, level) VALUES (8, '员工', 1, 'BRANCH');

INSERT INTO t_role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (1, 3);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (1, 4);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (1, 5);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (1, 6);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (1, 7);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (1, 8);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (1, 9);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (1, 10);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (1, 11);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (1, 12);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (2, 2);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 3);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (4, 4);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 5);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (6, 6);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (7, 7);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (8, 8);

INSERT INTO t_branch_group (id, name, hq_id, enabled) VALUES (1, 'area1', 1, TRUE);
INSERT INTO t_branch_group (id, name, hq_id, enabled) VALUES (2, 'area2', 1, TRUE);
INSERT INTO t_branch_group (id, name, hq_id, enabled) VALUES (3, 'area3', 1, TRUE);

INSERT INTO t_branch (id, name, number,group_id, hq_id, phone, province, city, district, address, enabled, geo)
VALUES (1, 'branch1', 1, 1, 1, '1', 'province1', 'city1', 'district1', 'address1', TRUE, POINT(20.1, 13.1));
INSERT INTO t_branch (id, name, number, group_id, hq_id, phone, province, city, district, address, enabled, geo)
VALUES (2, 'branch2', 2, 1, 1, '2', 'province2', 'city2', 'district2', 'address2', TRUE, POINT(20.2, 13.2));
INSERT INTO t_branch (id, name, number, group_id, hq_id, phone, province, city, district, address, enabled, geo)
VALUES (3, 'branch3', 3, 2, 1, '3', 'province3', 'city3', 'district3', 'address3', TRUE, POINT(20.3, 13.3));
INSERT INTO t_branch (id, name, number, group_id, hq_id, phone, province, city, district, address, enabled, geo)
VALUES (4, 'branch4', 4, 2, 1, '4', 'province4', 'city4', 'district4', 'address4', TRUE, POINT(20.4, 13.4));
INSERT INTO t_branch (id, name, number, group_id, hq_id, phone, province, city, district, address, enabled, geo)
VALUES (5, 'branch5', 5, 3, 1, '5', 'province5', 'city5', 'district5', 'address5', TRUE, POINT(20.5, 13.5));
INSERT INTO t_branch (id, name, number, group_id, hq_id, phone, province, city, district, address, enabled, geo)
VALUES (6, 'branch6', 6, 3, 1, '6', 'province6', 'city6', 'district6', 'address6', TRUE, POINT(20.6, 13.6));

INSERT INTO t_account (id, hq_id, credential_id, mobile, fullname, password, role_id, branch_group_id, branch_id, enabled, created_at)
VALUES (1, 1, '1', '1', 'account1', 'c4ca4238a0b923820dcc509a6f75849b', 1, 1, 1, TRUE, NOW());
INSERT INTO t_account (id, hq_id, credential_id, mobile, fullname, password, role_id, branch_group_id, branch_id, enabled, created_at)
VALUES (2, 1, '2', '2', 'account2', 'c81e728d9d4c2f636f067f89cc14862c', 2, 1, 2, TRUE, NOW());
INSERT INTO t_account (id, hq_id, credential_id, mobile, fullname, password, role_id, branch_group_id, branch_id, enabled, created_at)
VALUES (3, 1, '3', '3', 'account3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', 3, 2, 3, TRUE, NOW());
INSERT INTO t_account (id, hq_id, credential_id, mobile, fullname, password, role_id, branch_group_id, branch_id, enabled, created_at)
VALUES (4, 1, '4', '4', 'account4', 'a87ff679a2f3e71d9181a67b7542122c', 4, 2, 4, TRUE, NOW());
INSERT INTO t_account (id, hq_id, credential_id, mobile, fullname, password, role_id, branch_group_id, branch_id, enabled, created_at)
VALUES (5, 1, '5', '5', 'account5', 'e4da3b7fbbce2345d7772b0674a318d5', 5, 3, 5, TRUE, NOW());

INSERT INTO t_print_type (id, name, branch_id)
VALUES (1, 'printType1', 1);
INSERT INTO t_print_type (id, name, branch_id)
VALUES (2, 'printType2', 1);
INSERT INTO t_print_type (id, name, branch_id)
VALUES (3, 'printType3', 2);
INSERT INTO t_print_type (id, name, branch_id)
VALUES (4, 'printType4', 2);

INSERT INTO t_category (id, hq_id, name, inter_name, platform, display_order)
VALUES (1, 1, 'c1', 'ce1', 'POS', 4);
INSERT INTO t_category (id, hq_id, name, inter_name, platform, display_order)
VALUES (2, 1, 'c2', 'ce2', 'POS', 3);
INSERT INTO t_category (id, hq_id, name, inter_name, platform, display_order)
VALUES (3, 1, 'c3', 'ce3', 'IPAD', 2);
INSERT INTO t_category (id, hq_id, name, inter_name, platform, display_order)
VALUES (4, 1, 'c4', 'ce4', 'IPAD', 1);

INSERT INTO t_product (id, hq_id, name, inter_name, img_url, price, status, description, label)
VALUES (1, 1, 'product1', 'product-en1', 'url', 100, 'ONSALE', 'description1', 'label1');
INSERT INTO t_product (id, hq_id, name, inter_name, img_url, price, status, description, label)
VALUES (2, 1, 'product2', 'product-en2', 'url', 200, 'ONSALE', 'description2', 'label2');
INSERT INTO t_product (id, hq_id, name, inter_name, img_url, price, status, description, label)
VALUES (3, 1, 'product3', 'product-en3', 'url', 300, 'ONSALE', 'description3', 'label3');
INSERT INTO t_product (id, hq_id, name, inter_name, img_url, price, status, description, label)
VALUES (4, 1, 'product4', 'product-en4', 'url', 400, 'ONSALE', 'description4', 'label4');

INSERT INTO t_product_print_type (product_id, print_type_id)
VALUES (1, 1);
INSERT INTO t_product_print_type (product_id, print_type_id)
VALUES (2, 1);
INSERT INTO t_product_print_type (product_id, print_type_id)
VALUES (1, 2);
INSERT INTO t_product_print_type (product_id, print_type_id)
VALUES (2, 2);
INSERT INTO t_product_print_type (product_id, print_type_id)
VALUES (3, 3);
INSERT INTO t_product_print_type (product_id, print_type_id)
VALUES (4, 3);
INSERT INTO t_product_print_type (product_id, print_type_id)
VALUES (3, 4);
INSERT INTO t_product_print_type (product_id, print_type_id)
VALUES (4, 4);

INSERT INTO t_branch_table (id, code, branch_id, enabled) VALUES (1, 'table1', 1, TRUE);
INSERT INTO t_branch_table (id, code, branch_id, enabled) VALUES (2, 'table2', 1, TRUE);
INSERT INTO t_branch_table (id, code, branch_id, enabled) VALUES (3, 'table3', 2, TRUE);
INSERT INTO t_branch_table (id, code, branch_id, enabled) VALUES (4, 'table4', 2, TRUE);

INSERT INTO t_branch_product_status_record (id, product_id, branch_id, created_at, status)
VALUES (1, 1, 1, NOW(), 'SOLDOUT');
INSERT INTO t_branch_product_status_record (id, product_id, branch_id, created_at, status)
VALUES (2, 2, 1, NOW(), 'SOLDOUT');

INSERT INTO t_product_option_group (id, hq_id, name, enabled)
VALUES (1, 1, 'group1', TRUE);
INSERT INTO t_product_option_group (id, hq_id, name, enabled)
VALUES (2, 1, 'group2', TRUE);

INSERT INTO t_product_option (id, name, inter_name, price, group_id, enabled)
VALUES (1, 'option1', 'option-en1', 10, 1, TRUE);
INSERT INTO t_product_option (id, name, inter_name, price, group_id, enabled)
VALUES (2, 'option2', 'option-en2', 20, 1, TRUE);
INSERT INTO t_product_option (id, name, inter_name, price, group_id, enabled)
VALUES (3, 'option3', 'option-en3', 30, 2, TRUE);
INSERT INTO t_product_option (id, name, inter_name, price, group_id, enabled)
VALUES (4, 'option4', 'option-en4', 40, 2, TRUE);

INSERT INTO t_product_product_option_group (product_id, group_id)
VALUES (1, 1);
INSERT INTO t_product_product_option_group (product_id, group_id)
VALUES (1, 2);
INSERT INTO t_product_product_option_group (product_id, group_id)
VALUES (2, 1);
INSERT INTO t_product_product_option_group (product_id, group_id)
VALUES (2, 2);
INSERT INTO t_product_product_option_group (product_id, group_id)
VALUES (3, 1);
INSERT INTO t_product_product_option_group (product_id, group_id)
VALUES (4, 2);

INSERT INTO t_category_product (product_id, category_id, display_order)
VALUES (1, 1, 2);
INSERT INTO t_category_product (product_id, category_id, display_order)
VALUES (2, 1, 1);
INSERT INTO t_category_product (product_id, category_id, display_order)
VALUES (1, 2, 2);
INSERT INTO t_category_product (product_id, category_id, display_order)
VALUES (2, 2, 1);
INSERT INTO t_category_product (product_id, category_id, display_order)
VALUES (3, 3, 2);
INSERT INTO t_category_product (product_id, category_id, display_order)
VALUES (3, 4, 1);
INSERT INTO t_category_product (product_id, category_id, display_order)
VALUES (4, 3, 2);
INSERT INTO t_category_product (product_id, category_id, display_order)
VALUES (4, 4, 1);

INSERT INTO t_product_discount (id, hq_id, name, discount_type, discount_value, start_date, end_date, start_time, end_time)
VALUES (1, 1, 'discount1', 'FREE', NULL, NULL, NULL, NULL, NULL);
INSERT INTO t_product_discount (id, hq_id, name, discount_type, discount_value, start_date, end_date, start_time, end_time)
VALUES (2, 1, 'discount2', 'FREE', NULL, NULL, NULL, NULL, NULL);
INSERT INTO t_product_discount (id, hq_id, name, discount_type, discount_value, start_date, end_date, start_time, end_time)
VALUES (3, 1, 'discount3', 'PERCENTAGE', 93, DATE('2016-01-01 10:00'), DATE('2016-12-01 22:00'), 10, 22);
INSERT INTO t_product_discount (id, hq_id, name, discount_type, discount_value, start_date, end_date, start_time, end_time)
VALUES (4, 1, 'discount4', 'AMOUNT', 10, DATE('2016-01-01 10:00'), DATE('2016-12-01 22:00'), 10, 22);

INSERT INTO t_product_discount_product (discount_id, product_id)
VALUES (1, 1);
INSERT INTO t_product_discount_product (discount_id, product_id)
VALUES (1, 2);
INSERT INTO t_product_discount_product (discount_id, product_id)
VALUES (2, 1);
INSERT INTO t_product_discount_product (discount_id, product_id)
VALUES (2, 2);
INSERT INTO t_product_discount_product (discount_id, product_id)
VALUES (3, 3);
INSERT INTO t_product_discount_product (discount_id, product_id)
VALUES (3, 4);
INSERT INTO t_product_discount_product (discount_id, product_id)
VALUES (4, 3);
INSERT INTO t_product_discount_product (discount_id, product_id)
VALUES (4, 4);

INSERT INTO t_vip_card (id, hq_id, number, name, gender, mobile, password, branch_id, balance, invoice_quota, status,
                        point, grade, created_at, birth_date, id_card_number)
VALUES (1, 1, '1', 'card1', 'male', '1', 'password1', 1, 200, 100, 'ACTIVE', 1, 1, NOW(), '1986-07-01', '1');
INSERT INTO t_vip_card (id, hq_id, number, name, gender, mobile, password, branch_id, balance, invoice_quota, status,
                        point, grade, created_at, birth_date, id_card_number)
VALUES (2, 1, '2', 'card2', 'male', '2', 'password2', 2, 400, 200, 'ACTIVE', 2, 2, NOW(), '1986-07-02', '2');
INSERT INTO t_vip_card (id, hq_id, number, name, gender, mobile, password, branch_id, balance, invoice_quota, status,
                        point, grade, created_at, birth_date, id_card_number)
VALUES (3, 1, '3', 'card3', 'female', '3', 'password3', 3, 600, 300, 'ACTIVE', 3, 3, NOW(), '1986-07-03', '3');
INSERT INTO t_vip_card (id, hq_id, number, name, gender, mobile, password, branch_id, balance, invoice_quota, status,
                        point, grade, created_at, birth_date, id_card_number)
VALUES (4, 1, '4', 'card4', 'female', '4', 'password4', 4, 800, 400, 'ACTIVE', 4, 4, NOW(), '1986-07-04', '4');

INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (1, 1, 100, 25, 0, 125, NOW(), 'CASH', 1, 1, 'RECHARGE', 50, '1');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (2, 1, 100, 25, 125, 250, NOW(), 'UNI_PAY', 1, 1, 'RECHARGE', 50, '2');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (3, 1, 25, NULL, 250, 225, NOW(), 'CASH', 1, 1, 'CONSUME', NULL, '3');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (4, 1, 50, NULL, 225, 175, NOW(), 'UNI_PAY', 1, 1, 'CONSUME', NULL, '4');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (5, 1, 25, NULL, 175, 200, NOW(), 'UNI_PAY', 2, 1, 'REFUND', NULL, '5');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (6, 1, NULL, NULL, NULL, NULL, NOW(), NULL, 2, 1, 'INVOICE', 100, '6');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (7, 2, 200, 50, 0, 200, NOW(), 'CASH', 1, 1, 'RECHARGE', 100, '7');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (8, 2, 200, 50, 250, 500, NOW(), 'UNI_PAY', 1, 1, 'RECHARGE', 100, '8');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (9, 2, 50, NULL, 500, 450, NOW(), 'CASH', 1, 1, 'CONSUME', NULL, '9');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (10, 2, 100, NULL, 450, 350, NOW(), 'UNI_PAY', 1, 1, 'CONSUME', NULL, '10');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (11, 2, 50, NULL, 350, 400, NOW(), 'UNI_PAY', 2, 1, 'REFUND', NULL, '11');
INSERT INTO t_vip_card_transaction (id, vip_card_id, amount, bonus, balance_before, balance_after, created_at,
                                    pay_type, handler_id, branch_id, transaction_type, invoice_amount, number)
VALUES (12, 2, NULL, NULL, NULL, NULL, NOW(), NULL, 2, 1, 'INVOICE', 200, '12');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (1, 1, 1, 1, '2016-01-01 01:00', '1', 280, 'CASH', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.93, 1, NULL, 300, 20, 1);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (2, 1, 1, 2, '2016-01-02 02:00', '2', 560, 'UNI_PAY', 'IPAD', 'PACKAGE', 'COMPLETE', 2, NULL, 'remark', 600, NULL, 0.93, 2, NULL, 600, 40, 2);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (3, 1, 1, 1, '2016-01-03 03:00', '3', 280, 'WECHAT', 'APP', 'SELF_SERVICE', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.93, 3, NULL, 300, 20, 1);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (4, 1, 1, 2, '2016-01-04 04:00', '4', 560, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', 600, NULL, 0.93, 4, NULL, 600, 40, 2);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (5, 1, 2, 1, '2016-01-05 05:00', '5', 280, 'COMBINED', 'IPAD', 'PACKAGE', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.93, 5, NULL, 300, 20, 1);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (6, 1, 2, 2, '2016-01-06 06:00', '6', 560, 'SIGNED', 'APP', 'SELF_SERVICE', 'COMPLETE', 2, 1, 'remark', 600, NULL, 0.93, 6, NULL, 600, 40, 2);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (7, 1, 2, 1, '2016-01-07 07:00', '7', 280, 'VIPCARD', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', 300, 1, 0.93, 7, NULL, 300, 20, 1);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (8, 1, 2, 2, '2016-01-08 08:00', '8', 560, 'CASH', 'IPAD', 'PACKAGE', 'COMPLETE', 2, NULL, 'remark', 600, NULL, 0.93, 8, NULL, 600, 40, 2);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (9, 1, 1, 1, '2016-01-01 09:00', '9', -10, 'CASH', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', NULL, NULL, NULL, 9, '1', NULL, NULL, NULL);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (10, 1, 1, 2, '2016-01-02 10:00', '10', -20, 'UNI_PAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, 10, '2', NULL, NULL, NULL);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (11, 1, 1, 1, '2016-01-03 11:00', '11', -30, 'WECHAT', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', NULL, NULL, NULL, 11, '3', NULL, NULL, NULL);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (12, 1, 1, 2, '2016-01-04 12:00', '12', -40, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, 12, '4', NULL, NULL, NULL);

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

INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (1, 1);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (2, 2);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (3, 1);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (4, 2);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (5, 1);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (6, 2);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (7, 1);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (8, 2);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (9, 1);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (10, 2);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (11, 1);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (12, 2);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (13, 1);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (14, 2);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (15, 1);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (16, 2);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (17, 2);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (18, 2);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (19, 2);
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (20, 2);

INSERT INTO t_order_item_discount (order_item_id, discount_id)
VALUES (1, 1);
INSERT INTO t_order_item_discount (order_item_id, discount_id)
VALUES (3, 1);
INSERT INTO t_order_item_discount (order_item_id, discount_id)
VALUES (5, 1);
INSERT INTO t_order_item_discount (order_item_id, discount_id)
VALUES (7, 1);
INSERT INTO t_order_item_discount (order_item_id, discount_id)
VALUES (9, 2);
INSERT INTO t_order_item_discount (order_item_id, discount_id)
VALUES (11, 2);
INSERT INTO t_order_item_discount (order_item_id, discount_id)
VALUES (13, 2);
INSERT INTO t_order_item_discount (order_item_id, discount_id)
VALUES (15, 2);

INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (1, 280, '2016-01-01', 1);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (2, 560, '2016-01-02', 2);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (3, 280, '2016-01-03', 1);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (4, 560, '2016-01-04', 2);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (5, 280, '2016-01-05', 1);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (6, 560, '2016-01-06', 2);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (7, 280, '2016-01-07', 1);
INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id)
VALUE (8, 560, '2016-01-08', 2);

INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, total_sales, discounts, total_refund, wechat_refund,
                                alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders,
                                wechat_sales, alipay_sales, cash_sales, unipay_sales, coupons, signed_bills,
                                vip_card_sales, date)
VALUES (1, 1, 1, 1, 10000, 1000, 100, 25, 25, 25, 25, 1000, 500, 500, 100, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
        '2016-01-01');
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, total_sales, discounts, total_refund, wechat_refund,
                                alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders,
                                wechat_sales, alipay_sales, cash_sales, unipay_sales, coupons, signed_bills,
                                vip_card_sales, date)
VALUES (2, 1, 1, 1, 20000, 2000, 200, 50, 50, 50, 50, 2000, 1000, 1000, 200, 2000, 2000, 2000, 2000, 2000, 2000,
        2000, '2016-01-02');
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, total_sales, discounts, total_refund, wechat_refund,
                                alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders,
                                wechat_sales, alipay_sales, cash_sales, unipay_sales, coupons, signed_bills,
                                vip_card_sales, date)
VALUES (3, 1, 1, 2, 30000, 3000, 300, 75, 75, 75, 75, 3000, 1500, 1500, 300, 3000, 3000, 3000, 3000, 3000, 3000,
        3000, '2016-01-01');
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, total_sales, discounts, total_refund, wechat_refund,
                                alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders,
                                wechat_sales, alipay_sales, cash_sales, unipay_sales, coupons, signed_bills,
                                vip_card_sales, date)
VALUES (4, 1, 1, 2, 40000, 4000, 400, 100, 100, 100, 100, 4000, 2000, 2000, 400, 4000, 4000, 4000, 4000, 4000, 4000,
        4000, '2016-01-02');
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, total_sales, discounts, total_refund, wechat_refund,
                                alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders,
                                wechat_sales, alipay_sales, cash_sales, unipay_sales, coupons, signed_bills,
                                vip_card_sales, date)
VALUES (5, 1, 1, NULL, 30000, 3000, 300, 75, 75, 75, 75, 3000, 1500, 1500, 300, 3000, 3000, 3000, 3000, 3000,
        3000, 3000, '2016-01-01');
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, total_sales, discounts, total_refund, wechat_refund,
                                alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders,
                                wechat_sales, alipay_sales, cash_sales, unipay_sales, coupons, signed_bills,
                                vip_card_sales, date)
VALUES (6, 1, 1, NULL, 70000, 7000, 700, 175, 175, 175, 175, 7000, 3500, 3500, 700, 7000, 7000, 7000, 7000, 7000,
        7000, 7000, '2016-01-02');
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, total_sales, discounts, total_refund, wechat_refund,
                                alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders,
                                wechat_sales, alipay_sales, cash_sales, unipay_sales, coupons, signed_bills,
                                vip_card_sales, date)
VALUES (7, 1, NULL, NULL, 30000, 3000, 300, 75, 75, 75, 75, 3000, 1500, 1500, 300, 3000, 3000, 3000, 3000, 3000,
        3000, 3000, '2016-01-01');
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, total_sales, discounts, total_refund, wechat_refund,
                                alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders,
                                wechat_sales, alipay_sales, cash_sales, unipay_sales, coupons, signed_bills,
                                vip_card_sales, date)
VALUES (8, 1, NULL, NULL, 70000, 7000, 700, 175, 175, 175, 175, 7000, 3500, 3500, 700, 7000, 7000, 7000, 7000, 7000,
        7000, 7000, '2016-01-02');

INSERT INTO t_branch_shift_report (id, branch_id, account_id, start_time, end_time, total_sales, wechat_sales,
                                   alipay_sales, cash_sales, unipay_sales, coupons, signed_bills, vip_card_sales,
                                   discounts, cash_recharge, unipay_recharge, device)
VALUES (1, 1, 1, '2016-01-01 10:00', '2016-01-01 22:00', 10000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 100, 500,
        500, 1);
INSERT INTO t_branch_shift_report (id, branch_id, account_id, start_time, end_time, total_sales, wechat_sales,
                                   alipay_sales, cash_sales, unipay_sales, coupons, signed_bills, vip_card_sales,
                                   discounts, cash_recharge, unipay_recharge, device)
VALUES (2, 1, 1, '2016-01-02 10:00', '2016-01-02 22:00', 20000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 200, 1000,
        1000, 2);
INSERT INTO t_branch_shift_report (id, branch_id, account_id, start_time, end_time, total_sales, wechat_sales,
                                   alipay_sales, cash_sales, unipay_sales, coupons, signed_bills, vip_card_sales,
                                   discounts, cash_recharge, unipay_recharge, device)
VALUES (3, 2, 2, '2016-01-03 10:00', '2016-01-03 22:00', 30000, 3000, 3000, 3000, 3000, 3000, 3000, 3000, 300, 1500,
        1500, 3);
INSERT INTO t_branch_shift_report (id, branch_id, account_id, start_time, end_time, total_sales, wechat_sales,
                                   alipay_sales, cash_sales, unipay_sales, coupons, signed_bills, vip_card_sales,
                                   discounts, cash_recharge, unipay_recharge, device)
VALUES (4, 2, 2, '2016-01-04 10:00', '2016-01-04 22:00', 40000, 4000, 4000, 4000, 4000, 4000, 4000, 4000, 400, 2000,
        2000, 4);
INSERT INTO t_branch_shift_report (id, branch_id, account_id, start_time, end_time, total_sales, wechat_sales,
                                   alipay_sales, cash_sales, unipay_sales, coupons, signed_bills, vip_card_sales,
                                   discounts, cash_recharge, unipay_recharge, device)
VALUES (5, 3, 3, '2016-01-05 10:00', '2016-01-05 22:00', 50000, 5000, 5000, 5000, 5000, 5000, 5000, 5000, 500, 2500,
        2500, 5);
INSERT INTO t_branch_shift_report (id, branch_id, account_id, start_time, end_time, total_sales, wechat_sales,
                                   alipay_sales, cash_sales, unipay_sales, coupons, signed_bills, vip_card_sales,
                                   discounts, cash_recharge, unipay_recharge, device)
VALUES (6, 3, 3, '2016-01-06 10:00', '2016-01-06 22:00', 60000, 6000, 6000, 6000, 6000, 6000, 6000, 6000, 600, 3000,
        3000, 6);
