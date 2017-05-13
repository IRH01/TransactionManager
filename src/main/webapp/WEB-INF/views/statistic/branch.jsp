<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="BranchStatController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="statistic"/>
    <jsp:param name="view" value="statistic-branch"/>
</jsp:include>
<div class="content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">店铺排行</h3>
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
            <!--店铺筛选 start-->
            <div class="search-box">
                <span class="search-label">按店铺</span>
                <select id="filter-groups" name="groupId" class="search-sel" ng-model="filter.groupId"
                        ng-options="g.id as g.name for g in filterGroups"
                        ng-change="changeFilterGroup(filter)">
                    <option value="" ng-if="filterGroups.length>1">
                        <c:choose>
                            <c:when test="${param.required=='true'}">分组</c:when>
                            <c:otherwise>全部分组</c:otherwise>
                        </c:choose>
                    </option>
                </select>
            </div>
        </div>
        <div class="search-action">
            <a href="javascript:;" class="sea-btn1" ng-click="search()">筛选</a>
            <a href="javascript:;" class="sea-btn2" ng-click="reset()">重置</a>
            <a href="javascript:;" class="sai-link">↓显示更多选项</a>
        </div>
    </div>
    <div class="analyse-box" ng-cloak>
        <div class="analyse-title">排行</div>
        <div class="analyse-jun">平均<span>{{average}}</span></div>
        <div class="table-list">
            <table class="table-box">
                <thead>
                <tr>
                    <td>
                        <div class="danp-name">店铺名称</div>
                    </td>
                    <td ng-repeat="i in keys">
                        <span class="dx blue" ng-click='setSortBy(i)'>{{keyNames[$index]}}
                        <i class="dx-down" ng-if="sortBy==i"></i></span></td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="i in list">
                    <td>
                        <div class="danp-name">{{i.branch.name}}
                            <i class="number-one" ng-if="i.rank==100"></i></div>
                    </td>
                    <td ng-repeat="k in keys">
                        <span style="display:inline-block;width:80px;">{{k=='turnRate'? (i[k]==null?0:i[k]) + '%':i[k]}}</span>
                        <span class="danp-bg" ng-if="sortBy==k">
                            <em class="danp-percent-left" ng-style="{width:i.rank+'%'}"></em>
                            <em class="danp-percent-right" ng-style="{width:(100-i.rank)+'%'}"></em>
                        </span>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="no-data" ng-if="list && list.length==0">暂无数据</div>
        </div>
    </div>
</div>
<script src="/static/js/lib/laydate.js"></script>
<script src="/static/js/statistic/branch.js"></script>
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
