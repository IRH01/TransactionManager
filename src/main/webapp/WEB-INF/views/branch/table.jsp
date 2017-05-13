<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="BranchTableController" ng-cloak>
<jsp:include page="../shared/layout.jsp"/>
<div class="model-list-box content-box">
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">桌位管理</h3>
    </div>
    <div class="sel-box">
        <div class="search-w">
            <jsp:include page="../shared/filter-groups-branches.jsp"/>
        </div>
    </div>

    <div class="addBtn-box" ng-if="filter.branchId">
        <span class="addBtn" ng-click="edit()">+<em>添加桌位</em></span>
    </div>

    <div class="table-list" ng-if="filter.branchId">
        <table class="table-box">
            <thead>
            <tr>
                <td>序号</td>
                <td>桌号</td>
                <td>店铺</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list" ng-class="{'stop-tr':!i.enabled}">
                <td>{{i.id}}</td>
                <td>{{i.code}}</td>
                <td>{{getBranchName(i.branchId)}}</td>
                <td>{{i.enabled ? '正常' : '停用'}}</td>
                <td>
                    <a href="javascript:;" class="red" ng-if="i.enabled" ng-click="changeStatus(i,false)">停用</a>
                    <a href="javascript:;" class="green" ng-if="!i.enabled" ng-click="changeStatus(i,true)">启用</a>
                    <a href="javascript:;" class="editor" ng-click="edit(i.id)">编辑</a>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="no-data" ng-if="list!=null && list.length==0">暂无数据</div>
    </div>
    <!--分页start-->
    <div class="page-clumn">
    </div>
    <!--分页end-->
</div>

<div class="add-content hide">
    <div class="clumn-nav clearfix">
        <a class="nav-return" title="返回" href="#" ng-click="cancelEdit()">< 返回</a>
        <h3 class="nav-title">{{!model.id?'添加桌位':model.code}}</h3>
    </div>
    <form name="editForm" novalidate>
        <div class="tjia-con">
            <ul class="tjia-ul">
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>桌号</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input"
                               ng-class="{'error-on':editForm.code.$invalid && editForm.code.$dirty}"
                               name="code" ng-model="model.code" placeholder="请输入桌号"
                               required/>
                        <span class="error-icon" ng-if="editForm.code.$invalid && editForm.code.$dirty"></span>
                    </div>
                </li>
            </ul>
        </div>
        <div class="tjia-action">
            <a href="javascript:;" class="tjia-submit"
               ng-click="!editForm.code.$setDirty() && editForm.code.$valid && submit(false)">提交</a>
            <a href="javascript:;" class="tjia-submit2"
               ng-click="!editForm.code.$setDirty() && editForm.code.$valid && submit(true)"
               ng-if="!model.id">提交,并继续添加</a>
        </div>
    </form>
</div>

<!--成功提示-->
<div class="success-tips" style="opacity:0;">
    <i class="success-icon"></i><span>{{tipMessage}}</span>
</div>
<!--错误提示-->
<div class="fail-tips" style="opacity:0;">
    <i class="fail-icon"></i><span>{{tipMessage}}</span>
</div>

<script src="/static/js/branch/table.js"></script>
</body>
</html>
