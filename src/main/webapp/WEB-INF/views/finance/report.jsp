<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="FinancialReportController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="finance"/>
    <jsp:param name="view" value="finance-report"/>
</jsp:include>
<div class="model-list-box content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">财务报表</h3>
        <ul class="navList">
            <li ng-class="{'on': !branchView}">
                <a href="#" ng-click="setBranchView(false)">汇总报表</a></li>
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
                <td>日期</td>
                <td>单数</td>
                <td>销售额</td>
                <td>现金</td>
                <td>刷卡</td>
                <td>支付宝</td>
                <td>微信</td>
                <td>会员卡充值</td>
                <td>打折</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list">
                <td>{{i.date}}</td>
                <td>{{i.orders}}</td>
                <td>{{i.totalSales}}</td>
                <td>{{i.cashSales}}</td>
                <td>{{i.unipaySales}}</td>
                <td>{{i.alipaySales}}</td>
                <td>{{i.wechatSales}}</td>
                <td>{{i.totalRecharge}}</td>
                <td>{{i.discounts}}</td>
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
                <td>起始日期</td>
                <td>结束日期</td>
                <td>店铺</td>
                <td>订单数</td>
                <td>销售额</td>
                <td>现金</td>
                <td>刷卡</td>
                <td>支付宝</td>
                <td>微信</td>
                <td>会员卡充值</td>
                <td>打折</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in branchList">
                <td>{{filter.dateFrom}}</td>
                <td>{{filter.dateTo}}</td>
                <td>{{i.branch.name}}</td>
                <td>{{i.orders}}</td>
                <td>{{i.totalSales}}</td>
                <td>{{i.cashSales}}</td>
                <td>{{i.unipaySales}}</td>
                <td>{{i.alipaySales}}</td>
                <td>{{i.wechatSales}}</td>
                <td>{{i.totalRecharge}}</td>
                <td>{{i.discounts}}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script src="/static/js/lib/laydate.js"></script>
<script src="/static/js/finance/report.js"></script>
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
