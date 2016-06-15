(function(){
    "use strict";

    angular.module("app.gerenciador").controller("cadastro.ctrl", ['$scope', 'api.service', '$compile', 'api.autenticacao.service', '$window', 'Constants', CadastroController]);

    function CadastroController($scope, ApiService, $compile, AutenticacaoService, $window, Constants){
        var map, marker, geocoder, infowindow;

        $scope.marcador = {};
        $scope.marcador.obra = {};

        // AutenticacaoService.autenticar()
        // .then(function(res){
        //     $scope.credencial = res.data;
        // }, function(err){
        //     if(err.status == 401)
        //         $window.location.href = Constants.url + '/login.html';
        // });

        $scope.cadastrarMarcador = function(marcador){
            var file = $scope.myFile;

            ApiService.cadastrarMarcador(marcador)
            .then(function(res){
                $scope.marcador = {};

                if(!!file){
                    ApiService.uploadImagem(file, res.data.obra.id)
                    .then(function(res){
                        console.log(res);
                    }, null);
                }

            }, null);
        };

        $scope.listarTipos = function(){
            ApiService.listarTipos()
            .then(function(res){
                $scope.tipos = res.data;
            }, null);
        };

        $scope.setCategorias = function(categorias){
            $scope.categorias = angular.copy(categorias);
            console.log($scope.categorias);
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
                $scope.status = res.data;
            }, null);
        }();

        $scope.modalCadastro = function(){
            $("#modal-cadastrar").modal("show");
        };

        $scope.initMap = function(){
            map = new google.maps.Map(document.getElementById('mapa'), {
                center: {lat: -5.7968718, lng: -35.4148505},
                zoom: 12
            });

            geocoder = new google.maps.Geocoder();

            marker = new google.maps.Marker({
                map: map,
            });

            google.maps.event.addListener(map,"click", function (event) {
                $scope.marcador.latitude = event.latLng.lat();
                $scope.marcador.longitude = event.latLng.lng();

                var latlng = new google.maps.LatLng($scope.marcador.latitude, $scope.marcador.longitude);

                map.panTo(latlng);
                marker.setPosition(latlng);

                geocoder.geocode({'location': latlng}, function(results, status) {
                    if (status === google.maps.GeocoderStatus.OK) {
                        if(!!results[0]){
                            $scope.marcador.obra.rua = results[0].formatted_address;
                        }

                        var content = '<div.content><h4>' + results[0].formatted_address + '</h4>' +
                                      '<button class="btn btn-primary" ng-click="modalCadastro()">Cadastrar obra</button></div>';

                        var compiled = $compile(content)($scope);
                        console.log(compiled);

                        infowindow = new google.maps.InfoWindow({
                            content: compiled[0]
                        });

                        infowindow.open(map, marker);
                    }
                });

                // $scope.$apply();
                // $scope.modalCadastro();
            });
        }();

        $scope.listarTipos();
    }
})();
