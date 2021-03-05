package com.ui;

import com.Collection.CarHashSet;
import com.Model.User;
import com.database.TempDB;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserDriver {
    Scanner scan = new Scanner(System.in);
    Menus menus = new Menus();
    CarServices carServices = new CarServices();
    TempDB DB = new TempDB();

    public void MoveToUserMenu(User user){
        int userInput;

        do{
            menus.printUserMenu();
            try{
                userInput = scan.nextInt();
                scan.nextLine();

                switch (userInput){
                    case 1:{
                        CarHashSet cars = carServices.getCars(0);
                        System.out.println(cars.toString());
                        menus.printUserCarsMenu();
                        userInput = scan.nextInt();
                        scan.nextLine();
                        if(userInput == 1){
                            System.out.println("Enter Serial number: ");
                            String SN = scan.nextLine();
                            if(DB.checkForCar(SN)){
                                //TODO: Enter offer
                            }else{
                                //TODO: Car not found
                            }
                        }

                        break;
                    }
                    case 2:{}
                    case 3:{}
                    case 4:{
                        return;
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
        }while (true);
    }

}
