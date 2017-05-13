app.controller('CategoryController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.platforms = ['POS', 'IPAD', 'APP'];

        $scope.filter = {platform: 'POS', sortBy: 'display_order', sortOrder: 'ASC'};

        $scope.search = function () {
            HelperService.search('/api/product/category', $scope.filter, null, function (result) {
                $scope.list = result.items;
            });
        };

        $scope.setActivePlatform = function (platform) {
            if (platform == $scope.filter.platform) {
                return;
            }
            $scope.filter.platform = platform;
            $scope.search();
        };

        $scope.edit = function (id) {
            $scope.selectedProducts = {};
            $scope.editForm.$setPristine();
            if (!id) {
                $scope.model = {
                    displayOrder: $scope.list.length + 1,
                    platform: $scope.activePlatform,
                    products: [],
                    enabled: true
                };
                setSelectedProducts($scope.model);
                $('.content-box').hide();
                $('.add-content').show();
                return;
            }
            $http.get('/api/product/category/' + id).success(function (result) {
                $scope.model = result.data;
                setSelectedProducts($scope.model);
                $('.content-box').hide();
                $('.add-content').show();
            });
        };

        $scope.submit = function (continueEdit) {
            var category = $scope.model;
            category.platform = $scope.filter.platform;
            var categoryProducts = [];
            var keys = Object.getOwnPropertyNames($scope.selectedProducts);
            var selectedKeys = [];
            for (var i in keys) {
                var pId = keys[i];
                if ($scope.selectedProducts[keys[i]]) {
                    selectedKeys.push(pId);
                }
            }
            for (var i in selectedKeys) {
                var pId = selectedKeys[i];
                var categoryProduct = null;
                for (var j in category.products) {
                    var temp = category.products[j];
                    if (temp.product.id.toString() == pId) {
                        categoryProduct = temp;
                        break;
                    }
                }
                if (!categoryProduct) {
                    categoryProduct = {displayOrder: selectedKeys.length + 1, product: {id: pId}};
                }
                categoryProducts.push(categoryProduct);
            }
            HelperService.sortByDisplayOrder(categoryProducts);
            category.products = categoryProducts;

            $http.post('/api/product/category/' + (category.id ? 'update' : 'save'), category).success(
                function (result) {
                    if (!category.id) {
                        category.id = result.data;
                        $scope.list.push(category);
                        HelperService.tipSuccessMessage($scope, "新增分类成功")
                    } else {
                        HelperService.replaceListItem($scope.list, category);
                        HelperService.tipSuccessMessage($scope, "修改分类成功");
                    }
                    if (continueEdit) {
                        return $scope.edit();
                    }
                    $scope.cancelEdit();
                }).error(function () {
                if (!category.id) {
                    HelperService.tipErrorMessage($scope, "新增分类失败")
                } else {
                    HelperService.tipErrorMessage($scope, "修改分类失败");
                }
            });
        };

        $scope.changeStatus = function (model, enabled) {
            HelperService.changeStatus('/api/product/category/update', model, enabled, '分类');
            if (enabled) {
                HelperService.tipSuccessMessage($scope, "启用分类成功");
            } else {
                HelperService.tipSuccessMessage($scope, "停用分类成功");
            }
        };

        $scope.sortCategory = function (category, type, index) {
            var params = {};
            for (var i = 0; i < $scope.list.length; i++) {
                var item = $scope.list[i];
                if (i == index) {
                    if (type == 'TOP') {
                        item.displayOrder = 0;
                    } else if (type == 'UP') {
                        item.displayOrder--;
                    } else if (type == 'DOWN') {
                        item.displayOrder++;
                    } else {
                        item.displayOrder = $scope.list.length + 1;
                    }
                } else {
                    if (type == 'TOP' || (type == 'UP' && i == index - 1)) {
                        item.displayOrder++;
                    } else if (type == 'BOTTOM' || (type == 'DOWN' && i == index + 1)) {
                        item.displayOrder--;
                    }
                }
            }

            HelperService.sortByDisplayOrder($scope.list);
            for (var i = 0; i < $scope.list.length; i++) {
                var item = $scope.list[i];
                params[item.id] = item.displayOrder;
            }

            $http.post('/api/product/category/update-display-order', params).success(function () {
                HelperService.tipSuccessMessage($scope, "分类排序成功");
            });
        };

        $scope.editProductOrders = function (id) {
            $http.get('/api/product/category/' + id).success(function (result) {
                $scope.model = result.data;
                $('#category-list').hide();
                $('#product-list').show();
            });
        };

        $scope.cancelProductOrders = function () {
            $('#product-list').hide();
            $('#category-list').show();
        };

        $scope.sortProduct = function (product, type, index) {
            for (var i = 0; i < $scope.model.products.length; i++) {
                var item = $scope.model.products[i];
                if (i == index) {
                    if (type == 'TOP') {
                        item.displayOrder = 0;
                    } else if (type == 'UP') {
                        item.displayOrder--;
                    } else if (type == 'DOWN') {
                        item.displayOrder++;
                    } else {
                        item.displayOrder = $scope.list.length + 1;
                    }
                } else {
                    if (type == 'TOP' || (type == 'UP' && i == index - 1)) {
                        item.displayOrder++;
                    } else if (type == 'BOTTOM' || (type == 'DOWN' && i == index + 1)) {
                        item.displayOrder--;
                    }
                }
            }

            HelperService.sortByDisplayOrder($scope.model.products);
            $http.post('/api/product/category/update', $scope.model).success(function () {
                HelperService.tipSuccessMessage($scope, "单品排序成功");
            });
        };

        $scope.search();

        $http.get('/api/product/product').success(function (result) {
            $scope.products = result.data.items;
        });

        function setSelectedProducts(category) {
            for (var i in category.products) {
                var categoryProduct = category.products[i];
                $scope.selectedProducts[categoryProduct.product.id] = true;
            }
        }
    }]);