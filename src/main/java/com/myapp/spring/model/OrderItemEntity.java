package com.myapp.spring.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="orderItem")
@SequenceGenerator(name="orderitem_id_seq",sequenceName="orderitem_id_seq")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id",scope=OrderItemEntity.class)
@JsonIgnoreProperties("inspection")
public class OrderItemEntity  implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="orderitem_id_seq")
	private long id;
	
	@Column(name="status",nullable=false)
	private String status;
	
	@Column(name="price",precision=20,scale=5)
	private BigDecimal price;
	
	@Temporal(TemporalType.DATE)
	@Column(name="lastUpdate",nullable=false,length=29)
	private Date lastUpdate;
	
	@Column(name="quantity",nullable=false)
	private int quantity;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id",nullable=true)
	//@JsonBackReference
	private OrderEntity order;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="catalogItem_id",nullable=true)
	//@JsonBackReference
	private CatalogItemEntity catalogItem;
	
	public OrderItemEntity() {
		// TODO Auto-generated constructor stub
	}
	
	

	public OrderItemEntity(String status, BigDecimal price, Date lastUpdate, int quantity) {
		this.status = status;
		this.price = price;
		this.lastUpdate = lastUpdate;
		this.quantity = quantity;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}
	
	
	
	
	
}





