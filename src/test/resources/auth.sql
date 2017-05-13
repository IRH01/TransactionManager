DROP PROCEDURE IF EXISTS productData;
DELIMITER //
CREATE PROCEDURE productData()
BEGIN
DECLARE var_hq_key INT DEFAULT 1;
DECLARE var_bg_key INT DEFAULT 1;
DECLARE var_branch_key INT DEFAULT 1;
DECLARE var_account_key INT DEFAULT 1;
DECLARE var_role_key INT DEFAULT 1;
DECLARE var_branch_table_key INT DEFAULT 1;
DECLARE var_product_key INT DEFAULT 1;
DECLARE var_tem_product_numb INT DEFAULT 0;
DECLARE var_print_type_key INT DEFAULT 1;
DECLARE var_product_option_group_key INT DEFAULT 1;
DECLARE var_product_option_key INT DEFAULT 1;
DECLARE var_product_discount_key INT DEFAULT 1;
DECLARE var_order_key INT DEFAULT 1;
DECLARE var_order_counter_key INT DEFAULT 1;

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

WHILE var_hq_key <= 5 DO
INSERT INTO t_headquarter (id , `name` , `code`,logo ,enabled) VALUES(var_hq_key ,var_hq_key ,var_hq_key , CONCAT('logo',var_hq_key),1);

INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES ((var_hq_key-1)*8+1, '总部管理员', var_hq_key, 'HQ');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES ((var_hq_key-1)*8+2, '总部财务', var_hq_key, 'HQ');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES ((var_hq_key-1)*8+3, '区域负责人', var_hq_key, 'BRANCH_GROUP');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES ((var_hq_key-1)*8+4, '区域财务', var_hq_key, 'BRANCH_GROUP');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES ((var_hq_key-1)*8+5, '店长', var_hq_key, 'BRANCH');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES ((var_hq_key-1)*8+6, '店内财务', var_hq_key, 'BRANCH');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES ((var_hq_key-1)*8+7, '店员', var_hq_key, 'BRANCH');
INSERT INTO t_role (id, NAME, hq_id, LEVEL) VALUES ((var_hq_key-1)*8+8, '员工', var_hq_key, 'BRANCH');

BEGIN
DECLARE pog_nu INT DEFAULT 1;
WHILE pog_nu <= FLOOR(RAND()*5+3) DO
INSERT INTO t_product_option_group (id ,hq_id ,`name` ,enabled) VALUES (var_product_option_group_key ,var_hq_key ,CONCAT('group',pog_nu),1);
BEGIN
DECLARE po_nu INT DEFAULT 1;
WHILE po_nu <= FLOOR(RAND()*5) DO
INSERT INTO t_product_option (id ,`name` ,inter_name ,price ,group_id ,enabled) VALUES (var_product_option_key,CONCAT('option',po_nu),CONCAT('option-en',po_nu),FLOOR(RAND()*20),var_product_option_group_key,1);
SET po_nu=po_nu+1;
SET var_product_option_key=var_product_option_key+1;
END WHILE;
END;
SET pog_nu=pog_nu+1;
SET var_product_option_group_key = var_product_option_group_key+1;
END WHILE;
END;

