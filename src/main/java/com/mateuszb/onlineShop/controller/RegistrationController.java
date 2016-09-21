package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.dao.FormDAO;
import com.mateuszb.onlineShop.dao.RoleDAO;
import com.mateuszb.onlineShop.dto.Form;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@RequestMapping("/")
@Controller
public class RegistrationController {

    @RequestMapping(value = "/registrationForm", method = RequestMethod.GET)
    public String form() {
        return "registrationForm";
    }

    @RequestMapping(value = "/registrationForm", method = RequestMethod.POST)
    public String handleTheForm(@ModelAttribute("Form") @Valid Form form, BindingResult result) {
        if (result.hasErrors()) {
            return "registrationForm";
        } else {
            ApplicationContext context = new ClassPathXmlApplicationContext("/Spring-module-form.xml");
            FormDAO formDAO = (FormDAO) context.getBean("formDAO");
            formDAO.insert(form);

            ApplicationContext context1 = new ClassPathXmlApplicationContext("/Spring-module-role.xml");
            RoleDAO roleDAO = (RoleDAO) context1.getBean("roleDAO");
            roleDAO.insert(formDAO.findByLogin(form.getLogin()).getLogin());

            return "redirect:/";
        }
    }

    @ModelAttribute("Form")
    public Form getFormularz() {
        return new Form();
    }
}