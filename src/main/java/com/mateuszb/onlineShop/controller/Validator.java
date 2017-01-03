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
    public boolean chceckPassword(){
        if (form.getLogin()==""){
            return false;
        }
        return true;
    }
}
