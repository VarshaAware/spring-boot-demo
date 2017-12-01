package com.myapp.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.spring.model.CatalogItemEntity;
import com.myapp.spring.model.CustomerEntity;

@Repository
public interface CatalogItemRepository 
extends JpaRepository<CatalogItemEntity, Integer> {
	
	
	
}











