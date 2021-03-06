package com.skilldistillery.xtreme.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.skilldistillery.xtreme.services.PostService;

@RequestMapping("api")
@RestController
public class PostController {

	@Autowired
	private PostDAO postDao;
	
	@Autowired
	private PostService postSvc;
	
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}
	
	@GetMapping("posts")
	public List<Post> index() {
		return postDao.findAll();
	}
	
	@GetMapping("posts/{postId}")
	public Post show(
			@PathVariable Integer postId,
			HttpServletResponse res
	) {
		Post post = postDao.findById(postId);
		if (post == null) {
			res.setStatus(404);
		}
		return post;
	}
	
	@PostMapping("posts")
	public Post createPost(
			@RequestBody Post post,
			HttpServletRequest req,
			HttpServletResponse res
	) {
		try {
			postDao.create(post);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(post.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			post = null;
		}
		return post;
	}
	
	@PutMapping("posts/{postId}")
	public Post updatePost(
			@RequestBody Post post,
			@PathVariable Integer postId,
			HttpServletResponse res
	) {
		try {
			post = postDao.update(postId, post);
			if (post == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			post = null;
		}
		return post;
	}
	
	@DeleteMapping("posts/{postId}")
	public void deletePost(
			@PathVariable Integer postId,
			HttpServletResponse res
	)
	{
		try {
			boolean deleted = postDao.delete(postId);
			if (deleted) {
				res.setStatus(204);
			}
			else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}
	
	@GetMapping("categories/{catId}/posts")
	public List<Post> postsForCategory(
			@PathVariable Integer catId,
			HttpServletResponse res
	) {
		List<Post> posts = postSvc.getPostsForCategoryId(catId);
		if (posts == null) {
			res.setStatus(404);
		}
		return posts;
	}
	
//	List<Post>	GET api/posts/search/{keyword}	Gets all posts by a name or title
	@GetMapping("posts/search/{keyword}")
	public List<Post> keywordSearch(@PathVariable String keyword) {
		return postSvc.searchByNameTitle(keyword);
	}
	
	
//	List<Post>	GET api/posts/search/price/{low}/{high}	Gets all posts within price range
	@GetMapping("posts/search/price/{low}/{high}")
	public List<Post> priceRangeSearch(
			@PathVariable Double low,
			@PathVariable Double high
	) {
		return postSvc.searchByPriceRange(low, high);
	}
	
	
}
