app.controller('BranchStatController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.keys = ['turnRate', 'sales', 'count', 'average'];

        $scope.keyNames = ['翻桌率', '销售额', '客单数', '单均价'];

        $scope.sortBy = 'turnRate';

        $scope.filter = {/*dateFrom: HelperService.formatDate(new Date().getTime(), false)*/};

        $scope.reset = function () {
            $scope.filter = {};
            $('#startDate, #endDate').val('');
            $scope.search();
        };

        $scope.search = function () {
            $scope.filter.dateFrom = $('#startDate').val();
            $scope.filter.dateTo = $('#endDate').val();
            HelperService.search('/api/statistic/branch', $scope.filter, null, function (result) {
                $scope.list = result;
                updateRanking();
            });
        };

        $scope.setSortBy = function (key) {
            if ($scope.sortBy == key) {
                return;
            }
            $scope.sortBy = key;
            updateRanking();
        };

        HelperService.getManagedBranchesAndGroups($scope, function () {
            $scope.search();
        });

        function updateRanking() {
            var max = null;
            var total = 0;
            for (var i in $scope.list) {
                var item = $scope.list[i];
                if (!max || item[$scope.sortBy] > max) {
                    max = item[$scope.sortBy];
                }
                total += item[$scope.sortBy];
            }
            for (var i in $scope.list) {
                var item = $scope.list[i];
                item.rank = (item[$scope.sortBy] * 100 / max).toFixed(2);
            }
            $scope.list.sort(function (item1, item2) {
                return item2[$scope.sortBy] - item1[$scope.sortBy];
            });
            $scope.average = '';
            if ($scope.list.length > 0) {
                $scope.average = (total / $scope.list.length).toFixed(2);
                if ($scope.sortBy == 'turnRate') {
                    $scope.average += '%';
                }
            }
        }
    }]);