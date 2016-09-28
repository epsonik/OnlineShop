package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.domain.Cart;
import com.mateuszb.onlineShop.domain.CartItem;
import com.mateuszb.onlineShop.domain.Product;
import com.mateuszb.onlineShop.exception.ProductNotFoundException;
import com.mateuszb.onlineShop.service.CartService;
import com.mateuszb.onlineShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "rest/onlineCart")
public class CartRestController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Cart create(@RequestBody Cart cart) {
		return  cartService.create(cart);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public @ResponseBody
    Cart read(@PathVariable(value = "cartId") String cartId) {
		return cartService.read(cartId);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable(value = "cartId") String cartId, @RequestBody Cart cart) {
		cartService.update(cartId, cart);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "cartId") String cartId) {
		cartService.delete(cartId);
	}
	
	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable String productId, HttpServletRequest request) {
		
		String sessionId = request.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if(cart== null) {
			cart = cartService.create(new Cart(sessionId));
		}
		
		Product product = productService.getProductById(productId);
		if(product == null) {
			throw new IllegalArgumentException(new ProductNotFoundException(productId));
		}
		
		cart.addCartItem(new CartItem(product));
		
		cartService.update(sessionId, cart);
	}
	
	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable String productId, HttpServletRequest request) {
		
		String sessionId = request.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if(cart== null) {
			cart = cartService.create(new Cart(sessionId));
		}
		
		Product product = productService.getProductById(productId);
		if(product == null) {
			throw new IllegalArgumentException(new ProductNotFoundException(productId));
		}
		
		cart.removeCartItem(new CartItem(product));
		
		cartService.update(sessionId, cart);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST,  reason="Niepoprawne ��danie, sprawd� przesy�ane dane.")
	public void handleClientErrors(Exception ex) { }

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Wewn�trzny b��d serwera")
	public void handleServerErrors(Exception ex) {	}
}
