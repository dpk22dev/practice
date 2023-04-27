package com.practice.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practice.inventory.entity.Pricing;

@Repository
public interface PricingRepository extends CrudRepository<Pricing, Long> {

}
