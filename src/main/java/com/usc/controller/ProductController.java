package com.usc.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usc.beans.Product;
import com.usc.beans.ProductCategory;
import com.usc.dao.ProductCategoryDao;
import com.usc.dao.ProductDao;
import com.usc.http.ProductDto;
import com.usc.http.Response;
import com.usc.service.ProductService;


@RestController()
@RequestMapping("/products")
//@CrossOrigin
public class ProductController {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductCategoryDao productCategoryDao;
	
	@Autowired
	ProductService productService;
	
//	@GetMapping
//	public Map<String, Object>getProducts(@RequestParam int page, @RequestParam int size) {	
//		Pageable paging = PageRequest.of(page, size);
//		Page<Product> pageProducts = productDao.findAll(paging);
//		Map<String, Object> map = new HashMap<>();
//		map.put("products", pageProducts.getContent());
//		map.put("currentPage", pageProducts.getNumber());
//		map.put("totalElements", pageProducts.getTotalElements());
//		return map;
//	}
	
	@GetMapping
	public List<Product> getProducts() {
		return productDao.findAll();
		
	}	
	
	
	@GetMapping("/search/{name}")	
	public List<Product> getProductsBySearchName(@PathVariable String name) {
		return productDao.findByNameContaining(name);
	}
	
	@GetMapping("/{id}")	
	public Optional<Product> getProduct(@PathVariable Long id){		
		return productDao.findById(id);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping
	public Response addProduct(@RequestBody ProductDto product){		
//		System.out.println("ProductController" + product);
		return productService.addProduct(product);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public Response deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public Response updatePrice(@PathVariable Long id, @RequestBody Product unitPrice) {		
		return productService.updateProduct(id,	unitPrice);
		
	}
	
	
	

	
}

