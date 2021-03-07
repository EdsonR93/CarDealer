package com.ui;

import com.Collection.CarHashSet;
import com.Model.Car;
import com.Model.User;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CustomerDriver {
    Scanner scan = new Scanner(System.in);
    Menus menus = new Menus();
    CarServices carServices = new CarServices();
    CustomerServices customerServices = new CustomerServices();

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

                        boolean continueOffer = true;
                        do {
                            menus.printUserCarsMenu();
                            userInput = scan.nextInt();
                            scan.nextLine();

                            if(userInput == 1){
                                System.out.println("Enter Serial number: ");
                                String SN = scan.nextLine();
                                Car car = cars.getById(SN);
                                if(car!=null){
                                    customerServices.MakeAnOffer(car, user);
                                    continueOffer = false;
                                }else{
                                    System.out.println("Car not found, please make sure you have the right serial number");
                                    continueOffer = true;
                                }
                            }else if(userInput == 2 ){
                                continueOffer = false;
                            }
                        }while (continueOffer);

                        break;
                    }
                    case 2:{
                        CarHashSet cars = carServices.getCars(user.getId());
                        System.out.println(cars);
                        break;
                    }
                    case 3:{
                        //TODO: Refactor purchase so you can select the amount of months you wish to pay the car
                        break;
                    }
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
