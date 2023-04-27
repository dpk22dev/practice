package com.practice.inventory.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

import com.practice.inventory.entity.Product;
import com.practice.inventory.repository.ProductRepository;

import dto.ProductDto;

/**
 * InventoryController
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

  @Autowired
  ProductRepository productRepository;

  @GetMapping
  public ResponseEntity<Product> getProduct() {
    Optional<Product> Product = Optional.empty();

    Product = Optional.of(new Product());

    if (Product.isPresent()) {
      return ResponseEntity.ok(Product.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("")
  public ResponseEntity<Product> createProduct(@Validated @RequestBody ProductDto item) {
    try {
      Product newProduct = new Product();
      newProduct.setProductId(UUID.randomUUID());
      newProduct.setProductName(item.getProductName());
      newProduct.setProductDesc(item.getProductDesc());
      Product savedProduct = productRepository.save(newProduct);
      return ResponseEntity.ok(savedProduct);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

}