<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<span id="account-role-level" class="hide"><sec:authentication property="principal.role.level"/></span>
<div class="header clearfix">
    <span class="header-act">
        <em class="user-name">用户名</em>
        <a href="javascript:;" class="exit-btn" ng-click="logout()">退出登录</a>
    </span>
    <span class="menu-icon">
				<em class="menu-line"></em>
				<em class="menu-line"></em>
				<em class="menu-line"></em>
	</span>
    <div class="menu-box">
        <ul>
            <li class="on"><a href="/statistic/total">营业数据</a></li>
            <li><a href="/statistic/branch">店铺排行</a></li>
            <li><a href="/statistic/menu">菜单分析</a></li>
        </ul>
    </div>
</div>
<div class="nav-box">
    <div class="ping-box">
        <img src="/static/images/logo_1.png"/>
        <span class="ping-name"><sec:authentication property="principal.hq.name"/><br/><a href="#">品牌管理</a></span>

    </div>
    <ul class="nav-ul">
        <sec:authorize access="hasAuthority('data')">
            <li class="nav-li${param.tab=="statistic"?" select":""}">
                <a href="javascript:;" class="nav-link"><span>实时数据</span><span class="nav-icon"></span></a>
                <ul class="subnav-ul"${param.tab=="statistic"?"style='display:block;'":""}>
                    <li><a href="/statistic/total" class="${param.view=="statistic-total"?"active":""}">营业数据</a></li>
                    <li><a href="/statistic/branch" class="${param.view=="statistic-branch"?"active":""}">店铺排行</a></li>
                    <li><a href="/statistic/menu" class="${param.view=="statistic-menu"?"active":""}">菜单统计</a></li>
                    <li><a href="/statistic/discount" class="${param.view=="statistic-discount"?"active":""}">优惠统计</a></li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAuthority('finance')">
            <li class="nav-li${param.tab=="finance"?" select":""}">
                <a href="javascript:;" class="nav-link"><span>财务数据</span><span class="nav-icon"></span></a>
                <ul class="subnav-ul"${param.tab=="finance"?"style='display:block;'":""}>
                    <li><a href="/finance/report" class="${param.view=="finance-report"?"active":""}">财务报表</a></li>
                    <li><a href="/finance/order" class="${param.view=="finance-order"?"active":""}">订单流水</a></li>
                    <li><a href="/finance/vip-card" class="${param.view=="finance-vip-card"?"active":""}">会员卡报表</a></li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAuthority('product')">
            <li class="nav-li${param.tab=="product"?" select":""}">
                <a href="javascript:;" class="nav-link"><span>菜单管理</span><span class="nav-icon"></span></a>
                <ul class="subnav-ul"${param.tab=="product"?"style='display:block;'":""}>
                    <li><a href="/product/category" class="${param.view=="product-category"?"active":""}">分类管理</a></li>
                    <li><a href="/product/product-option-group"
                           class="${param.view=="product-option-group"?"active":""}">
                        单品选项组</a></li>
                    <li><a href="/product/product" class="${param.view=="product-product"?"active":""}">单品管理</a></li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAuthority('branch')">
            <li class="nav-li${param.tab=="branch"?" select":""}">
                <a href="javascript:;" class="nav-link"><span>店铺管理</span><span class="nav-icon"></span></a>
                <ul class="subnav-ul"${param.tab=="branch"?"style='display:block;'":""}>
                    <li><a href="/branch/branch-group" class="${param.view=="branch-group"?"active":""}">店铺组管理</a></li>
                    <li><a href="/branch/branch" class="${param.view=="branch-branch"?"active":""}">店铺管理</a></li>
                    <li><a href="/branch/table" class="${param.view=="branch-table"?"active":""}">桌号管理</a></li>
                    <li><a href="/branch/print-type" class="${param.view=="branch-print-type"?"active":""}">打印类别</a>
                    </li>
                    <li><a href="/branch/product-status-record"
                           class="${param.view=="branch-product-status"?"active":""}">分店停售菜单
                    </a></li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAuthority('account')">
            <li class="nav-li${param.tab=="account"?" select":""}">
                <a href="javascript:;" class="nav-link"><span>员工管理</span><span class="nav-icon"></span></a>
                <ul class="subnav-ul"${param.tab=="account"?"style='display:block;'":""}>
                    <li><a href="/account/role" class="${param.view=="account-role"?"active":""}">职位管理</a></li>
                    <li><a href="/account/account" class="${param.view=="account-account"?"active":""}">账号管理</a></li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAuthority('marketing')">
            <li class="nav-li${param.tab=="marketing"?" select":""}">
                <a href="javascript:;" class="nav-link"><span>营销管理</span><span class="nav-icon"></span></a>
                <ul class="subnav-ul"${param.tab=="marketing"?"style='display:block;'":""}>
                    <li><a href="/marketing/member" class="${param.view=="marketing-vip-card"?"active":""}">会员管理</a>
                    </li>
                    <li><a href="/marketing/discount" class="${param.view=="marketing-discount"?"active":""}">优惠活动
                    </a></li>
                </ul>
            </li>
        </sec:authorize>
    </ul>
</div>