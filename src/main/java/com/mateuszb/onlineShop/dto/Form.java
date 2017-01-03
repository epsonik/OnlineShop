package com.mateuszb.onlineShop.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER_TABLE")
public class Form {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    @Size(min=3, max=20)
    private String firstName;

    @Column(name = "lastName")
    @Size(min=3, max=20)
    private String lastName;

    @Column(name = "email")
    @Pattern(regexp ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$")
    private String email;

    @Column(name = "login")
    @NotEmpty
    @Size(min=5,max=20)
    private String login;

    @Column(name = "password")
    @NotEmpty
    @Size(min=5,max=20)
    private String password;

    public void setId(int id) {
        this.id = id;
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