<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="DiscountStatController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="statistic"/>
    <jsp:param name="view" value="statistic-discount"/>
</jsp:include>
<div class="content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">优惠统计</h3>
    </div>
    <div class="sel-box">
        <!--日期 start-->
        <div class="search-box">
            <span class="search-label">日期</span>
            <input id="startDate" type="text" class="search-input search-input1"/>
            &nbsp;&nbsp;到&nbsp;&nbsp;
            <input id="endDate" type="text" class="search-input search-input1"/>
        </div>
        <div class="search-w hide">
            <jsp:include page="../shared/filter-groups-branches.jsp"/>
        </div>
        <div class="search-action">
            <a href="javascript:;" class="sea-btn1" ng-click="search()">筛选</a>
            <a href="javascript:;" class="sea-btn2" ng-click="reset()">重置</a>
            <a href="javascript:;" class="sai-link">↓显示更多选项</a>
        </div>
    </div>
    <div class="analyse-box" ng-cloak>
        <div class="table-list">
            <table class="table-box">
                <thead>
                <tr>
                    <td>优惠名称</td>
                    <td>单数</td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="i in list">
                    <td>{{i.discountName}}</td>
                    <td>{{i.count}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="/static/js/lib/laydate.js"></script>
<script src="/static/js/statistic/discount.js"></script>
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
