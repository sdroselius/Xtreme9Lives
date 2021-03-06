package com.skilldistillery.xtreme.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.xtreme.entities.Post;

@SpringBootTest
class PostRepositoryTest {

	@Autowired
	private PostRepository repo;
	
	@Test
	void test_findByCategory_Id() {
		List<Post> posts = repo.findByCategory_Id(1);
		assertNotNull(posts);
		assertTrue(posts.size() > 0);
		assertEquals(19, posts.size());
	}

}
