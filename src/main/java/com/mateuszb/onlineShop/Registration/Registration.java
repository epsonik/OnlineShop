package com.mateuszb.onlineShop.Registration;

import com.mateuszb.onlineShop.dao.*;
import com.mateuszb.onlineShop.dto.ContactData;
import com.mateuszb.onlineShop.dto.Form;
import com.mateuszb.onlineShop.dto.Role;
import com.mateuszb.onlineShop.dto.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Registration {

    public void addNewUser(String userLogin) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");
        FormDAO formDAO = context.getBean(FormDAO.class);
        RoleDAO roleDAO = context.getBean(RoleDAO.class);
        UserDAO userDAO = context.getBean(UserDAO.class);
        ContactDataDAO contactDataDAO = context.getBean(ContactDataDAO.class);

        Form data = formDAO.getDataByLogin(userLogin);

        User user = new User();
        user.setLogin(data.getLogin());
        user.setPassword(data.getPassword());
        userDAO.insertNewUserToDatabase(user);

        int userID = userDAO.getIdByLogin(data.getLogin());

        Role role = new Role();
        role.setUser_id(userID);
        role.setRole_id(1);
        roleDAO.insertRole(role);

        ContactData contactData = new ContactData();
        contactData.setUser_id(userID);
        contactData.setFirstName(data.getFirstName());
        contactData.setLastName(data.getLastName());
        contactData.setEmail(data.getEmail());
        contactData.setStreet(data.getStreet());
        contactData.setCity(data.getCity());
        contactDataDAO.insertContactData(contactData);

        LogsDAO logsDAO = context.getBean(LogsDAO.class);
        logsDAO.insert("Dodano nowego użytkownika o loginie: " + data.getLogin());
    }
}
