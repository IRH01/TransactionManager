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

INSERT INTO t_headquarter (id, NAME, CODE, logo, enabled) VALUES (1, '1', '1', 'logo1', TRUE);
INSERT INTO t_headquarter (id, NAME, CODE, logo, enabled) VALUES (2, '2', '2', 'logo2', TRUE);

INSERT INTO t_permission (id, NAME, CODE, category) VALUES (1, '实时数据', 'data', '查看权限');
INSERT INTO t_permission (id, NAME, CODE, category) VALUES (2, '财务报表', 'finance', '查看权限');
INSERT INTO t_permission (id, NAME, CODE, category) VALUES (3, '操作日志', 'audit', '查看权限');
INSERT INTO t_permission (id, NAME, CODE, category) VALUES (4, '营销管理', 'marketing', '操作后台');
INSERT INTO t_permission (id, NAME, CODE, category) VALUES (5, '菜单管理', 'product', '操作后台');
INSERT INTO t_permission (id, NAME, CODE, category) VALUES (6, '店铺管理', 'branch', '操作后台');
INSERT INTO t_permission (id, NAME, CODE, category) VALUES (7, '人员管理', 'account', '操作后台');
INSERT INTO t_permission (id, NAME, CODE, category) VALUES (8, '权限管理', 'permission', '操作后台');
INSERT INTO t_permission (id, NAME, CODE, category) VALUES (9, '打折', 'discount', 'POS权限');
INSERT INTO t_permission (id, NAME, CODE, category) VALUES (10, '退货', 'order-refund', 'POS权限');
INSERT INTO t_permission (id, NAME, CODE, category) VALUES (11, '撤单', 'order-cancel', 'POS权限');
INSERT INTO t_permission (id, NAME, CODE, category) VALUES (12, '签单', 'sign', '其他权限');

INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES (1, '总部管理员', 1, 'HQ');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES (2, '总部财务', 1, 'HQ');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES (3, '区域负责人', 1, 'BRANCH_GROUP');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES (4, '区域财务', 1, 'BRANCH_GROUP');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES (5, '店长', 1, 'BRANCH');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES (6, '店内财务', 1, 'BRANCH');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES (7, '店员', 1, 'BRANCH');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES (8, '员工', 1, 'BRANCH');

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
INSERT INTO t_role_permission (role_id, permission_id) VALUES (4, 4);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (6, 6);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (7, 7);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (8, 8);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 1);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 2);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 3);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 4);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 5);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 6);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 7);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 8);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 9);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 10);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 11);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (3, 12);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 1);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 2);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 3);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 4);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 5);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 6);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 7);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 8);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 9);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 10);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 11);
INSERT INTO t_role_permission (role_id, permission_id) VALUES (5, 12);

INSERT INTO t_branch_group (id, NAME, hq_id, enabled) VALUES (1, 'area1', 1, TRUE);
INSERT INTO t_branch_group (id, NAME, hq_id, enabled) VALUES (2, 'area2', 1, TRUE);
INSERT INTO t_branch_group (id, NAME, hq_id, enabled) VALUES (3, 'area3', 1, TRUE);

INSERT INTO t_branch (id, NAME,number, group_id, hq_id, phone, province, city, district, address, enabled, geo)
VALUES (1, 'branch1',1, 1, 1, '1', 'province1', 'city1', 'district1', 'address1', TRUE, POINT(20.1, 13.1));
INSERT INTO t_branch (id, NAME,number, group_id, hq_id, phone, province, city, district, address, enabled, geo)
VALUES (2, 'branch2',2, 1, 1, '2', 'province2', 'city2', 'district2', 'address2', TRUE, POINT(20.2, 13.2));
INSERT INTO t_branch (id, NAME,number, group_id, hq_id, phone, province, city, district, address, enabled, geo)
VALUES (3, 'branch3',3, 2, 1, '3', 'province3', 'city3', 'district3', 'address3', TRUE, POINT(20.3, 13.3));
INSERT INTO t_branch (id, NAME,number, group_id, hq_id, phone, province, city, district, address, enabled, geo)
VALUES (4, 'branch4',4, 2, 1, '4', 'province4', 'city4', 'district4', 'address4', TRUE, POINT(20.4, 13.4));
INSERT INTO t_branch (id, NAME,number, group_id, hq_id, phone, province, city, district, address, enabled, geo)
VALUES (5, 'branch5',5, 3, 1, '5', 'province5', 'city5', 'district5', 'address5', TRUE, POINT(20.5, 13.5));
INSERT INTO t_branch (id, NAME,number, group_id, hq_id, phone, province, city, district, address, enabled, geo)
VALUES (6, 'branch6',6, 3, 1, '6', 'province6', 'city6', 'district6', 'address6', TRUE, POINT(20.6, 13.6));

INSERT INTO t_account (id, hq_id, credential_id, mobile, fullname, PASSWORD, role_id, branch_group_id, branch_id, enabled, created_at)
VALUES (1, 1, '1', '1', 'account1', 'c4ca4238a0b923820dcc509a6f75849b', 1, 1, 1, TRUE, NOW());
INSERT INTO t_account (id, hq_id, credential_id, mobile, fullname, PASSWORD, role_id, branch_group_id, branch_id, enabled, created_at)
VALUES (2, 1, '2', '2', 'account2', 'c4ca4238a0b923820dcc509a6f75849b', 2, 1, 2, TRUE, NOW());
INSERT INTO t_account (id, hq_id, credential_id, mobile, fullname, PASSWORD, role_id, branch_group_id, branch_id, enabled, created_at)
VALUES (3, 1, '3', '3', 'account3', 'c4ca4238a0b923820dcc509a6f75849b', 3, 2, 3, TRUE, NOW());
INSERT INTO t_account (id, hq_id, credential_id, mobile, fullname, PASSWORD, role_id, branch_group_id, branch_id, enabled, created_at)
VALUES (4, 1, '4', '4', 'account4', 'c4ca4238a0b923820dcc509a6f75849b', 4, 2, 4, TRUE, NOW());
INSERT INTO t_account (id, hq_id, credential_id, mobile, fullname, PASSWORD, role_id, branch_group_id, branch_id, enabled, created_at)
VALUES (5, 1, '5', '5', 'account5', 'c4ca4238a0b923820dcc509a6f75849b', 5, 3, 5, TRUE, NOW());
INSERT INTO  `t_account` (`id`, `hq_id`, `credential_id`, `password`, `fullname`, `mobile`, `created_at`, `enabled`, `role_id`, `branch_group_id`, `branch_id`)
VALUES ('6', '1', '6', 'c4ca4238a0b923820dcc509a6f75849b', 'account6', '6', '2016-05-18 14:37:25', b'1', '5', '3', '6');
INSERT INTO `t_account` (`id`, `hq_id`, `credential_id`, `password`, `fullname`, `mobile`, `created_at`, `enabled`, `role_id`, `branch_group_id`, `branch_id`)
VALUES ('7', '1', '7', 'c4ca4238a0b923820dcc509a6f75849b', 'account7', '7', '2016-05-18 14:37:25', b'1', '3', '2', '4');

