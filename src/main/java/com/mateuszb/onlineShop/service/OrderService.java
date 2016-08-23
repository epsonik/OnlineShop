package com.mateuszb.onlineShop.service;

public interface OrderService {
	
	void processOrder(String productId, long quantity);
	void processName(String prductId, String name2);
}
