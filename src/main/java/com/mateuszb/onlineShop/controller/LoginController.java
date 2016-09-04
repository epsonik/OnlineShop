package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.dto.Form;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginController {

    @RequestMapping(value="/logowanie", method= RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/logowanie", method=RequestMethod.POST)
    public String handleTheLogin(@ModelAttribute("form") @Valid Form form, BindingResult result){
        if(result.hasErrors()){
            return "login";
        } else {
            return "redirect:/poZalogowaniu";
        }
    }
}
