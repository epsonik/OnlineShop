package com.mateuszb.onlineShop.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by mbarto on 29.09.16.
 */
public class CartItemTest {
    private CartItem cartItem;
    @Before
    public void setup(){
        cartItem= new CartItem();
    }
    @Test
    public void setCartItem_price_should_be_equal_to_product_unit_price_in_case_of_single_quantity(){
        Product iphone= new Product("P1234", "iPhone 5s", new BigDecimal(500));
        cartItem.setProduct(iphone);
        BigDecimal totalPrice = cartItem.getTotalPrice();
        Assert.assertEquals(iphone.getUnitPrice(), totalPrice);
    }
}