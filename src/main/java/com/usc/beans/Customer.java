package com.usc.beans;

import java.util.ArrayList;
import java.util.Collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.usc.beans.*;

@Entity
@Table(name= "customer")
public class Customer implements UserDetails  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq", sequenceName="customer_seq")
	@GeneratedValue(strategy= GenerationType.AUTO,  generator = "SEQ")
	private Long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name="last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable= false, unique=true)
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
	private Set<Order> orders = new HashSet<>();
	
	@Column(name="username", nullable=false, unique= true)
	private String username;
	
	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(name="customer_role", joinColumns = {
			@JoinColumn(name="customer_id", referencedColumnName = "id")}, inverseJoinColumns = {
					@JoinColumn(name="role_id", referencedColumnName="id") })
	private List<Role> roles = new ArrayList<>();


	public void add(Order order) {
		if (order != null) {
			if (orders == null) { 
				orders = new HashSet<>();
			}
			orders.add(order);
			order.setCustomer(this);
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return roles ;
	}
	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
	 return true;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return username;
	}

	public Long getId() {
		return id;
	}
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	


	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}


	public void setUsername(String userName) {
		this.username = userName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
}
