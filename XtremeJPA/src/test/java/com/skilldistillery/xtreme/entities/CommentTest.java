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

class CommentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Comment comment;

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
		comment = em.find(Comment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comment = null;
	}

//	| id | name             | content              | post_id |
//	+----+------------------+----------------------+---------+
//	|  1 | Augustus Gibbons | This seems dangerous |       1 |
	@Test
	void test_Comment_entity_mapping() {
		assertNotNull(comment);
		assertEquals("Augustus Gibbons", comment.getName());
	}

	@Test
	void test_Comment_Post_ManyToOne_mapping() {
		assertNotNull(comment);
		assertNotNull(comment.getPost());
		assertEquals(1, comment.getPost().getId());
	}
	
}
