app.controller('BranchTableController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        var submitting = false;

        $scope.filter = {page: 1, size: HelperService.PAGE_SIZE};

        $scope.roleLevel = $('#account-role-level').text();

        HelperService.getManagedBranchesAndGroups($scope, function () {
            if ($scope.roleLevel == "BRANCH") {
                $scope.search(1);
            }
        });

        $scope.search = function (page) {
            $scope.filter.page = page || 1;
            HelperService.search('/api/branch/branch-table/', $scope.filter, function (page) {
                $scope.search(page);
            }, function (result) {
                $scope.list = result.items;
            });
        };

        $scope.$watch(function (scope) {
            return scope.filter.branchId;
        }, function (branchId) {
            if (branchId) {
                $scope.search(1);
            }
        });

        $scope.getBranchName = function (id) {
            var list = $scope.filterBranches;
            for (var i in list) {
                if (id == list[i].id) {
                    return list[i].name;
                }
            }
            return "";
        };

        $scope.edit = function () {
            $scope.model = {enabled: true};
            $scope.editForm.$setPristine();
            $scope.model.branchId = $scope.filter.branchId;
            $(".add-content").show();
            $(".model-list-box").hide();
        };

        $scope.changeStatus = function (table, enabled) {
            if (submitting) {
                return;
            }
            submitting = true;
            if (confirm('是否确认' + (enabled ? '启用' : '停用') + '此桌号？')) {
                table.enabled = enabled;
                $http.post('/api/branch/branch-table/update/', table).error(function () {
                    submitting = false;
                    table.enabled = !enabled;
                }).success(function () {
                    submitting = false;
                    if (enabled) {
                        HelperService.tipSuccessMessage($scope, "启用桌号 " + table.name + " 成功");
                    } else {
                        HelperService.tipSuccessMessage($scope, "停用桌号 " + table.name + " 成功");
                    }
                });
            }
        };

        $scope.submit = function (continueEdit) {
            if (submitting) {
                submitting = true;
            }
            var branchTable = $scope.model;
            $http.post("/api/branch/branch-table/save/", branchTable).success(function (result) {
                if (result.code == "03") {
                    submitting = false;
                    return HelperService.tipErrorMessage($scope, "此座号在该店铺中已存在");
                }
                branchTable.id = result.data;
                branchTable.code = $scope.model.code;
                branchTable.branchId = $scope.model.branchId;
                $scope.list.unshift(branchTable);
                HelperService.tipSuccessMessage("新增桌号成功");
                if (continueEdit) {
                    submitting = false;
                    return $scope.edit();
                }
                $scope.cancelEdit();
            });
        };

    }]);