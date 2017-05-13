app.controller('AccountController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.filter = {page: 1, size: HelperService.PAGE_SIZE, sortBy: 'id', sortOrder: 'DESC'};

        $scope.temp = {};

        $scope.roleLevel = $('#account-role-level').text();

        $scope.reset = function () {
            $scope.filter = {page: 1, size: HelperService.PAGE_SIZE, sortBy: 'id', sortOrder: 'DESC'}
            $scope.search(1);
        };

        $scope.search = function (page) {
            $scope.filter.page = page || 1;
            HelperService.search('/api/account/account', $scope.filter, function (page) {
                $scope.search(page);
            }, function (result) {
                $scope.list = result.items;
            });
        };

        $scope.edit = function (id) {
            $scope.temp = {};
            $scope.editForm.$setPristine();
            if (!id) {
                $scope.model = {enabled: true};
                if ($scope.roleLevel != 'HQ') {
                    $scope.model.branchGroup = $scope.filterGroups[0];
                }
                if ($scope.roleLevel == 'BRANCH') {
                    $scope.model.branch = $scope.filterBranches[0];
                }
                $('.content-box').hide();
                $('.add-content').show();
                return;
            }
            $http.get('/api/account/account/' + id).success(function (result) {
                $scope.model = result.data;
                $('.content-box').hide();
                $('.add-content').show();
            });
        };

        $scope.submit = function (continueEdit) {
            if ($scope.temp.confirmPassword != $scope.temp.password) {
                return;
            }
            var model = $scope.model;
            if ($scope.temp.password != null && $scope.temp.password.length > 0) {
                model.password = md5($scope.temp.password);
            }
            if (model.role.level != 'HQ') {
                if (!model.branchGroup) {
                    return;
                }
                if (model.role.level == 'BRANCH' && !model.branch) {
                    return;
                }
            }
            $http.post('/api/account/account/' + (model.id ? 'update' : 'save'), model).success(
                function (result) {
                    if (result.code == '03') {
                        return HelperService.tipErrorMessage($scope, '此帐号已存在，请重新修改');
                    }
                    if (!model.id) {
                        model.id = result.data;
                        $scope.list.unshift(model);
                        HelperService.tipSuccessMessage($scope, '新增账号成功');
                    } else {
                        HelperService.replaceListItem($scope.list, model);
                        HelperService.tipSuccessMessage($scope, '修改账号信息成功');
                    }
                    if (continueEdit) {
                        return $scope.edit();
                    }
                    $scope.cancelEdit();
                });
        };

        $scope.changeStatus = function (model, enabled) {
            HelperService.changeStatus('/api/account/account/update', model, enabled, '账号').success(function () {
                if (enabled) {
                    HelperService.tipSuccessMessage($scope, "启用账号成功");
                } else {
                    HelperService.tipSuccessMessage($scope, "停用账号成功");
                }
            });
        };

        HelperService.getManagedBranchesAndGroups($scope, function () {
            $scope.search(1);
        });

        $http.get('/api/account/managed-roles').success(function (result) {
            $scope.managedRoles = result.data;
        });

    }]);