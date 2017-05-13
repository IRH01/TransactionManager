<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="../shared/head.jsp"/>
</head>
<body ng-controller="ProductController">
<jsp:include page="../shared/layout.jsp">
    <jsp:param name="tab" value="product"/>
    <jsp:param name="view" value="product-product"/>
</jsp:include>
<div class="model-list-box content-box" ng-cloak>
    <div class="clumn-nav clearfix">
        <h3 class="nav-title">单品管理</h3>
    </div>
    <div class="sel-box">
        <div class="search-box">
            <span class="search-label">按状态</span>
            <select name="zx" class="search-sel" ng-model="filter.status">
                <option value="">全部</option>
                <option value="ONSALE">在售</option>
                <option value="DISABLED">停售</option>
            </select>
        </div>
        <div class="search-w search-box hide">
            <span class="search-label">名称</span>
            <input type="text" class="search-input" ng-model="filter.name" placeholder="请输入名称搜索"/>
        </div>
        <div class="search-action">
            <a href="javascript:;" class="sea-btn1" ng-click="search(1)">筛选</a>
            <a href="javascript:;" class="sea-btn2" ng-click="reset()">重置</a>
            <a href="javascript:;" class="sai-link">↓显示更多选项</a>
        </div>
    </div>

    <!--添加按钮 start-->
    <div class="addBtn-box">
        <span class="addBtn" ng-click="edit()">+<em>添加单品</em></span>
    </div>
    <!--添加按钮 end-->

    <div class="table-list" ng-cloak>
        <table class="table-box">
            <thead>
            <tr>
                <td>序号</td>
                <td>图片</td>
                <td>名称</td>
                <td>价格</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="i in list" ng-class="{'stop-tr':i.status!='ONSALE'}">
                <td>{{$index+1}}</td>
                <td><img ng-src="{{i.imgUrl}}" ng-if="i.imgUrl!=null" width="50" height="50"/></td>
                <td>{{i.name}}</td>
                <td>{{i.price}}</td>
                <td>{{i.status=='ONSALE'?'在售':'停售'}}</td>
                <td>
                    <a href="javascript:;" class="editor" ng-click="edit(i.id)">编辑</a>
                    <a href="javascript:;" class="red" ng-if="i.status=='ONSALE'"
                       ng-click="changeStatus(i,'DISABLED')">停售</a>
                    <a href="javascript:;" class="green" ng-if="i.status!='ONSALE'"
                       ng-click="changeStatus(i,'ONSALE')">上架</a>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="no-data" ng-if="list!=null && list.length==0">暂无数据</div>
    </div>
    <!--分页 start-->
    <div class="page-clumn">
    </div>
    <!--分页 end-->
</div>
<div class="add-content hide">
    <div class="clumn-nav clearfix">
        <a class="nav-return" title="返回" href="#" ng-click="cancelEdit()">< 返回</a>
        <h3 class="nav-title">{{!model.id?'添加单品':'编辑单品'}}</h3>
    </div>
    <form name="editForm" novalidate>
        <div class="tjia-con">
            <ul class="tjia-ul">
                <li>
                    <span class="tjia-label">添加照片</span>
                    <div class="tjia-box clearfix">
                        <div class="tj-img" ng-if="model==null || model.imgUrl==null"
                             ng-class="{'error-on':imgInvalid}"></div>
                        <img class="tj-img" ng-src="{{model.imgUrl}}" ng-if="model!=null && model.imgUrl!=null"/>
                        <div class="ctu-act">
                            <div class="ctu-btn">
                                <label for="img-file" class="ctu-a">上传图片</label>
                                <input id="img-file" type="file" class="ctu-file"
                                       accept=".jpg,.png,.gif"/>
                            </div>
                            <div class="ctu-tips">图片大小不能超过200k，格式：jpg，png<br/>建议使用800*800大小的图片</div>
                        </div>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>名称</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input"
                               ng-class="{'error-on':editForm.name.$invalid && editForm.name.$dirty}"
                               name="name" ng-model="model.name" placeholder="请输入账号"
                               required/>
                        <span class="error-icon" ng-if="editForm.name.$invalid && editForm.name.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red"></em>外文名</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input"
                               name="interName" ng-model="model.interName" placeholder="请输入账号"/>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red">*</em>价格</span>
                    <div class="tjia-box clearfix">
                        <input type="number" class="tjia-input"
                               ng-class="{'error-on':editForm.price.$invalid && editForm.price.$dirty}"
                               name="price" ng-model="model.price" placeholder="请输入账号"
                               required min="0"/>
                        <span class="error-icon" ng-if="editForm.price.$invalid && editForm.price.$dirty"></span>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red"></em>标签</span>
                    <div class="tjia-box clearfix">
                        <input type="text" class="tjia-input"
                               name="label" ng-model="model.label" min="0"/>
                    </div>
                </li>
                <li class="sr-li">
                    <span class="tjia-label"><em class="tjia-sel red"></em>简介</span>
                    <div class="tjia-box clearfix">
                        <textarea name="description" ng-model="model.description" class="address-text">{{model.description}}</textarea>
                    </div>
                </li>
                <li>
                    <span class="tjia-label">选项组</span>
                    <div class="tjia-box clearfix">
                        <div class="service-lie clearfix">
                            <span ng-repeat="i in optionGroups">
                                <input type="checkbox" class="service-checkbox" ng-model="selectedOptionGroups[i.id]"/>{{i.name}}</span>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="tjia-action">
            <a href="javascript:;" class="tjia-submit"
               ng-click="
               !editForm.name.$setDirty() && editForm.name.$valid
               &&!editForm.price.$setDirty() && editForm.price.$valid
               &&submit(false)">提交</a>
            <a href="javascript:;" class="tjia-submit2"
               ng-click="(editForm.name.$valid && !editForm.name.setDirty)&&
               (editForm.price.$valid && !editForm.price.setDirty)&&
               submit(true)"
               ng-if="!model.id">提交,并继续添加</a>
        </div>
    </form>
</div>

<!--成功提示-->
<div class="success-tips" style="opacity:0;">
    <i class="success-icon"></i><span>{{tipMessage}}</span>
</div>
<!--错误提示-->
<div class="fail-tips" style="opacity:0;">
    <i class="fail-icon"></i><span>{{tipMessage}}</span>
</div>

<script src="/static/js/product/product.js"></script>
</body>
</html>
