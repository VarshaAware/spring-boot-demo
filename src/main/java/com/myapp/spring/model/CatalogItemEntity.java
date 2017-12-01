package com.myapp.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
@Table(name="catalogItem")
@SequenceGenerator(name="catalog_id_seq",sequenceName="catalog_id_seq")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id",scope=CatalogItemEntity.class)
@JsonIgnoreProperties("inspection")
public class CatalogItemEntity implements Serializable {
	
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="catalog_id_seq")
	private long id;
	
	@Column(name="itemNumber",nullable=false)
	private String itemNumber;
	
	@Column(name="itemName",nullable=false)
	private String itemName;
	
	@Column(name="itemType",nullable=false)
	private String itemType;

	@OneToMany(mappedBy="catalogItem")
	//@JsonManagedReference
	private Set<OrderItemEntity> orderItems=new HashSet<>();
	
	public CatalogItemEntity() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public CatalogItemEntity(String itemNumber, String itemName, String itemType) {
		this.itemNumber = itemNumber;
		this.itemName = itemName;
		this.itemType = itemType;
	}




	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Set<OrderItemEntity> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItemEntity> orderItems) {
		this.orderItems = orderItems;
	}

	
	
	
}



