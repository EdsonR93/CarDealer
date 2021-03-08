package com.ui;

import com.Model.User;
import com.database.DataBaseServices;

import java.util.Scanner;

public class UserServices {
    Scanner scan = new Scanner(System.in);
    DataBaseServices DB = DataBaseServices.getInstance();

    private UserServices(){}
    private static UserServices instance;
    public static UserServices getInstance(){
        if(instance == null){
            instance = new UserServices();
        }
        return instance;
    }
    
    public User showLoginForm (){
        String username,password;

        System.out.println("Username:");
        username = scan.nextLine();
        System.out.println("Password:");
        password = scan.nextLine();


        return DB.Login(username,password);
    }

    public User showRegisterForm (){
        String username, password, name, lastname;

        System.out.println("Create username:");
        username = scan.nextLine();
        if(DB.checkForUsername(username))
            System.out.println("Username already in use, select another one");
        else
            System.out.println("Username is available");

        System.out.println("Create password:");
        password = scan.nextLine();
        //TODO: validate password format??

        System.out.println("First name:");
        name = scan.nextLine();

        System.out.println("Last name:");
        lastname = scan.nextLine();

        return new User(username,password,name,lastname);
    }

    public boolean AddNewUser(User newUser){
        DB.addNewUser(newUser);
        return DB.checkForUsername(newUser.getUsername());
    }
}
