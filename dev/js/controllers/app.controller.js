(function(){
    "use strict";

    angular.module("app.gerenciador").controller("app.controller", AppController);

    function AppController($scope, $timeout){
        $scope.$on('LOAD', function(){
            $scope.loading = true;
        });

        $scope.$on('UNLOAD', function(){
            $timeout(function(){
                $scope.loading = false;
            }, 500);
        });
    }

    AppController.$inject = ['$scope', '$timeout'];
})();
