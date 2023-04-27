package com.practice.inventory.repository;

import org.springframework.data.repository.CrudRepository;

import com.practice.inventory.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
