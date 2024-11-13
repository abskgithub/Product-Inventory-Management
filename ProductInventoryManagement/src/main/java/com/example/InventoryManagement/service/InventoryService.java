package com.example.InventoryManagement.service;

import com.example.InventoryManagement.model.Buyer;
import com.example.InventoryManagement.model.ProductOrder;
import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.repository.BuyerRepository;
import com.example.InventoryManagement.repository.ProductOrderRepository;
import com.example.InventoryManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public Product addOrUpdateProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findById(product.getProductId());
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setInStockQuantity(updatedProduct.getInStockQuantity() + product.getInStockQuantity());
            return productRepository.save(updatedProduct);
        } else {
            return productRepository.save(product);
        }
    }

    public Buyer addBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    public String orderProduct(Long productId, Long buyerId, int quantity) {
        Optional<Product> productOpt = productRepository.findById(productId);
        Optional<Buyer> buyerOpt = buyerRepository.findById(buyerId);

        if (productOpt.isPresent() && buyerOpt.isPresent()) {
            Product product = productOpt.get();
            if (product.getInStockQuantity() >= quantity) {
                product.setInStockQuantity(product.getInStockQuantity() - quantity);
                productRepository.save(product);

                ProductOrder productOrder = new ProductOrder(buyerOpt.get(), product, quantity);
                productOrderRepository.save(productOrder);

                return "Order placed successfully";
            } else {
                return "Insufficient stock";
            }
        } else {
            return "Product or Buyer not found";
        }
    }

    public Optional<Integer> getProductStock(Long productId) {
        return productRepository.findById(productId).map(Product::getInStockQuantity);
    }

    public List<ProductOrder> listAllOrders() {
        return productOrderRepository.findAll();
    }
}
