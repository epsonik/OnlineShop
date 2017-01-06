package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.dto.Form;

/**
 * Created by mbarto on 29.12.16.
 */
public class Validator {
    Form form;

    public Validator(Form form) {
        this.form = form;
    }
    public boolean checkLogin(){
        if (form.getLogin().equals("mbarto")){
            return true;
        }
        return false;
    }
    public boolean checkFirstName(){
        if (form.getLastName().equalsIgnoreCase("rzedkowski")){
            return true;
        }
        return false;
    }    public boolean checkMail(){
        if (form.getEmail().contains("gmail")){
            return true;
        }
        return false;
    }
}
