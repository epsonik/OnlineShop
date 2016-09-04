package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.dto.Form;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@RequestMapping("/")
@Controller
public class RegistrationController {

    @RequestMapping(value="/rejestracja", method=RequestMethod.GET)
    public String form(){
        return "form";
    }

    @RequestMapping(value="/rejestracja", method=RequestMethod.POST)
    public String handleTheForm(@ModelAttribute("form") @Valid Form form, BindingResult result){
        if(result.hasErrors()){
            return "form";
        } else {
            return "redirect:/logowanie";
        }
    }

    @ModelAttribute("form")
    public Form getFormularz(){
        return new Form();
    }
}