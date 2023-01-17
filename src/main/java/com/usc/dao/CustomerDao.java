package com.usc.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usc.beans.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
	Optional<Customer> findByEmail(String email);

	Customer findByUsername(String username);

	boolean existsByUsername(String username);
}
