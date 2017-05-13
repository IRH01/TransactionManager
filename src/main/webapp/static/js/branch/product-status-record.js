app.controller('BranchProductStatusRecordController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.filter = {page: 1, size: HelperService.PAGE_SIZE};

        $scope.search = function (page) {
            $scope.filter.page = page || 1;
            HelperService.search('/api/branch/product-status-record', $scope.filter, function (page) {
                $scope.search(page);
            }, function (result) {
                $scope.list = result.items;
            });
        };

        $scope.deleteRecord = function (id, index) {
            if (confirm('是否确认此记录？')) {
                $http.post('/api/branch/product-status-record/delete/' + id).success(function () {
                    $scope.list.splice(index, 1);
                    HelperService.tipSuccessMessage($scope,"删除一条停售单品记录")
                });
            }
        };

        $scope.edit = function () {
            $scope.model = {status: 'SOLDOUT', branch: {id: $scope.filter.branchId}};
            $scope.editForm.$setPristine();
            $('.content-box').hide();
            $('.add-content').show();
        };

        $scope.submit = function (continueEdit) {
            $http.post('/api/branch/product-status-record/save', $scope.model).success(function (result) {
                if (result.code == '03') {
                    return HelperService.tipErrorMessage($scope,'此单品记录已存在');
                }
                $scope.list.unshift($scope.model);
                HelperService.tipSuccessMessage('新增分店停售菜单成功');
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

        $http.get('/api/product/product').success(function (result) {
            $scope.products = result.data.items;
        });
    }]);