package com.skilldistillery.xtreme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.xtreme.data.PostDAO;
import com.skilldistillery.xtreme.entities.Post;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class PostController {
	
	@Autowired
	private PostDAO postDao;
	
//	@GetMapping(path={"posts","posts/"})
	@GetMapping({"posts","posts/"})
	private List<Post> getAllPosts() {
		return postDao.findAll();
	}

	@GetMapping("posts/{postId}")
	private Post getOnePosts( @PathVariable("postId") Integer postId,
			                  HttpServletResponse res
	) {
		Post post = postDao.findById(postId);
		if (post == null) {
			res.setStatus(404);
		}
		return post;
	}
	
	@PostMapping({"posts","posts/"})
	private Post addPost(@RequestBody Post post, HttpServletResponse res,
			HttpServletRequest req
	) {
		System.out.println(post);
		try {
			post = postDao.create(post);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			res.setHeader("Location", url.append("/").append(post.getId()).toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			post = null;
		}
		return post;
	}
	
	@PutMapping("posts/{postId}")
	public Post updatePost(
			@PathVariable("postId") Integer postId,
			@RequestBody Post post,
			HttpServletResponse res
	) {
		try {
			post = postDao.update(postId, post);
			if (post == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			post = null;
		}
		return post;
	}
	
	@DeleteMapping("posts/{postId}")
	public void deletePost(
			@PathVariable("postId") Integer postId,
			HttpServletResponse res
	) {
		try {
			if (postDao.deleteById(postId)) {
				res.setStatus(204);
			}
			else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
}
