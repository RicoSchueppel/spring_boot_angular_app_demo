app.service('customerService',['$http','$rootScope',function($http,$rootScope){
	
	var self = this;
	
	self.customer = [];
	
	this.getCustomers = function(successCallback, errorCallback){
		$http({
			method: "GET",
			url: "/api/customers/",
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
	
	this.getCustomer = function(id, successCallback, errorCallback){
		$http({
			method: "GET",
			url: "/api/customers/" + id,
			headers: {'Accept': 'application/json'},
		}).then(
		function(resp){
			if (typeof successCallback !== 'undefined') successCallback(resp.data);
			},
		function(resp){
			console.log("ERROR while trying to request /customers", resp);
			if (typeof errorCallback !== 'undefined') errorCallback(resp);
			}
		);
	};
	
	this.createCustomer = function(customer){
		$http({
			method: "POST",
			url: "/api/customers/?" + jQuery.param(customer),
			headers: {'Accept': 'application/json'},
		}).then(
		function(resp){
			self.getCustomers();
			},
		function(resp){
			console.log("ERROR while trying to create new /customer", resp);
			}
		);
	}
	
	this.updateCustomer = function(customer){
		$http({
			method: "PUT",
			url: "/api/customers/?" + jQuery.param(customer),
			headers: {'Accept': 'application/json'},
		}).then(
		function(resp){
			self.getCustomers();
			},
		function(resp){
			console.log("ERROR while trying to create new /customer", resp);
			}
		);
	}
	
	this.deleteCustomer = function(id){
		$http({
			method: "DELETE",
			url: "/api/customers/" + id,
			headers: {'Accept': 'application/json'},
		}).then(
		function(resp){
			self.getCustomers();
			},
		function(resp){
			console.log("ERROR while trying to create new /customer", resp);
			}
		);
	}
	
}]);