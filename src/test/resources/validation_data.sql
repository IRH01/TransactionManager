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


INSERT INTO t_headquarter ( id, NAME, CODE, logo, enabled) VALUES
(1 ,'漫咖啡' ,'man' ,'logo' ,TRUE),
(2 ,'巧克力吧' ,'qiao' ,'logo' ,TRUE);


INSERT INTO t_branch_group (id ,NAME, hq_id, enabled) VALUES
(1,"华南区",1,1),
(2,"华北区",1,1),
(3,"华南区",2,1),
(4,"华北区",2,1);

INSERT INTO t_branch (id, name, number, hq_id, group_id, phone, province, city, district, address, geo, enabled) VALUES
 (1,'1号店',1,1,1,'18676865252','湖南省','长沙市','芙蓉区','湘江南路',POINT(20.2, 13.2),TRUE ),
 (2,'2号店',2,1,1,'18676865252','河北省','唐山市','玉田县 ','北江路' ,POINT(20.2, 13.2),TRUE ),
 (3,'3号店',3,1,2,'18676865252','湖南省','长沙市','芙蓉区','湘江南路',POINT(20.2, 13.2),TRUE ),
 (4,'4号店',4,1,2,'18676865252','河北省','唐山市','玉田县 ','北江路' ,POINT(20.2, 13.2),TRUE ),
 (5,'5号店',1,2,3,'18676865252','湖南省','长沙市','芙蓉区','湘江南路',POINT(20.2, 13.2),TRUE ),
 (6,'6号店',2,2,3,'18676865252','湖南省','长沙市','芙蓉区','湘江南路',POINT(20.2, 13.2),TRUE ),
 (7,'7号店',3,2,4,'18676865252','河北省','唐山市','玉田县 ','北江路' ,POINT(20.2, 13.2),TRUE ),
 (8,'8号店',4,2,4,'18676865252','湖南省','长沙市','芙蓉区','湘江南路',POINT(20.2, 13.2),TRUE );

INSERT INTO t_branch_table (id, CODE, branch_id, parent_id, enabled) VALUES
(1  ,'1' ,'1',NULL,TRUE),
(2  ,'2' ,'2',NULL,TRUE),
(3  ,'3' ,'3',NULL,TRUE),
(4  ,'4' ,'4',NULL,TRUE),
(5  ,'5' ,'5',NULL,TRUE),
(6  ,'6' ,'6',NULL,TRUE),
(7  ,'7' ,'7',NULL,TRUE),
(8  ,'8' ,'8',NULL,TRUE),
(11 ,'11','1',NULL,TRUE),
(12 ,'12','2',NULL,TRUE),
(13 ,'13','3',NULL,TRUE),
(14 ,'14','4',NULL,TRUE),
(15 ,'15','5',NULL,TRUE),
(16 ,'16','6',NULL,TRUE),
(17 ,'17','7',NULL,TRUE),
(18 ,'18','8',NULL,TRUE);


INSERT INTO t_permission (id, NAME, CODE, category) VALUES
(1, '实时数据', 'data', '查看权限'),
(2, '财务报表', 'finance', '查看权限'),
(3, '操作日志', 'audit', '查看权限'),
(4, '营销管理', 'marketing', '操作后台'),
(5, '菜单管理', 'product', '操作后台'),
(6, '店铺管理', 'branch', '操作后台'),
(7, '人员管理', 'account', '操作后台'),
(8, '权限管理', 'permission', '操作后台'),
(9, '打折', 'discount', 'POS权限'),
(10, '退货', 'order-refund', 'POS权限'),
(11, '撤单', 'order-cancel', 'POS权限'),
(12, '签单', 'sign', '其他权限');


INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES
(1, '总部管理员', 1, 'HQ'),
(2, '总部财务', 1, 'HQ'),
(3, '区域负责人', 1, 'BRANCH_GROUP'),
(4, '区域财务', 1, 'BRANCH_GROUP'),
(5, '店长', 1, 'BRANCH'),
(6, '店内财务', 1, 'BRANCH'),
(7, '店员', 1, 'BRANCH'),
(8, '员工', 1, 'BRANCH');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES
(11, '总部管理员', 2, 'HQ'),
(12, '总部财务', 2, 'HQ'),
(13, '区域负责人', 2, 'BRANCH_GROUP'),
(14, '区域财务', 2, 'BRANCH_GROUP'),
(15, '店长', 2, 'BRANCH'),
(16, '店内财务', 2, 'BRANCH'),
(17, '店员', 2, 'BRANCH'),
(18, '员工', 2, 'BRANCH');


INSERT INTO t_role_permission (role_id, permission_id) VALUES
(1,  1 ),(  1, 2 ),(1, 3 ),(1, 4 ),(1, 5 ),(1, 6 ),(1, 7 ),(1, 8 ),(1, 9 ),(1, 10),(1, 11),(1, 12),
(2,  2 ),(  3, 3 ),(4, 4 ),(5, 5 ),(6, 6 ),(7, 7 ),(8, 8 ),
(11, 1 ),(11, 2 ),(11, 3 ),(11, 4 ),(11, 5 ),(11, 6 ),(11, 7 ),(11, 8 ),(11, 9 ),(11, 10),(11, 11),(11, 12),
(12, 2 ),(13, 3 ),(14, 4 ),(15, 5 ),(16, 6 ),(17, 7 ),(18, 8 );

INSERT INTO t_account (id, hq_id, credential_id, password, fullname, mobile, created_at, enabled, role_id, branch_group_id, branch_id) VALUES
( 1, 1,  1, 'c4ca4238a0b923820dcc509a6f75849b', 'user 1', '18676823505', NOW(), TRUE ,1 ,1 ,1),
( 2, 1,  2, 'c4ca4238a0b923820dcc509a6f75849b', 'user 2', '18676823505', NOW(), TRUE ,2 ,1 ,1),
( 3, 1,  3, 'c4ca4238a0b923820dcc509a6f75849b', 'user 3', '18676823505', NOW(), TRUE ,3 ,1 ,1),
( 4, 1,  4, 'c4ca4238a0b923820dcc509a6f75849b', 'user 4', '18676823505', NOW(), TRUE ,4 ,1 ,1),
( 5, 1,  5, 'c4ca4238a0b923820dcc509a6f75849b', 'user 5', '18676823505', NOW(), TRUE ,5 ,1 ,1),
( 6, 1,  6, 'c4ca4238a0b923820dcc509a6f75849b', 'user 6', '18676823505', NOW(), TRUE ,6 ,1 ,1),
( 7, 1,  7, 'c4ca4238a0b923820dcc509a6f75849b', 'user 7', '18676823505', NOW(), TRUE ,7 ,1 ,1),
( 8, 1,  8, 'c4ca4238a0b923820dcc509a6f75849b', 'user 8', '18676823505', NOW(), TRUE ,8 ,1 ,1),
(11, 2, 11, 'c4ca4238a0b923820dcc509a6f75849b', 'user11', '18676823505', NOW(), TRUE ,1 ,3 ,5),
(12, 2, 12, 'c4ca4238a0b923820dcc509a6f75849b', 'user12', '18676823505', NOW(), TRUE ,2 ,3 ,5),
(13, 2, 13, 'c4ca4238a0b923820dcc509a6f75849b', 'user13', '18676823505', NOW(), TRUE ,3 ,3 ,5),
(14, 2, 14, 'c4ca4238a0b923820dcc509a6f75849b', 'user14', '18676823505', NOW(), TRUE ,4 ,3 ,5),
(15, 2, 15, 'c4ca4238a0b923820dcc509a6f75849b', 'user15', '18676823505', NOW(), TRUE ,5 ,3 ,5),
(16, 2, 16, 'c4ca4238a0b923820dcc509a6f75849b', 'user16', '18676823505', NOW(), TRUE ,6 ,3 ,5),
(17, 2, 17, 'c4ca4238a0b923820dcc509a6f75849b', 'user17', '18676823505', NOW(), TRUE ,7 ,3 ,5),
(18, 2, 18, 'c4ca4238a0b923820dcc509a6f75849b', 'user18', '18676823505', NOW(), TRUE ,8 ,3 ,5);