INSERT INTO t_print_type (id, NAME, branch_id)
VALUES (1, 'printType1', 1);
INSERT INTO t_print_type (id, NAME, branch_id)
VALUES (2, 'printType2', 1);
INSERT INTO t_print_type (id, NAME, branch_id)
VALUES (3, 'printType3', 2);
INSERT INTO t_print_type (id, NAME, branch_id)
VALUES (4, 'printType4', 2);
INSERT INTO t_print_type (id, NAME, branch_id) VALUES
(5, 'printType5', 1),
(6, 'printType6', 1),
(7, 'printType7', 1),
(8, 'printType8', 1),
(9, 'printType9', 1),

(11, 'printType11', 1),
(12, 'printType12', 1),
(13, 'printType13', 1),
(14, 'printType14', 1),
(15, 'printType15', 1),
(16, 'printType16', 1),
(17, 'printType17', 1),
(18, 'printType18', 1),
(19, 'printType19', 1),
(10, 'printType10', 1),

(21, 'printType21', 2),
(22, 'printType22', 2),
(23, 'printType23', 2),
(24, 'printType24', 2),
(25, 'printType25', 2),
(26, 'printType26', 2),
(27, 'printType27', 2),
(28, 'printType28', 2),
(29, 'printType29', 2),
(20, 'printType20', 2),

(31, 'printType31', 3),
(32, 'printType32', 3),
(33, 'printType33', 3),
(34, 'printType34', 3),
(35, 'printType35', 3),
(36, 'printType36', 3),
(37, 'printType37', 3),
(38, 'printType38', 3),
(39, 'printType39', 3),
(30, 'printType30', 3),

(41, 'printType41', 4),
(42, 'printType42', 4),
(43, 'printType43', 4),
(44, 'printType44', 4),
(45, 'printType45', 4),
(46, 'printType46', 4),
(47, 'printType47', 4),
(48, 'printType48', 4),
(49, 'printType49', 4),
(40, 'printType40', 4),

(51, 'printType51', 4),
(52, 'printType52', 4),
(53, 'printType53', 4),
(54, 'printType54', 4),
(55, 'printType55', 4),
(56, 'printType56', 4),
(57, 'printType57', 4),
(58, 'printType58', 4),
(59, 'printType59', 4),
(50, 'printType50', 4),

(61, 'printType61', 4),
(62, 'printType62', 4),
(63, 'printType63', 4),
(64, 'printType64', 4),
(65, 'printType65', 4),
(66, 'printType66', 4),
(67, 'printType67', 4),
(68, 'printType68', 4),
(69, 'printType69', 4),
(60, 'printType60', 4),

(71, 'printType71', 1),
(72, 'printType72', 1),
(73, 'printType73', 1),
(74, 'printType74', 1),
(75, 'printType75', 1),
(76, 'printType76', 1),
(77, 'printType77', 1),
(78, 'printType78', 1),
(79, 'printType79', 1),
(70, 'printType70', 1),

(81, 'printType81', 2),
(82, 'printType82', 2),
(83, 'printType83', 2),
(84, 'printType84', 2),
(85, 'printType85', 2),
(86, 'printType86', 2),
(87, 'printType87', 2),
(88, 'printType88', 2),
(89, 'printType89', 2),
(80, 'printType80', 2),
(91, 'printType91', 4),
(92, 'printType92', 4),
(93, 'printType93', 4),
(94, 'printType94', 4),
(95, 'printType95', 4),
(96, 'printType96', 4),
(97, 'printType97', 4),
(98, 'printType98', 4),
(99, 'printType99', 4),
(90, 'printType90', 4);

INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (1, 1, 'c1', 'ce1', 'POS', 4);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (2, 1, 'c2', 'ce2', 'POS', 3);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (3, 1, 'c3', 'ce3', 'IPAD', 2);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (4, 1, 'c4', 'ce4', 'IPAD', 1);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (5, 1, 'c5', 'ce5', 'IPAD', 1);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (6, 1, 'c6', 'ce6', 'POS', 1);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (7, 1, 'c7', 'ce7', 'POS', 1);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (8, 1, 'c8', 'ce8', 'IPAD', 1);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (9, 1, 'c9', 'ce9', 'POS', 1);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (10, 1, 'c10', 'ce10', 'IPAD', 1);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (11, 1, 'c11', 'ce11', 'IPAD', 1);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (12, 1, 'c12', 'ce12', 'POS', 1);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (13, 1, 'c13', 'ce13', 'POS', 1);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (14, 1, 'c14', 'ce14', 'IPAD', 1);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (15, 1, 'c15', 'ce15', 'POS', 1);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order)
VALUES (16, 1, 'c16', 'ce16', 'IPAD', 1);


INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (1, 1, 'product1', 'product-en1',    'http://img.51hchc.com/product/3/2016-05-07/044a03fa-a086-41a0-95f1-18eaf67e92f1.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (2, 1, 'product2', 'product-en2',    'http://img.51hchc.com/product/3/2016-05-07/0756aeff-72c1-4ed9-8583-7afd1719c9ac.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (3, 1, 'product3', 'product-en3',    'http://img.51hchc.com/product/3/2016-05-07/077c43b1-8f71-4abc-be76-c8585e52c2e1.jpg', 300, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (4, 1, 'product4', 'product-en4',    'http://img.51hchc.com/product/3/2016-05-07/0d60a826-fe2e-41d5-b07c-7e0507b44fdb.jpg', 400, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (5 , 1, 'product5 ', 'product-en5 ', 'http://img.51hchc.com/product/3/2016-05-07/0d8eab7b-248b-44ef-bd61-bbd0aa34ceb3.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (6 , 1, 'product6 ', 'product-en6 ', 'http://img.51hchc.com/product/3/2016-05-07/16dc216f-4bee-4e79-9b8a-7fc429942682.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (7 , 1, 'product7 ', 'product-en7 ', 'http://img.51hchc.com/product/3/2016-05-07/1b79e71c-d265-4447-8852-986b3befc5fb.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (8 , 1, 'product8 ', 'product-en8 ', 'http://img.51hchc.com/product/3/2016-05-07/1bcbb9f6-9e73-4f73-ba6b-94fd2950472e.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (9 , 1, 'product9 ', 'product-en9 ', 'http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (10, 1, 'product10', 'product-en10', 'http://img.51hchc.com/product/3/2016-05-07/359bd9ec-cf93-416e-a1b4-9b3597b54d26.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (11, 1, 'product11', 'product-en11', 'http://img.51hchc.com/product/3/2016-05-07/3aec20c1-88f9-4105-ae50-15e7ae15f30b.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (12, 1, 'product12', 'product-en12', 'http://img.51hchc.com/product/3/2016-05-07/3e9ad898-ff2a-4620-bf88-ac71985f7225.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (13, 1, 'product13', 'product-en13', 'http://img.51hchc.com/product/3/2016-05-07/3ec251a8-db14-4c38-b010-740bca2ddd2c.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (14, 1, 'product14', 'product-en14', 'http://img.51hchc.com/product/3/2016-05-07/42c3ca02-1a73-4bb3-9c78-446fd753bbda.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (15, 1, 'product15', 'product-en15', 'http://img.51hchc.com/product/3/2016-05-07/492ef173-4116-4f6f-a5ed-3bc629eaf64c.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (16, 1, 'product16', 'product-en16', 'http://img.51hchc.com/product/3/2016-05-07/4a21a12c-54f9-48d6-9f9e-c1d1b4fdd048.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (17, 1, 'product17', 'product-en17', 'http://img.51hchc.com/product/3/2016-05-07/4f3c9d5e-4442-4d74-9e8d-0f66035e2252.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (18, 1, 'product18', 'product-en18', 'http://img.51hchc.com/product/3/2016-05-07/519e4926-fc44-4868-b64a-61f2405f46ac.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (19, 1, 'product19', 'product-en19', 'http://img.51hchc.com/product/3/2016-05-07/54722d4f-9c62-4451-97ab-55e6e66a31d8.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (20, 1, 'product20', 'product-en20', 'http://img.51hchc.com/product/3/2016-05-07/56d6db63-933b-47ef-b998-c02a41231aa7.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (21, 1, 'product21', 'product-en21', 'http://img.51hchc.com/product/3/2016-05-07/6337d185-bc07-48b2-b09c-d82384e0f3b1.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (22, 1, 'product22', 'product-en22', 'http://img.51hchc.com/product/3/2016-05-07/66826e48-e5d8-4086-bb52-cde7e58a3bf4.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (23, 1, 'product23', 'product-en23', 'http://img.51hchc.com/product/3/2016-05-07/6a9cd5d2-0a46-4961-9b47-7f7a3028d959.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (24, 1, 'product24', 'product-en24', 'http://img.51hchc.com/product/3/2016-05-07/6c502f78-6d3d-4e6c-af68-5e43428bc085.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (25, 1, 'product25', 'product-en25', 'http://img.51hchc.com/product/3/2016-05-07/7579ef2f-bdc9-46be-a596-ec9997e72f43.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (26, 1, 'product26', 'product-en26', 'http://img.51hchc.com/product/3/2016-05-07/8016c7e1-5326-47ef-b0f2-b451398389dc.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (27, 1, 'product27', 'product-en27', 'http://img.51hchc.com/product/3/2016-05-07/834a7af7-bbe2-4a97-b0a6-8b4137d9e084.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (28, 1, 'product28', 'product-en28', 'http://img.51hchc.com/product/3/2016-05-07/837b004c-54a2-4941-a8d4-61e27a2de942.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (29, 1, 'product29', 'product-en29', 'http://img.51hchc.com/product/3/2016-05-07/8c2c99dc-5b28-401b-bc22-e009c25cb059.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (30, 1, 'product30', 'product-en30', 'http://img.51hchc.com/product/3/2016-05-07/8d0451da-470a-4a14-9451-c50c7663d02c.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (31, 1, 'product31', 'product-en31', 'http://img.51hchc.com/product/3/2016-05-07/8f98b40a-56c5-4c18-9df7-07fda01eb5fb.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (32, 1, 'product32', 'product-en32', 'http://img.51hchc.com/product/3/2016-05-07/97f32240-6b42-4845-9887-138e499c1d23.jpg', 200, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (33, 1, 'product33', 'product-en33', 'http://img.51hchc.com/product/3/2016-05-07/9e0df455-1c6c-4c28-aa93-e29385887009.jpg', 100, 'ONSALE');
INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS)  VALUES (34, 1, 'product34', 'product-en34', 'http://img.51hchc.com/product/3/2016-05-07/a09f3f31-f19a-4677-85e3-8115607a26a9.jpg', 200, 'ONSALE');


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
INSERT INTO t_product_print_type (product_id, print_type_id) VALUES
(1 , 1 ),(1 , 11 ),
(2 , 2 ),(2 , 12 ),
(3 , 3 ),(3 , 13 ),
(4 , 4 ),(4 , 14 ),
(5 , 5 ),(5 , 15 ),
(6 , 6 ),(6 , 16 ),
(7 , 7 ),(7 , 17 ),
(8 , 8 ),(8 , 18 ),
(9 , 9 ),(9 , 19 ),
(10, 10),(10, 21),
(11, 11),(11, 22),
(12, 12),(12, 23),
(13, 13),(13, 24),
(14, 14),(14, 25),
(15, 15),(15, 26),
(16, 16),(16, 27),
(17, 17),(17, 28),
(18, 18),(18, 29),
(19, 19),(19, 31),
(20, 20),(20, 32),
(21, 21),(21, 33),
(22, 22),(22, 34),
(23, 23),(23, 35),
(24, 24),(24, 36),
(25, 25),(25, 37),
(26, 26),(26, 38),
(27, 27),(27, 39),
(28, 28),(28, 41),
(29, 29),(29, 42),
(30, 30),(30, 43),
(31, 31),(31, 44),
(32, 32),(32, 45),
(33, 33),(33, 46);