BEGIN
DECLARE pro_nu INT DEFAULT 1;
SET var_tem_product_numb = var_product_key;
WHILE pro_nu <= FLOOR(RAND()*3+1) DO
INSERT INTO t_product (id,hq_id,`name`,inter_name,img_url,price,`status`) VALUES
(var_product_key,var_hq_key,CONCAT('剁椒鱼头',pro_nu),CONCAT('剁椒鱼头-EN',pro_nu),'http://www3.autoimg.cn/newsdfs/g4/M06/79/BC/368x184_0_autohomecar__wKjB01cz62GAHJfmAAKCL4SZ84A287.jpg',FLOOR(RAND()*50+1),'ONSALE');
SET var_product_key=var_product_key+1;
INSERT INTO t_product (id,hq_id,`name`,inter_name,img_url,price,`status`) VALUES
(var_product_key,var_hq_key,CONCAT('重庆鸡丝凉面',pro_nu),CONCAT('重庆鸡丝凉面-EN',pro_nu),'http://www3.autoimg.cn/newsdfs/g4/M06/79/BC/368x184_0_autohomecar__wKjB01cz62GAHJfmAAKCL4SZ84A287.jpg',FLOOR(RAND()*50+1),'ONSALE');
SET var_product_key=var_product_key+1;
INSERT INTO t_product (id,hq_id,`name`,inter_name,img_url,price,`status`) VALUES
(var_product_key,var_hq_key,CONCAT('水果拼盘',pro_nu),CONCAT('水果拼盘-EN',pro_nu),'http://www3.autoimg.cn/newsdfs/g4/M06/79/BC/368x184_0_autohomecar__wKjB01cz62GAHJfmAAKCL4SZ84A287.jpg',FLOOR(RAND()*50+1),'ONSALE');
SET var_product_key=var_product_key+1;
INSERT INTO t_product (id,hq_id,`name`,inter_name,img_url,price,`status`) VALUES
(var_product_key,var_hq_key,CONCAT('红烧猪蹄',pro_nu),CONCAT('红烧猪蹄-EN',pro_nu),'http://www3.autoimg.cn/newsdfs/g4/M06/79/BC/368x184_0_autohomecar__wKjB01cz62GAHJfmAAKCL4SZ84A287.jpg',FLOOR(RAND()*50+1),'ONSALE');
SET var_product_key=var_product_key+1;
INSERT INTO t_product (id,hq_id,`name`,inter_name,img_url,price,`status`) VALUES
(var_product_key,var_hq_key,CONCAT('星巴克咖啡',pro_nu),CONCAT('星巴克咖啡-EN',pro_nu),'http://www3.autoimg.cn/newsdfs/g4/M06/79/BC/368x184_0_autohomecar__wKjB01cz62GAHJfmAAKCL4SZ84A287.jpg',FLOOR(RAND()*50+1),'ONSALE');
SET var_product_key=var_product_key+1;
INSERT INTO t_product (id,hq_id,`name`,inter_name,img_url,price,`status`) VALUES
(var_product_key,var_hq_key,CONCAT('烤羊腿',pro_nu),CONCAT('烤羊腿-EN',pro_nu),'http://www3.autoimg.cn/newsdfs/g4/M06/79/BC/368x184_0_autohomecar__wKjB01cz62GAHJfmAAKCL4SZ84A287.jpg',FLOOR(RAND()*50+1),'ONSALE');
SET var_product_key=var_product_key+1;
SET pro_nu=pro_nu+1;
END WHILE;
END;

BEGIN
DECLARE pd_nu INT DEFAULT 1;
WHILE pd_nu <= 5 DO
INSERT INTO t_product_discount (id, hq_id, NAME, discount_type, discount_value, start_date, end_date, start_time, end_time)
VALUES (var_product_discount_key, var_hq_key, 'discount1', 'FREE', NULL, NULL, NULL, NULL, NULL);
SET var_product_discount_key=var_product_discount_key+1;
INSERT INTO t_product_discount (id, hq_id, NAME, discount_type, discount_value, start_date, end_date, start_time, end_time)
VALUES (var_product_discount_key, var_hq_key, 'discount2', 'FREE', NULL, NULL, NULL, NULL, NULL);
SET var_product_discount_key=var_product_discount_key+1;
INSERT INTO t_product_discount (id, hq_id, NAME, discount_type, discount_value, start_date, end_date, start_time, end_time)
VALUES (var_product_discount_key, var_hq_key, 'discount3', 'PERCENTAGE', 93, DATE('2016-01-01 10:00'), DATE('2016-12-01 22:00'), 10, 22);
SET var_product_discount_key=var_product_discount_key+1;
INSERT INTO t_product_discount (id, hq_id, NAME, discount_type, discount_value, start_date, end_date, start_time, end_time)
VALUES (var_product_discount_key, var_hq_key, 'discount4', 'AMOUNT', 10, DATE('2016-01-01 10:00'), DATE('2016-12-01 22:00'), 10, 22);
SET var_product_discount_key=var_product_discount_key+1;
SET pd_nu=pd_nu+1;
END WHILE;
END;

