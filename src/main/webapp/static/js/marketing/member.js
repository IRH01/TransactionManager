/**
 * Created by iritchie on 2016/5/23 0023.
 */
app.controller("MemberController", ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {
        var submitting = false;

        $scope.statusName = {
            ACTIVE: "正常",
            FROZEN: "已冻结",
            DISABLED: "已停用"
        };

        $scope.filter = {page: 1, size: HelperService.PAGE_SIZE, sortBy: 'id', sortOrder: 'DESC'};

        HelperService.getManagedBranchesAndGroups($scope, function () {
            $scope.search(1);
        });

        $scope.search = function (page) {
            $scope.filter.page = page || 1;
            HelperService.search("/api/marketing/member/", $scope.filter, function (page) {
                $scope.search(page);
            }, function (result) {
                $scope.activeCount = result.activeCount;
                $scope.frozenCount = result.frozenCount;
                $scope.disabledCount = result.disableCount;
                $scope.list = result.search.items;
            });
        };

        $scope.reset = function () {
            $scope.filter = {page: 1, size: HelperService.PAGE_SIZE, sortBy: 'id', sortOrder: 'DESC'};
            $scope.search(1);
        }

        $scope.editForm = {};

        $scope.edit = function (id) {
            if (submitting) {
                return;
            }
            submitting = true;
            $scope.editForm.$setPristine();
            if (!id) {
                $('.content-box').hide();
                $('.add-content').show();
                submitting = false;
                return;
            } else {
                $http.get('/api/marketing/member/' + id).success(function (result) {
                    $scope.model = result.data;
                    $("")
                    $('.content-box').hide();
                    $('.add-content').show();
                    submitting = false;
                });
            }
        };

        $scope.submit = function (continueEdit) {
            if (submitting) {
                return;
            }
            submitting = true;
            var vipCard = $scope.model;
            console.log(vipCard);
            $http.post('/api/marketing/member/' + (vipCard.id ? 'update' : 'save'), vipCard).success(function (result) {
                if (result.code == '03') {
                    return HelperService.tipErrorMessage($scope, '此职位已存在');
                }
                submitting = false;
                if (!vipCard.id) {
                    vipCard.id = result.data;
                    $scope.list.unshift(vipCard);
                    HelperService.tipSuccessMessage($scope, "新增会员卡成功");
                } else {
                    HelperService.replaceListItem($scope.list, vipCard);
                    HelperService.tipSuccessMessage($scope, "修改会员卡信息成功");
                }
                if (continueEdit) {
                    return $scope.edit();
                }

                $scope.cancelEdit();
            }).error(function () {
                submitting = false;
            });
        };

        $scope.getDate = function () {
            laydate({
                max: laydate.now(), //最大日期
                format: 'YYYY-MM-DD',
                choose: function (datas) {
                    $scope.model.birthDate = datas;
                }
            })
        };


        $scope.changeStatus = function (vipCard, status) {
            if (submitting) {
                return;
            }
            submitting = true;
            if (confirm('是否确认' + (status ? '解冻' : '冻结') + '此会员卡？')) {
                branch.status = status;
                $http.post('/api/marketing/member/', branch).error(function () {
                    branch.status = !status;
                    submitting = false;
                }).success(function () {
                    submitting = false;
                    if (enabled) {
                        HelperService.tipSuccessMessage($scope, "启用会员卡 " + vipCard.name + " 成功");
                    } else {
                        HelperService.tipSuccessMessage($scope, "停用会员卡 " + vipCard.name + " 成功");
                    }
                });
            }
        };
    }]);
