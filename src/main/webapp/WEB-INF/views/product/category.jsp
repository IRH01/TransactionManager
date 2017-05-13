<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="CategoryController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="product"/>
    <jsp:param name="view" value="product-category"/>
</jsp:include>
<div id="category-list" class="model-list-box content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">分类管理</h3>
        <ul class="navList">
            <li ng-repeat="i in platforms" ng-class="{'on':i==filter.platform}">
                <a href="#" ng-click="setActivePlatform(i)">{{i}}</a></li>
        </ul>
    </div>
    <!--添加按钮 start-->
    <div class="addBtn-box">
        <span class="addBtn" ng-click="edit()">+<em>添加分类</em></span>
    </div>
    <!--添加按钮 end-->
    <div class="table-list" ng-cloak>
        <table class="table-box">
            <thead>
            <tr>
                <td>序号</td>
                <td>名称</td>
                <td>状态</td>
                <td>操作</td>
                <td>排序</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list">
                <td>{{$index+1}}</td>
                <td>{{i.name}}</td>
                <td>{{i.enabled?'正常':'停用'}}</td>
                <td>
                    <a href="javascript:;" class="editor" ng-click="edit(i.id)">编辑</a>
                    <a href="javascript:;" class="editor" ng-click="editProductOrders(i.id)">单品排序</a>
                    <a href="javascript:;" class="red" ng-if="i.enabled" ng-click="changeStatus(i,false)">停用</a>
                    <a href="javascript:;" class="green" ng-if="!i.enabled" ng-click="changeStatus(i,true)">启用</a>
                </td>
                <td>
                    <i class="rank-icon1" ng-click="sortCategory(i, 'TOP', $index)"></i>
                    <i class="rank-icon3" ng-click="sortCategory(i, 'UP', $index)"></i>
                    <i class="rank-icon4" ng-click="sortCategory(i, 'DOWN', $index)"></i>
                    <i class="rank-icon2" ng-click="sortCategory(i, 'BOTTOM', $index)"></i>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="no-data" ng-if="list!=null && list.length==0">暂无数据</div>
    </div>
</div>
<div id="product-list" class="content-box hide">
    <div class="clumn-nav clearfix">
        <a class="nav-return" title="返回" href="#" ng-click="cancelProductOrders()">< 返回</a>
        <h3 class="nav-title">单品排序</h3>
    </div>
    <div class="table-list" ng-cloak>
        <table class="table-box">
            <thead>
            <tr>
                <td>序号</td>
                <td>单品</td>
                <td>排序</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in model.products">
                <td>{{i.displayOrder}}</td>
                <td>{{i.product.name}}</td>
                <td>
                    <i class="rank-icon1" ng-click="sortProduct(i, 'TOP', $index)"></i>
                    <i class="rank-icon3" ng-click="sortProduct(i, 'UP', $index)"></i>
                    <i class="rank-icon4" ng-click="sortProduct(i, 'DOWN', $index)"></i>
                    <i class="rank-icon2" ng-click="sortProduct(i, 'BOTTOM', $index)"></i>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="add-content hide">
    <div class="clumn-nav clearfix">
        <a class="nav-return" title="返回" href="#" ng-click="cancelEdit()">< 返回</a>
        <h3 class="nav-title">{{!model.id?'添加分类':'编辑分类'}}</h3>
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
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red"></em>外文名</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input"
                               name="interName" ng-model="model.interName" placeholder="请输入外文名"/>
                    </div>
                </li>
                <li>
                    <span class="tjia-label">选择单品</span>
                    <div class="tjia-box clearfix">
                        <div class="service-lie clearfix">
                            <span ng-repeat="i in products">
                                <input type="checkbox" class="service-checkbox" ng-model="selectedProducts[i.id]"/>{{i.name}}</span>
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
<div class="success-tips" style="opacity:0;display: none;">
    <i class="success-icon"></i><span>{{tipMessage}}</span>
</div>
<!--错误提示-->
<div class="fail-tips" style="opacity:0;display: none;">
    <i class="fail-icon"></i><span>{{tipMessage}}</span>
</div>


<script src="/static/js/product/category.js"></script>
</body>
</html>
