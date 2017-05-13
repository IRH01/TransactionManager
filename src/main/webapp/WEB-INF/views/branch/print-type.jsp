<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="PrintTypeController">
<jsp:include page="../shared/layout.jsp"/>
<div class="model-list-box content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">打印类别管理</h3>
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
                <td>名称</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list">
                <td>{{i.name}}</td>
                <td>
                    <a href="javascript:;" class="editor" ng-click="edit(i.id)">编辑</a>
                </td>
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
        <h3 class="nav-title">{{!model.id?'添加打印类别':'编辑打印类别'}}</h3>
    </div>
    <form name="editForm" novalidate>
        <div class="tjia-con">
            <ul class="tjia-ul">
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>名称</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input"
                               ng-class="{'error-on':editForm.name.$invalid && editForm.$dirty}"
                               name="name" ng-model="model.name" placeholder="请输入名称"
                               required/>
                        <span class="error-icon" ng-if="editForm.name.$invalid && editForm.$dirty"></span>
                    </div>
                </li>
                <li>
                    <span class="tjia-label">选择单品</span>
                    <div class="tjia-box clearfix">
                        <div class="xzhe-box clearfix">
                            <div class="xzhe-left">
                                <ul class="xzhe-ul">
                                    <li ng-repeat="id in categoryIds" ng-click="setActiveCategory(id)" ng-class="{'current':id==activeCategoryId}">
                                        <input type="checkbox" class="service-checkbox" ng-model="selectedCategories[id]" ng-change="onSelectedCategoryChange(id)">{{categories[id].name}}</li>
                                </ul>
                            </div>
                            <div class="xzhe-right">
                                <ul class="xzhe-ul">
                                    <li ng-repeat="i in categories[activeCategoryId].products">
                                        <input type="checkbox" class="service-checkbox" ng-model="selectedProducts[i.product.id]" ng-change="onSelectedProductChange()">{{i.product.name}}</li>
                                </ul>
                            </div>
                        </div>
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

<script src="/static/js/branch/print-type.js"></script>
</body>
</html>
