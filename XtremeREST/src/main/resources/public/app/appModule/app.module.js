angular.module("appModule",["ngRoute"])
	.config(function($routeProvider){
	$routeProvider   
		.when('/', {
	      template : '<post-component></post-component>'
	    })
	    .when('/new', {
	      template : '<new-post-component></new-post-component>'
	    })
	    	.when('/about', {
	      template : '<about-component></about-component>'
	    })
	    .otherwise({
	      template : '<not-found>loading 404</not-found>'
	    })
});
