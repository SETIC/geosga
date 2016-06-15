(function(){
    "use strict";

    angular.module("app.geogoncalo").service("api.autenticacao.service", ['$http', 'Constants', AutenticacaoService]);

    function AutenticacaoService($http, Constants){
        this.login = function(credencial){
            return $http.post(Constants.url + 'rest/autenticar/login', credencial);
        };

        this.logout = function(){
            return $http.get(Constants.url + 'rest/autenticar/logout');
        };

        this.autenticar = function(){
            return $http.get(Constants.url + 'rest/autenticar');
        };
    }
})();
