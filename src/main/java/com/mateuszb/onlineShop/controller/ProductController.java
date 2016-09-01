package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.domain.repository.ProductRepository;
import com.mateuszb.onlineShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	@RequestMapping("/all")
	public String allProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	@RequestMapping("/{category}")
	public String getProductByCategory(Model model, @PathVariable("category") String productCategory){
		model.addAttribute("products",productService.getProductByCategory((productCategory)));
		return "products";
}
	@RequestMapping("/filter/{ByCritieria}")
	public String getProductsByFilter(@MatrixVariable (pathVar = "ByCriteria")
		Map<String, List<String>> filterParams, Model model){
		model.addAttribute("products", productService.getProductByFilter(filterParams));
		return "products";
	}
}
