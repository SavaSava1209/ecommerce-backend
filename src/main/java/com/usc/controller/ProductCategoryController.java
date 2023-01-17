package com.usc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usc.beans.Product;
import com.usc.beans.ProductCategory;
import com.usc.dao.ProductCategoryDao;
import com.usc.dao.ProductDao;

@RestController()
@RequestMapping("/product-category")
public class ProductCategoryController {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductCategoryDao productCategoryDao;
	
	
	@GetMapping
	@CrossOrigin
	public List<ProductCategory> getProductCategory() {	
		return productCategoryDao.findAll();
	}
	
	@GetMapping("/{id}")
	@CrossOrigin
	public List<Product> getProductsByCategoryId(@PathVariable Long id) {
		return productDao.findByCategoryId(id);
	}
	
}
