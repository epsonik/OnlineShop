package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private final ProductRepository productRepository;

    @Autowired
    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value="/")
	public String welcome(Model model) {
		model.addAttribute("products", productRepository.getAllProducts());
		return "home";
	}
}