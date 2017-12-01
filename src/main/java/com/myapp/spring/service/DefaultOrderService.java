package com.myapp.spring.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.spring.model.CatalogItemEntity;
import com.myapp.spring.model.CustomerEntity;
import com.myapp.spring.model.OrderEntity;
import com.myapp.spring.repository.CatalogItemRepository;
import com.myapp.spring.repository.CustomerRepository;
import com.myapp.spring.repository.OrderItemRepository;
import com.myapp.spring.repository.OrderRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = HibernateException.class)
public class DefaultOrderService implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CatalogItemRepository catalogItemRepository;

	public void save(List<CustomerEntity> customers) {
		customerRepository.save(customers);

	}

	@Override
	public List<OrderEntity> getOrderDetails() {
		// TODO Auto-generated method stub
		List<OrderEntity> list = new ArrayList<>();
		orderRepository.findAll().forEach(list::add);
		return list;
	}

	@Override
	public void save(OrderEntity orderEntity) {
		// TODO Auto-generated method stub
		orderRepository.save(orderEntity);
	}

	@Override
	public void saveCatalogItems(List<CatalogItemEntity> catalogs) {
		catalogItemRepository.save(catalogs);

	}

	@Override
	public void processOrderStatusUpdate(List<OrderEntity> orders, String status) {
		// TODO Auto-generated method stub
		// List<Long> orderIds=new ArrayList<>();
		List<Long> orderIds = orders.stream().map(o -> o.getId()).collect(Collectors.toList());

		orderRepository.updateStatus(status, new Date(), orderIds);
	}

	@Override
	public void updateCustomer(long id, CustomerEntity customer)
			throws IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		CustomerEntity existing = customerRepository.findOne(Long.valueOf(id));
		BeanUtils.copyProperties(existing, customer);
		customerRepository.save(existing);

	}

	@Override
	public CustomerEntity findCustomer(long id) {
		// TODO Auto-generated method stub
		return customerRepository.findOne(Long.valueOf(id));
	}

}
