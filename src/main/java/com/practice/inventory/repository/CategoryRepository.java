package com.practice.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practice.inventory.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
