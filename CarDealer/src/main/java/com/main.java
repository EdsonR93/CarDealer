package com;

import com.Model.User;
import com.ui.EmployeeDriver;
import com.ui.CustomerDriver;
import com.ui.UserServices;
import com.ui.Menus;

import java.util.*;

public class main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Menus menus = Menus.getInstance();
        UserServices userServices = UserServices.getInstance();
        EmployeeDriver employeeServices = EmployeeDriver.getInstance();
        CustomerDriver customerDriver = CustomerDriver.getInstance();

        int userInput;
        boolean dontExit = true;
        do{
           menus.printWelcomeMenu();

           try{
               userInput = scan.nextInt();

               switch (userInput){
                   case 1:{
                       boolean keepTrying;

                       String tryAgain;
                       do{
                           User user = userServices.showLoginForm();

                           if(user != null && user.isEmployee()){
                               employeeServices.moveToEmployeeMenu(user);
                               keepTrying = false;

                           }else if (user!=null){
                               customerDriver.MoveToCustomerMenu(user);
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
                       if(userServices.AddNewUser(newUser)){
                           System.out.println("Successfully added new user");
                       }else{
                           System.out.println("Error trying to add new user, please try again");
                       }
                       break;
                   }
                   case 3:{
                       dontExit = false;
                       break;
                   }
                   default:{
                       System.out.println("input only one of the options.");
                       break;
                   }
               }

           }catch(InputMismatchException e){
               System.out.println("Not a number");
               scan.nextLine();
           }catch(NoSuchElementException e){
               System.out.println("No input was read");
           }catch(IllegalStateException e){
               System.out.println("Scanner is closed");
           }catch(Exception e){
               e.printStackTrace();
           }

        }while(dontExit);
    }
}
