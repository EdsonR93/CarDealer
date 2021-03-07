package com.ui;

import com.Model.User;
import com.database.DataBaseServices;

import java.util.Scanner;

public class UserServices {
    Scanner scan = new Scanner(System.in);
    DataBaseServices DB = new DataBaseServices();
    
    public String[] showLoginForm (){
        String username,password;

        System.out.println("Username:");
        username = scan.nextLine();
        System.out.println("Password:");
        password = scan.nextLine();

        return new String[]{username, password};
    }

    public User showRegisterForm (){
        boolean isEmployee = false;
        String emp, username, password, name, lastname;
        //Car[] carsOwned = {};

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

        //TODO: check if the new user wants to add owned cars?
        boolean keepAsking;
        do{
            System.out.println("Are you an employee of the company?:");
            emp = scan.nextLine();
            if(emp.equalsIgnoreCase("Y") || emp.equalsIgnoreCase("Yes")){
                isEmployee = true;
                keepAsking = false;
            }else if (emp.equalsIgnoreCase("N") || emp.equalsIgnoreCase("no")){
                isEmployee = false;
                keepAsking = false;
            }else{
                keepAsking = true;
            }
        }while (keepAsking);

        return new User(isEmployee,username,password,name,lastname);
    }
}
