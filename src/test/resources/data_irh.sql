TRUNCATE t_financial_report;
TRUNCATE t_order_invoice_record;
TRUNCATE t_order_payment_record;
TRUNCATE t_order_item_discount;
TRUNCATE t_order_item_option;
TRUNCATE t_order_item;
TRUNCATE t_order;
TRUNCATE t_vip_card_transaction;
TRUNCATE t_vip_card;
TRUNCATE t_product_discount_product;
TRUNCATE t_product_discount;
TRUNCATE t_branch_product_status_record;
TRUNCATE t_product_product_option_group;
TRUNCATE t_category_product;
TRUNCATE t_product_print_type;
TRUNCATE t_product;
TRUNCATE t_product_option;
TRUNCATE t_product_option_group;
TRUNCATE t_print_type;
TRUNCATE t_branch_table;
TRUNCATE t_category;
TRUNCATE t_account;
TRUNCATE t_branch;
TRUNCATE t_branch_group;
TRUNCATE t_role_permission;
TRUNCATE t_role;
TRUNCATE t_permission;
TRUNCATE t_headquarter;

INSERT INTO t_headquarter (id,name,code,logo,enabled) VALUES (1,'hq_name1',1,'logo',1);

INSERT INTO t_branch_group (id,name, hq_id, enabled) VALUES (1,'branch_group1',1,1);

INSERT INTO t_branch (id,name,number, hq_id, group_id, phone, province, city, district, address, geo, enabled)
VALUES (1,'岳麓山店',1,1,1,'18676853505','湖南省','长沙市','岳麓区','枫林路113号',NULL,1);

INSERT INTO t_role (id ,name, level, hq_id) VALUES (1,'role1','HQ',1);

INSERT INTO t_account (id,hq_id, credential_id, password, fullname, mobile, created_at, enabled, role_id, branch_group_id, branch_id)
VALUES (1,1,'10','123','account1','1386569552','2016-05-14 12:04:01',1,1,1,1);

INSERT INTO t_product (id,hq_id, name, inter_name, img_url, price, status) VALUES (1,1,"满堂红","剁椒鱼头","img.com",56,'ONSALE');
INSERT INTO t_product (id,hq_id, name, inter_name, img_url, price, status) VALUES (2,1,"口味虾","龙虾","img.com",126,'ONSALE');

INSERT INTO t_print_type (id,branch_id, name) VALUES (1,1,"湘菜类打印");

INSERT INTO t_branch_product_status_record (id,product_id, branch_id, created_at, status) VALUES (1,1,1,'2016-05-15 12:02:04','ONSALE');

INSERT INTO t_branch_table (id, code, branch_id, parent_id, enabled) VALUES (1,"大明湖畔桌",1,1,1);

INSERT INTO t_category (id,name, inter_name, hq_id, platform, display_order, enabled) values (1,"主菜","大菜",1,"APP",5,1);

INSERT INTO t_vip_card (hq_id, number, name, gender, birth_date, id_card_number, mobile, password, branch_id, balance, invoice_quota, status, point, grade, created_at)
VALUES (1,1,'李华','male','1992-02-17','1','13973437883','123',1,500,100,'ACTIVE',1,1,'2016-05-15 12:02:04');

INSERT INTO t_order (id, hq_id, branch_id, handler_id, created_at, bill, price, pay_type, platform, service_type, status, number_of_people, signer_id, remark, original_price, vip_card_id, discount, device, original_bill, received, `change`, table_id)
VALUES (1, 1, 1, 1, '2016-05-01 01:00', '1', 280, 'CASH', 'POS', 'RESTAURANT', 'COMPLETE', 1, NULL, 'remark', 300, NULL, 0.80, '1', NULL, 300, 20, 1);

INSERT INTO t_order_invoice_record (order_id, amount, created_at, handler_id) VALUES (1,50,'2016-05-15 12:02:04',1);

INSERT INTO t_order_payment_record (order_id, amount, pay_type) VALUES (1,50,'CASH');

INSERT INTO t_permission (id, name, code, category) VALUES (1,'管理层','manager','操作后台');

INSERT INTO t_product_option_group (id, hq_id, name, enabled) VALUES (1,1,"进口饮品",1);

INSERT INTO t_product_discount (id, hq_id, name, discount_type, discount_value, start_date, end_date, start_time, end_time)
VALUES (1,1,"换季促销","AMOUNT",100,'2016-05-20','2016-06-01',50,50);

INSERT INTO t_product_option (id, name, inter_name, price, group_id, enabled) VALUES (1,"产品组","产品内部组",50,1,1);




