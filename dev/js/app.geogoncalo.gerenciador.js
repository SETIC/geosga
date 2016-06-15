(function(){
    "use strict";

    angular.module("app.gerenciador", ['ngRoute', 'app.geogoncalo', 'file-model', 'ui.utils.masks'])
    .config(['$routeProvider', Config]);

    function Config($routeProvider){
        $routeProvider
            .when("/", {
                templateUrl: 'dist/views/home.html'
            })
            .when("/cadastrar", {
                templateUrl: 'dist/views/cadastro.html',
                controller: "cadastro.ctrl"
            })
            .when("/gerenciar", {
                templateUrl: 'dist/views/gerenciamento.html',
                controller: "gerenciamento.ctrl"
            })
            .otherwise("/");

        function CredenciaisResolve(AutenticacaoService, $window, Constants){
            AutenticacaoService.autenticar()
            .then(function(res){
                return res.data;
            }, function(err){
                $window.location.href = Constants.url + "login.html#?logout=true";
            });
        }
    }
})();
