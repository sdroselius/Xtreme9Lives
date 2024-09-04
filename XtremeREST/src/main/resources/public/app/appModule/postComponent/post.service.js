angular.module("appModule")
.factory("postService", function($http, $location){
	var service = {};

	service.index = function(){		
		return $http({
			method : "GET",
			url:"api/posts/"
		})
	}
	
	service.show = function(id){
		return $http({
			method : "GET",
			url:`api/posts/${id}`
		})
	}
	
	service.create = function(post){		
		return $http({
			method : "POST",
			url:"api/posts",
			data: post
		})
	}
	
	service.update = function(id, post){

		return $http({
			method : "PUT",
			url:`api/posts/${id}`,
			data: post
		})	
	}
	
	service.destroy = function(id){
		return $http({
			method : "DELETE",
			url:`api/posts/${id}`
		})	
	}
	
	service.getCommentsForPost = function(id){
		return $http({
			method : "GET",
			url:`api/posts/${id}/comments`
		})
	}
 	
	service.leaveCommentsOnPost = function(id, comment){
		return $http({
			method : "POST",
			url:`api/posts/${id}/comments`,
			data: comment
		})
	}
	
	service.deleteComment = function(pid, cid){
		return $http({
			method : "DELETE",
			url:`api/posts/${pid}/comments/${cid}`
		})
	}
	
	service.searchForPosts = function(word){
		return $http({
			method : "GET",
			url:`api/posts/search/${word}`
		})
	}
	
	return service;
});