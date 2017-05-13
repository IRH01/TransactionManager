app.controller('DiscountController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.categories = {};
        $scope.categoryIds = [];

        $scope.DISCOUNT_TYPES = {
            'PERCENTAGE': '折扣',
            'AMOUNT': '固定优惠',
            'FREE': '赠送'
        };

        $scope.HOURS = [];
        for (var h = 0; h <= 23; h++) {
            $scope.HOURS.push(h < 10 ? '0' + h : h.toString());
        }

        $scope.filter = {page: 1, size: HelperService.PAGE_SIZE, sortBy: 'id', sortOrder: 'DESC'};

        $scope.search = function (page) {
            $scope.filter.page = page || 1;
            HelperService.search('/api/marketing/discount', $scope.filter, function (page) {
                $scope.search(page);
            }, function (result) {
                for (var i in result.items) {
                    var item = result.items[i];
                    setDiscountStatus(item);
                }
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
                $scope.model = {products: [], always: true, fulltime: true};
                $('.content-box').hide();
                $('.add-content').show();
                return;
            }

            $http.get('/api/marketing/discount/' + id).success(function (result) {
                $scope.model = result.data;
                $scope.model.fulltime = $scope.model.startTime == null;
                $scope.model.always = $scope.model.startDate == null;
                if ($scope.model.startTime != null) {
                    $scope.model.startTime = HelperService.getDoubleNumberString($scope.model.startTime);
                    $scope.model.endTime = HelperService.getDoubleNumberString($scope.model.endTime);
                }
                if ($scope.model.startDate) {
                    $scope.model.startDate = HelperService.formatDate($scope.model.startDate, true);
                    $scope.model.endDate = HelperService.formatDate($scope.model.endDate, true);
                }
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

        $scope.checkDate = function () {
            if ($scope.model.always) {
                return true;
            }
            var model = $scope.model;
            var valid = true;
            model.startDate = $('#startDate').val();
            if (model.startDate.length == 0) {
                $scope.editForm.startDate.$setValidity('required', false);
                valid = false;
            } else {
                $scope.editForm.startDate.$setValidity('required', true);
            }
            model.endDate = $('#endDate').val();
            if (model.endDate.length == 0) {
                $scope.editForm.endDate.$setValidity('required', false);
                valid = false;
            } else {
                $scope.editForm.endDate.$setValidity('required', true);
            }
            return valid;
        };

        $scope.submit = function (continueEdit) {
            var model = $scope.model;
            var valid = true;
            if (!model.always) {
                valid = $scope.checkDate();
            }
            if (!model.fulltime && (!model.endTime || !model.startTime)) {
                valid = false;
            }
            if (!valid) {
                return;
            }
            if (model.always) {
                delete model.startDate;
                delete model.endDate;
            }
            if (model.fulltime) {
                delete model.startTime;
                delete model.endTime;
            }
            model.products = [];
            for (var pId in $scope.selectedProducts) {
                model.products.push({id: pId});
            }
            $http.post('/api/marketing/discount/' + (model.id ? 'update' : 'save'), model).success(
                function (result) {
                    setDiscountStatus(model);
                    if (!model.id) {
                        model.id = result.data;
                        $scope.list.unshift(model);
                        HelperService.tipSuccessMessage($scope, "新增优惠活动成功");
                    } else {
                        HelperService.replaceListItem($scope.list, model);
                        HelperService.tipSuccessMessage($scope, "修改优惠活动的成功");
                    }
                    if (continueEdit) {
                        return $scope.edit();
                    }

                    $scope.cancelEdit();
                });
        };

        $scope.reset = function () {
            delete $scope.filter.name;
            $scope.search(1);
        };

        $scope.getDiscountValue = function (discount) {
            if (discount.discountType == 'PERCENTAGE') {
                return discount.discountValue + '折';
            } else if (discount.discountType == 'AMOUNT') {
                return '减' + discount.discountValue + '元';
            }
            return '赠送';
        };

        $scope.search(1);

        $http.get('/api/product/category').success(function (result) {
            for (var i in result.data.items) {
                var item = result.data.items[i];
                $scope.categories[item.id] = item;
                $scope.categoryIds.push(item.id);
            }
        });

        function setDiscountStatus(discount) {
            if (!discount.startDate) {
                discount.status = '有效';
                return;
            }
            var startDate = new Date(discount.startDate);
            var endDate = new Date(discount.endDate);
            var now = new Date();
            if (now >= startDate && now < endDate) {
                discount.status = '有效';
            } else if (now < startDate) {
                discount.status = '未开始';
            } else {
                discount.status = '已结束';
            }
        }

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

        $scope.changeStatus = function (branch, enabled) {
            if (confirm('是否确认' + (enabled ? '启用' : '停用') + '此店铺？')) {
                branch.enabled = enabled;
                $http.post('/api/marketing/discount/update/', branch).error(function () {
                    branch.enabled = !enabled;
                }).success(function () {
                    if (enabled) {
                        HelperService.tipSuccessMessage($scope, "启用店铺 " + branch.name + " 成功");
                    } else {
                        HelperService.tipSuccessMessage($scope, "停用店铺 " + branch.name + " 成功")
                    }
                });
            }
        };

    }]);