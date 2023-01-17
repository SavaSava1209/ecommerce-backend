package com.usc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.usc.beans.Customer;
import com.usc.dao.CustomerDao;

@Service
public class CustomerServiceImpl implements UserDetailsService {
	@Autowired
	CustomerDao customerDao;
	
	
	// implements UserDetailsService allows us to pull data from database 
	// and use object UserDetails
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerDao.findByUsername(username);
		if (customer == null) {
			throw new UsernameNotFoundException("Username" + username + "was not found");
		}
		
		return customer;
	}
	
	
}


