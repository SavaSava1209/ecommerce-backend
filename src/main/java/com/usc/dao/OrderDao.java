package com.usc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usc.beans.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {

	List<Order> findByCustomerEmail(String email);
}
