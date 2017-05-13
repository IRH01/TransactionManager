app.controller('TotalStatController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        var LABELS = {
            serviceType: {
                RESTAURANT: '堂食',
                PACKAGE: '外带',
                SELF_SERVICE: '自取',
                DELIVERY: '外送'
            },
            platform: {
                POS: 'POS',
                IPAD: 'iPad',
                APP: 'App',
                WECHAT: '微信',
                PC: 'PC'
            },
            payType: {
                CASH: '现金',
                'UNI_PAY': '刷卡',
                ALIPAY: '支付宝',
                WECHAT: '微信',
                VIPCARD: '会员卡',
                SIGNED: '签单',
                COUPON: '现金券'
            }
        };

        var WEEK_DAYS = ['日', '一', '二', '三', '四', '五', '六'];

        var charts = {};

        $scope.keys = ['sales', 'count', 'average', 'turnRate'];

        $scope.key = 'sales';

        $scope.keyNames = {
            sales: '营业额',
            count: '客单数',
            average: '单均价',
            turnRate: '翻桌率'
        };

        $scope.filter = {};

        $scope.setKey = function (key) {
            if ($scope.key == key) {
                return;
            }
            $scope.key = key;
            renderCharts();
        };

        $scope.reset = function () {
            $scope.filter = {};
            $('#startDate, #endDate').val('');
            $scope.search();
        };

        $scope.search = function () {
            $scope.filter.dateFrom = $('#startDate').val();
            $scope.filter.dateTo = $('#endDate').val();
            HelperService.search('/api/statistic/total', $scope.filter, null, function (result) {
                var dataByDate = {};
                var dataByHour = {};
                var dataByWeekday = {};
                var average = {
                    sales: new BigDecimal('0'),
                    count: new BigDecimal('0'),
                    turnRate: new BigDecimal('0'),
                    average: new BigDecimal('0')
                };
                var total = {
                    sales: new BigDecimal('0'),
                    count: 0
                };
                if (result.hourlyDetails) {
                    for (var i in result.hourlyDetails) {
                        var item = result.hourlyDetails[i];
                        item.weekday = new Date(item.key).getDay();
                        setDateItem(dataByDate, 'key', item);
                        setDateItem(dataByHour, 'hour', item);
                        setDateItem(dataByWeekday, 'weekday', item);
                        for (var i in $scope.keys) {
                            var key = $scope.keys[i];
                            var value = item[key];
                            if (value) {
                                var bigDecimalValue = new BigDecimal(value.toString());
                                if (key == 'sales') {
                                    total.sales = total.sales.add(bigDecimalValue);
                                } else if (key == 'count') {
                                    total.count += value;
                                }
                                average[key] = average[key].add(bigDecimalValue);
                            }
                        }
                    }
                }

                $scope.total = total;
                $scope.average = average;

                $scope.model = {
                    dataByServiceType: result.serviceTypeDetails || [],
                    dataByPlatform: result.platformDetails || [],
                    dataByPayType: result.payTypeDetails || [],
                    dataByDate: [],
                    dataByHour: [],
                    dataByWeekday: []
                };

                setModelDateData(dataByDate, 'dataByDate');
                setModelDateData(dataByHour, 'dataByHour');
                setModelDateData(dataByWeekday, 'dataByWeekday');
                $scope.model.dataByDate.sort(function (item1, item2) {
                    return new Date(item2.key).getTime() - new Date(item1.key).getTime();
                });
                $scope.model.dataByHour.sort(function (item1, item2) {
                    return item1.key - item2.key;
                });

                if ($scope.model.dataByDate.length > 0) {
                    for (var key in average) {
                        average[key] = average[key].divide(new BigDecimal(
                            $scope.model.dataByDate.length.toString())).setScale(2, 0).toString();
                    }
                }

                renderCharts();
            });
        };

        $scope.getAverage = function () {
            if (!$scope.model) {
                return '';
            }
            var average = new BigDecimal('0');
            for (var i in $scope.model.dataByDate) {
                var value = $scope.model.dataByDate[i][$scope.key];
                if (value) {
                    average = average.add(new BigDecimal(value.toString()).setScale(2, 0));
                }
            }
            return average.divide(new BigDecimal($scope.model.dataByDate.length.toString())).toString();
        };

        HelperService.getManagedBranchesAndGroups($scope, function () {
            $scope.search();
        });

        function renderCharts() {
            renderDonut($scope.model.dataByServiceType, 'serviceType');
            renderDonut($scope.model.dataByPlatform, 'platform');
            renderDonut($scope.model.dataByPayType, 'payType');
            renderArea($scope.model.dataByDate, 'date', {
                xLabels: 'day',
                dateFormat: function (timestamp) {
                    return HelperService.formatDate(timestamp, true);
                }
            });
            renderBar($scope.model.dataByWeekday);
            renderArea($scope.model.dataByHour, 'hour', {
                parseTime: false,
                xLabelFormat: function (item) {
                    return item.label + '点';
                }
            });
        }

        function renderDonut(source, chartKey) {
            var data = [];
            for (var i in source) {
                var item = source[i];
                if (item[$scope.key]) {
                    data.push({value: item[$scope.key], label: LABELS[chartKey][item.key]});
                }
            }
            if (charts[chartKey]) {
                charts[chartKey].setData(data);
            } else {
                charts[chartKey] = Morris.Donut({
                    element: 'chart-' + chartKey,
                    data: data,
                    backgroundColor: '#fff',
                    labelColor: '#17181a',
                    colors: [
                        '#57cfdb',
                        '#2d5163',
                        '#2faf47',
                        '#1c93ce',
                        '#9dc6af'
                    ],
                    formatter: function (value) {
                        return formatValue(value);
                    }
                });
            }
        }

        function renderArea(source, chartKey, option) {
            var data = [];
            for (var i in source) {
                var item = source[i];
                data.push({value: item[$scope.key], label: item.key});
            }
            if (charts[chartKey]) {
                charts[chartKey].options.labels = [$scope.keyNames[$scope.key]];
                charts[chartKey].setData(data);
            } else {
                var defaultOption = {
                    element: 'chart-' + chartKey,
                    behaveLikeLine: true,
                    fillOpacity: 0.1,
                    hideHover: 'auto',
                    pointFillColors: ['#fff'],
                    pointStrokeColors: ['#1c93ce'],
                    lineColors: ['#1c93ce', '#1c93ce'],
                    data: data,
                    xkey: 'label',
                    ykeys: ['value'],
                    labels: [$scope.keyNames[$scope.key]],
                    yLabelFormat: function (value) {
                        return formatValue(value);
                    }
                };
                if (option) {
                    for (var key in option) {
                        defaultOption[key] = option[key];
                    }
                }
                charts[chartKey] = Morris.Area(defaultOption);
            }
        }

        function renderBar(source) {
            var data = [];
            for (var i in source) {
                var item = source[i];
                data.push({value: item[$scope.key], label: '星期' + WEEK_DAYS[item.key]});
            }
            if (charts.weekday) {
                charts.weekday.options.labels = [$scope.keyNames[$scope.key]];
                charts.weekday.setData(data);
            } else {
                charts.weekday = Morris.Bar({
                    element: 'chart-weekday',
                    data: data,
                    xkey: 'label',
                    ykeys: ['value'],
                    labels: [$scope.keyNames[$scope.key]],
                    lineColors: ['#1c93ce'],
                    xLabelAngle: 30,
                    hideHover: 'auto'
                });
            }
        }

        function setDateItem(dataObj, key, sourceItem) {
            var keyValue = sourceItem[key];
            if (!dataObj[keyValue]) {
                dataObj[keyValue] = {
                    key: keyValue,
                    sales: new BigDecimal('0'),
                    count: 0
                };
            }
            var dataItem = dataObj[keyValue];
            dataItem.sales = dataItem.sales.add(new BigDecimal(sourceItem.sales.toString()).setScale(2, 0));
            dataItem.count += sourceItem.count;
            if (sourceItem.average) {
                if (dataItem.average == null) {
                    dataItem.average = sourceItem.average;
                } else {
                    dataItem.average = (dataItem.average + sourceItem.average) / 2;
                }
            }
            if (sourceItem.turnRate) {
                if (dataItem.turnRate == null) {
                    dataItem.turnRate = sourceItem.turnRate;
                } else {
                    dataItem.turnRate = (dataItem.turnRate + sourceItem.turnRate) / 2;
                }
            }
        }

        function setModelDateData(dataObj, modelKey) {
            for (var key in dataObj) {
                var dataItem = dataObj[key];
                $scope.model[modelKey].push({
                    key: dataItem.key,
                    sales: dataItem.sales.toString(),
                    count: dataItem.count,
                    turnRate: dataItem.turnRate ? dataItem.turnRate.toFixed(2) : 0,
                    average: dataItem.average ? dataItem.average.toFixed(2) : 0
                });
            }
        }

        function formatValue(value) {
            if ($scope.key == 'turnRate') {
                return value + '%';
            } else if ($scope.key != 'count') {
                return '¥ ' + value;
            }
            return value;
        }
    }]);