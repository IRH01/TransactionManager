app.controller('BranchController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.roleLevel = $('#account-role-level').html();

        var submitting = false;

        $scope.filter = {page: 1, size: HelperService.PAGE_SIZE, sortBy: 'id', sortOrder: 'DESC'};

        HelperService.getManagedBranchesAndGroups($scope, function () {
            $scope.search(1);
        });

        $scope.getGroupName = function (id) {
            var list = $scope.filterGroups;
            for (var i in list) {
                if (id == list[i].id) {
                    return list[i].name;
                }
            }
            return ''
        };

        $scope.search = function (page) {
            $scope.filter.page = page || 1;
            HelperService.search('/api/branch/branch/', $scope.filter, function (page) {
                $scope.search(page);
            }, function (result) {
                $scope.list = result.items;
            });
        };

        $scope.reset = function () {
            $scope.filter = {page: 1, size: HelperService.PAGE_SIZE, sortBy: 'id', sortOrder: 'DESC'};
            $scope.search(1);
        };

        $scope.edit = function (id) {
            $scope.editForm.$setPristine();
            $scope.provinces = dsy.Items[0];
            if (!id) {
                $scope.model = {enabled: true};
                $scope.cities = [];
                $scope.districts = [];
            } else {
                $http.get('/api/branch/branch/' + id).success(function (result) {
                    $scope.model = result.data;
                    $scope.getCities();
                    $scope.cityChange();
                });
            }
            $('.content-box').hide();
            $('.add-content').show();
        };

        $scope.getCities = function () {
            console.log($scope.model.province);
            var provinces = $scope.provinces;
            var province = $scope.model.province;
            for (var i in provinces) {
                if (provinces[i] == province) {
                    $scope.province_index = i;
                }
            }

            var items = dsy.Items;
            for (var i in items) {
                if (i == "0_" + $scope.province_index) {
                    $scope.cities = items[i];
                }
            }
        };

        $scope.provinceChange = function () {
            $scope.getCities();
            $scope.model.city = null;
            $scope.model.district = null;
        };

        $scope.cityChange = function () {
            console.log($scope.model.city);
            var citis = $scope.cities;
            for (var i in citis) {
                if (citis[i] == $scope.model.city) {
                    $scope.city_index = i;
                }
            }

            var items = dsy.Items;
            for (var i in items) {
                if (i == "0_" + $scope.province_index + "_" + $scope.city_index) {
                    $scope.districts = items[i];
                }
            }
        };

        $scope.submit = function (continueEdit) {
            if (submitting) {
                return;
            }
            submitting = true;
            var branch = $scope.model;
            $http.post('/api/branch/branch/' + (branch.id ? 'update' : 'save'), branch).success(
                function (result) {
                    if (result.code == '03') {
                        submitting = false;
                        return HelperService.tipErrorMessage($scope, '此店铺已存在，请重新修改');
                    }
                    if (!branch.id) {
                        branch.id = result.data;
                        $scope.list.unshift(branch);
                        HelperService.tipSuccessMessage($scope, "新增店铺成功");
                    } else {
                        HelperService.replaceListItem($scope.list, branch);
                        HelperService.tipSuccessMessage($scope, "修改店铺成功");
                    }
                    if (continueEdit) {
                        submitting = false;
                        return $scope.edit();
                    }
                    submitting = false;
                    $scope.cancelEdit();
                });
        };

        $scope.changeStatus = function (branch, enabled) {
            if (submitting) {
                return;
            }
            submitting = true;
            if (confirm('是否确认' + (enabled ? '启用' : '停用') + '此店铺？')) {
                branch.enabled = enabled;
                $http.post('/api/branch/branch/update/', branch).error(function () {
                    submitting = false;
                    branch.enabled = !enabled;
                }).success(function () {
                    submitting = false;
                    if (enabled) {
                        HelperService.tipSuccessMessage($scope, "启用店铺成功");
                    } else {
                        HelperService.tipSuccessMessage($scope, "停用店铺成功");
                    }
                });
            }
        };

    }]);