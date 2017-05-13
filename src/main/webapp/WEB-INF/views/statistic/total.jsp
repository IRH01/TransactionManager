<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="TotalStatController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="statistic"/>
    <jsp:param name="view" value="statistic-total"/>
</jsp:include>
<div class="content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">营业统计</h3>
        <ul class="navList">
            <li ng-repeat="k in keys" ng-class="{'on':k==key}" ng-click="setKey(k)">
                <a href="#">{{keyNames[k]}}</a></li>
        </ul>
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
    <div class="tubiao-box">
        <div class="analyse-title">比例分析</div>
        <div class="bar-con clearfix">
            <div class="bar-clumn">
                <div id="chart-serviceType"></div>
                <div class="code-text">服务类型</div>
            </div>
            <div class="bar-clumn">
                <div id="chart-platform"></div>
                <div class="code-text">点餐渠道</div>
            </div>
            <div class="bar-clumn">
                <div id="chart-payType"></div>
                <div class="code-text">支付方式</div>
            </div>
        </div>
    </div>
    <div class="tubiao-box">
        <div class="analyse-title">销售额</div>
        <div class="analyse-jun" ng-if="key=='sales'||key=='count'">共计
            <span>{{total[key].toString()}}</span></div>
        <div class="analyse-jun">按日期</div>
        <div id="chart-date"></div>
        <div class="tubiao-main clearfix">
            <div class="tubiao-bar">
                <div class="analyse-jun">按日期</div>
                <div id="chart-weekday"></div>
            </div>
            <div class="tubiao-area">
                <div class="analyse-jun">按小时</div>
                <div id="chart-hour"></div>
            </div>
        </div>
    </div>
</div>
<script src="/static/js/lib/laydate.js"></script>
<script src="/static/js/lib/BigDecimal-all-last.min.js"></script>
<script src="//cdn.bootcss.com/morris.js/0.5.1/morris.min.js"></script>
<script src="//cdn.bootcss.com/prettify/r298/prettify.min.js" type="text/javascript"></script>
<script src="//cdn.bootcss.com/raphael/2.1.4/raphael-min.js" type="text/javascript"></script>
<script src="/static/js/statistic/total.js"></script>
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
