<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <select id="filter-branches" name="branchId" class="search-sel" ng-model="filter.branchId"
            ng-options="b.id as b.name for b in filterBranches">
        <option value="" ng-if="filterBranches.length>1">
            <c:choose>
                <c:when test="${param.required=='true'}">店铺</c:when>
                <c:otherwise>全部店铺</c:otherwise>
            </c:choose>
        </option>
    </select>
</div>
