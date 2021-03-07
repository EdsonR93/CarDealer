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
    Menus menus = new Menus();
    CarServices carServices = new CarServices();
    EmployeeServices employeeServices = new EmployeeServices();

    DataBaseServices DB = new DataBaseServices();

    public void moveToEmployeeMenu(User user){
        int userInput;

        do {
            menus.printEmployeeMenu();
            try{
                userInput = scan.nextInt();
                scan.nextLine();

                switch (userInput){
                    case 1:{
                        Car newCar = carServices.showAddNewCarForm(user);
                        DB.addNewCar(newCar);
                        if(DB.checkForCar(newCar.getId()))
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
                            String SN = scan.nextLine();

                            System.out.println(DB.deleteCarBySerialNumber(SN));

                        }else{
                            CarHashSet cars =  carServices.getCars(0);//0 is the Id for the company/CarDealer
                            System.out.println(cars.toString());
                            System.out.println("Enter the Serial number of the car to delete:");

                            String SN = scan.nextLine();
                            System.out.println(DB.deleteCarBySerialNumber(SN));
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
                        return;
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

        }while(true);
    }

}
