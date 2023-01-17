package com.usc.dao;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


//@DataJpaTest
class ProductDaoTest {
	
//	@Autowired
//	ProductDao underTest;

	
//	@Test
	void itShouldDindTheProductContainsName() {
		// set up
		// need a product object
//		String testName = "testName";
//		Product product = new Product(testName, 123);
////		
////		// save to the database 
//		underTest.save(product);
////		
////		// action
//		List<Product> expected = underTest.findByNameContaining("testName");
////		
//		List<Product> actual = new ArrayList<Product>();
//		actual.add(product);
////		// assertion
//		Assertions.assertThat(expected).allSatisfy(prod -> {
//			Assertions.assertThat(prod.getName()).contains("testName");
//
//		});
	}
}
