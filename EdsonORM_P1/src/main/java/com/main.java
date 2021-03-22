package com;

import com.Model.User;
import com.enterprise.util.TestDiscovery;
import com.ui.CustomerDriver;
import com.ui.EmployeeDriver;
import com.ui.Menus;
import com.ui.UserServices;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
               scan.nextLine();
               switch (userInput){
                   case 1:{
                       boolean keepTrying;

                       String tryAgain;
                       do{
                           User user = userServices.ShowLoginForm();

                           if(user != null && user.isEmployee()){
                               employeeServices.MoveToEmployeeMenu(user);
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
                       User newUser = userServices.ShowRegisterForm();
                       if(userServices.AddNewUser(newUser)){
                           System.out.println("Successfully added new user");
                       }else{
                           System.out.println("Error trying to add new user, please try again");
                       }
                       break;
                   }
                   case 0:{
                       dontExit = false;
                       break;
                   }
                   case 555:{
                       System.out.println("Running testing framework");
                       HashMap resultMap = null;

                       try {
                           resultMap = (new TestDiscovery()).runAndStoreTestInformation();
                       } catch (Exception var3) {
                           var3.printStackTrace();
                       }

                       System.out.println(resultMap);
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