INSERT INTO t_category (id, NAME, inter_name, hq_id, platform, display_order, enabled) VALUES
(1, '饮料', 'drink'  , 1, 'POS' , 1, TRUE),
(2, '面类', 'noodle' , 1, 'IPAD', 1, TRUE),
(3, '米类', 'rice'   , 1, 'APP' , 1, TRUE),
(4, '饮料', 'drink'  , 2, 'POS' , 1, TRUE),
(5, '面类', 'noodle' , 2, 'IPAD', 1, TRUE),
(6, '米类', 'rice'   , 2, 'APP' , 1, TRUE);


INSERT INTO t_product (id, hq_id, NAME, inter_name, img_url, price, STATUS, description, label) VALUES
(1 , 1, '龙井茶'   , 'long jin tea'        ,'http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg' ,10 ,TRUE ,'养生清凉可口',NULL ),
(2 , 1, '奶茶'     , 'milk tea'            ,'http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg' ,20 ,TRUE ,'甜美',NULL ),
(3 , 1, '面包'     , 'bread'               ,'http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg' ,30 ,TRUE ,'美味',NULL ),
(4 , 1, '包子'     , 'steamed'             ,'http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg' ,40 ,TRUE ,'文化底蕴',NULL ),
(5 , 1, '蛋炒饭'   , 'rice&ages'           ,'http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg' ,50 ,TRUE ,'金黄华丽精华',NULL ),
(6 , 1, '米粉'     , 'rice flour'          ,'http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg' ,60 ,TRUE ,'爽口',NULL ),
(11, 2, '南美咖啡' , 'south america coffee','http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg' ,10 ,TRUE ,'提神',NULL ),
(12, 2, '鸡尾酒'   , 'orray'               ,'http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg' ,20 ,TRUE ,'解压怡情',NULL ),
(13, 2, '蛋糕'     , 'cake'                ,'http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg' ,30 ,TRUE ,'美满幸福',NULL ),
(14, 2, '意大利面' , 'spaghetti'           ,'http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg' ,40 ,TRUE ,'外国货，没尝过',NULL ),
(15, 2, '扬州炒饭' , 'Yangzhou fried rice' ,'http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg' ,50 ,TRUE ,'传统功夫炒饭',NULL ),
(16, 2, '蒸蒸糕'   , 'cookie'              ,'http://img.51hchc.com/product/3/2016-05-07/2a12e66b-c3f5-411d-aefa-a055cd56090a.jpg' ,60 ,TRUE ,'蒸蒸日上',NULL );


INSERT INTO t_category_product (product_id, category_id, display_order) VALUES
(1 ,1,1 ),
(2 ,1,2 ),
(3 ,2,3 ),
(4 ,2,4 ),
(5 ,3,5 ),
(6 ,3,6 ),
(11,4,11),
(12,4,12),
(13,5,13),
(14,5,14),
(15,6,15),
(16,6,16);

INSERT INTO t_product_option_group (id, hq_id, NAME, enabled) VALUES
(1, 1, '甜度' , TRUE ),
(2, 1, '辣度' , TRUE ),
(3, 2, '甜度' , TRUE ),
(4, 2, '辣度' , TRUE );

