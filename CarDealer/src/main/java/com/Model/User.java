package com.Model;

import java.util.Arrays;

public class User {

    private int id;
    private boolean isEmployee;
    private String Username;
    private String Password;
    private String Name;
    private String Lastname;
    private Car[] CarsOwned;

    public User(int id, boolean isEmployee, String username, String password, String name, String lastname, Car[] carsOwned) {
        this.id = id;
        this.isEmployee = isEmployee;
        Username = username;
        Password = password;
        Name = name;
        Lastname = lastname;
        CarsOwned = carsOwned;
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

    public Car[] getCarsOwned() {
        return CarsOwned;
    }

    public void setCarsOwned(Car[] carsOwned) {
        CarsOwned = carsOwned;
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
                //", CarsOwned=" + Arrays.toString(CarsOwned) +
                '}';
    }
}
