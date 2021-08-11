package com.skilldistillery.xtreme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.xtreme.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
