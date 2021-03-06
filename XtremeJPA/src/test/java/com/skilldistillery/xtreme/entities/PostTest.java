package com.skilldistillery.xtreme.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Post post;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("XtremeJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		post = em.find(Post.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		post = null;
	}

	@Test
	void test_Post_entity_mapping() {
		assertNotNull(post);
		assertEquals("Xtreme Single Engine", post.getTitle());
		assertEquals("Xander Cage", post.getName());
	}

	@Test
	void test_Post_Category_ManyToOne_entity_mapping() {
		assertNotNull(post);
		assertNotNull(post.getCategory());
		assertEquals("skydiving", post.getCategory().getName());
	}
	
	@Test
	void test_Post_Comment_OneToMany_relationship_mapping() {
		assertNotNull(post);
		assertNotNull(post.getComments());
		assertTrue(post.getComments().size() > 0);
	}
	
}
