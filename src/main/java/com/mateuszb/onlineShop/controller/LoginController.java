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

@Controller
public class LoginController {

    @RequestMapping(value="/logowanie", method= RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/logowanie", method=RequestMethod.POST)
    public String handleTheLogin(@ModelAttribute("Form") @Valid Form form, BindingResult result){
        if(result.hasErrors()){
            return "login";
        } else {
            ApplicationContext context = new ClassPathXmlApplicationContext("/Spring-module-form.xml");
            FormDAO formDAO = (FormDAO) context.getBean("formDAO");
            Form form1 =  formDAO.findByLogin(form.getLogin());

            ApplicationContext context1 = new ClassPathXmlApplicationContext("/Spring-module-role.xml");
            RoleDAO roleDAO = (RoleDAO) context1.getBean("roleDAO");

            if( form1.getPassword().equals(form.getPassword()) ){
                if(roleDAO.getRoleId(form1.getId()) == 1){
                    return "redirect:/onlineHome";
                } else {
                    return "redirect:/onlineAdminHome";
                }
            } else {
                return "login";
            }
        }
    }

    @ModelAttribute("Form")
    public Form getFormularz(){
        return new Form();
    }
}