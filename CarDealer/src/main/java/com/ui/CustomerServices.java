package com.ui;

import com.Model.Car;
import com.Model.User;
import com.database.DataBaseServices;

import java.util.Scanner;

public class CustomerServices {
    Scanner scan = new Scanner(System.in);
    DataBaseServices DB = new DataBaseServices();

    public void MakeAnOffer(Car car, User user) {
        String offer;

        System.out.println(car.toString());
        System.out.println("How much do you want to offer?");
        offer = scan.nextLine();

        if(DB.addNewOffer(car.getId(),String.valueOf(user.getId()),offer)){
            System.out.println("Offer made successfully");
            System.out.println("Pending for approval");
        }else{
            System.out.println("failed to save Offer, please try again");
        }
    }
}
