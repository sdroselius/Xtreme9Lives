package com.skilldistillery.xtreme.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.xtreme.entities.Comment;

@SpringBootTest
class CommentRepositoryTest {

	@Autowired
	private CommentRepository repo;
	
	@Test
	void test_findByPost_Id() {
		List<Comment> c = repo.findByPost_Id(1);
		assertNotNull(c);
		assertTrue(c.size() > 0);
	}

}
