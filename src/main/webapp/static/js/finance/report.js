app.controller('FinancialReportController', ['$scope', 'HelperService',
    function ($scope, HelperService) {

        $scope.branchView = false;

        $scope.filter = {
            dateFrom: HelperService.formatDate(new Date().getTime() - 86400000 * 365, true),
            dateTo: HelperService.formatDate(new Date().getTime(), true),
            size: HelperService.PAGE_SIZE
        };

        $scope.setBranchView = function (branchView) {
            if ($scope.branchView != branchView) {
                $scope.branchView = branchView;
            }
        };

        $scope.reset = function () {
            $scope.filter = {
                dateFrom: HelperService.formatDate(new Date().getTime() - 86400000 * 365, true),
                dateTo: HelperService.formatDate(new Date().getTime(), true)
            };
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
            HelperService.search('/api/finance/report', $scope.filter, function (page) {
                $scope.search(page);
            }, function (result) {
                for (var i in result.items) {
                    var item = result.items[i];
                    item.date = HelperService.formatDate(item.date, true);
                }
                $scope.list = result.items;
            });

            if ($scope.filter.page == 1) {
                var branchFilter = HelperService.copySimpleObject($scope.filter);
                branchFilter.groupByBranch = true;
                HelperService.search('/api/finance/report', branchFilter, null, function (result) {
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
            window.open(HelperService.createSearchUrl("/finance/report/export", exportFilter));
        };

        HelperService.getManagedBranchesAndGroups($scope, function () {
            $scope.search(1);
        });
    }]);