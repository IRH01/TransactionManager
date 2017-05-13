<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="OrderTransactionController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="finance"/>
    <jsp:param name="view" value="finance-order"/>
</jsp:include>
<div class="model-list-box content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">订单流水</h3>
    </div>
    <div class="sel-box">
        <jsp:include page="../shared/filter-groups-branches.jsp"/>
        <div class="search-w search-box hide">
            <span class="search-label">日期</span>
            <input id="startDate" type="text" class="search-input search-input1"/>
            &nbsp;&nbsp;到&nbsp;&nbsp;
            <input id="endDate" type="text" class="search-input search-input1"/>
        </div>
        <div class="search-w search-box hide">
            <span class="search-label">支付方式</span>
            <select ng-model="filter.payType">
                <option value="">全部</option>
                <option ng-repeat="i in PAY_TYPES" value="{{i}}">{{PAY_TYPE_NAMES[i]}}</option>
            </select>
        </div>
        <div class="search-action">
            <a href="javascript:;" class="sea-btn1" ng-click="search(1)">筛选</a>
            <a href="javascript:;" class="sea-btn2" ng-click="reset()">重置</a>
            <a href="javascript:;" class="sai-link">↓显示更多选项</a>
        </div>
    </div>
    <div class="addBtn-box">
        <span class="addBtn" ng-click="export()">
            <img src="\static\images\ic-download.png" width="18px" height="18px"><em>下载表格</em></span>
    </div>
    <div class="table-list">
        <table class="table-box">
            <thead>
            <tr>
                <td>日期</td>
                <td>订单号</td>
                <td>支付方式</td>
                <td>金额</td>
                <td>实收</td>
                <td>优惠券</td>
                <td>打折</td>
                <td>发票</td>
                <td>原订单号</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list">
                <td>{{i.createdAt}}</td>
                <td>{{i.bill}}</td>
                <td>{{PAY_TYPE_NAMES[i.payType]}}</td>
                <td>{{i.price}}</td>
                <td>{{i.price-i.coupon||0}}</td>
                <td>{{i.coupon}}</td>
                <td>{{i.discount*100}}</td>
                <td>{{i.invoice}}</td>
                <td>{{i.originalBill}}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <!--分页start-->
    <div class="page-clumn">
    </div>
    <!--分页end-->
</div>
<script src="/static/js/lib/laydate.js"></script>
<script src="/static/js/finance/order.js"></script>
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
