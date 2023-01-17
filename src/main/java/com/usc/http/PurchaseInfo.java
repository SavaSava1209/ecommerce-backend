package com.usc.http;

import java.util.Set;

import com.usc.beans.Address;
import com.usc.beans.Customer;
import com.usc.beans.Order;
import com.usc.beans.OrderItem;

public class PurchaseInfo {
	private Customer customer ;
	
	private Address shippingAddress;
	
	private Order order;
	
	private Set<OrderItem> orderItems;

	public PurchaseInfo(Customer customer, Address shippingAddress, Order order, Set<OrderItem> orderItems) {
		super();
		this.customer = customer;
		this.shippingAddress = shippingAddress;
		this.order = order;
		this.orderItems = orderItems;
	}

	public PurchaseInfo() {
		super();
	}

	@Override
	public String toString() {
		return "PurchaseInfo [customer=" + customer + ", shippingAddress=" + shippingAddress + ", order=" + order
				+ ", orderItems=" + orderItems + "]";
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	
	
}
