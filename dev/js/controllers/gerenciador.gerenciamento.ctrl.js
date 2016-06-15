(function(){
    "use strict";

    angular.module("app.gerenciador").controller("gerenciamento.ctrl", ['$scope', 'api.service', '$route', GerenciamentoController]);

    function GerenciamentoController($scope, ApiService, $routes){
        console.log($routes);

        $scope.listarMarcadores = function(){
            ApiService.listarMarcadores()
            .then(function(res){
                console.log(res);
                $scope.marcadores = res.data;
            }, null);
        };

        $scope.editar = function(marcador){
            $scope.obraEdit = angular.copy(marcador.obra);
            $("#modal-editar").modal("show");
            $scope.listarCategoriasPorTipo(obra.tipo.id);
        };

        $scope.editarObra = function(obra){
            ApiService.atualizarObra(obra)
            .then(function(res){
                console.log(res);
            }, null);
        };

        $scope.deletarMarcador = function(id){
            ApiService.deletarMarcador(id)
            .then(function(res){
                console.log(res);
                $scope.listarMarcadores();
            }, null);
        };

        $scope.listarStatus = function(){
            ApiService.listarStatus()
            .then(function(res){
                $scope.statusList = res.data;
                console.log(res);
            }, null);
        }();

        $scope.listarTipos = function(){
            ApiService.listarTipos()
            .then(function(res){
                $scope.tipos = res.data;
            }, null);
        }();

        $scope.listarCategoriasPorTipo = function(tipoId){
            ApiService.listarCategoriasPorTipo(tipoId)
            .then(function(res){
                $scope.categorias = res.data;
            }, null);
        };

        $scope.listarStatus = function(){
            ApiService.listarStatus()
            .then(function(res){
                $scope.status = res.data;
            }, null);
        }();

        $scope.listarMarcadores();
    }
})();
