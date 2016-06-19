(function(){
    "use strict";

    angular.module("app.gerenciador").controller("menu.ctrl", ['$scope', MenuController]);

    function MenuController($scope){        
        $scope.itens = [
            {
                link: '#/',
                nome: 'Inicio',
                icone: 'fa fa-home'
            },
            {
                link: '#/gerenciar',
                nome: 'Gerenciar obras',
                icone: 'fa fa-list'
            },
            {
                link: '#/cadastrar',
                nome: 'Cadastrar Obras',
                icone: 'fa fa-plus'
            }
        ];
    }
})();
