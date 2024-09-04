angular.module('appModule')
.component("jumbotron", {
	templateUrl : "app/appModule/jumboTronComponent/jumbo.component.html",
	controller : function($anchorScroll){
		
		  var vm = this;
		  
		  vm.videoUrl = "video/Mighty.mp4";
		  
		  vm.scroll = function(){
			  console.log("called");
			  $anchorScroll("contentStart");
		  }
		
	},
	controllerAs : 'vm'
});