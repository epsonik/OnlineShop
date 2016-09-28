package com.mateuszb.onlineShop.domain.repository;


import com.mateuszb.onlineShop.domain.Cart;

public interface CartRepository {

	Cart create(Cart cart);
	
	Cart read(String cartId);
	
	void update(String cartId, Cart cart);
	
	void delete(String cartId);

}
