package com.skilldistillery.xtreme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.xtreme.entities.Category;
import com.skilldistillery.xtreme.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	List<Post> findByCategory(Category cat);
	
	List<Post> findByCategory_Id(int catId);
	List<Post> findByNameLikeOrTitleLike(String keyword1, String keyword2);
	List<Post> findByPriceBetween(double low, double high);

}
