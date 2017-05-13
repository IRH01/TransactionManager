<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="BranchController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="branch"/>
    <jsp:param name="view" value="branch"/>
</jsp:include>
<div class="model-list-box content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">店铺管理</h3>
    </div>

    <div class="sel-box">
        <div class="search-box">
            <jsp:include page="../shared/filter-groups-branches.jsp"/>
        </div>
        <div class="search-w search-box hide">
            <span class="search-label">店铺名称</span>
            <input type="text" class="search-input" ng-model="filter.name" placeholder="请输入店铺名称搜索">
        </div>
        <div class="search-action">
            <a href="javascript:;" class="sea-btn1" ng-click="search(1)">筛选</a>
            <a href="javascript:;" class="sea-btn2" ng-click="reset()">重置</a>
            <a href="javascript:;" class="sai-link">↓显示更多选项</a>
        </div>
    </div>
    <div class="addBtn-box" ng-if="roleLevel=='HQ'">
        <span class="addBtn" ng-click="edit()">+<em>添加店铺</em></span>
    </div>

    <div class="table-list">
        <table class="table-box">
            <thead>
            <tr>
                <td>序号</td>
                <td>店铺名</td>
                <td>所属分组</td>
                <td>地址</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list" ng-class="{'stop-tr':!i.enabled}">
                <td>{{i.id}}</td>
                <td>{{i.name}}</td>
                <td>{{getGroupName(i.groupId)}}</td>
                <td>{{i.province}}&nbsp;{{i.city}}&nbsp;{{i.district}}</td>
                <td>{{i.enabled ? '正常' : '停用'}}</td>
                <td><a href="javascript:;" class="red" ng-if="i.enabled" ng-click="changeStatus(i,false)">停用</a>
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
</div>

<div class="add-content hide">
    <div class="clumn-nav clearfix">
        <a class="nav-return" title="返回" href="#" ng-click="cancelEdit()">< 返回</a>
        <h3 class="nav-title">{{!model.id?'添加店铺':model.code}}</h3>
    </div>
    <form name="editForm" novalidate>
        <div class="tjia-con">
            <ul class="tjia-ul">
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>名称</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input"
                               ng-class="{'error-on':editForm.name.$invalid && editForm.name.$dirty}"
                               name="name" ng-model="model.name" placeholder="请输入店铺名称"
                               required/>
                        <span class="error-icon" ng-if="editForm.name.$invalid && editForm.name.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>电话</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input" placeholder="请输入电话" name="phone"
                               ng-class="{'error-on':editForm.phone.$invalid && editForm.phone.$dirty}"
                               ng-pattern="/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d)|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/"
                               ng-model="model.phone" required/>
                        <span class="error-icon" ng-if="editForm.phone.$invalid && editForm.phone.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>所属分组</span>
                    <div class="tjia-box clearfix">
                        <select name="groupId" class="tjia-select" ng-model="model.groupId"
                                ng-class="{'error-on':editForm.groupId.$invalid && editForm.groupId.$dirty}" required>
                            <option value="" ng-selected="model.groupId">分组</option>
                            <option ng-repeat="i in filterGroups" value="{{i.id}}" ng-selected="i.id==model.groupId">
                                {{i.name}}
                            </option>
                        </select>
                        <span class="error-icon" ng-if="editForm.groupId.$invalid && editForm.groupId.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>地址</span>
                    <div class="tjia-box clearfix">
                        <select name="province" ng-model="model.province" class="search-sel"
                                ng-class="{'error-on':editForm.province.$invalid && editForm.province.$dirty}"
                                ng-change="provinceChange()"
                                required>
                            <option value="" ng-selected="model.province">省</option>
                            <option ng-repeat="p in provinces" value="{{p}}" ng-selected="p==model.province">{{p}}
                            </option>
                        </select>
                        <select name="city" ng-model="model.city" class="search-sel"
                                ng-class="{'error-on':editForm.city.$invalid && editForm.city.$dirty}"
                                ng-change="cityChange()"
                                required>
                            <option value="" ng-selected="model.city">市</option>
                            <option ng-repeat="c in cities" value="{{c}}" ng-selected="c==model.city">{{c}}</option>
                        </select>
                        <select name="district" ng-model="model.district" class="search-sel"
                                ng-class="{'error-on':editForm.district.$invalid && editForm.district.$dirty}"
                                ng-change="districtChange()"
                                required>
                            <option value="" ng-selected="model.district">区县</option>
                            <option ng-repeat="d in districts" value="{{d}}" ng-selected="d==model.district">{{d}}
                            </option>
                        </select>

                        <script class="resources library" src="/static/js/lib/area.js"
                                type="text/javascript"></script>
                    <span class="error-icon" ng-if="
                            (editForm.province.$invalid && editForm.province.$dirty)
                            ||(editForm.city.$invalid && editForm.city.$dirty)
                            ||(editForm.district.$invalid && editForm.district.$dirty)
                    "></span>
                        <br/>
                    <textarea name="address" class="address-text" ng-model="model.address"
                              ng-class="{'error-on':editForm.address.$invalid && editForm.address.$dirty}"
                              placeholder="补全地址" required></textarea>
                    <span class="error-icon" ng-if="
                            editForm.address.$invalid && editForm.address.$dirty
                    "></span>
                    </div>
                </li>
            </ul>
        </div>
        <div class="tjia-action">
            <a href="javascript:;" class="tjia-submit"
               ng-click="
                    !editForm.name.$setDirty() && editForm.name.$valid
                    && !editForm.phone.$setDirty() && editForm.phone.$valid
                    && !editForm.groupId.$setDirty() && editForm.groupId.$valid
                    && !editForm.province.$setDirty() && editForm.province.$valid
                    && !editForm.city.$setDirty() && editForm.city.$valid
                    && !editForm.district.$setDirty() && editForm.district.$valid
                    && !editForm.address.$setDirty() && editForm.address.$valid
                    && submit(false)">提交</a>
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

<script src="/static/js/branch/branch.js"></script>
</body>
</html>
