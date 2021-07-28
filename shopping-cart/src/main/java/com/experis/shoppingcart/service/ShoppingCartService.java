package com.experis.shoppingcart.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.experis.shoppingcart.dto.Params;
import com.experis.shoppingcart.dto.ShoppingCartRequest;
import com.experis.shoppingcart.entity.ProductEntity;
import com.experis.shoppingcart.entity.ShoppingCart;

public interface ShoppingCartService {
	public Page<ProductEntity> getAllProducts(Params params);

	public ShoppingCart createShoppingCard(ShoppingCartRequest shoppingCart);
	
	public List<ProductEntity> getProductsShoppingCard(String myCart);
	
	public void deleteProductsShoppingCard(String myCart);
	
	public void payProductsShoppingCard(String myCart);
}
