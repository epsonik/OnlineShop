package com.mateuszb.onlineShop.controller;

import com.mateuszb.onlineShop.dto.Form;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mbarto on 29.12.16.
 */
public class Validator {
    Form form;

    public Validator(Form form) {
        this.form = form;
    }
    public boolean checkPassword(){
        if (5>form.getPassword().length() || 20<form.getPassword().length()){
            return true;
        }
        return false;
    }
    public boolean checkLogin(){
        if (5>form.getLogin().length() || 20<form.getLogin().length()){
            return true;
        }
        return false;
    }
    public boolean checkFirstName(){
        if (3>form.getFirstName().length() || 20<form.getFirstName().length()){
            return true;
        }
        return false;
    }

    public boolean checkLastName(){
        if (3>form.getLastName().length() || 20<form.getLastName().length()){
            return true;
        }
        return false;
    }
    public boolean checkStreet(){
        if (3>form.getStreet().length() || 20<form.getStreet().length()){
            return true;
        }
        return false;
    }
    public boolean checkMail(){
        String line = form.getEmail();
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        if (!m.find( )) {
            return true;
        }
        return false;
    }
    public boolean checkCity(){
        if (3>form.getCity().length() || 20<form.getCity().length()){
            return true;
        }
        return false;
    }
}
