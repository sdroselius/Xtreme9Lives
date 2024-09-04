package com.skilldistillery.xtreme.data;

import java.util.List;

import com.skilldistillery.xtreme.entities.Post;

public interface PostDAO {
	List<Post> findAll();
}
