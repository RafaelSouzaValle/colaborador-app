angular.module('colaboradorApp', [])
.controller('MainController', ['$http', function($http) {
    var vm = this;
    vm.baseUrl = 'http://localhost:8080';
    
    vm.colaborador = {};
    vm.colaboradores = [];
    vm.cargos = [];
    vm.editMode = false;
    vm.passwordStrength = null; // Adicionando variável para armazenar a força da senha
    vm.showPasswordStrength = false
    
    $http.get(vm.baseUrl + '/cargos')
    .then(function(response) {
        vm.cargos = response.data;
    });
    
    function loadColaboradores() {
        $http.get(vm.baseUrl + '/colaboradores')
        .then(function(response) {
            vm.colaboradores = response.data;
        });
    }
    loadColaboradores();

    vm.onPasswordChange = function() {
        vm.showPasswordStrength = vm.colaborador.senha && vm.colaborador.senha.length >= 2;
        if (vm.showPasswordStrength) {
            // Envia uma solicitação HTTP para avaliar a força da senha
            $http.post(vm.baseUrl + '/validate-password', { password: vm.colaborador.senha })
            .then(function(response) {
                vm.passwordStrength = response.data;
            })
            .catch(function(error) {
                console.error('Erro ao avaliar a força da senha:', error);
            });
        } else {
            vm.passwordStrength = null;
        }
    };

    vm.checkPasswordStrength = function() {
        if (vm.colaborador.senha && vm.colaborador.senha.length > 0) {
            // Envia uma solicitação HTTP para avaliar a força da senha
            $http.post(vm.baseUrl + '/validate-password', { password: vm.colaborador.senha })
            .then(function(response) {
                vm.passwordStrength = response.data;
            })
            .catch(function(error) {
                console.error('Erro ao avaliar a força da senha:', error);
            });
        } else {
            vm.passwordStrength = null;
        }
    };
    
    vm.submit = function() {
        if (vm.editMode && (!vm.colaborador.senha || vm.colaborador.senha.length < 2)) {
            alert("A senha deve ter pelo menos 2 caracteres.");
            return;
        }

        if (vm.editMode) {
            console.log("JSON enviado para atualização:", vm.colaborador);
            $http.put(vm.baseUrl + '/colaboradores/' + vm.colaborador.id, vm.colaborador)
            .then(function(response) {
                loadColaboradores();
                vm.cancelEdit();
            });
        } else {
            console.log("JSON enviado para criação:", vm.colaborador);
            $http.post(vm.baseUrl + '/colaboradores', vm.colaborador)
            .then(function(response) {
                loadColaboradores();
                vm.colaborador = {};
            });
        }
    };

    vm.edit = function(colaborador) {
        vm.colaborador = angular.copy(colaborador);
        vm.editMode = true;
        vm.showPasswordStrength = false; // Remover a exibição da força da senha em tempo real
    };
    
    vm.cancelEdit = function() {
        vm.colaborador = {};
        vm.editMode = false;
    };
    
    vm.delete = function(id) {
        $http.delete(vm.baseUrl + '/colaboradores/' + id)
        .then(function(response) {
            loadColaboradores();
        });
    };
}]);
