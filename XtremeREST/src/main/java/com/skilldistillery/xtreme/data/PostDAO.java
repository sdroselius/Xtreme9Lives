package com.skilldistillery.xtreme.data;

import java.util.List;

import com.skilldistillery.xtreme.entities.Post;

public interface PostDAO {
	List<Post> findAll();
	Post findById(int postId);
	Post create(Post post);
	Post update(int postId, Post post);
	boolean deleteById(int postId);
}
