app.controller('PrintTypeController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.categories = {};
        $scope.categoryIds = [];

        $scope.filter = {page: 1, size: HelperService.PAGE_SIZE, sortBy: 'id', sortOrder: 'DESC'};

        $scope.search = function (page) {
            $scope.filter.page = page || 1;
            HelperService.search('/api/branch/print-type', $scope.filter, function (page) {
                $scope.search(page);
            }, function (result) {
                $scope.list = result.items;
            });
        };

        $scope.edit = function (id) {
            $scope.selectedCategories = {};
            $scope.selectedProducts = {};
            $scope.activeCategoryId = $scope.categoryIds[0];
            $scope.productsByCategory = [];
            $scope.editForm.$setPristine();
            if (!id) {
                $scope.model = {products: [], branchId: $scope.filter.branchId};
                $('.content-box').hide();
                $('.add-content').show();
                return;
            }
            $http.get('/api/branch/print-type/' + id).success(function (result) {
                $scope.model = result.data;
                for (var i in $scope.model.products) {
                    $scope.selectedProducts[$scope.model.products[i].id] = true;
                }
                setSelectedCategories();
                $('.content-box').hide();
                $('.add-content').show();
            });
        };

        $scope.setActiveCategory = function (id) {
            $scope.activeCategoryId = id;
        };

        $scope.onSelectedCategoryChange = function (id) {
            var category = $scope.categories[id];
            for (var i in category.products) {
                if ($scope.selectedCategories[id]) {
                    $scope.selectedProducts[category.products[i].product.id] = true;
                } else {
                    delete $scope.selectedProducts[category.products[i].product.id];
                }
            }
            setSelectedCategories();
        };

        $scope.onSelectedProductChange = function () {
            setSelectedCategories();
        };

        $scope.submit = function (continueEdit) {
            var model = $scope.model;
            model.products = [];
            console.log($scope.selectedProducts);
            for (var pId in $scope.selectedProducts) {
                if ($scope.selectedProducts[pId]) {
                    model.products.push({id: pId});
                }
            }
            $http.post('/api/branch/print-type/' + (model.id ? 'update' : 'save'), model).success(
                function (result) {
                    if (!model.id) {
                        model.id = result.data;
                        $scope.list.unshift(model);
                        HelperService.tipSuccessMessage($scope,"新增打印类成功");
                    } else {
                        HelperService.replaceListItem($scope.list, model);
                        HelperService.tipSuccessMessage($scope,"修改打印类成功");
                    }
                    if (continueEdit) {
                        return $scope.edit();
                    }
                    $scope.cancelEdit();
                });
        };

        $scope.$watch(function (scope) {
            return scope.filter.branchId;
        }, function (branchId) {
            if (branchId) {
                $scope.search(1);
            }
        });

        HelperService.getManagedBranchesAndGroups($scope, function () {
            if ($scope.filterBranches.length == 1) {
                $scope.filter.branchId = $scope.filterBranches[0].id;
            }
        });

        $http.get('/api/product/category').success(function (result) {
            for (var i in result.data.items) {
                var item = result.data.items[i];
                $scope.categories[item.id] = item;
                $scope.categoryIds.push(item.id);
            }
        });

        function setSelectedCategories() {
            for (var i in $scope.categoryIds) {
                var id = $scope.categoryIds[i];
                var selectedCount = 0;
                var category = $scope.categories[id];
                for (var j in category.products) {
                    if ($scope.selectedProducts[category.products[j].product.id]) {
                        selectedCount++;
                    }
                }
                if (selectedCount == category.products.length) {
                    $scope.selectedCategories[id] = true;
                } else {
                    delete $scope.selectedCategories[id];
                }
            }
        }
    }]);