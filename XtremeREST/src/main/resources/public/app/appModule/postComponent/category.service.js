angular.module("appModule")
.factory("categoryService", function($http, $location){
	var service = {};

	service.index = function(){		
		return $http({
			method : "GET",
			url:"api/categories/"
		})
	}
	
	return service;
});