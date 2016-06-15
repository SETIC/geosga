(function(){
    "use strict";

    angular.module("app.login").controller("login.ctrl", ['$scope', '$window', '$location', 'Constants', 'api.autenticacao.service', LoginController]);

    function LoginController($scope, $window, $location, Constants, AutenticacaoService){
        var qs = $location.search();
        console.log(qs);
        if(qs.logout == "true")
            AutenticacaoService.logout()
            .then(function(res){
                console.log(res);
            })

        $scope.login = function(credencial){
            AutenticacaoService.login(credencial)
            .then(function(res){
                $window.location.href = Constants.url;
            }, null);
        };
    }
})();
