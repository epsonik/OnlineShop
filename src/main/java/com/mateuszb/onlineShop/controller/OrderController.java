package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/P1234/2")
    public String process() {
        orderService.processOrder("P1234", 2);
        return "redirect:/products";
    }

    @RequestMapping("/order/P1235/2")
    public String process2() {
        orderService.processOrder("P1235", 2);
        return "redirect:/products";
    }

    @RequestMapping("/changeName/P1235/jolo")
    public String process3() {
        orderService.processName("P1235", "jolo");
        return "redirect:/products";
    }
}