package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.Registration.Registration;
import com.mateuszb.onlineShop.dao.*;
import com.mateuszb.onlineShop.dto.ContactData;
import com.mateuszb.onlineShop.dto.Form;
import com.mateuszb.onlineShop.dto.Role;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/loginfailure", method = RequestMethod.GET)
    public String loginfailure(Model model) {
        model.addAttribute("loginerror", "true");
        return "registrationForm";
    }
    @RequestMapping(value = "/mailfailed", method = RequestMethod.GET)
    public String mailerror(Model model) {
        model.addAttribute("mailerror", "true");
        return "registrationForm";
    }
    @RequestMapping(value = "/firstnamefailed", method = RequestMethod.GET)
    public String lastnameerror(Model model) {
        model.addAttribute("firstnameerror", "true");
        return "registrationForm";
    }
    @RequestMapping(value = "/passwordfailure", method = RequestMethod.GET)
    public String passwordfailure(Model model) {
        model.addAttribute("passworderror", "true");
        return "registrationForm";
    }
    @RequestMapping(value = "/lastnamefailed", method = RequestMethod.GET)
    public String lastnamefailure(Model model) {
        model.addAttribute("lastnameerror", "true");
        return "registrationForm";
    }
    @RequestMapping(value = "/cityfailed", method = RequestMethod.GET)
    public String  ityfailure(Model model) {
        model.addAttribute("cityerror", "true");
        return "registrationForm";
    }
    @RequestMapping(value = "/streetfailed", method = RequestMethod.GET)
    public String streetfailure(Model model) {
        model.addAttribute("streeterror", "true");
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
                Validator validator = new Validator(form);
                if(validator.checkLogin()){
                    return "redirect:/loginfailure";
                }
                if(validator.checkPassword()){
                    return "redirect:/passwordfailure";
                }
                if(validator.checkFirstName()){
                    return "redirect:/firstnamefailed";
                }
                if(validator.checkLastName()){
                    return "redirect:/lastnamefailed";
                }
                if(validator.checkMail()){
                    return "redirect:/mailfailed";
                }
                if(validator.checkCity()){
                    return "redirect:/cityfailed";
                }
                if(validator.checkStreet()){
                    return "redirect:/streetfailed";
                }
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