INSERT INTO t_product_option (id , NAME, inter_name, price, group_id, enabled ) VALUES
 (1 , '超甜' , 'super sweet'  , 3 , 1, TRUE ),
 (2 , '中甜' , 'middle sweet' , 2 , 1, TRUE ),
 (3 , '微甜' , 'tiny sweet'   , 1 , 1, TRUE ),
 (4 , '超辣' , 'super sweet'  , 3 , 2, TRUE ),
 (5 , '中辣' , 'middle sweet' , 2 , 2, TRUE ),
 (6 , '微辣' , 'tiny sweet'   , 1 , 2, TRUE ),
 (11, '超甜' , 'super sweet'  , 3 , 3, TRUE ),
 (12, '中甜' , 'middle sweet' , 2 , 3, TRUE ),
 (13, '微甜' , 'tiny sweet'   , 1 , 3, TRUE ),
 (14, '超辣' , 'super sweet'  , 3 , 4, TRUE ),
 (15, '中辣' , 'middle sweet' , 2 , 4, TRUE ),
 (16, '微辣' , 'tiny sweet'   , 1 , 4, TRUE );

INSERT INTO t_product_product_option_group (product_id, group_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(4, 1),
(4, 2),
(4, 3),
(4, 4);

INSERT INTO t_product_discount (id, hq_id, NAME, discount_type, discount_value, start_date, end_date, start_time, end_time) VALUES
(1, 1, '情侣折扣', 'PERCENTAGE', 80   ,'2016-05-01' ,'2016-06-01', '10', '20'),
(2, 1, '单身折扣', 'AMOUNT'    , 5    ,'2016-05-01' ,'2016-06-01', '10', '20'),
(3, 1, '幸运折扣', 'FREE'      , NULL ,'2016-05-01' ,'2016-06-01', '10', '20'),
(4, 2, '情侣折扣', 'PERCENTAGE', 80   ,'2016-05-01' ,'2016-06-01', '10', '20'),
(5, 2, '单身折扣', 'AMOUNT'    , 5    ,'2016-05-01' ,'2016-06-01', '10', '20'),
(6, 2, '幸运折扣', 'FREE'      , NULL ,'2016-05-01' ,'2016-06-01', '10', '20');

INSERT INTO t_product_discount_product (discount_id, product_id) VALUES
(1 ,1),
(1 ,2);

INSERT INTO t_print_type (id,branch_id, NAME) VALUES
(1,1,"1号店打印");

INSERT INTO t_product_print_type (product_id, print_type_id) VALUES
(1,1);

------------------------------------------------------------------------------------------------------------------------
--------------------------------------------前面的是基础数据。后面的是动态生成的订单。----------------------------------
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------巧克力吧的单--------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(1001, 2, 5, 11, '2016-05-01 10:00:00', 1001, 76, 50, null, 'CASH', 'POS', 'RESTAURANT','COMPLETE' ,2 ,NULL ,'remark', NULL, 2, 76, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(2001 ,1001 ,1 ,1 ,18  ,10  ,'RESTAURANT' ,null),
(2002 ,1001 ,2 ,2 ,29  ,20  ,'RESTAURANT' ,null);
INSERT INTO t_order_item_option (order_item_id, option_id) VALUES
(2001,1),
(2001,5),
(2001,4),
(2002,4),
(2002,1),
(2002,2),
(2002,6);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(1001, 76, 'CASH');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(1002, 2, 5, 1, '2016-05-02 14:00:00', 1002, 136, 110, null, 'WECHAT', 'IPAD', 'PACKAGE','COMPLETE' ,2 ,NULL ,'remark', NULL, 1, 136, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(2003 ,1002 ,3 ,1 ,38  ,30  ,'PACKAGE' ,null),
(2004 ,1002 ,4 ,2 ,49  ,40  ,'PACKAGE' ,null);
INSERT INTO t_order_item_option (order_item_id, option_id) VALUES
(2003,1),
(2004,5),
(2004,4),
(2003,4),
(2004,1),
(2003,2),
(2004,6);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(1002, 136, 'WECHAT');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(1003, 2, 5, 12, '2016-05-03 20:00:00', 1003, 193, 170, null, 'UNI_PAY', 'APP', 'SELF_SERVICE','COMPLETE' ,2 ,NULL ,'remark', NULL, 3, 193, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(2005 ,1003 ,5 ,1 ,61  ,50  ,'SELF_SERVICE' ,null),
(2006 ,1003 ,6 ,2 ,66  ,60  ,'SELF_SERVICE' ,null);
INSERT INTO t_order_item_option (order_item_id, option_id) VALUES
(2005,1),
(2006,5),
(2006,4),
(2005,4),
(2005,1),
(2005,2),
(2006,6);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(1003, 193, 'UNI_PAY');


INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(1004, 2, 5, 12, '2016-05-04 10:00:00', 1004, 75, 50, null, 'CASH', 'POS', 'RESTAURANT','COMPLETE' ,2 ,NULL ,'remark', NULL, 1, 75, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(2007 ,1004 ,1 ,1 ,19  ,10  ,'RESTAURANT' ,null),
(2008 ,1004 ,2 ,2 ,28  ,20  ,'RESTAURANT' ,null);
INSERT INTO t_order_item_option (order_item_id, option_id) VALUES
(2007,1),
(2008,5),
(2008,4),
(2007,4),
(2007,1),
(2008,2),
(2008,6);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(1004, 75, 'CASH');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(1005, 2, 5, 12, '2016-05-02 14:00:00', 1005, 134, 110, null, 'WECHAT', 'IPAD', 'PACKAGE','COMPLETE' ,2 ,NULL ,'remark', NULL, 2, 134, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(2009 ,1005 ,3 ,1 ,40  ,30  ,'PACKAGE' ,null),
(2010 ,1005 ,4 ,2 ,47  ,40  ,'PACKAGE' ,null);
INSERT INTO t_order_item_option (order_item_id, option_id) VALUES
(2009,1),
(2009,5),
(2009,4),
(2010,4),
(2010,1),
(2009,2),
(2010,6);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(1005, 134, 'WECHAT');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(1006, 2, 5, 12, '2016-05-03 20:00:00', 1006, 194, 170, null, 'UNI_PAY', 'APP', 'SELF_SERVICE','COMPLETE' ,2 ,NULL ,'remark', NULL, 0, 194, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(2011 ,1006 ,5 ,1 ,60  ,50  ,'SELF_SERVICE' ,null),
(2012 ,1006 ,6 ,2 ,67  ,60  ,'SELF_SERVICE' ,null);
INSERT INTO t_order_item_option (order_item_id, option_id) VALUES
(2011,1),
(2012,5),
(2011,4),
(2012,4),
(2011,1),
(2012,2),
(2011,6);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(1006, 194, 'UNI_PAY');



------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------漫咖啡的单----------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------
------------UNI_PAY WECHAT SIGNED ALIPAY COMBINED VIPCARD CASH
------------POS IPAD APP
------------RESTAURANT PACKAGE SELF_SERVICE
INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(1, 1, 1, 1, '2016-05-01 10:00:00', 1, 73, 50, null, 'CASH', 'POS', 'RESTAURANT','COMPLETE' ,2 ,NULL ,'remark', NULL, 1, 73, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(1 ,1 ,1 ,1 ,21  ,10  ,'RESTAURANT' ,null),
(2 ,1 ,2 ,2 ,26  ,20  ,'RESTAURANT' ,null);
INSERT INTO t_order_item_option (order_item_id, option_id) VALUES
(1,1),
(1,5),
(1,4),
(2,4),
(2,1),
(2,2),
(2,6);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(1, 73, 'CASH');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(2, 1, 1, 1, '2016-05-02 14:00:00', 2, 136, 110, null, 'WECHAT', 'IPAD', 'PACKAGE','COMPLETE' ,2 ,NULL ,'remark', NULL, 2, 136, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(3 ,2 ,3 ,1 ,38  ,30  ,'PACKAGE' ,null),
(4 ,2 ,4 ,2 ,49  ,40  ,'PACKAGE' ,null);
INSERT INTO t_order_item_option (order_item_id, option_id) VALUES
(3,1),
(4,5),
(4,4),
(3,4),
(4,1),
(3,2),
(4,6);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(2, 136, 'WECHAT');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(3, 1, 1, 1, '2016-05-03 20:00:00', 3, 194, 170, null, 'UNI_PAY', 'APP', 'SELF_SERVICE','COMPLETE' ,2 ,NULL ,'remark', NULL, 3, 194, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(5 ,3 ,5 ,1 ,56  ,50  ,'SELF_SERVICE' ,null),
(6 ,3 ,6 ,2 ,69  ,60  ,'SELF_SERVICE' ,null);
INSERT INTO t_order_item_option (order_item_id, option_id) VALUES
(5,1),
(4,5),
(4,4),
(5,4),
(5,1),
(5,2),
(4,6);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(3, 194, 'UNI_PAY');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(4, 1, 2, 1, '2016-05-04 10:00:00', 4, 75, 50, null, 'CASH', 'POS', 'RESTAURANT','COMPLETE' ,2 ,NULL ,'remark', NULL, 2, 75, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(7 ,4 ,1 ,1 ,19  ,10  ,'RESTAURANT' ,null),
(8 ,4 ,2 ,2 ,28  ,20  ,'RESTAURANT' ,null);
INSERT INTO t_order_item_option (order_item_id, option_id) VALUES
(7,1),
(8,5),
(8,4),
(7,4),
(7,1),
(8,2),
(8,6);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(4, 75, 'CASH');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(5, 1, 2, 1, '2016-05-02 14:00:00', 5, 134, 110, null, 'WECHAT', 'IPAD', 'PACKAGE','COMPLETE' ,2 ,NULL ,'remark', NULL, 3, 134, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(9 ,5 ,3 ,1 ,40  ,30  ,'PACKAGE' ,null),
(10 ,5 ,4 ,2 ,47  ,40  ,'PACKAGE' ,null);
INSERT INTO t_order_item_option (order_item_id, option_id) VALUES
(9,1),
(9,5),
(9,4),
(10,4),
(10,1),
(9,2),
(10,6);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(5, 134, 'WECHAT');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(6, 1, 2, 1, '2016-05-03 20:00:00', 6, 170, 170, null, 'UNI_PAY', 'APP', 'SELF_SERVICE','COMPLETE' ,2 ,NULL ,'remark', NULL, 1, 170, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(11 ,6 ,5 ,1 ,50  ,50  ,'SELF_SERVICE' ,null),
(12 ,6 ,6 ,2 ,60  ,60  ,'SELF_SERVICE' ,null);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(6, 170, 'UNI_PAY');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(7, 1, 2, 1, '2016-05-03 20:00:00', 7, -10, -10, null, 'UNI_PAY', 'APP', 'SELF_SERVICE','COMPLETE' ,2 ,NULL ,'remark', NULL, 1, 50, 0.00, 1 ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(13 ,7 ,1 ,-1 ,10  ,10  ,'SELF_SERVICE' ,1);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(7, -10, 'UNI_PAY');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, original_price, discount, pay_type, platform, service_type, `status`, number_of_people, signer_id, remark, vip_card_id, device, received, `change`, original_bill, table_id) VALUES
(8, 1, 2, 1, '2016-05-03 20:00:00', 8, -10, -10, null, 'UNI_PAY', 'APP', 'SELF_SERVICE','COMPLETE' ,2 ,NULL ,'remark', NULL, 2, 50, 0.00, NULL ,1);
INSERT INTO t_order_item (id, order_id, product_id, `count`, price, original_price, service_type, original_id) VALUES
(14 ,8 ,2 ,-1 ,20  ,20  ,'SELF_SERVICE' ,2),
(15 ,8 ,1 ,1 ,10  ,10  ,'SELF_SERVICE' ,null);
INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES
(8, -10, 'UNI_PAY');






