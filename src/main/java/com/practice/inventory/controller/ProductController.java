package com.practice.inventory.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.practice.inventory.entity.Category;
import com.practice.inventory.entity.Pricing;
import com.practice.inventory.entity.Product;
import com.practice.inventory.repository.CategoryRepository;
import com.practice.inventory.repository.PricingRepository;
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

  @Autowired
  PricingRepository pricingRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @GetMapping
  public ResponseEntity<List<Product>> getProducts() {

    List<Product> products = (List<Product>) productRepository.findAll();
    return ResponseEntity.ok(products);
  }

  @GetMapping("{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
    Optional<Product> item = productRepository.findById(id);

    if (item.isPresent()) {
      return ResponseEntity.ok(item.get());
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

      fillProduct(newProduct, item.getPriceId(), item.getCategories());

      Product savedProduct = productRepository.save(newProduct);
      return ResponseEntity.ok(savedProduct);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PutMapping("{id}")
  public ResponseEntity<Product> updateProduct(@NonNull @PathVariable("id") Long id, @RequestBody ProductDto item) {
    try {
      Optional<Product> existingProduct = productRepository.findById(id);
      if (existingProduct.isPresent()) {
        existingProduct.get().setProductName(item.getProductName());
        existingProduct.get().setProductDesc(item.getProductDesc());

        fillProduct(existingProduct.get(), item.getPriceId(), item.getCategories());

        Product savedProduct = productRepository.save(existingProduct.get());
        return ResponseEntity.ok(savedProduct);
      } else {
        return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  private Product fillProduct(Product to, Long priceId, Long[] categoryIds) {
    Pricing pricing = null;

    List<Category> categories = new ArrayList();

    if (priceId != null) {
      pricing = pricingRepository.findById(priceId).get();
    }

    if (categoryIds != null) {
      categories = (List<Category>) categoryRepository.findAllById(Arrays.asList(categoryIds));
    }
    to.setPrice(pricing);
    to.setCategories(categories);

    return to;
  }

}