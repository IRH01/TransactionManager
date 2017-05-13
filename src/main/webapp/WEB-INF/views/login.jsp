<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html ng-app="app">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>餐饮管理后台-权限管理</title>
    <meta name="keywords" content="餐饮管理后台"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="description" content="餐饮管理后台，餐饮互联网化"/>
    <script src="//cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/angular.js/1.5.0/angular.min.js"></script>
    <script src="/static/js/app.js"></script>
    <sec:csrfMetaTags/>
</head>
<style>
    body, h1, h2, h3, h4, h5, ol, ul, li, dl, dt, dd, form, label, input, button, div, p, img {
        margin: 0;
        padding: 0;
    }

    td, th {
        padding: 0;
    }

    h1, h2, h3, h4, h5 {
        font-size: 100%;
        font-weight: normal;
    }

    table {
        border-collapse: collapse;
        border-spacing: 0;
    }

    fieldset, img {
        border: 0;
    }

    ul, ol, li {
        list-style: none;
    }

    input, textarea, select, button {
        font-size: 100%;
    }

    th, strong {
        font-weight: normal;
    }

    i, em {
        font-style: normal;
    }

    body, input, textarea, select, button {
        font-family: 'Microsoft Yahei', Arial, '\5FAE\8F6F\96C5\9ED1', 'Hiragino Sans GB', '\5B8B\4F53';
    }

    body {
        font-size: 14px;
        line-height: 1.5;
        color: #17181a;
        background: #e7e8eb;
    }

    a {
        text-decoration: none;
        color: #17181a;
        outline: none;
    }

    :focus {
    }

    .clearfix:before, .clearfix:after {
        content: "";
        display: table;
        height: 0;
        font-size: 0;
        line-height: 0;
        visibility: hidden;
    }

    .clearfix:after {
        clear: both;
    }

    .clearfix {
        *zoom: 1;
    }

    .hide {
        display: none;
    }

    .login-box {
        position: fixed;
        top: 50%;
        left: 50%;
        width: 450px;
        margin-left: -225px;
        padding: 40px 0 50px;
        background: #fff;
        border-radius: 10px;
        transform: translateY(-50%);
        -webkit-transform: translateY(-50%);
    }

    .login-list {
        width: 382px;
        margin: 0 auto;
    }

    .login-list li {
        margin-top: 20px;
    }

    .login-label {
        display: inline-block;
        width: 46px;
        margin-right: 5px;
    }

    .login-input {
        width: 318px;
        height: 30px;
        text-indent: 10px;
        border: 1px solid #e7e8eb;
        outline: none;
    }

    .login-tj {
        margin-top: 40px;
        text-align: center;
    }

    .login-submit {
        border: none;
        background: #1c93ce;
        width: 120px;
        height: 32px;
        color: #fff;
        border-radius: 3px;
        cursor: pointer;
    }

    .login-submit:hover {
        background: #1f99d5;
    }

    .login-error {
        position: absolute;
        left: 90px;
        top: 200px;
        color: #d82316;
    }

    .error-on{border-color:#d82316;}

</style>
<body ng-controller="LoginController">
<form name="loginForm" novalidate ng-submit="loginForm.$valid && login()">
    <section class="login-box">
        <div class="login-list">
            <ul>
                <li><label class="login-label">用户名</label>
                    <input name="credentialId " type="text" class="login-input"
                           ng-class="{'error-on':loginForm.credentialId.$invalid && loginForm.credentialId.$dirty}"
                           ng-model="model.credentialId" required/></li>
                <li><label class="login-label">密码</label>
                    <input name="password" type="password" class="login-input" type="password"
                           ng-class="{'error-on':loginForm.password.$invalid && loginForm.password.$dirty}"
                           ng-model="model.password" required/></li>
                <li><label class="login-label">品牌码</label>
                    <input name="hqCode" type="text" class="login-input"
                           ng-class="{'error-on':loginForm.hqCode.$invalid && loginForm.hqCode.$dirty}"
                           ng-model="model.hqCode" required/></li>
            </ul>
        </div>
        <span id="login-error" class="login-error hide">用户名和密码错误</span>
        <div class="login-tj"><input type="submit" value="登录" class="login-submit"/></div>
    </section>
</form>
<script src="//cdn.bootcss.com/blueimp-md5/2.3.0/js/md5.min.js"></script>
<script src="/static/js/login.js"></script>
</body>
</html>
