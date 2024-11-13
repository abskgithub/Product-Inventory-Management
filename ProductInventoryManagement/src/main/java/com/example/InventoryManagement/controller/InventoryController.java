package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.model.Buyer;
import com.example.InventoryManagement.model.ProductOrder;
import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PutMapping("/product")
    public ResponseEntity<Product> addOrUpdateProduct(@RequestBody Product product) {
        Product savedProduct = inventoryService.addOrUpdateProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/buyer")
    public ResponseEntity<Buyer> addBuyer(@RequestBody Buyer buyer) {
        Buyer savedBuyer = inventoryService.addBuyer(buyer);
        return ResponseEntity.ok(savedBuyer);
    }

    @PostMapping("/product/{productId}/buyer/{buyerId}/order")
    public ResponseEntity<String> orderProduct(
            @PathVariable Long productId,
            @PathVariable Long buyerId,
            @RequestBody int quantity) {
        String result = inventoryService.orderProduct(productId, buyerId, quantity);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/product/{productId}/stockInHand")
    public ResponseEntity<Integer> getStockInHand(@PathVariable Long productId) {
        return inventoryService.getProductStock(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/product/order")
    public ResponseEntity<List<ProductOrder>> listAllOrders() {
        List<ProductOrder> orders = inventoryService.listAllOrders();
        return ResponseEntity.ok(orders);
    }
}
