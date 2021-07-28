package com.experis.shoppingcart.dto;

import java.util.List;

import com.experis.shoppingcart.entity.ProductEntity;
import com.experis.shoppingcart.entity.ShoppingCart;

public class ShoppingCartRequest {
	ShoppingCart shoppingCart;
	List<ProductEntity> products;
	
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public List<ProductEntity> getProducts() {
		return products;
	}
	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}
}
