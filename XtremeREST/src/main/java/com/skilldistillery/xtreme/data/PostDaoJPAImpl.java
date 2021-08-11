package com.skilldistillery.xtreme.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.xtreme.entities.Post;

@Service
@Transactional
public class PostDaoJPAImpl implements PostDAO {
	
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
		em.persist(post);
		return post;
	}

	@Override
	public Post update(int postId, Post post) {
		Post managedPost = em.find(Post.class, postId);
		if (managedPost != null) {
			managedPost.setTitle(post.getTitle());
			managedPost.setName(post.getName());
			managedPost.setEmail(post.getEmail());
			managedPost.setDescription(post.getDescription());
			managedPost.setPrice(post.getPrice());
			managedPost.setImageUrl(post.getImageUrl());
			managedPost.setBrand(post.getBrand());
			if (post.getCategory() != null) {
				managedPost.setCategory(post.getCategory());
			}
			em.flush();
		}
		return managedPost;
	}

	@Override
	public boolean delete(int postId) {
		boolean deleted = false;
		Post post = em.find(Post.class, postId);
		if (post != null) {
			em.remove(post);
			deleted = true;
		}
		return deleted;
	}
		
}
