package com.usc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.usc.beans.Product;
import com.usc.dao.ProductDao;
import com.usc.service.ProductService;

import org.springframework.test.context.ContextConfiguration;


//@RunWith(SpringRunner.class) //juni4
@WebMvcTest(value = ProductController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
@ContextConfiguration(classes = { ProductController.class })
class ProductControllerTest {
	
	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writer();
	
	// a mocked http server
	@Autowired 
	MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;
	
	@MockBean
	private ProductDao productDao;
	
	@MockBean
	private ProductController productController;
	
	
	
	
	@WithMockUser
	@Test
	void testGetAllProducts_success() throws Exception {
		Product product1 = new Product(1L, "test1", 1);
		Product product2= new Product(2L, "test2", 2);
		Product product3 = new Product(3L, "test3", 3);
		
		// given
		List<Product> products = new ArrayList<>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		
		// when 
		Mockito.when(productDao.findAll()).thenReturn(products);
		
		
		
		// then 
		mockMvc.perform(MockMvcRequestBuilders
				.get("/products")				
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())			
//				.andExpect(jsonPath("$", hasSize(3)))
//				.andExpect(jsonPath("$[2].name", is("test3")))
				;
	}
//	@Disabled
	@Test
	void testGetProductById_success() throws Exception {
		Product product1 = new Product(1L, "test1", 1);
		Mockito.when(productDao.findById(product1.getId())).thenReturn(Optional.of(product1));
		mockMvc.perform(MockMvcRequestBuilders
				.get("/products/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(jsonPath("$", notNullValue()))
//				.andExpect(jsonPath("$.name", is("test1")))
				;
	}
//	@Disabled
	@Test
	void testAddProduct_success() throws Exception {
		Product product1 = new Product(1L, "test1", 1);
		Mockito.when(productDao.save(product1)).thenReturn(product1);
		
		// generate a json from a java object
		String content = writer.writeValueAsString(product1);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(content)) // requestBody
				.andExpect(status().isOk())
//				.andExpect(jsonPath("$", notNullValue()))
//				.andExpect(jsonPath("$.name", is("test1")))
				;
		
	}
//	@Disabled
	@Test
	void testDeleteProductById__success() throws Exception {
		Product product2= new Product(2L, "test2", 2);
		Mockito.when(productDao.findById(2L)).thenReturn(Optional.of(product2));
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/products/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				;
				
		
	}

//	@Disabled
	@Test
	void testUpdatePrice() throws Exception {
		Product product1 = new Product(1L, "test1", 1);
		Product updatedProduct = new Product(1L, "test1", 2);
		
		Mockito.when(productDao.findById(1L)).thenReturn(Optional.of(product1));
		Mockito.when(productDao.save(updatedProduct)).thenReturn(updatedProduct);
		
		String content = writer.writeValueAsString(updatedProduct);
		
		mockMvc.perform(MockMvcRequestBuilders
				.put("/products/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(content)) // requestBody
				.andExpect(status().isOk())
//				.andExpect(jsonPath("$", notNullValue()))
//				.andExpect(jsonPath("$.unitPrice", is(2)))
				;
		
	}

}
