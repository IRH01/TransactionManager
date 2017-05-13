<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="MemberController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="marketing"/>
    <jsp:param name="view" value="marketing-vip-card"/>
</jsp:include>
<div class="model-list-box content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">会员管理</h3>
        <ul class="navList">
            <li ng-repeat="i in timeStatus" ng-class="{'on':i==activeTimeStatus}">
                <a href="#" ng-click="setActiveTimeStatus(i)">{{i}}</a></li>
        </ul>
    </div>
    <div class="sel-box">
        <div class="search-box">
            <jsp:include page="../shared/filter-groups-branches.jsp"/>
        </div>
        <div class="search-w hide">
            <div class="search-box">
                <span class="search-label">状态</span>
                <select name="zx" class="search-sel" ng-model="filter.status">
                    <option value="">全部</option>
                    <option value="ACTIVE">正常</option>
                    <option value="FROZEN">已冻结</option>
                    <option value="DISABLED">已停用</option>
                </select>
            </div>
            <div class="search-box">
                <span class="search-label">模糊搜索</span>
                <input type="text" class="search-input" ng-model="filter.info" placeholder="请输入名称或手机号码搜索"/>
            </div>
        </div>
        <div class="search-action">
            <a href="javascript:;" class="sea-btn1" ng-click="search(1)">筛选</a>
            <a href="javascript:;" class="sea-btn2" ng-click="reset()">重置</a>
            <a href="javascript:;" class="sai-link">↓显示更多选项</a>
        </div>
    </div>
    <!--添加按钮 start-->
    <div class="addBtn-box">
        <div style="float: left;">
            <span style="margin-right: 30px;">正常：{{activeCount}}</span>
            <span style="margin-right: 30px;">已冻结：<em style="color: #f00;">{{frozenCount}}</em></span>
            <span style="margin-right: 30px;">已停用：{{disabledCount}}</span>
        </div>
        <span class="addBtn" ng-click="edit()">+<em>添加会员</em></span>
    </div>
    <!--添加按钮 end-->
    <div class="table-list">
        <table class="table-box">
            <thead>
            <tr>
                <td>序号</td>
                <td>卡号</td>
                <td>姓名</td>
                <td>电话</td>
                <td>会员状态</td>
                <td>余额</td>
                <td>开卡分店</td>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list" ng-class="{'stop-tr':i.status=='FROZEN'}">
                <td>{{$index+1}}</td>
                <td>{{i.number}}</td>
                <td>{{i.name}}</td>
                <td>{{i.mobile}}</td>
                <td>{{statusName[i.status]}}</td>
                <td>{{i.balance}}</td>
                <td>{{i.branch.name}}</td>
                <td>
                    <a href="javascript:;" class="red" ng-if="i.status=='ACTIVE'" ng-click="changeStatus(i,'FROZEN')">冻结</a>
                    <a href="javascript:;" class="green" ng-if="i.status=='FROZEN'" ng-click="changeStatus(i,'ACTIVE')">解冻</a>
                    <a href="javascript:;" class="editor" ng-click="edit(i.id)">编辑</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
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
                    <span class="tjia-label"><em class="tjia-sel red">*</em>卡号</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input"
                               ng-class="{'error-on':editForm.number.$invalid && editForm.number.$dirty}"
                               name="number" ng-model="model.number" placeholder="请输入卡号"
                               ng-pattern="/^\d+$/"
                               required/>
                        <span class="error-icon" ng-if="editForm.number.$invalid && editForm.number.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>姓名</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input"
                               ng-class="{'error-on':editForm.name.name.$invalid && editForm.name.$dirty}"
                               name="name" ng-model="model.name" placeholder="请输入姓名"
                               required/>
                        <span class="error-icon" ng-if="editForm.name.$invalid && editForm.name.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>电话</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input"
                               ng-class="{'error-on':editForm.mobile.$invalid && editForm.mobile.$dirty}"
                               name="mobile" ng-model="model.mobile" placeholder="请输入电话号码"
                               ng-pattern="/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d)|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/"
                               required/>
                        <span class="error-icon" ng-if="editForm.mobile.$invalid && editForm.mobile.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>性别</span>
                    <div class="tjia-box clearfix">
                        <span><input name="gender" ng-model="model.gender" type="radio"
                                     ng-class="{'error-on':editForm.gender.$invalid && editForm.gender.$dirty}"
                                     value="male" class="service-checkbox" required>男</span>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span><input name="gender" ng-model="model.gender" type="radio"
                                     ng-class="{'error-on':editForm.gender.$invalid && editForm.gender.$dirty}"
                                     value="female" class="service-checkbox" required>女</span>
                        <span class="error-icon" ng-if="editForm.gender.$invalid && editForm.gender.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red"></em>生日</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input" ng-focus="getDate()"
                               name="birthDate" ng-model="model.birthDate | date:'yyyy-MM-dd'" placeholder="请选择生日"
                        />
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red"></em>身份证</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input"
                               name="idCardNumber" ng-model="model.idCardNumber" placeholder="请输入卡号"
                        />
                    </div>
                </li>
            </ul>
        </div>
        <div class="tjia-action">
            <a href="javascript:;" class="tjia-submit"
               ng-click="!editForm.number.$setDirty() && editForm.number.$valid
               && !editForm.name.$setDirty() && editForm.name.$valid
               && !editForm.mobile.$setDirty() && editForm.mobile.$valid
               && !editForm.gender.$setDirty() && editForm.gender.$valid
               && submit(false)">提交</a>
            <a href="javascript:;" class="tjia-submit2"
               ng-click="!editForm.$setDirty() && editForm.$valid && submit(true)"
               ng-if="!model.id">提交,并继续添加</a>
        </div>
    </form>
</div>
</body>
<script src="/static/js/marketing/member.js"></script>
<script src="/static/js/lib/laydate.js" title="http://laydate.layui.com/"></script>
</html>
