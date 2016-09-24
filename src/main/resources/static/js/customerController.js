app.controller('customerController',['$scope','customerService',function($scope,customerService){
	
	customerService.getCustomers();
	
	$scope.$on("CUSTOMERS_LOADED", function(){
		$scope.customers = customerService.customers;
	});
	
	$scope.newCustomer = function(){
		$scope.customer = {};
		jQuery('#newCustomerModalId').modal('toggle');
	}
	
	$scope.createCustomer = function(){
		customerService.createCustomer($scope.customer);
		jQuery('#newCustomerModalId').modal('hide');
		$scope.customer = {};
	}
	
	$scope.editCustomer = function(id){
		customerService.getCustomer(id, function(customer){
			$scope.customer = customer;
			jQuery('#editCustomerModalId').modal('toggle');	
		});
	}
	
	$scope.updateCustomer = function(){
		customerService.updateCustomer($scope.customer);
		jQuery('#editCustomerModalId').modal('hide');
		$scope.customer = {};
	}
	
	$scope.deleteCustomer = function(id){
		customerService.deleteCustomer(id);
	}
	
}]);
