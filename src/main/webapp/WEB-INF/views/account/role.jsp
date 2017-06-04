<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="RoleController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="account"/>
    <jsp:param name="view" value="account-role"/>
</jsp:include>
<div class="model-list-box content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">职位管理</h3>
    </div>
    <div class="sel-box">
        <div class="search-box">
            <span class="search-label">名称</span>
            <input type="text" class="search-input" ng-model="filter.name" placeholder="请输入名称搜索"/>
        </div>
        <div class="search-action">
            <a href="javascript:;" class="sea-btn1" ng-click="search(1)">筛选</a>
            <a href="javascript:;" class="sea-btn2" ng-click="reset()">重置</a>
        </div>
    </div>
    <!--添加按钮 start-->
    <div class="addBtn-box">
        <span class="addBtn" ng-click="edit()">+<em>添加职位</em></span>
    </div>
    <!--添加按钮 end-->
    <div class="table-list" ng-cloak>
        <table class="table-box">
            <thead>
            <tr>
                <td>序号</td>
                <td>名称</td>
                <td>等级</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list">
                <td>{{$index+1}}</td>
                <td>{{i.name}}</td>
                <td>{{i.levelName}}</td>
                <td><a href="javascript:;" class="editor" ng-click="edit(i.id)">编辑</a></td>
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
        <h3 class="nav-title">{{!model.id?'添加职位':'编辑职位'}}</h3>
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
                    <span class="tjia-label"><em class="tjia-sel red">*</em>职位</span>
                    <div class="tjia-box clearfix">
                        <select name="level" class="tjia-select"
                                ng-class="{'error-on':editForm.level.$invalid && editForm.$dirty}"
                                ng-model="model.level" required>
                            <option value="HQ">总部</option>
                            <option value="BRANCH_GROUP">店铺组</option>
                            <option value="BRANCH">店铺</option>
                        </select>
                        <span class="error-icon" ng-if="editForm.level.$invalid && editForm.$dirty"></span>
                    </div>
                </li>
                <li>
                    <span class="tjia-label">权限</span>
                    <div class="tjia-box clearfix">
                        <div class="xzhe-box clearfix">
                            <div class="xzhe-left">
                                <ul class="xzhe-ul">
                                    <li ng-repeat="c in permissionCategories"
                                        ng-class="{'current':c==activePermissionCategory}">
                                        <a href="#" ng-click="setActivePermissionCategory(c)">{{c}}</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="xzhe-right">
                                <ul class="xzhe-ul">
                                    <li ng-repeat="i in permissions[activePermissionCategory]">
                                        <input type="checkbox" class="service-checkbox"
                                               ng-model="candidatePermissions[i.id]"/>{{i.name}}
                                    </li>
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
<div class="success-tips" style="opacity:0;display: none;">
    <i class="success-icon"></i><span>{{tipMessage}}</span>
</div>
<!--错误提示-->
<div class="fail-tips" style="opacity:0;display: none;">
    <i class="fail-icon"></i><span>{{tipMessage}}</span>
</div>

<script src="/static/js/account/role.js"></script>
</body>
</html>
