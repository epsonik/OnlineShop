package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.dao.FormDAO;
import com.mateuszb.onlineShop.dao.RoleDAO;
import com.mateuszb.onlineShop.dto.Form;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /* @RequestMapping(value="/logowanie", method=RequestMethod.POST)
    public String handleTheLogin(@ModelAttribute("Form") @Valid Form form, BindingResult result){
        if(result.hasErrors()){
            return "login";
        } else {
            ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/views/Spring-module-form.xml");
            FormDAO formDAO = (FormDAO) context.getBean("formDAO");
            Form form1 =  formDAO.findByLogin(form.getLogin());

            ApplicationContext context1 = new ClassPathXmlApplicationContext("/WEB-INF/views/Spring-module-role.xml");
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
    } */

    @ModelAttribute("Form")
    public Form getFormularz() {
        return new Form();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
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