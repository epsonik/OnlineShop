package com.mateuszb.onlineShop.controller;

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
    public String logout(Model model) {
        return "login";
    }

    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        String role = authentication.getAuthorities().toString();
        String targetUrl = "";
        if(role.contains("2")){
            targetUrl = "onlineAdminHome";
        } else if (role.contains("1")) {
            targetUrl = "onlineHome";
        } else {
            System.out.println("jestem tutaj i oznajmiam, że sie coś spieprzyło z uprawnieniami");
        }

        httpServletResponse.sendRedirect(targetUrl);
    }

    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("Błąd: " + e.getMessage());
        httpServletResponse.sendRedirect("loginfailed");
    }
}