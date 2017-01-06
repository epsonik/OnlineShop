package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.Registration.Registration;
import com.mateuszb.onlineShop.dao.*;
import com.mateuszb.onlineShop.dto.ContactData;
import com.mateuszb.onlineShop.dto.Form;
import com.mateuszb.onlineShop.dto.Role;
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

    @RequestMapping(value= "/registration", method=RequestMethod.GET)
    public String form(){
        return "registrationForm";
    }

    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String handleTheForm(@ModelAttribute("Form") @Valid Form form, BindingResult result){
        if(result.hasErrors()){
            return "registrationForm";
        } else {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");

            UserDAO userDAO = context.getBean(UserDAO.class);
            if (userDAO.checkExistingLogin(form.getLogin())) {
                // istnieje już taki użytkownik w bazie
                System.out.println("Trzeba coś z tym zrobić");
            } else {
                // dodajemy nowego użytkownika
                FormDAO formDAO = context.getBean(FormDAO.class);
                formDAO.insertForm(form);

                Registration registration = new Registration();
                registration.addNewUser(form.getLogin());
            }
            return "redirect:/";
        }
    }

    @ModelAttribute("Form")
    public Form getForm(){
        return new Form();
    }
}