package com.ui;

import com.Model.Car;
import com.Model.User;
import com.database.DataBaseServices;

import java.util.Scanner;

public class CustomerServices {
    Scanner scan = new Scanner(System.in);
    DataBaseServices DB = DataBaseServices.getInstance();

    private CustomerServices(){}

    private static CustomerServices instance;

    public static CustomerServices getInstance(){
        if(instance == null){
            instance = new CustomerServices();
        }
        return instance;
    }

    public void MakeAnOffer(Car car, User user) {
        float offer = 0f;
        int months =0;

        System.out.println(car.toString());
        System.out.println("How much do you want to offer?");
        offer = scan.nextFloat();
        scan.nextLine();
        System.out.println("How many months do you want to set the lease to?");
        months = scan.nextInt();
        scan.nextLine();


        if(DB.addNewOffer(car.getSerialNum(),user.getId(),offer,months)){
            System.out.println("Offer made successfully");
            System.out.println("Pending for approval");
        }else{
            System.out.println("failed to save Offer, please try again");
        }
    }
}
