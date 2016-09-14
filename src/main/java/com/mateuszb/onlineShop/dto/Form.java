package com.mateuszb.onlineShop.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.search.bridge.String2FieldBridgeAdaptor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.ModelAttribute;

public class Form {

    private int id;

    @Size(min=3, max=20)
    private String firstName;

    @Size(min=3, max=20)
    private String lastName;

    @Email
    private String email;

    @NotEmpty
    @Size(min=5,max=20)
    private String login;

    @NotEmpty
    @Size(min=5,max=20)
    private String password;

    public Form(){};

    public Form(String firstName, String lastName, String email, String login, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public Form(int id, String firstName, String lastName, String email, String login, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public int getId(){ return id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "Imie: " + firstName + " nazwisko: " + lastName + " email: " + email + " login: " + login + " hasło: " + password;
    }
}
