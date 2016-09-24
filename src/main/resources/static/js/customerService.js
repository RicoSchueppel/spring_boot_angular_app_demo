app.service('customerService',['$http','$rootScope',function($http,$rootScope){
	
	var self = this;
	
	self.customer = [];
	
	this.loadCustomers = function(successCallback, errorCallback){
		$http({
			method: "GET",
			url: "/customers",
			headers: {'Accept': 'application/json'},
		}).then(
		function(resp){
			self.customers = resp.data;
			if (typeof successCallback !== 'undefined') successCallback(resp);
			$rootScope.$broadcast("CUSTOMERS_LOADED");
			},
		function(resp){
			console.log("ERROR while trying to request /customers", resp);
			if (typeof errorCallback !== 'undefined') errorCallback(resp);
			}
		);
	};
}]);