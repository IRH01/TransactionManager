app.controller('ProductOptionGroupController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.filter = {page: 1, size: HelperService.PAGE_SIZE, sortBy: 'id', sortOrder: 'DESC'};

        $scope.reset = function () {
            delete $scope.filter.name;
            $scope.search(1);
        };

        $scope.search = function (page) {
            $scope.filter.page = page || 1;
            HelperService.search('/api/product/product-option-group', $scope.filter, function (page) {
                $scope.search(page);
            }, function (result) {
                $scope.list = result.items;
            });
        };

        $scope.edit = function (id) {
            $scope.editForm.$setPristine();
            if (!id) {
                $scope.model = {enabled: true, options: [{enabled: true, price: 0}]};
                $('.content-box').hide();
                $('.add-content').show();
                return;
            }

            $http.get('/api/product/product-option-group/' + id).success(function (result) {
                $scope.model = result.data;
                $('.content-box').hide();
                $('.add-content').show();
            });
        };

        $scope.submit = function (continueEdit) {
            var model = $scope.model;
            $http.post('/api/product/product-option-group/' + (model.id ? 'update' : 'save'), model).success(
                function (result) {
                    if (!model.id) {
                        model.id = result.data;
                        $scope.list.unshift(model);
                        HelperService.tipSuccessMessage($scope, "新增单品选项组成功");
                    } else {
                        HelperService.replaceListItem($scope.list, model);
                        HelperService.tipSuccessMessage($scope, "修改单品选项组成功");
                    }
                    HelperService.showMessage('操作成功');
                    if (continueEdit) {
                        return $scope.edit();
                    }

                    $scope.cancelEdit();
                }).error(function () {
                if (!model.id) {
                    HelperService.tipErrorMessage($scope, "新增单品选项组失败");
                } else {
                    HelperService.tipErrorMessage($scope, "修改单品选项组失败");
                }

            });
        };

        $scope.changeStatus = function (model, enabled) {
            HelperService.changeStatus('/api/product/product-option-group/update', model, enabled, '选项组').success(function () {
                if (enabled) {
                    HelperService.tipSuccessMessage($scope, "启用选项组成功");
                } else {
                    HelperService.tipSuccessMessage($scope, "停用选项组成功");
                }
            });
        };

        $scope.newOption = function () {
            $scope.model.options.push({enabled: true, price: 0});
        };

        $scope.search(1);
    }]);