var cartApp = angular.module('cartApp', []);
 
cartApp.controller('cartCtrl',  function ($scope, $http) {
	
	$scope.refreshCart = function(cartId) {
		  						$http.get('/rest/onlineCart/'+$scope.cartId)
		  						 	 .success(function(data) {
		  						 		 		$scope.cart = data;
		  						 	 		});
		  					};
		  					
	$scope.clearCart = function() {
		  						$http.delete('/rest/onlineCart/'+$scope.cartId)
		  							 .success($scope.refreshCart($scope.cartId));
		  						
	  					  };
	  					  
	$scope.initCartId = function(cartId) {
		  					$scope.cartId=cartId;
		  					$scope.refreshCart($scope.cartId);
	  						};
	  						
	  $scope.addToCart = function(productId) {
		  						$http.put('/rest/onlineCart/add/'+productId)
		  						 	 .success(function(data) {
		  						 		 		$scope.refreshCart($http.get('/rest/onlineCart/get/cartId'));
		  						 		 		alert("Produkt pomyślnie dodany do koszyka!");
		  						 	 		});
		  					};
	  $scope.removeFromCart = function(productId) {
			  						$http.put('/rest/onlineCart/remove/'+productId)
			  						 	 .success(function(data) {
			  						 		 	$scope.refreshCart($http.get('/rest/onlineCart/get/cartId'));
			  						 	 		});
			  					};
	  });