app.controller('RoleController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.filter = {page: 1, size: HelperService.PAGE_SIZE, sortBy: 'id', sortOrder: 'DESC'};

        $scope.reset = function () {
            delete $scope.filter.name;
            $scope.search(1);
        };

        $scope.search = function (page) {
            $scope.filter.page = page || 1;
            HelperService.search('/api/account/role', $scope.filter, function (page) {
                $scope.search(page);
            }, function (result) {
                for (var i in result.items) {
                    setRoleLevelName(result.items[i]);
                }
                $scope.list = result.items;
            });
        };

        $scope.setActivePermissionCategory = function (category) {
            $scope.activePermissionCategory = category;
        };

        $scope.edit = function (id) {
            $scope.candidatePermissions = {};
            $scope.activePermissionCategory = $scope.permissionCategories[0];
            $scope.editForm.$setPristine();
            if (!id) {
                $scope.model = {level: 'HQ'};
                $('.content-box').hide();
                $('.add-content').show();
                return;
            }
            $http.get('/api/account/role/' + id).success(function (result) {
                $scope.model = result.data;
                for (var i in $scope.model.permissions) {
                    $scope.candidatePermissions[$scope.model.permissions[i].id] = true;
                }
                $('.content-box').hide();
                $('.add-content').show();
            });
        };

        $scope.submit = function (continueEdit) {
            var role = $scope.model;
            role.permissions = [];
            for (var pId in $scope.candidatePermissions) {
                if ($scope.candidatePermissions[pId]) {
                    role.permissions.push({id: pId});
                }
            }
            $http.post('/api/account/role/' + (role.id ? 'update' : 'save'), role).success(function (result) {
                if (result.code == '03') {
                    return HelperService.tipErrorMessage($scope,'此职位已存在');
                }
                setRoleLevelName(role);
                if (!role.id) {
                    role.id = result.data;
                    $scope.list.unshift(role);
                    HelperService.tipSuccessMessage($scope,"新增职位成功");
                } else {
                    HelperService.replaceListItem($scope.list, role);
                    HelperService.tipSuccessMessage($scope,"修改职位信息成功");
                }
                if (continueEdit) {
                    return $scope.edit();
                }

                $scope.cancelEdit();
            });
        };

        $scope.search(1);

        $http.get('/api/account/role/permissions').success(function (result) {
            var permissions = {};
            var permissionCategories = [];
            for (var i in result.data) {
                var item = result.data[i];
                if (!permissions[item.category]) {
                    permissions[item.category] = [];
                    permissionCategories.push(item.category);
                }
                permissions[item.category].push(item);
            }
            $scope.permissionCategories = permissionCategories;
            $scope.permissions = permissions;
        });

        function setRoleLevelName(role) {
            if (role.level == 'HQ') {
                role.levelName = '总部';
            } else if (role.level == 'BRANCH_GROUP') {
                role.levelName = '店铺组';
            } else {
                role.levelName = '店铺';
            }
        }
    }]);