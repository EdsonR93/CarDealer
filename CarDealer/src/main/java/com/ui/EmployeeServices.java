package com.ui;

import com.Model.Car;
import com.database.TempDB;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EmployeeServices {

    Scanner scan = new Scanner(System.in);
    Menus menus = new Menus();
    CarServices carServices = new CarServices();
    TempDB DB = new TempDB();

    public void moveToEmployeeMenu(){
        //boolean doNotExit = true;
        int userInput;

        do {
            menus.printEmployeeMenu();
            try{
                userInput = scan.nextInt();
                scan.nextLine();

                switch (userInput){
                    case 1:{
                        Car newCar = carServices.showAddNewCarForm();
                        System.out.println(DB.addNewCar(newCar));
                        break;
                    }
                    case 2:{
                        System.out.println("To delete a car, you need to provide the Car's Serial number");
                        System.out.println("Do you have the Serial number: y/n");


                        if(scan.nextLine().equalsIgnoreCase("Y")){
                            System.out.println("Enter Serial number: ");
                            String SN = scan.nextLine();
                            System.out.println(DB.deleteCarBySerialNumber(SN));

                        }else{
                            //TODO:Show all cars so it can look for SN
                        }
                        break;
                    }
                    case 3:{}
                    case 4:{}

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
