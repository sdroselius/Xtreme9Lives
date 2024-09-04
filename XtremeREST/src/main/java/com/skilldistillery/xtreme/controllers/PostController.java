package com.skilldistillery.xtreme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.xtreme.data.PostDAO;
import com.skilldistillery.xtreme.entities.Post;

@RestController
@RequestMapping("api")
public class PostController {
	
	@Autowired
	private PostDAO postDao;
	
	@GetMapping("posts")
	private List<Post> getAllPosts() {
		return postDao.findAll();
	}

}