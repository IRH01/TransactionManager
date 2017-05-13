app.controller('DiscountStatController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.filter = {};

        $scope.reset = function () {
            $('#startDate, #endDate').val('');
            $scope.filter = {};
            $scope.search();
        };

        $scope.search = function () {
            $scope.filter.dateFrom = $('#startDate').val();
            $scope.filter.dateTo = $('#endDate').val();
            HelperService.search('/api/statistic/discount', $scope.filter, null, function (result) {
                $scope.list = result;
            });
        };

        HelperService.getManagedBranchesAndGroups($scope, function () {
            $scope.search();
        });
    }]);