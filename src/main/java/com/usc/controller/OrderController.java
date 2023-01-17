package com.usc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usc.beans.Order;
import com.usc.dao.OrderDao;
import com.usc.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {
//	@Autowired
//	OrderService orderService;
	
	@Autowired
	OrderDao orderDao;
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping
	public List<Order> getOrders(@RequestParam String email) {
		//get orders from database
		
		System.out.println("OrderController " + orderDao.findByCustomerEmail(email));
		return orderDao.findByCustomerEmail(email);
		
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/admin")
	public List<Order> adminGetOrders() {
		return orderDao.findAll();
	}

}
