package com.experis.shoppingcart.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shoppingCart")
public class ShoppingCart {
	
	@Id
    private String id;
	private String clientName;	
	private Boolean already_paid;
	List<ProductEntity> products;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Boolean getAlready_paid() {
		return already_paid;
	}
	public void setAlready_paid(Boolean already_paid) {
		this.already_paid = already_paid;
	}
	public List<ProductEntity> getProducts() {
		return products;
	}
	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}	
}
