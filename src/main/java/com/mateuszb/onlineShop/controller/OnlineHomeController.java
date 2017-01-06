package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.dao.ContactDataDAO;
import com.mateuszb.onlineShop.dao.UserDAO;
import com.mateuszb.onlineShop.domain.repository.ProductRepository;
import com.mateuszb.onlineShop.dto.ContactData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OnlineHomeController {

    private final ProductRepository productRepository;

    @Autowired
    public OnlineHomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value="/onlineHome")
    public String welcome(Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");
        UserDAO userDAO = context.getBean(UserDAO.class);
        ContactDataDAO contactDataDAO = context.getBean(ContactDataDAO.class);

        model.addAttribute("products", productRepository.getAllProducts());
        model.addAttribute("user", getPrincipal());
        model.addAttribute("contactData", contactDataDAO.getContactDataByUserID(userDAO.getIdByLogin(getPrincipal())));

        return "onlineHome";
    }

    private String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}