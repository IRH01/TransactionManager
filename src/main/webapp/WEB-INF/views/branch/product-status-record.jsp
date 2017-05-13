<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="BranchProductStatusRecordController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="branch"/>
    <jsp:param name="view" value="product-status-record"/>
</jsp:include>
<div class="model-list-box content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">分店停售菜单</h3>
    </div>

    <div class="sel-box">
        <jsp:include page="../shared/filter-groups-branches.jsp">
            <jsp:param name="required" value="true"/>
        </jsp:include>
    </div>

    <!--添加按钮 start-->
    <div class="addBtn-box" ng-if="filter.branchId">
        <span class="addBtn" ng-click="edit()">+<em>添加</em></span>
    </div>
    <!--添加按钮 end-->
    <div ng-if="filter.branchId" class="table-list" ng-cloak>
        <table class="table-box">
            <thead>
            <tr>
                <td>单品</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list" ng-class="{'stop-tr':i.status=='DISABLED'}">
                <td>{{i.product.name}}</td>
                <td>{{i.status=='DISABLED'?'停售':'沽清'}}</td>
                <td>
                    <a href="javascript:;" class="red" ng-click="deleteRecord(i.id, $index)">删除</a></td>
            </tr>
            </tbody>
        </table>
        <div class="no-data" ng-if="list!=null && list.length==0">暂无数据</div>
    </div>

    <!--分页 start-->
    <div class="page-clumn">
    </div>
    <!--分页 end-->
</div>
<div class="add-content hide">
    <div class="clumn-nav clearfix">
        <a class="nav-return" title="返回" href="#" ng-click="cancelEdit()">< 返回</a>
        <h3 class="nav-title">添加</h3>
    </div>
    <form name="editForm" novalidate>
        <div class="tjia-con">
            <ul class="tjia-ul">
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>单品</span>
                    <div class="tjia-box clearfix">
                        <select name="product" class="tjia-select" ng-model="model.product" required
                                ng-options="i as i.name for i in products track by i.id"
                                ng-class="{'error-on':editForm.product.$invalid && editForm.$dirty}">
                        </select>
                        <span class="error-icon" ng-if="editForm.product.$invalid && editForm.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>状态</span>
                    <div class="tjia-box clearfix">
                        <select name="status" class="tjia-select" ng-model="model.status" required>
                            <option value="SOLDOUT">沽清</option>
                            <option value="DISABLED">停售</option>
                        </select>
                    </div>
                </li>
            </ul>
        </div>
        <div class="tjia-action">
            <a href="javascript:;" class="tjia-submit"
               ng-click="!editForm.$setDirty() && editForm.$valid && submit(false)">提交</a>
            <a href="javascript:;" class="tjia-submit2"
               ng-click="!editForm.$setDirty() && editForm.$valid && submit(true)"
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

<script src="/static/js/branch/product-status-record.js"></script>
</body>
</html>
