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

class CategoryTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Category category;

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
		category = em.find(Category.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		category = null;
	}

	@Test
	void test_Category_entity_mapping() {
		assertNotNull(category);
		assertEquals("skydiving", category.getName());
	}

	@Test
	void test_Category_Post_OneToMany_mapping() {
		assertNotNull(category);
		assertNotNull(category.getPosts());
		assertTrue(category.getPosts().size() > 0);
	}
	
}
