package com.usc.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usc.beans.Product;
import com.usc.beans.ProductCategory;
import com.usc.dao.ProductCategoryDao;
import com.usc.dao.ProductDao;
import com.usc.http.ProductDto;
import com.usc.http.Response;

@Service
public class ProductService {
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductCategoryDao productCategoryDao;
	
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}
	
	
	
	public Response addProduct(ProductDto productDto) {
		Product product = new Product();
		product.setDateCreated(new Date());
		product.setActive(true);
		product.setDescription(productDto.getDescription());
		product.setName(productDto.getName());
		product.setImageUrl(productDto.getImageUrl());
		product.setUnitsInStock(productDto.getUnitsInStock());
		product.setUnitPrice(productDto.getUnitPrice());
		product.setSku(productDto.getSku());

		// find the category 
		Optional<ProductCategory> productCategory = productCategoryDao.findById(productDto.getCategory());
		ProductCategory category = productCategory.get();

		category.add(product);
		productCategoryDao.save(category);
		
		return new Response(true, "Product has been added successfully!");
	}
	
	
	public Response deleteProduct(long id) {
		// find the product 
		Product product = productDao.findById(id).get();
		
		if (product != null) {
			productDao.deleteById(id);

			return new Response(true, "Product has been deleted!");
		} else {
			return new Response(false, "Unable to find the product.");
		}
	}
	
	public Response updateProduct(Long id, Product unitPrice) {
		// find the product and update the price 
				Product product = productDao.findById(id).get();
				product.setUnitPrice(unitPrice.getUnitPrice());
//				System.out.println(product.toString());
				productDao.save(product);
				return new Response(true, "Price has updated");
		
	}
}
