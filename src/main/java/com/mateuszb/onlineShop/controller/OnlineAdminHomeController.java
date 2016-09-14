package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.domain.Product;
import com.mateuszb.onlineShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@Controller
public class OnlineAdminHomeController {

    @Autowired
    private  ProductService productService;

    @RequestMapping(value = "/onlineAdminHome",method = RequestMethod.GET)
    public String getAddNewProductForm(Model model){
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "onlineAdminHome";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result){
        productService.addProduct(newProduct);
        String[] suppressedFields =result.getSuppressedFields();
        if(suppressedFields.length>0){
            throw new RuntimeException("Proba wiązania niedozwolonych pol:" );
        }
        return "redirect:/onlineAdminHome";
    }

}
