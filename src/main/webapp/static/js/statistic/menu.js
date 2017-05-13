app.controller("ProductSalesStatisticProductController", ["HelperService", '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.filter = {};

        $scope.categoryfilter = {};

        $scope.productfilter = {};

        $scope.navKey = 'product';

        $scope.navKeys = ['product', 'category'];

        $scope.navKeyNames = {product: '单品', category: '分类'};

        $scope.keys = ['orderRate', 'sales', 'count'];

        $scope.keyNames = ['点选率', '销售额', '销售量'];

        $scope.sortBy = 'orderRate';

        $scope.setNavKey = function (key) {
            if ($scope.navKey == key) {
                return;
            }
            hideMoreSelect();
            $scope[$scope.navKey + 'filter'] = angular.copy($scope.filter);
            $scope.navKey = key;
            $scope.filter = angular.copy($scope[$scope.navKey + 'filter']);
            //layDays兼容性处理 start
            $("#startDate").val($scope.filter.dateFrom);
            $("#endDate").val($scope.filter.dateTo);
            start.max = $scope.filter.dateTo;
            end.min = $scope.filter.dateFrom;
            //layDays兼容性处理 end
            $scope.search();
        };

        function hideMoreSelect() {
            $('.sai-link').text('↓显示更多选项');
            $('.search-w').addClass('hide');
        }

        $scope.getCategoryNameById = function (id) {
            if (!id) {
                return '';
            }
            var list = $scope.filterCategories;
            for (var i in list) {
                if (id == list[i].id) {
                    return name;
                }
            }
            return '';

        }

        HelperService.getManagedBranchesAndGroups($scope, function () {
            $scope.search();
            $scope.getCategories();
        });

        $scope.search = function () {
            HelperService.search('/api/statistic/menu/' + $scope.navKey, $scope.filter, function () {
                $scope.search();
            }, function (result) {
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
                if ($scope.sortBy == 'orderRate') {
                    $scope.average += '%';
                }
            }
        };

        $scope.getCategories = function () {
            $http.get("/api/product/category").success(function (result) {
                $scope.filterCategories = result.data.items;
            });
        };

        $scope.reset = function () {
            $scope[$scope.navKey + 'Filter'] = {};
            $scope.filter = {};
            $scope.search();
        };

        $scope.getSalesSummaryById = function (id) {
            if (!id) {
                return;
            }
            var list = $scope.list;
            for (var i in list) {
                if (list[i][$scope.navKey].id == id) {
                    return list[i];
                }
            }
        };

        $scope.getDetails = function (item) {
            $http.get("/api/statistic/menu/" + $scope.navKey + "/detail/" + item.id, $scope.filter).success(function (result) {
                $scope.details = result.data;
                $('.model-list-box').hide();
                $('.add-content').show();

                showCharts(result.data);
            });
        };

        function showCharts(data) {
            $scope.showStatisticByServiceType(data['serviceTypeDetails']);

            $scope.showStatisticByProduct(data['optionDetails']);

            $scope.showStatisticSalesByDate(data['dailySummaries']);

            $scope.showStatisticCountByDate(data['dailySummaries']);

            $scope.showStatisticOrderRateByDate(data['dailySummaries']);
        }

        var colors = ['#2faf47', '#2d5163', '#507a8f', '#1c93ce', '#9dc6af', '#57db5a'];

        var LABELS = {
            serviceType: {
                RESTAURANT: '堂食',
                PACKAGE: '外带',
                SELF_SERVICE: '自取',
                DELIVERY: '外送'
            },
        };

        $scope.showStatisticByServiceType = function (list) {
            $("#graph1").empty();

            var textArray = [];
            for (var i in list) {
                var data = {};
                data.label = LABELS['serviceType'][list[i].key];
                data.value = list[i].sales;
                textArray.push(data);
            }

            Morris.Donut({
                element: 'graph1',
                data: textArray,
                backgroundColor: '#fff',
                labelColor: '#17181a',
                colors: colors
            });
        };

        $scope.showStatisticByProduct = function (list) {
            $("#graph2").empty();

            var textArray = [];
            for (var i in list) {
                var data = {};
                data.label = list[i]['key'];
                data.value = list[i]['sales'];
                textArray.push(data);
            }

            Morris.Donut({
                element: 'graph2',
                data: textArray,
                backgroundColor: '#fff',
                labelColor: '#17181a',
                colors: colors,
            });
        };

        $scope.showStatisticSalesByDate = function (list) {
            $("#graph4").empty();

            var textArray = [];
            for (var i in list) {
                var data = {};
                data.period = list[i]['date'];
                data.sorned = list[i]['sales'];
                textArray.push(data);
            }

            Morris.Area({
                element: 'graph4',
                behaveLikeLine: true,
                fillOpacity: 0.1,
                hideHover: 'auto',
                pointFillColors: ['#fff'],
                pointStrokeColors: ['#1c93ce'],
                lineColors: ['#1c93ce', '#1c93ce'],
                dateFormat: function (timestamp) {
                    return HelperService.formatDate(timestamp, true);
                },
                data: textArray,
                xLabels: 'day',
                xkey: 'period',
                ykeys: ['sorned'],
                labels: ['金额']
            });
        };

        $scope.showStatisticCountByDate = function (list) {
            $("#graph5").empty();

            var textArray = [];
            for (var i in list) {
                var data = {};
                data.period = list[i]['date'];
                data.sorned = list[i]['count'];
                textArray.push(data);
            }

            Morris.Area({
                element: 'graph5',
                behaveLikeLine: true,
                fillOpacity: 0.1,
                hideHover: 'auto',
                pointFillColors: ['#fff'],
                pointStrokeColors: ['#1c93ce'],
                lineColors: ['#1c93ce', '#1c93ce'],
                dateFormat: function (timestamp) {
                    return HelperService.formatDate(timestamp, true);
                },
                data: textArray,
                xLabels: 'day',
                xkey: 'period',
                ykeys: ['sorned'],
                labels: ['销量']
            });
        };

        $scope.showStatisticOrderRateByDate = function (list) {
            $("#graph6").empty();
            var textArray = [];
            for (var i in list) {
                var data = {};
                data.period = list[i]['date'];
                data.sorned = list[i]['orderRate'];
                textArray.push(data);
            }

            Morris.Area({
                element: 'graph6',
                behaveLikeLine: true,
                fillOpacity: 0.1,
                hideHover: 'auto',
                pointFillColors: ['#fff'],
                pointStrokeColors: ['#1c93ce'],
                lineColors: ['#1c93ce', '#1c93ce'],
                dateFormat: function (timestamp) {
                    return HelperService.formatDate(timestamp, true);
                },
                data: textArray,
                xLabels: 'day',
                xkey: 'period',
                yLabelFormat: function (value) {
                    return value + '%';
                },
                ykeys: ['sorned'],
                labels: ['点选率']
            });
        };

        //日期选择
        laydate.skin('danlan');//切换皮肤

        var start = {
            max: laydate.now(), //最大日期
            choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas; //将结束日的初始值设定为开始日
                $scope.filter.dateFrom = datas;
            }
        };

        var end = {
            min: laydate.now(),
            max: laydate.now(),
            choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
                $scope.filter.dateTo = datas;
            }
        };

        $scope.getDate = function (point) {
            if (point == 'from') {
                laydate(start);
            } else if (point == 'to') {
                laydate(end);
            }
        }
    }]);
