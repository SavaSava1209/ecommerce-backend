package com.usc.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usc.beans.Customer;
import com.usc.beans.Role;
import com.usc.dao.CustomerDao;
import com.usc.http.LoginDto;
import com.usc.http.RegisterDto;
import com.usc.http.Response;
import com.usc.service.CustomerService;

@CrossOrigin
@RestController()
@RequestMapping("/auth")
@Transactional
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/user")
	public Customer getUserDetail(Authentication authentication) {
		System.out.println("userDetails");
		System.out.println("authentication "+ authentication.getName());
		return customerDao.findByUsername(authentication.getName());
//		return null;

	}
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping
	public List<Customer> getUser() {
		return customerDao.findAll();
	}

	@PostMapping("/register")	
	public Response register(@RequestBody RegisterDto registerDto) {

		if (customerDao.existsByUsername(registerDto.getUsername())) {
			return new Response(false, "Username is taken!");
		};
		
		Customer customer = new Customer();
		customer.setEmail(registerDto.getEmail());
		customer.setFirstName(registerDto.getFirstName());
		customer.setLastName(registerDto.getLastName());
		customer.setUsername(registerDto.getUsername());
		customer.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		List<Role> roles = new ArrayList<>(); 
		roles.add(new Role(2));
		customer.setRoles(roles);
		customerDao.save(customer);
		
		
		return new Response(true, "Registration success");
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/registerAdmin")	
	public Response registerAdmin(@RequestBody RegisterDto registerDto) {		
		if (customerDao.existsByUsername(registerDto.getUsername())) {
			Customer customer = customerDao.findByUsername(registerDto.getUsername());
			List<Role> roles = customer.getRoles();
			roles.add(new Role(1));
			customerDao.save(customer);
			return new Response(true, "Registration Admin success");
		};
		
		Customer customer = new Customer();
		customer.setEmail(registerDto.getEmail());
		customer.setFirstName(registerDto.getFirstName());
		customer.setLastName(registerDto.getLastName());
		customer.setUsername(registerDto.getUsername());
		customer.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		List<Role> roles = new ArrayList<>(); 
		roles.add(new Role(1));
		customer.setRoles(roles);
		customerDao.save(customer);
		
		
		return new Response(true, "Registration Admin success");
	}
	
	@GetMapping("/checkLogin")
	public Response checkLogin(Authentication authentication) {
//		System.out.println(authentication.getName());
		return new Response(authentication != null);
	}
	
	@GetMapping("/isAdmin")
	public boolean isAdmin(Authentication authentication) {
		if (authentication == null) {
			return false;
		}
		return this.customerService.isAdmin(authentication.getAuthorities());	
	}
	
	
	
}
