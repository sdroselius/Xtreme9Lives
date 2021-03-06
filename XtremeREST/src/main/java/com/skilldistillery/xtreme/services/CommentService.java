package com.skilldistillery.xtreme.services;

import java.util.List;

import com.skilldistillery.xtreme.entities.Comment;

public interface CommentService {
   List<Comment> commentsForPostId(int postId);
   Comment createForPostId(int postId, Comment comment);
   boolean deleteComment(int postId, int commentId);
}
