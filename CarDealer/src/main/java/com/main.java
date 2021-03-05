package com;

import com.Model.User;
import com.database.TempDB;
import com.ui.EmployeeDriver;
import com.ui.UserServices;
import com.ui.Menus;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        TempDB DB = new TempDB();
        Scanner scan = new Scanner(System.in);
        UserServices userServices = new UserServices();
        EmployeeDriver employeeServices = new EmployeeDriver();
        Menus menus = new Menus();


        int userInput;
        do{
           menus.printWelcomeMenu();

           try{
               userInput = scan.nextInt();

               switch (userInput){
                   case 1:{
                       boolean keepTrying;

                       String tryAgain;
                       do{
                           String[] newUserData = userServices.showLoginForm();
                           User user = DB.Login(newUserData[0],newUserData[1]);
                           if(user != null && user.isEmployee()){
                               //System.out.println("Logged in successfully");
                               employeeServices.moveToEmployeeMenu(user);
                               keepTrying = false;

                           }else if (user!=null){

                               keepTrying = false;
                           }else {
                               System.out.println("Username or name ar incorrect");
                               System.out.println("try again? y/n");
                               tryAgain = scan.nextLine();
                               keepTrying = tryAgain.equalsIgnoreCase("y");

                           }
                       }while (keepTrying);

                       break;
                   }
                   case 2:{
                       User newUser = userServices.showRegisterForm();
                       DB.addNewUser(newUser);
                       if(DB.checkForUsername(newUser.getUsername()))
                           System.out.println("Successful");
                       else
                           System.out.println("Could not add to DB");
                       break;
                   }
                   case 3:{
                       return;
                   }
                   default:{
                       System.out.println("input only one of the options.");
                       break;
                   }
               }

           }catch(InputMismatchException e){
               //e.printStackTrace();
               System.out.println("Not a number");
               scan.nextLine();
           }catch(NoSuchElementException e){
               System.out.println("No input was read");
           }catch(IllegalStateException e){
               System.out.println("Scanner is closed");
           }catch(Exception e){
               System.out.println("I dunno whats going on");
               e.printStackTrace();
           }

        }while(true);
    }
}
