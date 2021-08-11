angular.module("appModule")
.filter('filterPostByCategory', function(){
	return function(posts, category){
		if(category && category.name == "all") return posts;
		
		var results = [];
		posts.forEach(function(val, index, arr){
			if(val.category.name == category.name) results.push(val);
		});
		
		return results;
	}
})