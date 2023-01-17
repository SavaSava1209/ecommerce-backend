package com.usc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usc.beans.Customer;
import com.usc.dao.CustomerDao;

@Service
public class CustomerService {
	@Autowired
	CustomerDao customerDao;
	
	
//	@Autowired
//	PasswordEncoder passwordEncoder;
	
//	public Response register(Customer customer) {
//		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
//	}
	
	
	public boolean isAdmin(Collection<? extends GrantedAuthority> profiles) {
		boolean isAdmin = false;
		for (GrantedAuthority profile: profiles) {
			if (profile.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
			}
		}
		return isAdmin;
	}
}
