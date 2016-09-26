package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private ProductRepository productRepository = null;

    public HomeController(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/Spring-module-product.xml");
        productRepository = (ProductRepository) context.getBean("productDAO");
    }

    @RequestMapping(value="/")
	public String welcome(Model model) {
        model.addAttribute("products", productRepository.getAllProducts());
		return "home";
	}
}