<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="AccountController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="account"/>
    <jsp:param name="view" value="account-account"/>
</jsp:include>

<div class="model-list-box content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">账号管理</h3>
    </div>

    <div class="sel-box">
        <div class="search-box">
            <jsp:include page="../shared/filter-groups-branches.jsp"/>
        </div>
        <div class="search-w search-box hide">
            <span class="search-label">姓名</span>
            <input type="text" class="search-input" ng-model="filter.name" placeholder="请输入姓名搜索"/>
        </div>
        <div class="search-action">
            <a href="javascript:;" class="sea-btn1" ng-click="search(1)">筛选</a>
            <a href="javascript:;" class="sea-btn2" ng-click="reset()">重置</a>
            <a href="javascript:;" class="sai-link">↓显示更多选项</a>
        </div>
    </div>

    <!--添加按钮 start-->
    <div class="addBtn-box">
        <span class="addBtn" ng-click="edit()">+<em>添加账号</em></span>
    </div>
    <!--添加按钮 end-->

    <div class="table-list" ng-cloak>
        <table class="table-box">
            <thead>
            <tr>
                <td>序号</td>
                <td>账号</td>
                <td>姓名</td>
                <td>职位</td>
                <td>手机</td>
                <td>所属</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list" ng-class="{'stop-tr':!i.enabled}">
                <td>{{$index+1}}</td>
                <td>{{i.credentialId}}</td>
                <td>{{i.fullname}}</td>
                <td>{{i.role.name}}</td>
                <td>{{i.mobile}}</td>
                <td>{{i.branch.name}}</td>
                <td>{{i.enabled?'正常':'停用'}}</td>
                <td>
                    <a href="javascript:;" class="editor" ng-click="edit(i.id)">编辑</a>
                    <a href="javascript:;" class="red" ng-if="i.enabled" ng-click="changeStatus(i,false)">停用</a>
                    <a href="javascript:;" class="green" ng-if="!i.enabled" ng-click="changeStatus(i,true)">启用</a>
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
        <h3 class="nav-title">{{!model.id?'添加账号':'编辑账号'}}</h3>
    </div>
    <form name="editForm" novalidate>
        <div class="tjia-con">
            <ul class="tjia-ul">
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>账号</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input"
                               ng-class="{'error-on':editForm.credentialId.$invalid && editForm.$dirty}"
                               name="credentialId" ng-model="model.credentialId" placeholder="请输入账号"
                               required/>
                        <span class="error-icon" ng-if="editForm.credentialId.$invalid && editForm.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>密码</span>
                    <div class="tjia-box clearfix">
                        <input type="password" name="password" class="tjia-input"
                               ng-class="{'error-on':!model.id && (!temp.password || temp.password.length==0) && editForm.$dirty}"
                               ng-model="temp.password" placeholder="请输入密码"/>
                        <span class="error-icon"
                              ng-if="!model.id && (!temp.password || temp.password.length==0) && editForm.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>确认密码</span>
                    <div class="tjia-box clearfix">
                        <input type="password" name="confirmPassword" class="tjia-input"
                               ng-class="{'error-on':temp.password!=null && temp.password.length>0 && temp.confirmPassword!=temp.password}"
                               ng-model="temp.confirmPassword"
                               placeholder="请重复输入一次密码"/>
                        <span class="error-icon"
                              ng-if="temp.password!=null && temp.password.length>0 && temp.confirmPassword!=temp.password"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>姓名</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input" name="fullname"
                               ng-class="{'error-on':editForm.fullname.$invalid && editForm.$dirty}"
                               placeholder="请输入姓名" ng-model="model.fullname" required/>
                        <span class="error-icon" ng-if="editForm.fullname.$invalid && editForm.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>电话</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input" placeholder="请输入电话" name="mobile"
                               ng-class="{'error-on':editForm.mobile.$invalid && editForm.$dirty}"
                               ng-pattern="/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d)|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/"
                               ng-model="model.mobile" required/>
                        <span class="error-icon" ng-if="editForm.mobile.$invalid && editForm.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>职位</span>
                    <div class="tjia-box clearfix">
                        <select name="role" class="tjia-select" ng-model="model.role" required
                                ng-options="i as i.name for i in managedRoles track by i.id"
                                ng-class="{'error-on':editForm.role.$invalid && editForm.$dirty}">
                        </select>
                        <span class="error-icon" ng-if="editForm.role.$invalid && editForm.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label">所属分组</span>
                    <div class="tjia-box clearfix">
                        <select name="branchGroup" class="tjia-select" ng-model="model.branchGroup"
                                ng-class="{'error-on':model.role && model.role.level!='HQ' && !model.branchGroup}"
                                ng-options="i as i.name for i in filterGroups track by i.id">
                            <option value="" ng-if="roleLevel=='HQ'"></option>
                        </select>
                        <span class="error-icon"
                              ng-if="model.role && model.role.level!='HQ' && !model.branchGroup"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label">所属店铺</span>
                    <div class="tjia-box clearfix">
                        <select name="branch" class="tjia-select" ng-model="model.branch"
                                ng-class="{'error-on':model.role && model.role.level=='BRANCH' && !model.branch}"
                                ng-options="i as i.name for i in filterBranches track by i.id">
                            <option value="" ng-if="roleLevel=='BRANCH_GROUP'"></option>
                        </select>
                        <span class="error-icon"
                              ng-if="model.role && model.role.level=='BRANCH' && !model.branch"></span>
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

<script src="//cdn.bootcss.com/blueimp-md5/2.3.0/js/md5.min.js"></script>
<script src="/static/js/account/account.js"></script>
<script type="text/javascript">


</script>
</body>
</html>
