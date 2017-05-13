app.controller('LoginController', ['HelperService', '$http', '$scope',
    function (HelperService, $http, $scope) {

        $scope.model = {};

        $scope.login = function () {
            $http.post('/api/account/login', {
                credentialId: $scope.model.credentialId,
                password: md5($scope.model.password),
                hqCode: $scope.model.hqCode
            }).success(function (result) {
                if (!result.data) {
                    $(".login-error").show();
                    return;
                }
                location.href = HelperService.getRequestParam('redirect') || '/';
            });
        };
    }]);