package com.myapp.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.myapp.spring.model.OrderEntity;

@Repository
public interface OrderRepository 
extends PagingAndSortingRepository<OrderEntity, Integer> {
	
	@Query(value="select o from OrderEntity o where o.status=?1 order by o.timeOrderPlaced")
	Page<OrderEntity> findByStatus(String status,Pageable pageable);


	@Modifying
	@Query("update OrderEntity o set o.status=?1,o.lastUpdate=?2 where o.id in (?3)")
	int updateStatus(String code,Date lastUpdate, List<Long> orderIds);
	
}











