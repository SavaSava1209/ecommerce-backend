package com.usc.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
	
	
//	ProductService underTest;
//	
//	@Mock
//	ProductDao productDao;
	
//	@BeforeEach
//	void setUp() {
//		underTest = new ProductService();
//	}
//	
//	@Test  
//	void canGetAllProducts() {
//		underTest.getAllProducts();
//		
//		verify(productDao).findAll();
//		
//	}
//
//	@Test
//	void testAddProduct() {
//		// given
//		ProductDto product = new ProductDto("test", "test", 4, "test", "test", true,
//				100, 100);
//		//when 
//		underTest.addProduct(product);
//		
//		// then
//		// capture from the database that was saved by the Service
//		ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
//		
//		Product capturedProduct = productArgumentCaptor.getValue();
//		
//		Assertions.assertThat(capturedProduct).isEqualTo(product);
//	}
//
//	@Test
//	void testDeleteProduct() {
//		// given
//		long id = 1;
//		
//		given(productDao.existsById(id)).willReturn(true);
//		
//		// when 
//		underTest.deleteProduct(id);
//		
//		// then 
//		verify(productDao).deleteById(id);
//	}

}
