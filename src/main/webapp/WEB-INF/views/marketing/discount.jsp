<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="DiscountController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="marketing"/>
    <jsp:param name="view" value="marketing-discount"/>
</jsp:include>
<div class="model-list-box content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">优惠活动管理</h3>
        <ul class="navList">
            <li ng-repeat="i in timeStatus" ng-class="{'on':i==activeTimeStatus}">
                <a href="#" ng-click="setActiveTimeStatus(i)">{{i}}</a></li>
        </ul>
    </div>
    <div class="sel-box">
        <div class="search-box">
            <span class="search-label">名称</span>
            <input type="text" class="search-input" ng-model="filter.name" placeholder="请输入姓名搜索"/>
        </div>
        <div class="search-action">
            <a href="javascript:;" class="sea-btn1" ng-click="search(1)">筛选</a>
            <a href="javascript:;" class="sea-btn2" ng-click="reset()">重置</a>
        </div>
    </div>
    <!--添加按钮 start-->
    <div class="addBtn-box">
        <span class="addBtn" ng-click="edit()">+<em>添加优惠活动</em></span>
    </div>
    <!--添加按钮 end-->
    <div class="table-list">
        <table class="table-box">
            <thead>
            <tr>
                <td>名称</td>
                <td>优惠方式</td>
                <td>优惠内容</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list" ng-class="{'stop-tr':!i.enabled || i.status=='已结束'}">
                <td>{{i.name}}</td>
                <td>{{DISCOUNT_TYPES[i.discountType]}}</td>
                <td>{{getDiscountValue(i)}}</td>
                <td>{{i.status}}</td>
                <td>
                    <a href="javascript:;" class="red" ng-if="i.enabled" ng-click="changeStatus(i,false)">停用</a>
                    <a href="javascript:;" class="green" ng-if="!i.enabled" ng-click="changeStatus(i,true)">启用</a>
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
        <h3 class="nav-title">{{!model.id?'添加':'编辑'}}优惠</h3>
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
                        <span class="error-icon" ng-if="editForm.name.$invalid && editForm.name.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>优惠方式</span>
                    <div class="tjia-box clearfix">
                        <select name="discountType" class="tjia-select" ng-model="model.discountType"
                                ng-class="{'error-on':editForm.discountType.$invalid && editForm.$dirty}"
                                required>
                            <option value="PERCENTAGE" ng-select="model.discountType=='PERCENTAGE'">打折</option>
                            <option value="AMOUNT" ng-select="model.discountType=='AMOUNT'">减免</option>
                            <option value="FREE" ng-select="model.discountType=='FREE'">赠送</option>
                        </select>
                        <span class="error-icon"
                              ng-if="editForm.discountType.$invalid && editForm.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li" ng-if="model.discountType && model.discountType!='FREE'">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>优惠内容</span>
                    <div class="tjia-box clearfix">
                        <input type="number" class="tjia-input" min="0"
                               ng-class="{'error-on':editForm.discountValue.$invalid && editForm.$dirty}"
                               name="discountValue" ng-model="model.discountValue" placeholder="请输入优惠内容"
                               required/><span>{{model.discountType=='PERCENTAGE'?'%':'元'}}</span>
                        <span class="error-icon"
                              ng-if="editForm.discountValue.$invalid && editForm.discountValue.$dirty"></span>
                    </div>
                </li>
                <li>
                    <span class="tjia-label">选择单品</span>
                    <div class="tjia-box clearfix">
                        <div class="xzhe-box clearfix">
                            <div class="xzhe-left">
                                <ul class="xzhe-ul">
                                    <li ng-repeat="id in categoryIds" ng-click="setActiveCategory(id)"
                                        ng-class="{'current':id==activeCategoryId}">
                                        <input type="checkbox" class="service-checkbox"
                                               ng-model="selectedCategories[id]"
                                               ng-change="onSelectedCategoryChange(id)">{{categories[id].name}}
                                    </li>
                                </ul>
                            </div>
                            <div class="xzhe-right">
                                <ul class="xzhe-ul">
                                    <li ng-repeat="i in categories[activeCategoryId].products">
                                        <input type="checkbox" class="service-checkbox"
                                               ng-model="selectedProducts[i.product.id]"
                                               ng-change="onSelectedProductChange()">{{i.product.name}}
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label">优惠日期</span>
                    <div class="tjia-box clearfix">
                        <span class="tjia-ge">
                            <input ng-model="model.always" type="radio" ng-value="true" class="tjia-radio"/>永远</span>
                        <span class="tjia-ge">
                            <input ng-model="model.always" type="radio" ng-value="false" class="tjia-radio"/>
                            <input id="startDate" name="startDate" type="text" class="tian-input"
                                   ng-model="model.startDate"
                                   ng-disabled="model.always"
                                   ng-class="{'error-on':editForm.$dirty && editForm.startDate.$invalid}"> -
                            <input id="endDate" name="endDate" type="text" class="tian-input" ng-model="model.endDate"
                                   ng-disabled="model.always"
                                   ng-class="{'error-on':editForm.$dirty && editForm.endDate.$invalid}">
                            <span class="error-icon"
                                  ng-if="editForm.$dirty && !model.always && (editForm.startDate.$invalid || editForm.endDate.$invalid)"></span>
                        </span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label">优惠时间</span>
                    <div class="tjia-box clearfix">
                         <span class="tjia-ge">
                            <input ng-model="model.fulltime" type="radio" ng-value="true" class="tjia-radio"/>全天</span>
                        <span class="tjia-ge">
                            <input ng-model="model.fulltime" type="radio" ng-value="false" class="tjia-radio"/>
                            <select name="startTime" class="search-sel" ng-model="model.startTime"
                                    ng-disabled="model.fulltime"
                                    ng-class="{'error-on':editForm.$dirty && !model.fulltime && !model.startTime}">
                                <option ng-repeat="i in HOURS" ng-select="model.startTime==i">{{i}}</option>
                            </select> -
                            <select name="endTime" class="search-sel" ng-model="model.endTime"
                                    ng-disabled="model.fulltime"
                                    ng-class="{'error-on':editForm.$dirty && !model.fulltime && !model.endTime}">
                                <option ng-repeat="i in HOURS" ng-select="model.endTime==i">{{i}}</option>
                            </select>
                            <span class="error-icon"
                                  ng-if="editForm.$dirty && !model.fulltime && (!model.startTime||!model.endTime)"></span>
                        </span>
                    </div>
                </li>
            </ul>
        </div>
        <div class="tjia-action">
            <a href="javascript:;" class="tjia-submit"
               ng-click="!editForm.$setDirty() && checkDate() && editForm.$valid && submit(false)">提交</a>
            <a href="javascript:;" class="tjia-submit2"
               ng-click="!editForm.$setDirty() && checkDate() && editForm.$valid && submit(true)"
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
<script src="/static/js/lib/laydate.js"></script>
<script src="/static/js/marketing/discount.js"></script>
<script>
    //日期选择
    laydate.skin('danlan');//切换皮肤
    var start = {
        elem: '#startDate',
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas; //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#endDate',
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);//绑定元素
    laydate(end);//绑定元素
</script>
</body>
</html>
