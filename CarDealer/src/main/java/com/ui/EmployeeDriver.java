package com.ui;

import com.Collection.CarHashSet;
import com.Collection.OfferHashSet;
import com.Model.Car;
import com.Model.User;
import com.database.DataBaseServices;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EmployeeDriver {

    Scanner scan = new Scanner(System.in);

    Menus menus = Menus.getInstance();
    CarServices carServices = CarServices.getInstance();
    EmployeeServices employeeServices = EmployeeServices.getInstance();

    private EmployeeDriver(){}
    private static EmployeeDriver instance;
    public static EmployeeDriver getInstance(){
        if(instance == null){
            instance = new EmployeeDriver();
        }
        return instance;
    }

    public void moveToEmployeeMenu(User user){
        int userInput;
        boolean dontExit = true;
        do {
            menus.printEmployeeMenu();
            try{
                userInput = scan.nextInt();
                scan.nextLine();
                switch (userInput){
                    case 1:{
                        Car newCar = carServices.showAddNewCarForm(user);
                        if(employeeServices.AddNewCar(newCar))
                            System.out.println("Successful");
                        else
                            System.out.println("Could not add to DB");
                        break;
                    }
                    case 2:{
                        System.out.println("To delete a car, you need to provide the Car's Serial number");
                        System.out.println("Do you have the Serial number: y/n");

                        if(scan.nextLine().equalsIgnoreCase("Y")){
                            System.out.println("Enter Serial number: ");
                            int SN = scan.nextInt();
                            scan.nextLine();
                            if(employeeServices.DeleteCar(SN))
                                System.out.println("Successfully delete car with serial number: " +SN);
                            else
                                System.out.println("Couldnt delete car: "+SN);

                        }else{
                            CarHashSet cars =  carServices.getCars(0);
                            System.out.println(cars.toString());
                            System.out.println("Enter the Serial number of the car to delete:");
                            int SN = scan.nextInt();
                            scan.nextLine();
                            if(employeeServices.DeleteCar(SN))
                                System.out.println("Successfully delete car with serial number: " +SN);
                            else
                                System.out.println("Couldnt delete car: "+SN);
                        }
                        break;
                    }
                    case 3:{
                        OfferHashSet offers = employeeServices.getOffers();
                        System.out.println(offers);
                        employeeServices.takeOffer(offers);
                        break;
                    }
                    case 4:{
                        dontExit = false;
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
                System.out.println("I dunno whats going on");
                e.printStackTrace();
            }

        }while(dontExit);
    }

}
