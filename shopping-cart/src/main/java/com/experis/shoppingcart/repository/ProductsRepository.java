package com.experis.shoppingcart.repository;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.experis.shoppingcart.entity.ProductEntity;


public interface ProductsRepository extends MongoRepository<ProductEntity, Long>{ 
	@Query("{ $and: [{'nombre': {$regex: ?0 }}, {'marca': {$regex: ?1 }}, { 'precio' : { $gt: ?2, $lt: ?3 }} ]}")
	Page<ProductEntity> findAllByNombreAndMarca(String nombre, String marca, Long init, Long end, Pageable pageable);

	@Query("{'nombre': ?0}")
    Optional<ProductEntity> findByNombre(String nombre);
}
