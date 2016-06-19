(function(){
    "use strict";

    angular.module("app.gerenciador", ['ngRoute', 'app.geogoncalo', 'file-model', 'ui.utils.masks', 'ngCookies'])
    .config(Config)
    .run(Run);

    function Config($routeProvider){
        $routeProvider
            .when("/", {
                templateUrl: 'dist/views/home.html',
                resolve: {
                    credenciais: CredenciaisResolve
                }
            })
            .when("/cadastrar", {
                templateUrl: 'dist/views/cadastro.html',
                controller: "cadastro.ctrl"
            })
            .when("/gerenciar", {
                templateUrl: 'dist/views/gerenciamento.html',
                controller: "gerenciamento.ctrl"
            })
            .when("/editar/:obraId", {
                templateUrl: 'dist/views/editar_obra.html',
                controller: "editar_obra.ctrl"
            })
            .otherwise("/");

        function CredenciaisResolve(AutenticacaoService, $window, Constants, $cookies){
            if(!!!$cookies.getObject("credencial")){
                $window.location.href = Constants.url + "login.html#?logout=true";
            }
        }

        CredenciaisResolve.$inject = ['api.autenticacao.service', '$window', 'Constants', '$cookies'];
    }

    function Run($rootScope, $location, ApiService, $window, $cookies){
		if(!!$cookies.getObject("credencial")){
			greetings($cookies.getObject("credencial"));
		}

		function greetings(usuario){
            console.log(usuario);
			var saudacao = "";
			var hora = new Date().getHours();

			if(hora >= 5 && hora < 13){
				saudacao = "Bom dia ";
			} else if(hora >= 13 && hora < 18){
				saudacao = "Boa tarde ";
			} else {
				saudacao = "Boa noite ";
			}

			$.notify({
				message: saudacao  +'<b>'+ usuario.nome  + "</b>! Tenha um ótimo trabalho."
			},{
				type: 'success',
				animate: {
					enter: 'animated fadeInDown',
					exit: 'animated fadeOutUp'
				},
				icon: 'glyphicon glyphicon-warning-sign',
				delay: 5000
			});
		}
	}

    Config.$inject = ['$routeProvider'];
    Run.$inject = ['$rootScope', '$location', 'api.service', '$window', '$cookies'];
})();
