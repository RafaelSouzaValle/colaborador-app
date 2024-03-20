angular.module('colaboradorApp', [])
.controller('MainController', ['$http', function($http) {
    var vm = this;
    vm.baseUrl = 'http://localhost:8080'; // Altere a porta conforme necessário
    
    vm.colaborador = {};
    vm.colaboradores = [];
    vm.cargos = [];
    vm.editMode = false;
    
    // Carregar lista de cargos
    $http.get(vm.baseUrl + '/cargos')
    .then(function(response) {
        vm.cargos = response.data;
    });
    
    // Carregar lista de colaboradores
    function loadColaboradores() {
        $http.get(vm.baseUrl + '/colaboradores')
        .then(function(response) {
            vm.colaboradores = response.data;
        });
    }
    loadColaboradores();
    
    // Submeter novo colaborador ou atualizar colaborador existente
    // Submeter novo colaborador ou atualizar colaborador existente
    vm.submit = function() {

        if (vm.editMode && (!vm.colaborador.senha || vm.colaborador.senha.length < 2)) {
            alert("A senha deve ter pelo menos 2 caracteres.");
            return; // Impede o envio do formulário se a senha for inválida
        }

        if (vm.editMode) {
            console.log("JSON enviado para atualização:", vm.colaborador); // Exibe o JSON enviado para atualização no console
            $http.put(vm.baseUrl + '/colaboradores/' + vm.colaborador.id, vm.colaborador)
            .then(function(response) {
                loadColaboradores();
                vm.cancelEdit();
            });
        } else {
            console.log("JSON enviado para criação:", vm.colaborador); // Exibe o JSON enviado para criação no console
            $http.post(vm.baseUrl + '/colaboradores', vm.colaborador)
            .then(function(response) {
                loadColaboradores();
                vm.colaborador = {};
            });
        }
    };

    
    // Editar colaborador
    vm.edit = function(colaborador) {
        vm.colaborador = angular.copy(colaborador);
        vm.editMode = true;
    };
    
    // Cancelar edição
    vm.cancelEdit = function() {
        vm.colaborador = {};
        vm.editMode = false;
    };
    
    // Deletar colaborador
    vm.delete = function(id) {
        $http.delete(vm.baseUrl + '/colaboradores/' + id)
        .then(function(response) {
            loadColaboradores();
        });
    };
}]);
