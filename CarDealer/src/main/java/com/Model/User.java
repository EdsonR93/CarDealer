package com.Model;

import java.util.Arrays;

public class User {

    private int id;
    private boolean isEmployee;
    private String Username;
    private String Password;
    private String Name;
    private String Lastname;
    //TODO: Is it really necessary to store te cars he owned?
    //private Car[] CarsOwned;

    public User(boolean isEmployee, String username, String password, String name, String lastname) {
        this.isEmployee = isEmployee;
        this.Username = username;
        this.Password = password;
        this.Name = name;
        this.Lastname = lastname;
    }
    public User(int user_id, boolean isEmployee, String username, String password, String name, String lastname) {
        this.id = user_id;
        this.isEmployee = isEmployee;
        Username = username;
        Password = password;
        Name = name;
        Lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", isEmployee=" + isEmployee +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", Name='" + Name + '\'' +
                ", Lastname='" + Lastname + '\'' +
                '}';
    }
}
