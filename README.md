# TransactionManager

## <a name="index"/>目录
* [一、项目配置启动](#cc1)
* [二、项目结构](#cc2)
* [三、数据库结构](#cc3)
* [四、项目技术点讲解](#cc4)

<a name="cc1"/>

## 一、项目配置启动

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TransactionManager这个项目通过我自己的所学和总结，根据以前的项目需求文档重新设计和编写的一个项目，该项目采用测试驱动开发方式编写代码，项目中提供高覆盖率的测试用例。
+ **项目启动准备：**   
             * [1.安装java jdk环境。](#cc1)   
             * [2.下载tomcat容器，maven管理工具。](#cc1)  
             * [3.安装mysql数据库](#cc1) 
             * [4.将test/resources/ddl.sql数据库表结构在mysql客户端进行运行。](#cc1)    
             * [5.test/resources/下选择合适的数据初始化脚本运行。默认选择data.sql,用户登录用户名/密码/品牌码 1/1/1](#cc1)    
             * [6.修改代码中相应的数据库地址：com/irh/transaction/config/AppConfig.java:35，这里修改web项目所连接的数据库配置。](#cc1)    
             * [7.将war包放入tomcat目录下运行。](#cc1)    
+ **项目测试启动要点：**     
             * [1.修改测试数据库链接com/irh/transcation/services/TestConfig.java:31。](#cc1)  
             * [2.启动相应的测试类，每次测试都会将数据库进行清理](#cc1)    
             
## 一、项目结构


