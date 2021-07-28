package com.experis.shoppingcart.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.experis.shoppingcart.entity.ShoppingCart;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, Long> {
	@Query("{'clientName': ?0}")
    Optional<ShoppingCart> findByClientName(String nombre);
	
}
