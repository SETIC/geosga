(function(){
    "use strict";

    angular.module("app.gerenciador").controller("gerenciamento.ctrl", ['$scope', 'api.service', '$route', GerenciamentoController]);

    function GerenciamentoController($scope, ApiService, $routes){
        $scope.$emit('UNLOAD');
        $scope.listarMarcadores = function(){
            $scope.$emit('LOAD');

            ApiService.listarMarcadores()
            .then(function(res){
                $scope.marcadores = res.data;
                $scope.$emit('UNLOAD');
            }, function(err){
                $scope.$emit('UNLOAD');
                $.notify({message: 'Ocorreu um erro ao listar as obras, tente novamente. Se o erro persistir, contact o administrador do sistema.'}, {type: "warning"});
            });
        };

        $scope.deletarMarcador = function(id){
            $scope.$emit('LOAD');

            ApiService.deletarMarcador(id)
            .then(function(res){
                $.notify({message: "A obra foi deletada com sucesso!"}, {type: 'success'});
                $scope.listarMarcadores();
            }, function(err){
                $.notify({message: "Ocorreu um erro ao deletar a obra, tente novamente. Se o erro persistir, contacte o administrador do sistema."}, {type: "warning"});
                $scope.$emit('UNLOAD');
            });
        };

        $scope.listarStatus = function(){
            ApiService.listarStatus()
            .then(function(res){
                $scope.statusList = res.data;
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

        $scope.atualizarStatusObra = function(statusId, obraId){
            $scope.$emit('LOAD');

            ApiService.atualizarStatusObra(statusId, obraId)
            .then(function(res){
                $.notify({message: "O status da obra foi atualizado com sucesso!"}, {type: 'success'});
                $scope.$emit('UNLOAD');
            }, function(err){
                $.notify({message: "Ocorreu um erro ao atualizar o status da obra, tente novamente. Se o erro persistir, contacte o administrador do sistema."}, {type: 'warning'});
                $scope.$emit('UNLOAD');
            });
        };

        $scope.listarStatus = function(){
            ApiService.listarStatus()
            .then(function(res){
                $scope.status = res.data;
            });
        }();

        $scope.listarMarcadores();
    }
})();
