angular.module("appModule")
.component("postComponent", {
	templateUrl : "app/appModule/postComponent/post.component.html",
	controller : function(postService, categoryService){
		var vm = this;
		
		vm.categories = [];
		vm.posts = [];
		vm.comments = [];
		
		vm.selectedPost = null;
		vm.selectedCategory = null;
		vm.searchResults = false;
		
		vm.selectPost = function(post){
			postService.getCommentsForPost(post.id)
			.then(function(res){
				vm.comments = res.data;
				vm.selectedPost = post;
			})
			.catch(function(err){
				console.log(err);
			})
		}
		
		vm.returnToListView = function(){
			vm.selectedPost = null;
			vm.comments = [];
		}
		
		vm.filterByCategory = function(category){
			vm.selectedCategory = category;
		}
		
		vm.addComment = function(id, comment){
			postService.leaveCommentsOnPost(id, comment)
			.then(function(res){
				vm.selectPost(vm.selectedPost)
				loadPosts();
			})
			.catch(console.log);
		}
		
		vm.deleteComment = function(cid){
			postService.deleteComment(vm.selectedPost.id, cid)
			.then(function(res){
				vm.selectPost(vm.selectedPost)
				loadPosts();
			})
			.catch(console.log);		
		}
		
		vm.searchFor = function(search){
			postService.searchForPosts(search.text)
			.then(function(res){
				vm.searchResults = true;
				vm.posts = res.data;
			})
			.catch(function(err){
				console.log(err);
			})
		}
		
		vm.showAll = function(){
			loadPosts();
		}
		
		vm.deletePost = function(id){
			console.log(id)
			postService.destroy(id)
			.then(function(res){
				vm.selectedPost = null;
				loadPosts();
			})
			.catch(function(err){
				console.log(err);
			})
		}
		
		// helpers
		loadPosts = function(){
			postService.index()
			.then(function(res){
				vm.posts = res.data;
			})
			.catch(function(err){
				console.log(err);
			})
		}
		loadPosts();
		
		loadCategories = function(){
			categoryService.index()
			.then(function(res){
				vm.categories = res.data;
				vm.categories.unshift({
					name: "all"
				});
				vm.selectedCategory = vm.categories[0];
			})
			.catch(function(err){
				console.log(err);
			})
		}
		loadCategories();
	},
	controllerAs: "vm"
});
