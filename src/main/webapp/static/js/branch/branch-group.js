app.controller('BranchGroupController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.roleLevel = $('#account-role-level').html();

        var submitting = false;

        $scope.filter = {page: 1, size: HelperService.PAGE_SIZE};

        HelperService.getManagedBranchesAndGroups($scope, function () {
            $scope.search(1);
        });

        $scope.search = function (page) {
            $scope.filter.page = page || 1;
            HelperService.search('/api/branch/branch-group', $scope.filter, function (page) {
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
            if (!id) {
                $scope.model = {};
                $('.content-box').hide();
                $('.add-content').show();
                return
            }
            $http.get('/api/branch/branch-group/' + id).success(function (result) {
                $scope.model = result.data;
                $('.content-box').hide();
                $('.add-content').show();
            });
        };

        $scope.submit = function (continueEdit) {
            if (submitting) {
                return;
            }
            submitting = true;
            var branchGroup = $scope.model;
            $http.post('/api/branch/branch-group/' + (branchGroup.id ? 'update' : 'save'), branchGroup).success(
                function (result) {
                    if (result.code == '03') {
                        submitting = false;
                        return HelperService.tipErrorMessage($scope, '此店铺组已存在，请重新修改');
                    }
                    if (!branchGroup.id) {
                        branchGroup.id = result.data;
                        branchGroup.enabled = true;
                        $scope.list.unshift(branchGroup);
                        HelperService.tipSuccessMessage($scope, '新增店铺组成功');
                    } else {
                        HelperService.replaceListItem($scope.list, branchGroup);
                        HelperService.tipSuccessMessage($scope, '修改店铺组成功');
                    }
                    HelperService.showMessage('操作成功');
                    if (continueEdit) {
                        submitting = false;
                        return $scope.edit();
                    }
                    submitting = false;
                    $scope.cancelEdit();
                }).error(function () {
                if (!branchGroup.id) {
                    HelperService.tipErrorMessage($scope, "新增店铺组失败")
                } else {
                    HelperService.tipErrorMessage($scope, "修改店铺组失败")
                }
            });
        };

        $scope.changeStatus = function (branchGroup, enabled) {
            if (submitting) {
                return;
            }
            submitting = true;
            if (confirm('是否确认' + (enabled ? '启用' : '停用') + '此店铺组？')) {
                branchGroup.enabled = enabled;
                $http.post('/api/branch/branch-group/update', branchGroup).error(function () {
                    submitting = false;
                    branchGroup.enabled = !enabled;
                }).success(function () {
                    submitting = false;
                    if (enabled) {
                        HelperService.tipSuccessMessage($scope, "启用店铺组成功");
                    } else {
                        HelperService.tipSuccessMessage($scope, "停用店铺组成功");
                    }
                });
            }
        };

    }]);