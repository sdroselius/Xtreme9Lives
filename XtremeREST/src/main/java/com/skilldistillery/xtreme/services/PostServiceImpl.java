package com.skilldistillery.xtreme.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.xtreme.entities.Category;
import com.skilldistillery.xtreme.entities.Post;
import com.skilldistillery.xtreme.repositories.CategoryRepository;
import com.skilldistillery.xtreme.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CategoryRepository catRepo;

	@Override
	public List<Post> getPostsForCategoryId(int catId) {
		if (catRepo.existsById(catId)) {
		  return postRepo.findByCategory_Id(catId);
		}
		else {
			return null;
		}
	}

	@Override
	public List<Post> searchByNameTitle(String keyword) {
		keyword = "%" + keyword + "%";
		return postRepo.findByNameLikeOrTitleLike(keyword, keyword);
	}

	@Override
	public List<Post> searchByPriceRange(double low, double high) {
		return postRepo.findByPriceBetween(low, high);
	}

	@Override
	public List<Post> getPostsForCategory(int catId) {
		Optional<Category> catOpt = catRepo.findById(catId);
		if (catOpt.isPresent()) {
			Category cat = catOpt.get();
			return postRepo.findByCategory(cat);
		}
		return null;
	}
	
}
