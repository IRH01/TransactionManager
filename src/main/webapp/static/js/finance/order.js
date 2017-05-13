app.controller('OrderTransactionController', ['$scope', 'HelperService',
    function ($scope, HelperService) {

        $scope.PAY_TYPES = [
            'CASH',
            'UNI_PAY',
            'ALIPAY',
            'WECHAT',
            'VIPCARD',
            'SIGNED',
            'COMBINED'
        ];

        $scope.PAY_TYPE_NAMES = {
            'CASH': '现金',
            'SIGNED': '签单',
            'ALIPAY': '支付宝',
            'WECHAT': '微信支付',
            'UNI_PAY': '刷卡',
            'VIPCARD': '会员卡',
            'COMBINED': '混合',
            'OTHER': '其他'
        };

        $scope.filter = {size: HelperService.PAGE_SIZE, status: 'COMPLETE'};

        $scope.reset = function () {
            $scope.filter = {size: HelperService.PAGE_SIZE, status: 'COMPLETE'};
            $('#startDate, #endDate').val('');
            $scope.search();
        };

        $scope.search = function (page) {
            $scope.filter.page = page || 1;
            var dateFrom = $('#startDate').val();
            if (dateFrom.length > 0) {
                $scope.filter.createdAtFrom = dateFrom + ' 00:00:00';
            }
            var dateTo = $('#endDate').val();
            if (dateTo.length > 0) {
                $scope.filter.createdAtTo = dateTo + ' 23:59:59';
            }
            HelperService.search('/api/finance/order', $scope.filter, function (page) {
                $scope.search(page);
            }, function (result) {
                console.log(result);
                for (var i in result.items) {
                    var item = result.items[i];
                    item.createdAt = HelperService.formatDate(item.createdAt, true);
                    item.invoice = 0;
                    for (var j in item.paymentRecords) {
                        var record = item.paymentRecords[j];
                        if (record.payType == 'COUPON') {
                            item.coupon = record.amount;
                            break;
                        }
                    }
                    for (j in item.invoiceRecords) {
                        record = item.invoiceRecords[j];
                        item.invoice += record.amount;
                    }
                }
                $scope.list = result.items;
            });
        };

        $scope.export = function () {
            var exportFilter = HelperService.copySimpleObject($scope.filter);
            delete exportFilter.page;
            delete exportFilter.size;
            window.open(HelperService.createSearchUrl("/finance/order/export", exportFilter));
        };

        HelperService.getManagedBranchesAndGroups($scope, function () {
            $scope.search(1);
        });
    }]);