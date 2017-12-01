package com.myapp.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="customer")
@SequenceGenerator(name="customer_id_seq",sequenceName="customer_id_seq")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id",scope=CustomerEntity.class)
@JsonIgnoreProperties("inspection")
public class CustomerEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="customer_id_seq")
	private long id;
	
	@Column(nullable=false,name="firstName")
	private String firstName;
	
	@Column(nullable=false,name="lastName")
	private String lastName;
	
	@Column(nullable=false,name="email")
	private String email;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	//@JsonManagedReference
	private Set<OrderEntity> orders=new HashSet<>();
	
	public CustomerEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomerEntity(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}





	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Set<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderEntity> orders) {
		this.orders = orders;
	}
	
	
	

}
