<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="VipCardReportController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="finance"/>
    <jsp:param name="view" value="finance-vip-card"/>
</jsp:include>
<div class="model-list-box content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">会员卡报表</h3>
        <ul class="navList">
            <li ng-class="{'on': !branchView}">
                <a href="#" ng-click="setBranchView(false)">明细报表</a></li>
            <li ng-class="{'on': branchView}">
                <a href="#" ng-click="setBranchView(true)">分店报表</a></li>
        </ul>
    </div>
    <div class="sel-box">
        <div class="search-box">
            <span class="search-label">日期</span>
            <input id="startDate" type="text" class="search-input search-input1"/>
            &nbsp;&nbsp;到&nbsp;&nbsp;
            <input id="endDate" type="text" class="search-input search-input1"/>
        </div>
        <div class="search-w search-box hide">
            <jsp:include page="../shared/filter-groups-branches.jsp"/>
        </div>
        <div class="search-action">
            <a href="javascript:;" class="sea-btn1" ng-click="search(1)">筛选</a>
            <a href="javascript:;" class="sea-btn2" ng-click="reset()">重置</a>
            <a href="javascript:;" class="sai-link">↓显示更多选项</a>
        </div>
    </div>
    <div class="addBtn-box">
        <span class="addBtn" ng-click="export()"><img src="\static\images\ic-download.png" width="18px" height="18px"><em>下载表格</em></span>
    </div>
    <div class="table-list" ng-show="!branchView">
        <table class="table-box">
            <thead>
            <tr>
                <td>卡号</td>
                <td>姓名</td>
                <td>余额</td>
                <td>发票余额</td>
                <td>现金充值</td>
                <td>刷卡充值</td>
                <td>充值总额</td>
                <td>消费总额</td>
                <td>发票总额</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list">
                <td>{{i.vipCard.number}}</td>
                <td>{{i.vipCard.name}}</td>
                <td>{{i.vipCard.balance}}</td>
                <td>{{i.vipCard.invoiceQuota}}</td>
                <td>{{i.cashRecharge}}</td>
                <td>{{i.unipayRecharge}}</td>
                <td>{{i.totalRecharge}}</td>
                <td>{{i.consume}}</td>
                <td>{{i.invoice}}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <!--分页start-->
    <div class="page-clumn" ng-show="!branchView">
    </div>
    <!--分页end-->
    <div class="table-list" ng-show="branchView">
        <table class="table-box">
            <thead>
            <tr>
                <td>分店</td>
                <td>现金充值</td>
                <td>刷卡充值</td>
                <td>充值总额</td>
                <td>消费总额</td>
                <td>发票总额</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in branchList">
                <td>{{i.branch.name}}</td>
                <td>{{i.cashRecharge}}</td>
                <td>{{i.unipayRecharge}}</td>
                <td>{{i.totalRecharge}}</td>
                <td>{{i.consume}}</td>
                <td>{{i.invoice}}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script src="/static/js/lib/laydate.js"></script>
<script src="/static/js/finance/vip-card.js"></script>
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
