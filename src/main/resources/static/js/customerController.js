app.controller('customerController',['$scope','customerService',function($scope,customerService){
	
	customerService.loadCustomers();
	
	$scope.$on("CUSTOMERS_LOADED", function(){
		$scope.customers = customerService.customers;
	});
}]);
