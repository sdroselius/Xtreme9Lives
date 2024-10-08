package com.skilldistillery.xtreme.data;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.xtreme.entities.Category;
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

	@Override
	public Post findById(int postId) {
		return em.find(Post.class, postId);
	}

	@Override
	public Post create(Post post) {
		if ( post.getCategory() == null ) {
			post.setCategory(em.find(Category.class, 5));
		}
		em.persist(post);
		return post;
	}

	@Override
	public Post update(int postId, Post post) {
		Post existing = em.find(Post.class, postId);
		if (existing != null) {
			existing.setTitle(post.getTitle());
			existing.setName(post.getName());
			existing.setEmail(post.getEmail());
			existing.setDescription(post.getDescription());
			existing.setPrice(post.getPrice());
			existing.setImageUrl(post.getImageUrl());
			existing.setBrand(post.getBrand());
			if (post.getCategory() != null) {
				existing.setCategory(post.getCategory());
			}
			em.flush();
		}
		return existing;
	}

	@Override
	public boolean deleteById(int postId) {
		boolean deleted = false;
		Post toDelete = em.find(Post.class, postId);
		if (toDelete != null) {
			em.remove(toDelete);
			deleted = true;
		}
		return deleted;
	}
	
}
