package com.ui;

import com.Model.User;
import com.database.DBHandler;

import java.util.Scanner;

public class UserServices {
    Scanner scan = new Scanner(System.in);
    private final DBHandler DB = DBHandler.INSTANCE;

    private UserServices(){}
    private static UserServices instance;
    public static UserServices getInstance(){
        if(instance == null){
            instance = new UserServices();
        }
        return instance;
    }
    
    public User ShowLoginForm(){
        String username,password;

        System.out.println("Username:");
        username = scan.nextLine();
        System.out.println("Password:");
        password = scan.nextLine();


        return DB.Login(username,password);
    }

    public User ShowRegisterForm(){
        String username, password, name, lastname;

        System.out.println("Create username:");
        username = scan.nextLine();
        if(DB.CheckForUsername(username))
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
        DB.AddNewUser(newUser);
        return DB.CheckForUsername(newUser.getUsername());
    }
}
