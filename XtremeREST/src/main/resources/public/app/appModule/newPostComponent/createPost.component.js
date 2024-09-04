angular.module("appModule")
.component("newPostComponent", {
	templateUrl : "app/appModule/newPostComponent/createPost.component.html",
	controller : function(postService, categoryService, $location){
		var vm = this;
		
		vm.post = {};
		vm.categories = [];
		
		vm.submitPost = function(newPost){
			vm.post = newPost;
			
			vm.categories.forEach(function(val, idx, arr){
				if(val.name === newPost.category){
					vm.post.category = val; 
				}
			});
			
			postService.create(vm.post)
				.then(function(res){
					$location.path("/");
				})
				.catch(console.log)
		}

		loadCategories = function(){
			categoryService.index()
			.then(function(res){
				vm.categories = res.data;
			})
			.catch(function(err){
				console.log(err);
			})
		}
		loadCategories();
	},
	controllerAs: "vm"
});
