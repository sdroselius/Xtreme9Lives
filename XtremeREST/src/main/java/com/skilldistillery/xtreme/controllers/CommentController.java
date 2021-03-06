package com.skilldistillery.xtreme.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.xtreme.entities.Comment;
import com.skilldistillery.xtreme.services.CommentService;

@RequestMapping("api")
@RestController
public class CommentController {

	@Autowired
	private CommentService commentSvc;
	
	@GetMapping("posts/{id}/comments")
	public List<Comment> commentsForPostId(
			@PathVariable("id") Integer postId,
			HttpServletResponse res
	) {
		List<Comment> comments = commentSvc.commentsForPostId(postId);
		if (comments == null) {
			res.setStatus(404);
		}
		return comments;
	}
	
	@PostMapping("posts/{postId}/comments")
	public Comment addCommentForPost(
			@PathVariable Integer postId,
			@RequestBody Comment comment,
			HttpServletRequest req,
			HttpServletResponse res
	) {
		comment = commentSvc.createForPostId(postId, comment);
		try {
			if (comment == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(201); //Created
				StringBuffer url = req.getRequestURL();
				url.append("/").append(comment.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			res.setStatus(400);
			comment = null;
		}
		return comment;
	}
	
	@DeleteMapping("posts/{postId}/comments/{commentId}")
	public void deleteCommentForPost(
			@PathVariable Integer postId,
			@PathVariable Integer commentId,
			HttpServletResponse res
	) {
		try {
			boolean deleted = commentSvc.deleteComment(postId, commentId);
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
	
	
}
