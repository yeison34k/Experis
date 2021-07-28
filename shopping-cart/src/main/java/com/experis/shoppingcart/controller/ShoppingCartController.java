package com.experis.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.experis.shoppingcart.dto.Params;
import com.experis.shoppingcart.dto.ShoppingCartRequest;
import com.experis.shoppingcart.entity.ProductEntity;
import com.experis.shoppingcart.entity.ShoppingCart;
import com.experis.shoppingcart.service.ShoppingCartService;

@RestController
public class ShoppingCartController {

	@Autowired
	ShoppingCartService service;

	@PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	Page<ProductEntity> getProducts(@RequestBody Params params) {
		return service.getAllProducts(params);
	}

	@PostMapping(value = "/crate-shopping-cart", produces = MediaType.APPLICATION_JSON_VALUE)
	ShoppingCart crateShoppingCart(@RequestBody ShoppingCartRequest params) {
		return service.createShoppingCard(params);
	}

	@RequestMapping(value = "/get-products-shopping-cart/{name}", method = RequestMethod.GET)
	List<ProductEntity> getShoppingCart(@PathVariable("name") String name) {
		return service.getProductsShoppingCard(name);
	}

	@RequestMapping("/delete-products/{name}")
	void cleanShoppingCart(@PathVariable("name") String name) {
		service.deleteProductsShoppingCard(name);
	}

	@RequestMapping("/pay/{name}")
	void payShoppingCart(@PathVariable("name") String name) {
		service.payProductsShoppingCard(name);
	}
}
