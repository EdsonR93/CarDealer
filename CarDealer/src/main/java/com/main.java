package com;

import com.Model.User;
import com.database.TempDB;
import com.ui.UserServices;
import com.ui.Menus;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        TempDB DB = new TempDB();
        Scanner scan = new Scanner(System.in);
        UserServices userServices = new UserServices();
        Menus menus = new Menus();

        boolean loggedSuccessfully = false;
        int userInput;
        do{
           menus.showWelcomeMenu();

           try{
               userInput = scan.nextInt();
               switch (userInput){
                   case 1:{
                       String[] newUserData = userServices.showLoginForm();
                       //TODO: authenticate the user
                       break;
                   }
                   case 2:{
                       User newUser = userServices.showRegisterForm();
                       //TODO: Submit to DB
                        break;
                   }
                   case 3:{
                       return;
                   }
                   default:{
                       System.out.println("input only one of the options.");
                   }
               }

           }catch(Exception  e){
               System.out.println("input only one of the options.");
           }

        }while(!loggedSuccessfully);
    }
}
