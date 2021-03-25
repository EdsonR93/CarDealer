package com.ui;

import com.Collection.CarHashSet;
import com.Collection.OfferHashSet;
import com.Model.Car;
import com.Model.User;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EmployeeDriver {

    Scanner scan = new Scanner(System.in);

    private final Menus menus = Menus.getInstance();
    private final CarServices carServices = CarServices.getInstance();
    private final EmployeeServices employeeServices = EmployeeServices.getInstance();

    private EmployeeDriver(){}
    private static EmployeeDriver instance;
    public static EmployeeDriver getInstance(){
        if(instance == null){
            instance = new EmployeeDriver();
        }
        return instance;
    }

    public void MoveToEmployeeMenu(User user){
        int userInput;
        boolean dontExit = true;
        do {
            menus.printEmployeeMenu();
            try{
                userInput = scan.nextInt();
                scan.nextLine();
                switch (userInput){
                    case 1:{
                        Car newCar = carServices.ShowAddNewCarForm(user);
                        if(employeeServices.AddNewCar(newCar))
                            System.out.println("---Successful---\n");
                        else
                            System.out.println("Could not add to DB\n");
                        break;
                    }
                    case 2:{
                        System.out.println("To delete a car, you need to provide the Car's Serial number");
                        System.out.println("Do you have the Serial number: y/n");

                        if(scan.nextLine().equalsIgnoreCase("Y")){
                            System.out.println("Enter Serial number: ");
                            userInput = scan.nextInt();
                            scan.nextLine();
                            if(employeeServices.DeleteCar(userInput))
                                System.out.println("Successfully delete car with serial number: " +userInput);
                            else
                                System.out.println("Couldnt delete car: "+userInput);

                        }else{
                            CarHashSet cars =  carServices.getCars(0);
                            System.out.println("Available cars:");
                            menus.printCarsTable();
                            System.out.println(cars.toString());
                            System.out.println("Enter the Serial number of the car to delete:");
                            System.out.println("Enter 0 (zero) to go back");

                            userInput = scan.nextInt();
                            scan.nextLine();

                            if(userInput!=0 && employeeServices.DeleteCar(userInput))
                                System.out.println("Successfully delete car with serial number: " +userInput);
                            else if(userInput != 0)
                                System.out.println("Couldn't delete car: "+userInput);

                        }
                        break;
                    }
                    case 3:{
                        OfferHashSet offers = employeeServices.getOffers();
                        if (offers !=null && offers.Size()>0){
                            menus.printOffersTable();
                            System.out.println(offers + "\n\n");
                            System.out.println("1.- Take offer");
                            System.out.println("2.- Reject Offer");
                        }else
                            System.out.println("No offers to show\n");

                        System.out.println("0.- Go back");

                        //TODO: Refactor to repeat question
                        userInput = scan.nextInt();
                        if(userInput == 1){
                            if(employeeServices.TakeOffer(offers, true))
                                System.out.println("Successfully accepted offer");
                            else
                                System.out.println("Offer was not accepted, check if the info is right");
                        }else if(userInput == 2){
                            if(employeeServices.TakeOffer(offers, false))
                                System.out.println("Successfully rejected offer");
                            else
                                System.out.println("Offer was not rejected, check if the info is right");
                        }
                        scan.nextLine();
                        break;
                    }
                    case 0:{
                        dontExit = false;
                        break;
                    }
                    default:{
                        System.out.println("Input only one of the options");
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
