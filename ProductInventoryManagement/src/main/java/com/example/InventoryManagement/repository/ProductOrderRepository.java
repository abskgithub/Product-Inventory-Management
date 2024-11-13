package com.example.InventoryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InventoryManagement.model.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}



