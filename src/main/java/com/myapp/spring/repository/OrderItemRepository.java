package com.myapp.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myapp.spring.model.OrderItemEntity;

@Repository
public interface OrderItemRepository 
extends JpaRepository<OrderItemEntity, Integer> {
	
	@Query(value="select o from OrderItemEntity o where o.order.id=?1")
	List<OrderItemEntity> findByOrderId(long orderId);


	@Modifying
	@Query("update OrderItemEntity o set o.status=?1,o.lastUpdate=?2 where o.order.id in (?3)")
	int updateStatus(String code,Date lastUpdate, List<Long> orderIds);
	
}











