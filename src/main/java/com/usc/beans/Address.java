package com.usc.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "address")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String street;
	
	@Column
	private String City;
	
	@Column
	private String state;
	
	@Column
	private String country;
	
	@Column(name="zip_code")
	private String zipCode;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	@JsonIgnore
	private Order order;
	
	

	public Address() {
		super();
	}



	public Address(Long id, String street, String city, String state, String country, String zipCode, Order order) {
		super();
		this.id = id;
		this.street = street;
		City = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.order = order;
	}



//	@Override
//	public String toString() {
//		return "Address [id=" + id + ", street=" + street + ", City=" + City + ", state=" + state + ", country="
//				+ country + ", zipCode=" + zipCode + ", order=" + order + "]";
//	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getCity() {
		return City;
	}



	public void setCity(String city) {
		City = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getZipCode() {
		return zipCode;
	}



	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}



	public Order getOrder() {
		return order;
	}



	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}
