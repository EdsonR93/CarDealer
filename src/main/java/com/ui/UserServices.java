package com.ui;

import com.Model.Car;
import com.Model.User;
import com.database.TempDB;
import java.util.Scanner;

public class UserServices {
    TempDB DB = new TempDB();
    Scanner scan = new Scanner(System.in);
    
    public String[] showLoginForm (){
        String username;
        String password;
        System.out.println("Username:");
        username = scan.nextLine();
        System.out.println("Password:");
        password = scan.nextLine();
        return new String[]{username, password};
    }

//    public String login(String[] cred, TempDB BD){
//
//    }

    public User showRegisterForm (){
        int id = 1;
        boolean isEmployee = false;
        String emp;
        String username;
        String password;
        String name;
        String lastname;
        Car[] carsOwned = {};

        System.out.println("Create username:");
        username = scan.nextLine();
        //TODO: Check if it already exists

        System.out.println("Create password:");
        password = scan.nextLine();
        //TODO: validate password format??

        System.out.println("First name:");
        name = scan.nextLine();

        System.out.println("Last name:");
        lastname = scan.nextLine();

        //TODO: check if the new user wants to add owned cars?
        boolean keepAsking = false;
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


        return new User(id,isEmployee,username,password,name,lastname,carsOwned);
    }

}
