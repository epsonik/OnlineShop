package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.dao.LogsDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(Model model) {
        model.addAttribute("error", "true");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "login";
    }

    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");
        LogsDAO logsDAO = context.getBean(LogsDAO.class);

        String role = authentication.getAuthorities().toString();
        String targetUrl = "";
        if(role.contains("2")){
            targetUrl = "products/add";
            logsDAO.insert("Administrator zalogowany prawidlowo");
        } else if (role.contains("1")) {
            targetUrl = "onlineHome";
            logsDAO.insert("Uzytkownik zalogowany prawidlowo.");
        } else {
            logsDAO.insert("Autentykacja prawidlowa. Blad uprawnien. Brak podanego uprawnienia.");
        }

        httpServletResponse.sendRedirect(targetUrl);
    }

    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");
        LogsDAO logsDAO = context.getBean(LogsDAO.class);
        logsDAO.insert("Blad logowania: " + e.getMessage());
        httpServletResponse.sendRedirect("loginfailed");
    }
}