BEGIN
DECLARE jj INT DEFAULT 1;
WHILE jj<=FLOOR(RAND()*10+1) DO
INSERT INTO t_role_permission (role_id,permission_Id) VALUES (FLOOR(RAND()*8+(var_hq_key-1)*8+1),FLOOR(RAND()*12+1));
SET jj=jj+1;
END WHILE;
END;

INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order) VALUES ((var_hq_key-1)*8+1, var_hq_key, CONCAT('c',1), CONCAT('ce',1), 'POS', 4);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order) VALUES ((var_hq_key-1)*8+2, var_hq_key, CONCAT('c',2), CONCAT('ce',2), 'POS', 3);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order) VALUES ((var_hq_key-1)*8+3, var_hq_key, CONCAT('c',3), CONCAT('ce',3), 'IPAD', 2);
INSERT INTO t_category (id, hq_id, NAME, inter_name, platform, display_order) VALUES ((var_hq_key-1)*8+4, var_hq_key, CONCAT('c',4), CONCAT('ce',4), 'IPAD', 1);

BEGIN
DECLARE j INT DEFAULT 1;
WHILE j<= FLOOR(RAND()*5+1) DO
INSERT INTO t_branch_group (id , `name` , `hq_Id`) VALUES(var_bg_key,CONCAT('branch_group',j),var_hq_key);

BEGIN
DECLARE k INT DEFAULT 1;
WHILE k <= FLOOR(RAND()*5+10) DO
INSERT INTO t_branch (id , `name` , hq_id , group_id , phone , province ,city , district , address ,enabled)
              VALUES (var_branch_key ,CONCAT('branch' ,var_branch_key) ,var_hq_key ,var_bg_key ,'18676823505' ,'湖南省' ,'长沙市' ,'岳麓区' ,'枫林路133号' , 1);


BEGIN
DECLARE print_n INT DEFAULT 1;
WHILE print_n < FLOOR(RAND()*3+1) DO
INSERT INTO t_print_type (id, NAME, branch_id) VALUES (var_print_type_key, CONCAT('printType',print_n), var_branch_key);
BEGIN
DECLARE kk INT DEFAULT 1;
WHILE kk <= FLOOR(RAND()) DO
INSERT INTO t_product_print_type (product_id , print_type_id) VALUES (FLOOR(RAND()*(var_product_key-var_tem_product_numb)+var_tem_product_numb) , var_print_type_key);
SET kk=kk+1;
END WHILE;
END;
SET print_n=print_n+1;
SET var_print_type_key=var_print_type_key+1;
END WHILE;
END;

BEGIN
DECLARE n INT DEFAULT 1;
WHILE n<= FLOOR(RAND()*3+3) DO
INSERT INTO t_branch_table (id ,`code`,branch_id,enabled) VALUES (var_branch_table_key , CONCAT('table',n),var_branch_key,1);

SET n=n+1;
SET var_branch_table_key=var_branch_table_key+1;
END WHILE;
END;

BEGIN
DECLARE m INT DEFAULT 1;
WHILE m <= FLOOR(RAND()*3+3) DO
INSERT INTO t_account (id ,hq_id,credential_id,`password`,fullname,mobile,created_at,enabled,role_id,branch_group_id,branch_id)
               VALUES (var_account_key,var_hq_key,var_account_key,'c4ca4238a0b923820dcc509a6f75849b',CONCAT('account',var_account_key),'18676853525',
               CONCAT(FLOOR(2010+(RAND()*7)), '-', FLOOR(RAND()*11+1), '-', FLOOR(RAND()*28), ' ', FLOOR(11+(RAND()*13)),':',FLOOR(10+(RAND()*49)),':',FLOOR(10+(RAND()*49))),
               1,FLOOR(RAND()*8+(var_hq_key-1)*8+1),var_bg_key,var_branch_key);

SET m=m+1;
SET var_account_key =var_account_key+1;
END WHILE;
END;

SET k=k+1;
SET var_branch_key = var_branch_key +1;
END WHILE;
END;

SET j=j+1;
SET var_bg_key=var_bg_key+1;
END WHILE;
END;

SET var_hq_key=var_hq_key+1;
END WHILE;

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
END;
//

###CALL productData();






