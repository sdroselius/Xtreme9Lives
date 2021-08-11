package com.skilldistillery.xtreme.services;

import java.util.List;

import com.skilldistillery.xtreme.entities.Category;
import com.skilldistillery.xtreme.entities.Post;

public interface PostService {
	List<Post> getPostsForCategory(int catId);

	List<Post> getPostsForCategoryId(int catId);

	List<Post> searchByNameTitle(String keyword);

	List<Post> searchByPriceRange(double low, double high);

}
