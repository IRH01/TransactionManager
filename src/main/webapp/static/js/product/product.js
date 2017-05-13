app.controller('ProductController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.filter = {page: 1, size: HelperService.PAGE_SIZE, sortBy: 'id', sortOrder: 'DESC'};

        $scope.search = function (page) {
            $scope.filter.page = page || 1;
            HelperService.search('/api/product/product', $scope.filter, function (page) {
                $scope.search(page);
            }, function (result) {
                $scope.list = result.items;
            });
        };

        $scope.reset = function () {
            $scope.filter = {page: 1, size: HelperService.PAGE_SIZE};
            $scope.search(1);
        };

        $scope.edit = function (id) {
            $scope.editForm.$setPristine();
            $scope.selectedOptionGroups = {};
            if (!id) {
                $scope.model = {status: 'ONSALE', optionGroups: []};
                $('.content-box').hide();
                $('.add-content').show();
                return;
            }

            $http.get('/api/product/product/' + id).success(function (result) {
                $scope.model = result.data;
                for (var i in $scope.model.optionGroups) {
                    var optionGroup = $scope.model.optionGroups[i];
                    $scope.selectedOptionGroups[optionGroup.id] = true;
                }
                $('.content-box').hide();
                $('.add-content').show();
            });
        };

        $scope.submit = function (continueEdit) {
            var model = $scope.model;
            if (!model.imgUrl) {
                $scope.imgInvalid = true;
                return;
            }
            model.optionGroups = [];
            for (var gId in $scope.selectedOptionGroups) {
                if ($scope.selectedOptionGroups[gId]) {
                    model.optionGroups.push({id: gId});
                }
            }
            $http.post('/api/product/product/' + (model.id ? 'update' : 'save'), model).success(function (result) {
                if (!model.id) {
                    model.id = result.data;
                    $scope.list.unshift(model);
                    HelperService.tipSuccessMessage($scope, "新增单品成功");
                } else {
                    HelperService.replaceListItem($scope.list, model);
                    HelperService.tipSuccessMessage($scope, "修改单品成功");
                }
                HelperService.showMessage('操作成功');
                if (continueEdit) {
                    return $scope.edit();
                }

                $scope.cancelEdit();
            }).error(function () {
                if (!model.id) {
                    HelperService.tipErrorMessage($scope, "新增单品失败")
                } else {
                    HelperService.tipErrorMessage($scope, "修改单品失败")
                }
            });
        };

        $scope.changeStatus = function (model, status) {
            if (confirm('是否确认' + (status == 'ONSALE' ? '停售' : '上架') + '此单品？')) {
                $http.get('/api/product/product/' + model.id).success(function (result) {
                    result.data.status = status;
                    $http.post('/api/product/product/update', result.data).success(function () {
                        model.status = status;
                        if (enabled) {
                            HelperService.tipSuccessMessage($scope, "启用单品 " + model.name + " 成功");
                        } else {
                            HelperService.tipSuccessMessage($scope, "停用单品 " + model.name + " 成功");
                        }
                    });
                });
            }
        };

        $scope.search(1);

        $http.get('/api/product/product-option-group').success(function (result) {
            $scope.optionGroups = result.data.items;
        });

        document.getElementById('img-file').onchange = function () {
            HelperService.uploadImage('product', this, function (url) {
                if (url) {
                    $scope.$apply(function () {
                        $scope.model.imgUrl = url;
                        delete $scope.imgInvalid;
                    });
                }
            });
        };
    }]);