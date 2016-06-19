(function(){
    "use strict";

    angular.module("app.gerenciador").controller("editar_obra.ctrl", EditarObraController);

    function EditarObraController($scope, ApiService, $routeParams, $location){

        $scope.init = function(){
            ApiService.listarTipos()
            .then(function(res){
                $scope.tipos = res.data;
            });

            ApiService.selecionarMarcador($routeParams.obraId)
            .then(function(res){
                $scope.marcador = res.data;
            }, null);

            ApiService.listarCategorias()
            .then(function(res){
                $scope.categorias = res.data;
            });
        }();

        $scope.listarTipos = function(){
            ApiService.listarTipos()
            .then(function(res){
                $scope.tipos = res.data;
            }, null);
        };

        $scope.listarCategoriasPorTipo = function(tipoId){
            ApiService.listarCategoriasPorTipo(tipoId)
            .then(function(res){
                $scope.categorias = res.data;
            }, null);
        };

        $scope.listarStatus = function(){
            ApiService.listarStatus()
            .then(function(res){
                $scope.statusList = res.data;
            }, null);
        }();

        $scope.atualizarObra = function(obra){
            $scope.$emit('LOAD');

            ApiService.atualizarObra(obra)
            .then(function(res){
                $scope.$emit('UNLOAD');
                $.notify({message: 'Obra atualizada com sucesso!'}, {type: 'success'});
                $location.path("/gerenciar");
            }, function(err){
                $scope.$emit('UNLOAD');
                $.notify({message: 'Ocorreu um erro ao atualizar a obra, tente novamente. Se o erro persistir, contacte o administrador do sistema.'}, {type: 'success'});
            });
        };

        $scope.listarTipos();
    }

    EditarObraController.$inject = ['$scope', 'api.service', '$routeParams', '$location'];
})();
