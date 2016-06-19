(function(){
    "use strict";

    angular.module("app.geogoncalo").service("api.service", ['$http', 'Constants', ApiService]);

    function ApiService($http, Constants){

        // --- Inicio api marcador --- //

        this.listarMarcadores = function(marcador){
            return $http.get(Constants.url + 'rest/marcadores');
        };

        this.listarMarcadoresPorCategoria = function(id){
            return $http.get(Constants.url + 'rest/marcadores/categoria/' + id);
        };

        this.selecionarMarcador = function(id){
            return $http.get(Constants.url + 'rest/marcador/' + id);
        };

        this.cadastrarMarcador = function(marcador){
            return $http.post(Constants.url + 'rest/marcadores', marcador);
        };

        this.atuarlizarMarcador = function(id, marcador){
            return $http.put(Constants.url + 'rest/marcador/' + id, marcador);
        };

        this.deletarMarcador = function(id){
            return $http.delete(Constants.url + 'rest/marcador/' + id);
        };

        // --- Fim api marcador --- //

        // --- Inicio api tipo --- //

        this.listarTipos = function(){
            return $http.get(Constants.url + 'rest/tipos');
        };

        // --- Fim api tipo --- //

        // --- Inicio api categorias --- //

        this.listarCategorias = function(){
            return $http.get(Constants.url + 'rest/categorias');
        };

        this.listarCategoriasPorTipo = function(tipoId){
            return $http.get(Constants.url + 'rest/categoria/tipo/' + tipoId);
        };

        // --- Fim api tipo --- //

        this.selecionarObra = function(id){
            return $http.get(Constants.url + 'rest/obra/' + id);
        };

        this.listarObras = function(){
            return $http.get(Constants.url + 'rest/obras');
        };

        this.atualizarObra = function(obra){
            return $http.put(Constants.url + 'rest/obra/' + obra.id, obra);
        };

        this.atualizarStatusObra = function(obraId, statusId){
            return $http.put(Constants.url + 'rest/obra/' + obraId + '/status/' + statusId);
        };

        this.listarObrasRelacionadas = function(obraId, categoriaId){
            return $http.get(Constants.url + 'rest/obra/' + obraId + '/categoria/' + categoriaId + '/relacionados');
        };

        // --- Inicio api status --- //

        this.listarStatus = function(){
            return $http.get(Constants.url + 'rest/status');
        };

        this.uploadImagem = function(arquivo, obraId){
            var fd = new FormData();
    		fd.append('file', arquivo);
    		fd.append('obraId', obraId);

            return $http.post(Constants.url + 'rest/arquivo/upload/' + obraId, fd, {
    			transformRequest: angular.identity,
    			headers: {
    				'Content-Type': undefined
    			}
    		});
        };
    }
})();
