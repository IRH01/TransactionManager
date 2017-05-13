app.controller('VipCardReportController', ['$scope', 'HelperService', function ($scope, HelperService) {

    $scope.branchView = false;

    $scope.filter = {size: HelperService.PAGE_SIZE};

    $scope.setBranchView = function (branchView) {
        if ($scope.branchView != branchView) {
            $scope.branchView = branchView;
        }
    };

    $scope.reset = function () {
        $scope.filter = {size: HelperService.PAGE_SIZE};
        $('#startDate, #endDate').val('');
        $scope.search();
    };

    $scope.search = function (page) {
        var dateFrom = $('#startDate').val();
        if (dateFrom.length > 0) {
            $scope.filter.dateFrom = dateFrom;
        }
        var dateTo = $('#endDate').val();
        if (dateTo.length > 0) {
            $scope.filter.dateTo = dateTo;
        }

        $scope.filter.page = page || 1;
        HelperService.search('/api/finance/vip-card', $scope.filter, function (page) {
            $scope.search(page);
        }, function (result) {
            $scope.list = result.items;
        });

        if ($scope.filter.page == 1) {
            var branchFilter = HelperService.copySimpleObject($scope.filter);
            branchFilter.groupByBranch = true;
            HelperService.search('/api/finance/vip-card', branchFilter, null, function (result) {
                $scope.branchList = result.items;
            });
        }
    };

    $scope.export = function () {
        var exportFilter = HelperService.copySimpleObject($scope.filter);
        delete exportFilter.page;
        delete exportFilter.size;
        if ($scope.branchView) {
            exportFilter.groupByBranch = true;
        }
        window.open(HelperService.createSearchUrl("/finance/vip-card/export", exportFilter));
    };

    HelperService.getManagedBranchesAndGroups($scope, function () {
        $scope.search(1);
    });
}]);