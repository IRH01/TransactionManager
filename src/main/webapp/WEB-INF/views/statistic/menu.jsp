<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>

<body ng-controller="ProductSalesStatisticProductController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="statistic"/>
    <jsp:param name="view" value="statistic-menu"/>
</jsp:include>
<!--菜单分析 start-->
<div class="model-list-box content-box" ng-cloak>
    <!--二级导航 start-->
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">菜单分析</h3>
        <ul class="navList">
            <li ng-repeat="k in navKeys" ng-class="{on: k == navKey}" ng-click="setNavKey(k)">
                <a href="#">{{navKeyNames[k]}}</a></li>
        </ul>
    </div>
    <!--二级导航 end-->

    <div class="sel-box">
        <!--日期 start-->
        <div class="search-box">
            <span class="search-label">日期</span>
            <input id="startDate" type="text" class="search-input search-input1"
                   placeholder="起始日期"
                   ng-model="filter.dateFrom"
            <%--value="{{filter.dateFrom}}"--%>
                   ng-focus="getDate('from')"
            />
            &nbsp;&nbsp;到&nbsp;&nbsp;
            <input id="endDate" type="text" class="search-input search-input1"
                   placeholder="结束日期"
            <%--value="{{filter.dateTo}}"--%>
                   ng-model="filter.dateTo"
                   ng-focus="getDate('to')"
            />
        </div>
        <!--日期 end-->

        <div class="search-w hide">
            <div class="search-box">
                <jsp:include page="../shared/filter-groups-branches.jsp"/>
            </div>

            <!--状态 start-->
            <div class="search-box" ng-if="navKey == 'product'">
                <span class="search-label">按分类</span>
                <select name="zx" class="search-sel" ng-model="filter.categoryId">
                    <option value="">全部分类</option>
                    <option ng-repeat="i in filterCategories" value="{{i.id}}" ng-selected="filter.categoryId==i.id">
                        {{i.name}}
                    </option>
                </select>
            </div>
            <!--状态 end-->
        </div>

        <div class="search-action">
            <a href="javascript:;" class="sea-btn1" ng-click="search()">筛选</a>
            <a href="javascript:;" class="sea-btn2" ng-click="reset()">重置</a>
            <a href="javascript:;" class="sai-link">↓显示更多选项</a>
        </div>
    </div>

    <!--分析 start-->
    <div class="analyse-box">
        <div class="analyse-title">排行</div>
        <div class="analyse-jun">平均<span>{{average}}</span></div>
        <!--统计列表 start-->
        <div class="table-list">
            <table class="table-box">
                <thead>
                <tr>
                    <td>
                        <div class="danp-name">单品名称</div>
                    </td>
                    <td ng-repeat="i in keys">
                            <span class="dx blue" ng-click='setSortBy(i)'>{{keyNames[$index]}}
                            <i class="dx-down" ng-if="sortBy==i"></i></span></td>
                    <td><span class="blue"></span></td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="i in list" ng-class="{'stop-tr':!i.enabled}">
                    <td>
                        <div class="danp-name">{{i[navKey+'']['name']}}
                            <i class="number-one" ng-if="i.rank==100"></i></div>
                    </td>
                    <td ng-repeat="k in keys">
                        <span style="display:inline-block;width:80px;">{{k=='orderRate'? i[k]+ '%':i[k]}}</span>
                        <span class="danp-bg" ng-if="sortBy==k">
                            <em class="danp-percent-left" ng-style="{width:i.rank+'%'}"></em>
                            <em class="danp-percent-right" ng-style="{width:(100-i.rank)+'%'}"></em>
                        </span>
                    </td>
                    <td><a href="#" class="blue" ng-click="getDetails(i[navKey])">详情</a></td>
                </tr>
                </tbody>
            </table>
            <div class="no-data" ng-if="list!=null && list.length==0">暂无数据</div>
        </div>
        <!--统计列表 end-->
    </div>
    <!--分析 end-->
</div>
<!--菜单分析  end-->

<!--单品分析详情-->
<div class="add-content hide">
    <div class="clumn-nav clearfix">
        <a class="nav-return" title="返回" href="#" ng-click="cancelEdit()">< 返回</a>
        <h3 class="nav-title">{{getSalesSummaryById(details[navKey+'Id'])[navKey].name}}</h3>
    </div>

    <div class="tubiao-box">
        <div class="bar-con clearfix">
            <div class="bar-clumn">
                <div id="graph1"></div>
                <pre id="code1" class="prettyprint linenums" style="display:none;"></pre>
                <div class="code-text">服务类型销售额占比</div>
            </div>

            <div class="bar-clumn">
                <div id="graph2"></div>
                <pre id="code2" class="prettyprint linenums" style="display:none;"></pre>
                <div class="code-text">单品销售额占比</div>
            </div>

        </div>
    </div>

    <div class="tubiao-box">
        <div class="bar-con clearfix">
            <div class="analyse-title">销售额</div>
            <div class="analyse-jun">共计<span>¥{{getSalesSummaryById(details[navKey+'Id']).sales}}</span>
            </div>
            <div id="graph4"></div>
            <pre id="code4" class="prettyprint linenums" style="display:none;"></pre>
        </div>
    </div>

    <div class="tubiao-box">
        <div class="bar-con clearfix">
            <div class="analyse-title">销售量</div>
            <div class="analyse-jun">共计<span>{{getSalesSummaryById(details[navKey+'Id']).count}}</span></div>
            <div id="graph5"></div>
            <pre id="code5" class="prettyprint linenums" style="display:none;"></pre>
        </div>
    </div>

    <div class="tubiao-box">
        <div class="bar-con clearfix">
            <div class="analyse-title">点选率</div>
            <div class="analyse-jun"><span></span>
            </div>
            <div id="graph6"></div>
            <pre id="code6" class="prettyprint linenums" style="display:none;"></pre>
        </div>
    </div>
</div>
</br>
</br>
</br>
</br>
</br>
</body>
<script src="/static/js/statistic/menu.js"></script>
<script src="/static/js/lib/laydate.js" title="http://laydate.layui.com/"></script>
<script src="//cdn.bootcss.com/morris.js/0.5.1/morris.min.js"></script>
<script src="//cdn.bootcss.com/prettify/r298/prettify.min.js" type="text/javascript"></script>
<script src="//cdn.bootcss.com/raphael/2.1.4/raphael-min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        eval($('#code1').text());
        eval($('#code2').text());

        eval($('#code4').text());
        eval($('#code5').text());
        eval($('#code6').text());
        prettyPrint();
    })
</script>
</html>
