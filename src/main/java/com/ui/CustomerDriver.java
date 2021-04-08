package com.ui;

import com.Collection.CarHashSet;
import com.Collection.PaymentPlanHashSet;
import com.Collection.PaymentsHashSet;
import com.Model.Car;
import com.Model.User;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CustomerDriver {
    Scanner scan = new Scanner(System.in);

    private final Menus menus = Menus.getInstance();
    private final CarServices carServices = CarServices.getInstance();
    private final CustomerServices customerServices = CustomerServices.getInstance();

    private CustomerDriver(){}

    private static CustomerDriver instance;

    public static CustomerDriver getInstance(){
        if(instance == null){
            instance = new CustomerDriver();
        }
        return instance;
    }

    public void MoveToCustomerMenu(User user){
        int userInput;
        boolean dontExit = true;
        do{
            menus.printUserMenu();
            try{
                userInput = scan.nextInt();
                scan.nextLine();

                switch (userInput){
                    case 1:{
                        CarHashSet cars = carServices.getCars(0);
                        menus.printCarsTable();
                        System.out.println(cars.toString());

                        boolean continueOffer = true;
                        do {
                            menus.printUserCarsMenu();
                            userInput = scan.nextInt();
                            scan.nextLine();

                            if(userInput == 1){
                                System.out.println("Enter Serial number: ");
                                int SN = scan.nextInt();
                                scan.nextLine();

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
                            }else if(userInput == 0){
                                continueOffer = false;
                            } else {
                                System.out.println("Select only one of the options");
                            }
                        }while (continueOffer);

                        break;
                    }
                    case 2:{

                        CarHashSet cars = carServices.getCars(user.getId());
                        if(cars!=null && cars.Size()>0) {
                            menus.printCarsTable();
                            System.out.println(cars);
                        }else
                            System.out.println("No cars to show");

                        break;
                    }
                    case 3:{
                        //TODO: Modify DB so you can cancel offer?
                        customerServices.ReviewOffers(user);
                        break;
                    }
                    case 4:{
                        PaymentsHashSet payments = customerServices.getPayments(user.getId());
                        if(payments!=null && payments.Size()>0) {
                            System.out.println("--- Last 5 payments ---");
                            menus.printPaymentsTable();
                            System.out.println(payments);
                            System.out.println("---  ---");
                        }else {
                            System.out.println("\n--- ---");
                            System.out.println("No previous payments");
                            System.out.println("---  ---");
                        }

                        System.out.println("Make a payment: y/n");
                        if(scan.nextLine().equalsIgnoreCase("Y")){
                            PaymentPlanHashSet paymentsPlan = customerServices.getPaymentPlans(user.getId());
                            if(paymentsPlan!=null){
                                menus.printPaymentPlanTable();
                                System.out.println(paymentsPlan + "\n");
                                System.out.println("Select payment plan to continue");
                                System.out.println("Input 0 (zero) to exit:");
                                userInput = scan.nextInt();
                                if(paymentsPlan.getById(userInput) != null)
                                if(customerServices.MakePayment(paymentsPlan.getById(userInput))){
                                    System.out.print("Making payment");
                                    for (int i = 0; i<4; i++){
                                        System.out.print(".");
                                        Thread.sleep(1000);
                                    }
                                    System.out.println("\nPayment successful");
                                }else
                                    System.out.println("Please verify info and try again");
                            }
                        }
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
        }while (dontExit);
    }

}
