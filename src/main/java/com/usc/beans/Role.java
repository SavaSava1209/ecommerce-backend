package com.usc.beans;

import org.springframework.security.core.GrantedAuthority;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column
	private String type;
	
	
	public Role(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	
	public Role() {
		super();
	}
	

	public Role(int id) {
		super();
		this.id = id;
	}
	
	@Override
	public String getAuthority() {
		return type;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
