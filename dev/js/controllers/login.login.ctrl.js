(function(){
    "use strict";

    angular.module("app.login").controller("login.ctrl", LoginController);

    function LoginController($scope, $window, $location, Constants, AutenticacaoService, $cookies){
        var qs = $location.search();
        console.log(qs);

        if(qs.logout == "true")
            AutenticacaoService.logout()
            .then(function(res){
                $.notify({message: "Logout efetuado com sucesso!"}, {type: "success"});
                $cookies.put("credencial", null);
            });

        $scope.login = function(credencial){
            $.notify({message: "Efetuando login."}, {type: "info"});
            AutenticacaoService.login(credencial)
            .then(function(res){
                $cookies.putObject('credencial', res.data.funcionario);
                $window.location = Constants.url;
            }, function(err){
                $.notify({message: "Login ou senha incorretos. Tente novamente."}, {type: "danger"});
            });
        };
    }

    LoginController.$inject = ['$scope', '$window', '$location', 'Constants', 'api.autenticacao.service', '$cookies'];
})();
