(function(){
    "use strict";

    angular.module("app.gerenciador").controller("menu.top.ctrl", ['$scope', 'api.autenticacao.service', MenuTopController]);

    function MenuTopController($scope, AutenticacaoService){
        // AutenticacaoService.autenticar()
        // .then(function(res){
        //     $scope.credencial = res.data;
        // }, null);
    }
})();
