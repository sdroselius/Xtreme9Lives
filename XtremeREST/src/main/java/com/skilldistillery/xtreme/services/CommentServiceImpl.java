package com.skilldistillery.xtreme.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.xtreme.entities.Comment;
import com.skilldistillery.xtreme.entities.Post;
import com.skilldistillery.xtreme.repositories.CommentRepository;
import com.skilldistillery.xtreme.repositories.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private PostRepository postRepo;

	@Override
	public List<Comment> commentsForPostId(int postId) {
		List<Comment> comments = null;
		if (postRepo.existsById(postId)) {
			comments = commentRepo.findByPost_Id(postId);
		}
		return comments;
	}

	@Override
	public Comment createForPostId(int postId, Comment comment) {
		Optional<Post> postOpt = postRepo.findById(postId);
		if (postOpt.isPresent()) {
			Post post = postOpt.get();
			comment.setPost(post);
			return commentRepo.saveAndFlush(comment);
		}
		return null;
	}
	
	@Override
	public boolean deleteComment(int postId, int commentId) {
		boolean deleted = false;
		Optional<Comment> commentOpt = commentRepo.findById(commentId);
		if (commentOpt.isPresent()) {
			Comment comment = commentOpt.get();
			if (comment.getPost().getId() == postId) {
//				commentRepo.delete(comment);
				commentRepo.deleteById(commentId);
				deleted = true;
			}
		}
		return deleted;
	}

}
