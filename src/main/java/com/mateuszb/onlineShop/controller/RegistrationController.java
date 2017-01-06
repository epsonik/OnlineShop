package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.dao.FormDAO;
import com.mateuszb.onlineShop.dao.LogsDAO;
import com.mateuszb.onlineShop.dao.RoleDAO;
import com.mateuszb.onlineShop.dto.Form;
import com.mateuszb.onlineShop.dto.Role;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("error2", "true");
        return "registrationForm";
    }
    @RequestMapping(value = "/mailfailed", method = RequestMethod.GET)
    public String mailerror(Model model) {
        model.addAttribute("error3", "true");
        return "registrationForm";
    }
    @RequestMapping(value = "/firstnamefailed", method = RequestMethod.GET)
    public String lastnameerror(Model model) {
        model.addAttribute("error4", "true");
        return "registrationForm";
    }
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String handleTheForm(@ModelAttribute("Form") @Valid Form form, BindingResult result){
        if(result.hasErrors()){
            return "registrationForm";
        } else {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");
            FormDAO formDAO = context.getBean(FormDAO.class);

            Validator validator = new Validator(form);
                    if(validator.checkLogin()){
                        return "redirect:/loginfailed";
                    }
                    if(validator.checkFirstName()){
                        return "redirect:/firstnamefailed";
                    }
                    if(validator.checkMail()){
                        return "redirect:/mailfailed";
                    }
            formDAO.insertForm(form);

            RoleDAO roleDAO = context.getBean(RoleDAO.class);
            Role role = new Role();
            role.setUser_id(formDAO.getIdByLogin(form.getLogin()));
            role.setRole_id(1);
            roleDAO.insertRole(role);

            LogsDAO logsDAO = context.getBean(LogsDAO.class);
            logsDAO.insert("Rejestracja prawidlowa. Zarejestrowano uzytkownika: name - " +
                    form.getFirstName() + " login - " + form.getLogin());

            return "redirect:/";
        }
    }

    @ModelAttribute("Form")
    public Form getFormularz(){
        return new Form();
    }
}