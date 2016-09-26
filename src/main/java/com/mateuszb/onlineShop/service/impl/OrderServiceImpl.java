package com.mateuszb.onlineShop.service.impl;

import com.mateuszb.onlineShop.domain.Product;
import com.mateuszb.onlineShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import com.mateuszb.onlineShop.domain.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = null;

	public OrderServiceImpl(){
		ApplicationContext context = new ClassPathXmlApplicationContext("/Spring-module-product.xml");
		productRepository = (ProductRepository) context.getBean("productDAO");
	}

	public void processOrder(String productId, long quantity) {
		Product productById = productRepository.getProductById(productId);

		if(productById.getUnitsInStock() < quantity){
			throw new IllegalArgumentException("Zbyt mało towaru. Obecna liczba sztuk w magazynie "+ productById.getUnitsInStock());
		}
		
		productById.setUnitsInStock(productById.getUnitsInStock() - quantity);
	}
	public void processName(String productId, String name2){
        Product productById = productRepository.getProductById(productId);
        productById.setName(name2);
    }
}
