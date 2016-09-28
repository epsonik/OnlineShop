package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.domain.Product;
import com.mateuszb.onlineShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private  ProductService productService;

	@RequestMapping
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("/all")
	public ModelAndView allProducts() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("products", productService.getAllProducts());
		modelAndView.setViewName("products");
		return modelAndView;
	}

	@RequestMapping("/{category}")
	public String getProductsByCategory(@PathVariable("category") String productCategory, Model model) {
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}

	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(@MatrixVariable(pathVar="ByCriteria") Map<String,List<String>> filterParams, Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}

	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}

	@RequestMapping("/onlineProduct")
	public String getOnlineProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "onlineProduct";
	}

	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String getAddNewProductForm(Model model){
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		model.addAttribute("user", getPrincipal());
		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product productToBeAdded,
										   ModelMap map, BindingResult result, HttpServletRequest request) {
		String[] suppressedFields = result.getSuppressedFields();

		if (suppressedFields.length > 0) {
			throw new RuntimeException("Pr�ba wi�zania niedozwolonych p�l: " + org.springframework.util.StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		MultipartFile productImage = productToBeAdded.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		if (productImage!=null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(rootDirectory+"resources\\images\\"+productToBeAdded.getProductId() + ".png"));
			} catch (Exception e) {
				throw new RuntimeException("Pr�ba zapisu obrazka zako�czona niepowodzeniem", e);
			}
		} else {
			System.out.println("GÓWNO NIE DZIAŁA");
		}

		productService.addProduct(productToBeAdded);

		return "redirect:/products/add";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("productId","name","unitPrice","description","manufacturer","category","unitsInStock", "condition","productImage");
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}


