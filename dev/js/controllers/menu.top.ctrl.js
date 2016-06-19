(function(){
    "use strict";

    angular.module("app.gerenciador").controller("menu.top.ctrl",  MenuTopController);

    function MenuTopController($scope, $route, $timeout, $cookies, $compile){
        if(!!$cookies.getObject("credencial")){
            $scope.credencial = $cookies.getObject("credencial");
        }
    }

    MenuTopController.$inject = ['$scope', '$route', '$timeout', '$cookies', '$compile'];
})();
