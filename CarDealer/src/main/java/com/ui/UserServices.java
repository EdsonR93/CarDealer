package com.ui;

import com.Model.User;
import com.database.TempDB;

import java.util.Scanner;

public class UserServices {
    Scanner scan = new Scanner(System.in);
    TempDB DB = new TempDB();
    
    public String[] showLoginForm (){
        String username,password;

        System.out.println("Username:");
        username = scan.nextLine();
        System.out.println("Password:");
        password = scan.nextLine();

        return new String[]{username, password};
    }

    public User showRegisterForm (){
        String isEmployee = "False";
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
                isEmployee = "1";
                keepAsking = false;
            }else if (emp.equalsIgnoreCase("N") || emp.equalsIgnoreCase("no")){
                isEmployee = "0";
                keepAsking = false;
            }else{
                keepAsking = true;
            }
        }while (keepAsking);

        return new User(isEmployee,username,password,name,lastname);
    }

    public void moveToUserMenu(){

    }


}
