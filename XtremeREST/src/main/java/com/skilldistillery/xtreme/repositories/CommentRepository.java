package com.skilldistillery.xtreme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.xtreme.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	List<Comment> findByPost_Id(int postId);
}
