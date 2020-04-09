var app = angular.module('loginApp', []);
app.controller('loginCtrl', function($scope, $http) {



    $scope.logout =function() {
		      window.location.href = "/saml/logout";
                };


    function getLogin(element) {
            element.user = '';
            //element.url = '#';
// https://appmonitor.transpetro.com.br/saml/logout
//// headers: {'Access-Control-Allow-Origin': '*'} 
             $http({
                method : 'GET',
                withCredentials: true,
                url : '/hasLogged'
                }).then(function successCallback(response) {
                                   element.user = response.data.user;
                               },function errorCallback(error) {
                                   element.user = '';
                               });
            return true;
    };


    getLogin($scope);

    $scope.setModal = function() {
        $http({
                method : 'GET',
                withCredentials: true,
                url : '/details'
                }).then(function successCallback(response) {
                                   $scope.userdetail = response.data;
                               },function errorCallback(error) {
                                   $scope.userdetail = '';
                               });

//	$scope.userdetail  = JSON.parse('{"email":"luis.teste@teste", "fullName":"Teste 123"}');
	$('#updateModal').modal('show');
    };
    
});
