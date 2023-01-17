package com.usc.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.usc.beans.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
	Page<Product> findAll(Pageable pageable);
	Product findByName(String name);
	List<Product> findByCategoryId(Long id);
	List<Product> findByNameContaining(String name);
}
