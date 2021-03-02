package com.ui;

import com.Model.Car;

import java.util.Scanner;

public class CarServices {
    Scanner scan = new Scanner(System.in);

    public Car showAddNewCarForm(){
        String id,model,brand,miles,color;

        System.out.println("Car model:");
        model = scan.nextLine();
        System.out.println("Car brand:");
        brand = scan.nextLine();
        System.out.println("Miles on the car:");
        miles = scan.nextLine();
        System.out.println("Color");
        color = scan.nextLine();
        System.out.println("Car's Serial number: ");
        id = scan.nextLine();

        return new Car(id,model,brand,color,miles);
    }


}
