package com.skilldistillery.xtreme.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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

//	|  1 | Xtreme Single Engine | Xander Cage | Xman@ier.com | Lorem  lacus. |  6000 | https://i2.wp.com/www.thelostgirlsguide.com/wp-content/uploads/DSC_3138_-1.jpg | Air Time |           1 | 2018-03-01 00:00:00 | 2018-03-02 08:51:16 |
	@Test
	void test_Post_entity_mapping() {
		assertNotNull(post);
		assertEquals("Xtreme Single Engine", post.getTitle());
		assertEquals("Xander Cage", post.getName());
	}

	@Test
	void test_Post_Category_ManyToOne_mapping() {
		assertNotNull(post);
		assertNotNull(post.getCategory());
		assertEquals(1, post.getCategory().getId());
	}
	
	@Test
	void test_Post_Comment_OneToMany_mapping() {
		assertNotNull(post);
		assertNotNull(post.getComments());
		assertTrue(post.getComments().size() > 0);
	}
	
}
