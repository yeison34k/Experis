package com.experis.shoppingcart.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.experis.shoppingcart.dto.Params;
import com.experis.shoppingcart.dto.ShoppingCartRequest;
import com.experis.shoppingcart.entity.ProductEntity;
import com.experis.shoppingcart.entity.ShoppingCart;
import com.experis.shoppingcart.repository.ProductsRepository;
import com.experis.shoppingcart.repository.ShoppingCartRepository;
import com.experis.shoppingcart.service.ShoppingCartService;

@Service
public class CartServiceImpl implements ShoppingCartService {
	@Autowired
	ProductsRepository repo;

	@Autowired
	ShoppingCartRepository repoCar;

	@Override
	public Page<ProductEntity> getAllProducts(Params params) {
		Pageable requestedPage = PageRequest.of(params.getCurrentPage(), params.getTotal());
		return repo.findAllByNombreAndMarca(params.getFilter(), params.getMarca(), params.getStart(), params.getLimit(),
				requestedPage);
	}

	@Override
	public ShoppingCart createShoppingCard(ShoppingCartRequest shoppingCart) {
		List<ProductEntity> products = new ArrayList<ProductEntity>();
		
		shoppingCart.getProducts().forEach(x-> {
			Optional<ProductEntity> res = repo.findByNombre(x.getNombre());
			if(res.isPresent() && res.get().getStock() > 0)
				products.add(x);
		});
		
		Optional<ShoppingCart> exist = repoCar.findByClientName(shoppingCart.getShoppingCart().getClientName());
		if(exist.isPresent()) {
			exist.get().setProducts(products);
			return repoCar.save(exist.get());
		}
		
		shoppingCart.getShoppingCart().setProducts(products);
		return repoCar.save(shoppingCart.getShoppingCart());
	}
	
	@Override
	public List<ProductEntity> getProductsShoppingCard(String myCart) {
		Optional<ShoppingCart> res = repoCar.findByClientName(myCart);
		if(res.isPresent())
			return res.get().getProducts();
		
		return null;
	}
	
	@Override
	public void deleteProductsShoppingCard(String myCart) {
		Optional<ShoppingCart> res = repoCar.findByClientName(myCart);
		res.get().getProducts().clear();
		repoCar.save(res.get());
	}
	
	
	public void payProductsShoppingCard(String myCart) {
		Optional<ShoppingCart> res = repoCar.findByClientName(myCart);
		
		res.get().getProducts().forEach(x-> {
			Optional<ProductEntity> re = repo.findByNombre(x.getNombre());
			if(re.isPresent()) {
				re.get().setStock(re.get().getStock()-1);
				repo.save(re.get());
			}	
		});
		
		res.get().setAlready_paid(true);
		repoCar.save(res.get());
	}
}
