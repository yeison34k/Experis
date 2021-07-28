package com.experis.load.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.experis.load.entity.ProductEntity;

public interface ProductsRepository extends MongoRepository<ProductEntity, Long>{ }
