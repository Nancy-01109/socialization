package com.example.nick.Register;

import androidx.room.Entity;

@Entity
public class User {
    private String Name;
    private String Surname;
    private String EmergencyContact;
    private String Email;
    private String Password;

    private  Integer id;

    public Integer getId() {
        return id;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmergencyContact() {
        return EmergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        EmergencyContact = emergencyContact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public User(String name, String surname, String emergencyContact, String email, String password) {
        Name = name;
        Surname = surname;
        EmergencyContact = emergencyContact;
        Email = email;
        this.Password = password;
    }

    public User(String email, String password) {
        Email = email;
        Password = password;
    }

    public User(String name, String surname, String emergencyContact, String email) {
        Name = name;
        Surname = surname;
        EmergencyContact = emergencyContact;
        Email = email;

    }

    public User(String name, String surname, String emergencyContact) {
        Name = name;
        Surname = surname;
        EmergencyContact = emergencyContact;
    }

    public User(String name, String surname, String email, Integer id) {
        Name = name;
        Surname = surname;
        Email = email;
        this.id = id;
    }
}
