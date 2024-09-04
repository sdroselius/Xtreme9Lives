package com.skilldistillery.xtreme.data;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.xtreme.entities.Post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostDaoImpl implements PostDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Post> findAll() {
		String jpql = "SELECT p FROM Post p";
		return em.createQuery(jpql, Post.class).getResultList();
	}
}