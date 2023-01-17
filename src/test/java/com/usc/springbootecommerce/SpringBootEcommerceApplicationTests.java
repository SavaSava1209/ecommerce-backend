package com.usc.springbootecommerce;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class SpringBootEcommerceApplicationTests {

	@Test
	void contextLoads() {
		//set up 
		Calculate testRes = new Calculate();
		
		// action
		int res = testRes.add(1, 2);
		
		// assert
		Assertions.assertEquals(3, res);	
	}
	
	
	class Calculate {
		int a;
		int b;
		
		public int add(int a, int b) {
			return a + b;
		}
	}

}
