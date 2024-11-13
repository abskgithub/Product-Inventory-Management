package com.example.InventoryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InventoryManagement.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}


