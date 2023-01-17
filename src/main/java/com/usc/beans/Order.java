package com.usc.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


@Entity
@Table(name= "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "order_tracking_number")
	private String orderTrackingNumber;
	
	@Column(name="total_price")
	private BigDecimal totalPrice;
	
	@Column(name = "total_quantity")
	private int totalQuantity;	
	
	@Column(name="status")
	private String status;
	
	@Column(name="date_created")
	@CreationTimestamp
	private Date dateCreated;
	
	@Column(name="last_updated")
	@UpdateTimestamp
	private Date lastUpdated;	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "shipping_address_id", referencedColumnName="id")
	private Address shippingAddress;
	
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName="id")
	@JsonIgnore
	private Customer customer;
	
	@OneToMany(mappedBy="order", cascade= CascadeType.ALL)
	private Set<OrderItem> orderItems = new HashSet<>(); 
	
	
	public void add(OrderItem orderItem) {
		
		if (orderItem != null) {
			if (orderItems == null) {
				orderItems = new HashSet<>();			
			}
			this.orderItems.add(orderItem);
			orderItem.setOrder(this);
		}
		
	}

//	@Override
//	public String toString() {
//		return "Order [id=" + id + ", orderTrackingNumber=" + orderTrackingNumber + ", totalPrice=" + totalPrice
//				+ ", totalQuantity=" + totalQuantity + ", customer=" + customer + ", shippingAddress="
//				+ shippingAddress + ", status=" + status + ", dateCreated=" + dateCreated + ", lastUpdated="
//				+ lastUpdated + "]";
//	}

	public Order(Long id, String orderTrackingNumber, BigDecimal totalPrice, int totalQuantity, Customer customer,
			Address shippingAddress, String status, Date dateCreated, Date lastUpdated) {
		super();
		this.id = id;
		this.orderTrackingNumber = orderTrackingNumber;
		this.totalPrice = totalPrice;
		this.totalQuantity = totalQuantity;
		this.customer = customer;
		this.shippingAddress = shippingAddress;
		this.status = status;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
	}
	
	

	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	
		
	
}
