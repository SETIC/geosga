(function(){
    "use strict";

    angular.module("app.mapa").controller("mapa.ctrl", ['$scope', 'api.service', MapaController]);

    function MapaController($scope, ApiService){
        var map, marker, geocoder, infowindow;

        $scope.listarMarcadores = function(){
            ApiService.listarMarcadores()
            .then(function(res){
                $scope.marcadores = res.data;
                $scope.gerarMarcadores($scope.marcadores);
            }, null);
        };

        $scope.initMap = function(){
            map = new google.maps.Map(document.getElementById('mapa'), {
                center: {lat: -5.7968718, lng: -35.4148505},
                zoom: 12
            });

            $scope.listarMarcadores();
        }();

        $scope.gerarMarcadores = function(markers){
            for(var i = 0; i < markers.length; i++){
                var marker = new google.maps.Marker({
    		    	position: {lat: parseFloat(markers[i].latitude), lng: parseFloat(markers[i].longitude)},
    			   	map: map,
    		    	title: markers[i].obra.categoria.denominacao,
    		    	animation: google.maps.Animation.DROP,
    				icon: 'dist/img/50x60/ico_' + markers[i].obra.categoria.id +'.png',
    		    	categoria: markers[i].obra.categoria.denominacao
    			});

                (function(marker, i) {
    	           	google.maps.event.addListener(marker, 'mousedown', function() {
                        $scope.marcador = markers[i];
                        console.log($scope.marcador);
                        $("#modal-obra").modal("show");

		                map.panTo({lat: parseFloat(markers[i].latitude), lng: parseFloat(markers[i].longitude)});
                        $scope.$apply();
    	            });
    	        })(marker, i);

    			marker.setMap(map);
            }
        };
    }
})();
