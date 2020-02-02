var app = angular.module('loginApp', []);
app.controller('loginCtrl', function($scope, $http) {



    $scope.logout =function() {
		      window.location.href = "/saml/logout";
                };

    $scope.logout2 =function() {
             $http({
                method : 'GET',
                withCredentials: true,
                url : '/saml/logout',
                headers: {'Access-Control-Allow-Origin': '*','referer': 'https://appmonitor.transpetro.com.br'} 
                }).then(function successCallback(response) { 
                                      window.location.href = "/saml/logout";
                               },function errorCallback(error) { 
                                      window.location.href = "/saml/logout";
                               });
		   setTimeout(function () {
		      window.location.href = "/saml/logout";
		    }, 2000);
             return true;
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

    
});
