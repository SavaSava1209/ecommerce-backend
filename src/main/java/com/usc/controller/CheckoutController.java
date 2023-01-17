package com.usc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usc.http.PurchaseInfo;
import com.usc.http.PurchaseResponse;
import com.usc.service.CheckoutService;

@CrossOrigin
@RequestMapping("/checkout")
@RestController
public class CheckoutController {
	
	@Autowired
	private CheckoutService checkoutService; 
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody PurchaseInfo info) {
		PurchaseResponse response = checkoutService.placeOrder(info);
		return response;
	}
}
