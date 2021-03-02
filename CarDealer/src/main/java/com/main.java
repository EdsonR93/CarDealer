package com;

import com.Model.User;
import com.database.TempDB;
import com.ui.EmployeeServices;
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
        EmployeeServices employeeServices = new EmployeeServices();
        Menus menus = new Menus();

        int userInput;
//        String userString;

        do{
           menus.printWelcomeMenu();

           try{

               //TODO: Rewrite this code later /// Done, save in case of any mistake
//               userString = scan.nextLine();
//
//               if(userString.equals("1"))
//                   userInput = 1;
//               else if(userString.equals("2"))
//                   userInput = 2;
//               else if(userString.equals("3"))
//                   userInput = 3;
//               else
//                   userInput = 4;
               //Till here

               userInput = scan.nextInt();

               switch (userInput){
                   case 1:{
                       boolean keepTrying;
                       boolean[] loginResult;
                       String tryAgain;
                       do{
                           String[] newUserData = userServices.showLoginForm();
                           loginResult = DB.Login(newUserData[0],newUserData[1]);

                           if(loginResult[0])
                           {
                               if(loginResult[1]){
                                   //TODO: Redirect to emp menu
                                   System.out.println("its an employee");
                                   employeeServices.moveToEmployeeMenu();

                               }else{
                                   //TODO: Redirect to User menus
                                   System.out.println("not an employee");
                               }

                               keepTrying = false;
                           }else{
                               System.out.println("try again? y/n");
                               tryAgain = scan.nextLine();
                               keepTrying = tryAgain.equalsIgnoreCase("y");
                           }
                       }while (keepTrying);

                       break;
                   }
                   case 2:{
                       User newUser = userServices.showRegisterForm();
                       System.out.println(DB.addNewUser(newUser));
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