INSERT INTO t_branch_table (id, CODE, branch_id, enabled) VALUES (1, 'table1', 1, TRUE);
INSERT INTO t_branch_table (id, CODE, branch_id, enabled) VALUES (2, 'table2', 1, TRUE);
INSERT INTO t_branch_table (id, CODE, branch_id, enabled) VALUES (3, 'table3', 2, TRUE);
INSERT INTO t_branch_table (id, CODE, branch_id, enabled) VALUES (4, 'table4', 2, TRUE);

INSERT INTO t_branch_product_status_record (id, product_id, branch_id, created_at, STATUS)
VALUES (1, 1, 1, NOW(), 'SOLDOUT');
INSERT INTO t_branch_product_status_record (id, product_id, branch_id, created_at, STATUS)
VALUES (2, 2, 1, NOW(), 'SOLDOUT');

INSERT INTO t_product_option_group (id, hq_id, NAME, enabled)
VALUES (1, 1, 'group1', TRUE);
INSERT INTO t_product_option_group (id, hq_id, NAME, enabled)
VALUES (2, 1, 'group2', TRUE);

INSERT INTO t_product_option (id, NAME, inter_name, price, group_id, enabled)
VALUES (1, 'option1', 'option-en1', 10, 1, TRUE);
INSERT INTO t_product_option (id, NAME, inter_name, price, group_id, enabled)
VALUES (2, 'option2', 'option-en2', 20, 1, TRUE);
INSERT INTO t_product_option (id, NAME, inter_name, price, group_id, enabled)
VALUES (3, 'option3', 'option-en3', 30, 2, TRUE);
INSERT INTO t_product_option (id, NAME, inter_name, price, group_id, enabled)
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

INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (5 , 1, 5 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (6 , 1, 6 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (7 , 1, 7 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (8 , 1, 8 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (9 , 1, 9 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (10, 1, 10);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (11, 1, 11);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (12, 1, 12);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (13, 1, 13);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (14, 1, 14);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (15, 1, 15);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (16, 1, 16);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (17, 1, 17);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (18, 1, 18);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (19, 1, 19);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (20, 1, 20);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (21, 1, 21);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (22, 1, 22);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (23, 1, 23);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (24, 1, 24);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (25, 1, 25);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (26, 1, 26);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (27, 1, 27);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (28, 1, 28);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (29, 1, 29);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (30, 1, 30);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (31, 1, 31);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (32, 1, 32);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (33, 1, 33);

INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (5 , 3, 5 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (6 , 3, 6 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (7 , 3, 7 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (8 , 3, 8 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (9 , 3, 9 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (10, 3, 10);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (11, 3, 11);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (12, 3, 12);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (13, 3, 13);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (14, 3, 14);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (15, 3, 15);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (16, 3, 16);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (17, 3, 17);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (18, 3, 18);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (19, 3, 19);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (20, 4, 20);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (21, 4, 21);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (22, 4, 22);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (23, 4, 23);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (24, 4, 24);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (25, 4, 25);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (26, 4, 26);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (27, 4, 27);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (28, 4, 28);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (29, 4, 29);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (30, 4, 30);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (31, 4, 31);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (32, 4, 32);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (33, 4, 33);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (5 , 5, 5 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (6 , 5, 6 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (7 , 5, 7 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (8 , 5, 8 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (9 , 5, 9 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (10, 5, 10);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (11, 5, 11);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (12, 5, 12);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (13, 5, 13);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (14, 5, 14);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (15, 5, 15);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (16, 5, 16);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (17, 5, 17);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (18, 5, 18);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (19, 5, 19);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (20, 5, 20);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (21, 5, 21);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (22, 5, 22);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (23, 5, 23);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (24, 6, 24);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (25, 6, 25);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (26, 6, 26);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (27, 6, 27);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (28, 6, 28);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (29, 6, 29);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (30, 6, 30);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (31, 6, 31);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (32, 6, 32);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (33, 6, 33);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (5 , 7, 5 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (6 , 7, 6 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (7 , 7, 7 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (8 , 7, 8 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (9 , 7, 9 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (10, 7, 10);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (11, 7, 11);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (12, 7, 12);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (13, 7, 13);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (14, 7, 14);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (15, 7, 15);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (16, 8, 16);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (17, 8, 17);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (18, 8, 18);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (19, 8, 19);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (20, 8, 20);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (21, 8, 21);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (22, 8, 22);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (23, 8, 23);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (24, 8, 24);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (25, 9, 25);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (26, 9, 26);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (27, 9, 27);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (28, 9, 28);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (29, 9, 29);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (30, 9, 30);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (31, 9, 31);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (32, 10, 32);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (33, 10, 33);

INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (5 , 11, 5 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (6 , 11, 6 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (7 , 11, 7 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (8 , 11, 8 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (9 , 11, 9 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (10, 11, 10);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (11, 11, 11);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (12, 11, 12);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (13, 11, 13);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (14, 11, 14);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (15, 11, 15);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (16, 12, 16);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (17, 12, 17);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (18, 12, 18);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (19, 12, 19);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (20, 12, 20);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (21, 12, 21);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (22, 12, 22);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (23, 12, 23);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (24, 12, 24);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (25, 12, 25);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (26, 12, 26);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (27, 12, 27);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (28, 12, 28);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (29, 12, 29);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (30, 12, 30);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (31, 12, 31);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (32, 12, 32);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (33, 12, 33);

INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (5 , 13, 5 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (6 , 13, 6 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (7 , 13, 7 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (8 , 13, 8 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (9 , 13, 9 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (10, 13, 10);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (11, 13, 11);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (12, 13, 12);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (13, 13, 13);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (14, 13, 14);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (15, 13, 15);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (16, 13, 16);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (17, 13, 17);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (18, 13, 18);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (19, 13, 19);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (20, 13, 20);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (21, 14, 21);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (22, 14, 22);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (23, 14, 23);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (24, 14, 24);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (25, 14, 25);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (26, 14, 26);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (27, 14, 27);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (28, 14, 28);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (29, 14, 29);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (30, 14, 30);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (31, 14, 31);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (32, 14, 32);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (33, 14, 33);

INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (5 , 15, 5 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (6 , 15, 6 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (7 , 15, 7 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (8 , 15, 8 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (9 , 15, 9 );
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (10, 15, 10);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (11, 15, 11);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (12, 15, 12);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (13, 15, 13);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (14, 15, 14);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (15, 15, 15);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (16, 15, 16);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (17, 15, 17);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (18, 16, 18);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (19, 16, 19);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (20, 16, 20);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (21, 16, 21);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (22, 16, 22);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (23, 16, 23);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (24, 16, 24);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (25, 16, 25);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (26, 16, 26);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (27, 16, 27);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (28, 16, 28);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (29, 16, 29);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (30, 16, 30);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (31, 16, 31);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (32, 16, 32);
INSERT INTO t_category_product (product_id, category_id, display_order) VALUES (33, 16, 33);


INSERT INTO t_product_discount (id, hq_id, NAME, discount_type, discount_value, start_date, end_date, start_time, end_time)
VALUES (1, 1, 'discount1', 'FREE', NULL, NULL, NULL, NULL, NULL);
INSERT INTO t_product_discount (id, hq_id, NAME, discount_type, discount_value, start_date, end_date, start_time, end_time)
VALUES (2, 1, 'discount2', 'FREE', NULL, NULL, NULL, NULL, NULL);
INSERT INTO t_product_discount (id, hq_id, NAME, discount_type, discount_value, start_date, end_date, start_time, end_time)
VALUES (3, 1, 'discount3', 'PERCENTAGE', 93, DATE('2016-01-01 10:00'), DATE('2016-12-01 22:00'), 10, 22);
INSERT INTO t_product_discount (id, hq_id, NAME, discount_type, discount_value, start_date, end_date, start_time, end_time)
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

INSERT INTO t_vip_card (id, hq_id, number, name,gender, mobile, PASSWORD, branch_id, balance, invoice_quota, STATUS, POINT, grade, created_at, birth_date,id_card_number)
VALUES (1, 1, '1', 'card1','female', '1', '1', 1, 100, 100, 'ACTIVE', 1, 1, NOW(),'1992-02-17','123456');
INSERT INTO t_vip_card (id, hq_id, number, NAME,gender, mobile, PASSWORD, branch_id, balance, invoice_quota, STATUS, POINT, grade, created_at, birth_date,id_card_number)
VALUES (2, 1, '2', 'card2','male', '2', '2', 1, 200, 200, 'ACTIVE', 2, 2, NOW(),'1992-02-17','123466');
INSERT INTO t_vip_card (id, hq_id, number, NAME,gender, mobile, PASSWORD, branch_id, balance, invoice_quota, STATUS, POINT, grade, created_at, birth_date,id_card_number)
VALUES (3, 1, '3', 'card3','female', '3', '3', 2, 300, 300, 'ACTIVE', 3, 3, NOW(),'1992-02-17','123488');
INSERT INTO t_vip_card (id, hq_id, number, NAME,gender, mobile, PASSWORD, branch_id, balance, invoice_quota, STATUS, POINT, grade, created_at, birth_date,id_card_number)
VALUES (4, 1, '4', 'card4','male', '4', '4', 2, 400, 400, 'ACTIVE', 4, 4, NOW(),'1992-02-17','123434');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (1, 1, 1, 1, '2016-01-01 01:00', '1', 280, 'CASH', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.93, '1', NULL, 300, 20, 1);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (2, 1, 1, 2, '2016-01-02 02:00', '2', 560, 'UNI_PAY', 'IPAD', 'PACKAGE', 'COMPLETE', 2, NULL, 'remark', 600, NULL, 0.93, '1', NULL, 600, 40, 2);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (3, 1, 1, 1, '2016-01-03 03:00', '3', 280, 'WECHAT', 'APP', 'SELF_SERVICE', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.93, '1', NULL, 300, 20, 1);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (4, 1, 1, 2, '2016-01-04 04:00', '4', 560, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', 600, NULL, 0.93, '1', NULL, 600, 40, 2);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (5, 1, 2, 1, '2016-01-05 05:00', '5', 280, 'COMBINED', 'IPAD', 'PACKAGE', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.93, '1', NULL, 300, 20, 1);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (6, 1, 2, 2, '2016-01-06 06:00', '6', 560, 'SIGNED', 'APP', 'SELF_SERVICE', 'COMPLETE', 2, 1, 'remark', 600, NULL, 0.93, '1', NULL, 600, 40, 2);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (7, 1, 2, 1, '2016-01-07 07:00', '7', 280, 'VIPCARD', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', 300, 1, 0.93, '1', NULL, 300, 20, 1);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (8, 1, 2, 2, '2016-01-08 08:00', '8', 560, 'CASH', 'IPAD', 'PACKAGE', 'COMPLETE', 2, NULL, 'remark', 600, NULL, 0.93, '1', NULL, 600, 40, 2);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (9, 1, 1, 1, '2016-01-01 09:00', '9', -10, 'CASH', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', NULL, NULL, NULL, '1', '1', NULL, NULL, NULL);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (10, 1, 1, 2, '2016-01-02 10:00', '10', -20, 'UNI_PAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '2', NULL, NULL, NULL);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (11, 1, 1, 1, '2016-01-03 11:00', '11', -30, 'WECHAT', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', NULL, NULL, NULL, '1', '3', NULL, NULL, NULL);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (12, 1, 1, 2, '2016-01-04 12:00', '12', -40, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL);
INSERT INTO t_order
(id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES
(13, 1, 1, 2, '2016-05-05 12:00', '13', 700, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL);
INSERT INTO t_order
(id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES
(14, 1, 1, 2, '2016-05-06 12:00', '14', 500, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL);
INSERT INTO t_order
(id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES
(15, 1, 1, 2, '2016-05-07 12:00', '15', 1600, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL);
INSERT INTO t_order
(id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES
(16, 1, 1, 2, '2016-05-08 12:00', '16', -400, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL);
INSERT INTO t_order
(id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES
(17, 1, 1, 2, '2016-05-09 12:00', '17', 1100, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL);
INSERT INTO t_order
(id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES
(18, 1, 1, 2, '2016-05-10 12:00', '18', 100, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL);
INSERT INTO t_order
(id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES
(19, 1, 1, 2, '2016-05-11 12:00', '19', 720, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL);
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES
(20, 1, 2, 3, '2016-01-01 01:00', '20', 280, 'CASH', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.93, '1', NULL, 300, 20, 1),
(21, 1, 2, 3, '2016-01-02 02:00', '21', 560, 'UNI_PAY', 'IPAD', 'PACKAGE', 'COMPLETE', 2, NULL, 'remark', 600, NULL, 0.93, '1', NULL, 600, 40, 2),
(22, 1, 2, 3, '2016-01-03 03:00', '22', 280, 'WECHAT', 'APP', 'SELF_SERVICE', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.93, '1', NULL, 300, 20, 1),
(23, 1, 2, 3, '2016-01-04 04:00', '23', 560, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', 600, NULL, 0.93, '1', NULL, 600, 40, 2),
(24, 1, 2, 3, '2016-01-05 05:00', '24', 280, 'COMBINED', 'IPAD', 'PACKAGE', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.93, '1', NULL, 300, 20, 1),
(25, 1, 2, 3, '2016-01-06 06:00', '25', 560, 'SIGNED', 'APP', 'SELF_SERVICE', 'COMPLETE', 2, 1, 'remark', 600, NULL, 0.93, '1', NULL, 600, 40, 2),
(26, 1, 2, 3, '2016-01-07 07:00', '26', 280, 'VIPCARD', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', 300, 1, 0.93, '1', NULL, 300, 20, 1),
(27, 1, 2, 3, '2016-01-08 08:00', '27', 560, 'CASH', 'IPAD', 'PACKAGE', 'COMPLETE', 2, NULL, 'remark', 600, NULL, 0.93, '1', NULL, 600, 40, 2),
(28, 1, 2, 3, '2016-01-01 09:00', '28', -10, 'CASH', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', NULL, NULL, NULL, '1', '1', NULL, NULL, NULL),
(29, 1, 2, 3, '2016-01-02 10:00', '29', -20, 'UNI_PAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '2', NULL, NULL, NULL),
(30, 1, 2, 3, '2016-01-03 11:00', '30', -30, 'WECHAT', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', NULL, NULL, NULL, '1', '3', NULL, NULL, NULL),
(31, 1, 2, 3, '2016-01-04 12:00', '31', -40, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(32, 1, 2, 3, '2016-05-05 12:00', '32', 700, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(33, 1, 2, 3, '2016-05-06 12:00', '33', 500, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(34, 1, 2, 3, '2016-05-07 12:00', '34', 1600, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(35, 1, 2, 3, '2016-05-08 12:00', '35', -400, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(36, 1, 2, 3, '2016-05-09 12:00', '36', 1100, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(37, 1, 2, 3, '2016-05-10 12:00', '37', 100, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(38, 1, 2, 3, '2016-05-11 12:00', '38', 720, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL);

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, STATUS, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES
(40, 1, 6, 6, '2016-01-01 01:00', '40', 280, 'CASH', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.93, '1', NULL, 300, 20, 1),
(41, 1, 6, 6, '2016-01-02 02:00', '41', 560, 'UNI_PAY', 'IPAD', 'PACKAGE', 'COMPLETE', 2, NULL, 'remark', 600, NULL, 0.93, '1', NULL, 600, 40, 2),
(42, 1, 6, 6, '2016-01-03 03:00', '42', 280, 'WECHAT', 'APP', 'SELF_SERVICE', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.93, '1', NULL, 300, 20, 1),
(43, 1, 6, 6, '2016-01-04 04:00', '43', 560, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', 600, NULL, 0.93, '1', NULL, 600, 40, 2),
(44, 1, 6, 6, '2016-01-05 05:00', '44', 280, 'COMBINED', 'IPAD', 'PACKAGE', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.93, '1', NULL, 300, 20, 1),
(45, 1, 6, 6, '2016-01-06 06:00', '45', 560, 'SIGNED', 'APP', 'SELF_SERVICE', 'COMPLETE', 2, 1, 'remark', 600, NULL, 0.93, '1', NULL, 600, 40, 2),
(46, 1, 6, 6, '2016-01-07 07:00', '46', 280, 'VIPCARD', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', 300, 1, 0.93, '1', NULL, 300, 20, 1),
(47, 1, 6, 6, '2016-01-08 08:00', '47', 560, 'CASH', 'IPAD', 'PACKAGE', 'COMPLETE', 2, NULL, 'remark', 600, NULL, 0.93, '1', NULL, 600, 40, 2),
(48, 1, 6, 6, '2016-01-01 09:00', '48', -10, 'CASH', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', NULL, NULL, NULL, '1', '1', NULL, NULL, NULL),
(49, 1, 6, 6, '2016-01-02 10:00', '49', -20, 'UNI_PAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '2', NULL, NULL, NULL),
(50, 1, 6, 6, '2016-01-03 11:00', '50', -30, 'WECHAT', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', NULL, NULL, NULL, '1', '3', NULL, NULL, NULL),
(51, 1, 6, 6, '2016-01-04 12:00', '51', -40, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(52, 1, 6, 6, '2016-05-05 12:00', '52', 700, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(53, 1, 6, 6, '2016-05-06 12:00', '53', 500, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(54, 1, 6, 6, '2016-05-07 12:00', '54', 1600, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(55, 1, 6, 6, '2016-05-08 12:00', '55', -400, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(56, 1, 6, 6, '2016-05-09 12:00', '56', 1100, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(57, 1, 6, 6, '2016-05-10 12:00', '57', 100, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL),
(58, 1, 6, 6, '2016-05-11 12:00', '58', 720, 'ALIPAY', 'POS', 'RESTAURANT', 'COMPLETE', 2, NULL, 'remark', NULL, NULL, NULL, '1', '4', NULL, NULL, NULL);


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
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (13, 'ALIPAY', 700);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (14, 'ALIPAY', 500);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (15, 'ALIPAY', 1600);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (16, 'ALIPAY', -400);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (17, 'ALIPAY', 1100);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (18, 'ALIPAY', 100);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES (19, 'ALIPAY', 720);
INSERT INTO t_order_payment_record (order_id, pay_type, amount)
VALUES
  (21, 'CASH', 140),
  (21, 'COUPON', 140),
  (22, 'UNI_PAY', 280),
  (22, 'COUPON', 280),
  (23, 'WECHAT', 140),
  (23, 'COUPON', 140),
  (24, 'ALIPAY', 280),
  (24, 'COUPON', 280),
  (25, 'CASH', 140),
  (25, 'UNI_PAY', 140),
  (26, 'SIGNED', 280),
  (26, 'COUPON', 280),
  (27, 'VIPCARD', 140),
  (27, 'COUPON', 140),
  (28, 'CASH', 280),
  (28, 'COUPON', 280),
  (31, 'CASH', 140),
  (31, 'COUPON', 140),
  (32, 'UNI_PAY', 280),
  (32, 'COUPON', 280),
  (33, 'WECHAT', 140),
  (33, 'COUPON', 140),
  (34, 'ALIPAY', 280),
  (34, 'COUPON', 280),
  (35, 'CASH', 140),
  (35, 'UNI_PAY', 140),
  (36, 'SIGNED', 280),
  (36, 'COUPON', 280),
  (37, 'VIPCARD', 140),
  (37, 'COUPON', 140),
  (38, 'CASH', 280),
  (38, 'COUPON', 280),
  (41, 'CASH', 140),
  (41, 'COUPON', 140),
  (42, 'UNI_PAY', 280),
  (42, 'COUPON', 280),
  (43, 'WECHAT', 140),
  (43, 'COUPON', 140),
  (44, 'ALIPAY', 280),
  (44, 'COUPON', 280),
  (45, 'CASH', 140),
  (45, 'UNI_PAY', 140),
  (46, 'SIGNED', 280),
  (46, 'COUPON', 280),
  (47, 'VIPCARD', 140),
  (47, 'COUPON', 140),
  (48, 'CASH', 280),
  (48, 'COUPON', 280),
  (51, 'CASH', 140),
  (51, 'COUPON', 140),
  (52, 'UNI_PAY', 280),
  (52, 'COUPON', 280),
  (53, 'WECHAT', 140),
  (53, 'COUPON', 140),
  (54, 'ALIPAY', 280),
  (54, 'COUPON', 280),
  (55, 'CASH', 140),
  (55, 'UNI_PAY', 140),
  (56, 'SIGNED', 280),
  (56, 'COUPON', 280),
  (57, 'VIPCARD', 140),
  (57, 'COUPON', 140),
  (58, 'CASH', 280),
  (58, 'COUPON', 280) ;

INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (1, 1, 1, 1, 0, 70, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (2, 1, 2, 2, 140,  NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (3, 2, 1, 1, 0, 140, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (4, 2, 2, 2, 280, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (5, 3, 1, 1, 0, 70, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (6, 3, 2, 2, 140,  NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (7, 4, 1, 1, 0, 140, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (8, 4, 2, 2, 280,  NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (9, 5, 1, 1, 0, 70, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (10, 5, 2, 2, 140, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (11, 6, 1, 1, 0, 140, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (12, 6, 2, 2, 280, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (13, 7, 1, 1, 0, 70, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (14, 7, 2, 2, 140, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (15, 8, 1, 1, 0, 140, 'RESTAURANT', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (16, 8, 2, 2, 280, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (17, 9, 2, -1, 70, NULL, 'PACKAGE', 2);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (18, 10, 2, -2, 140, NULL, 'PACKAGE', 4);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (19, 11, 2, -1, 70, NULL, 'PACKAGE', 6);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES (20, 12, 2, -2, 140, NULL, 'PACKAGE', 8);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(21, 13, 2, 2, 200, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(22, 13, 3, 1, 300, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(23, 14, 4, 1, 400, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(24, 14, 5, 1, 100, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(25, 15, 2, 2, 200, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(26, 15, 3, 4, 300, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(27, 16, 2, -2, 200, NULL, 'PACKAGE',  15);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(28, 17, 1, 2, 100, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(29, 17, 2, 3, 300, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(30, 18, 1, -1, 100, NULL, 'PACKAGE', 17);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(31, 18, 2,  1, 200, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(32, 19, 2, 2, 200, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(33, 19, 3, 1, 300, NULL, 'PACKAGE', NULL);
INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(41, 21, 1, 1, 0, 70, 'RESTAURANT', NULL),
(42, 21, 2, 2, 140,  NULL, 'PACKAGE', NULL),
(43, 22, 1, 1, 0, 140, 'RESTAURANT', NULL),
(44, 22, 2, 2, 280, NULL, 'PACKAGE', NULL),
(45, 23, 1, 1, 0, 70, 'RESTAURANT', NULL),
(46, 23, 2, 2, 140,  NULL, 'PACKAGE', NULL),
(47, 24, 1, 1, 0, 140, 'RESTAURANT', NULL),
(48, 24, 2, 2, 280,  NULL, 'PACKAGE', NULL),
(49, 25, 1, 1, 0, 70, 'RESTAURANT', NULL),
(40, 25, 2, 2, 140, NULL, 'PACKAGE', NULL);

INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(51, 31, 1, 1, 0, 70, 'RESTAURANT', NULL),
(52, 31, 2, 2, 140,  NULL, 'PACKAGE', NULL),
(53, 32, 1, 1, 0, 140, 'RESTAURANT', NULL),
(54, 32, 2, 2, 280, NULL, 'PACKAGE', NULL),
(55, 33, 1, 1, 0, 70, 'RESTAURANT', NULL),
(56, 33, 2, 2, 140,  NULL, 'PACKAGE', NULL),
(57, 34, 1, 1, 0, 140, 'RESTAURANT', NULL),
(58, 34, 2, 2, 280,  NULL, 'PACKAGE', NULL),
(59, 35, 1, 1, 0, 70, 'RESTAURANT', NULL),
(50, 35, 2, 2, 140, NULL, 'PACKAGE', NULL);

INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(61, 41, 1, 1, 0, 70, 'RESTAURANT', NULL),
(62, 41, 2, 2, 140,  NULL, 'PACKAGE', NULL),
(63, 42, 1, 1, 0, 140, 'RESTAURANT', NULL),
(64, 42, 2, 2, 280, NULL, 'PACKAGE', NULL),
(65, 43, 1, 1, 0, 70, 'RESTAURANT', NULL),
(66, 43, 2, 2, 140,  NULL, 'PACKAGE', NULL),
(67, 44, 1, 1, 0, 140, 'RESTAURANT', NULL),
(68, 44, 2, 2, 280,  NULL, 'PACKAGE', NULL),
(69, 45, 1, 1, 0, 70, 'RESTAURANT', NULL),
(60, 45, 2, 2, 140, NULL, 'PACKAGE', NULL);

INSERT INTO t_order_item (id, order_id, product_id, COUNT, price, original_price, service_type, original_id)
VALUES
(71, 51, 1, 1, 0, 70, 'RESTAURANT', NULL),
(72, 51, 2, 2, 140,  NULL, 'PACKAGE', NULL),
(73, 52, 1, 1, 0, 140, 'RESTAURANT', NULL),
(74, 52, 2, 2, 280, NULL, 'PACKAGE', NULL),
(75, 53, 1, 1, 0, 70, 'RESTAURANT', NULL),
(76, 53, 2, 2, 140,  NULL, 'PACKAGE', NULL),
(77, 54, 1, 1, 0, 140, 'RESTAURANT', NULL),
(78, 54, 2, 2, 280,  NULL, 'PACKAGE', NULL),
(79, 55, 1, 1, 0, 70, 'RESTAURANT', NULL),
(70, 55, 2, 2, 140, NULL, 'PACKAGE', NULL);

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
INSERT INTO t_order_item_option (order_item_id, option_id)
VALUES (32, 2);

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
VALUES (1, 1, 1, 1, 10000, 1000, 100, 25, 25, 25, 25, 1000, 500, 500, 100, 1000, 1000, 1000, 1000, 1000, 1000, 1000, '2016-01-01');
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, total_sales, discounts, total_refund, wechat_refund,
                                alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders,
                                wechat_sales, alipay_sales, cash_sales, unipay_sales, coupons, signed_bills,
                                vip_card_sales, date)
VALUES (2, 1, 1, 1, 20000, 2000, 200, 50, 50, 50, 50, 2000, 1000, 1000, 200, 2000, 2000, 2000, 2000, 2000, 2000, 2000, '2016-01-02');
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, total_sales, discounts, total_refund, wechat_refund,
                                alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders,
                                wechat_sales, alipay_sales, cash_sales, unipay_sales, coupons, signed_bills,
                                vip_card_sales, date)
VALUES (3, 1, 1, 2, 30000, 3000, 300, 75, 75, 75, 75, 3000, 1500, 1500, 300, 3000, 3000, 3000, 3000, 3000, 3000, 3000, '2016-01-03');
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, total_sales, discounts, total_refund, wechat_refund,
                                alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders,
                                wechat_sales, alipay_sales, cash_sales, unipay_sales, coupons, signed_bills,
                                vip_card_sales, date)
VALUES (4, 1, 1, 2, 40000, 4000, 400, 100, 100, 100, 100, 4000, 2000, 2000, 400, 4000, 4000, 4000, 4000, 4000, 4000, 4000, '2016-01-04');
INSERT INTO t_financial_report (id, hq_id, group_id, branch_id, total_sales, discounts, total_refund, wechat_refund,
                                alipay_refund, cash_refund, unipay_refund, total_recharge, cash_recharge, unipay_recharge, orders,
                                wechat_sales, alipay_sales, cash_sales, unipay_sales, coupons, signed_bills,
                                vip_card_sales, date)
VALUES (5, 1, NULL, NULL, 5000, 5000, 500, 125, 125, 125, 125, 5000, 2500, 2500, 500, 5000, 5000, 5000, 5000, 5000, 5000, 5000, '2016-01-05');