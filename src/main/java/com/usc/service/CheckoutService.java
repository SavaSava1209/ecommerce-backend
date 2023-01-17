package com.usc.service;

import java.util.Set;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usc.beans.Address;
import com.usc.beans.Customer;
import com.usc.beans.Order;
import com.usc.beans.OrderItem;
import com.usc.dao.CustomerDao;
import com.usc.http.PurchaseInfo;
import com.usc.http.PurchaseResponse;

@Service
public class CheckoutService {
	@Autowired
	CustomerDao customerDao;
	
	public PurchaseResponse placeOrder(PurchaseInfo info) {
//		System.out.println("checkoutService");
		// retrieve the order info from dto
		Order order = info.getOrder();
		
		// generate tracking number 
		String orderTrackingNumber = generateOrderTrackingNumber();
		 order.setOrderTrackingNumber(orderTrackingNumber);
		 
		// populate order with order items
		Set<OrderItem> orderItems = info.getOrderItems();
		
		orderItems.forEach(item -> {

			order.add(item);
		});
		
		
		// populate order with shipping address
		Address shippingAddress = info.getShippingAddress();
		order.setShippingAddress(shippingAddress);		
		
		// populate customer with order
		// save() -- add a new instance
		// to do == find the customer from database and add orders
		String email = info.getCustomer().getEmail();
		Customer customer = customerDao.findByEmail(email).get();
//		System.out.println("email " + email);
		customer.add(order);
		
		// save to the database
		customerDao.save(customer);
		
		//return a response
		return new PurchaseResponse(orderTrackingNumber);
//		return null;

	}

	private String generateOrderTrackingNumber(){
		//Universally unique ID
		return UUID.randomUUID().toString();
	}
}
