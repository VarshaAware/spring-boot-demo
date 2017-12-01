package com.myapp.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="myorder")
@SequenceGenerator(name="order_id_seq",sequenceName="order_id_seq")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id",scope=OrderEntity.class)
@JsonIgnoreProperties("inspection")
public class OrderEntity implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="order_id_seq")
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TIMEMYORDERPLACED",nullable=false)
	private Date timeOrderPlaced;


	@Column(name="orderNumber",nullable=false)
	private String orderNumber;

	@Temporal(TemporalType.TIMESTAMP)
	
	@Column(name="lastUpdate",nullable=false,length=29)
	private Date lastUpdate;
	
	@Column(name="status",nullable=false)
	private String status;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="order",cascade=CascadeType.ALL)
	//@JsonManagedReference
	private Set<OrderItemEntity> orderItems=new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="customer_id",nullable=true)
	private CustomerEntity customer;
	
	
	public OrderEntity() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public OrderEntity(Date timeOrderPlaced, String orderNumber, Date lastUpdate, String status) {
		this.timeOrderPlaced = timeOrderPlaced;
		this.orderNumber = orderNumber;
		this.lastUpdate = lastUpdate;
		this.status = status;
	}




	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getTimeOrderPlaced() {
		return timeOrderPlaced;
	}

	public void setTimeOrderPlaced(Date timeOrderPlaced) {
		this.timeOrderPlaced = timeOrderPlaced;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	
	public Set<OrderItemEntity> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItemEntity> orderItems) {
		this.orderItems = orderItems;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", timeOrderPlaced=" + timeOrderPlaced + ", orderNumber=" + orderNumber
				+ ", lastUpdate=" + lastUpdate + ", status=" + status + "]";
	}
	
	
	
	
}















