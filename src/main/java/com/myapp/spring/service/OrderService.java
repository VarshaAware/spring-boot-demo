package com.myapp.spring.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.myapp.spring.model.CatalogItemEntity;
import com.myapp.spring.model.CustomerEntity;
import com.myapp.spring.model.OrderEntity;

public interface OrderService {

	List<OrderEntity> getOrderDetails();

	void save(OrderEntity orderEntity);

	void save(List<CustomerEntity> customers);

	void saveCatalogItems(List<CatalogItemEntity> catalogs);

	void processOrderStatusUpdate(List<OrderEntity> orders, String status);


	void updateCustomer(long id, CustomerEntity customer) throws IllegalAccessException, InvocationTargetException;

	CustomerEntity findCustomer(long id);

	

}
