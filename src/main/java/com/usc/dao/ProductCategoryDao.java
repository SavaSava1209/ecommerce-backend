package com.usc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usc.beans.ProductCategory;


@Repository
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Long> {
	
}
