package com.ui;

import com.Collection.CarHashSet;
import com.Model.Car;
import com.Model.User;
import com.database.DataBaseServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CarServices {
    Scanner scan = new Scanner(System.in);
    DataBaseServices DB = new DataBaseServices();

    public Car showAddNewCarForm(User u){
        String id,model,brand,miles,color,price;

        System.out.println("Car model:");
        model = scan.nextLine();
        System.out.println("Car brand:");
        brand = scan.nextLine();
        System.out.println("Miles on the car:");
        miles = scan.nextLine();
        System.out.println("Color:");
        color = scan.nextLine();
        System.out.println("Car's Serial number: ");
        id = scan.nextLine();
        System.out.println("Price:");
        price = scan.nextLine();

        String owner;
        if(u.isEmployee()) {
            owner = "0";
        }else{
            owner = String.valueOf(u.getId()) ;
        }
        return new Car(id,model,brand,color,miles,price,owner);
    }

    public CarHashSet getCars(int id){
        ResultSet rs = DB.fetchAllCars(id);
        CarHashSet cars = new CarHashSet();

        try{
            while(rs.next()){
                cars.add(new Car(rs.getString("car_id"),rs.getString("model"),rs.getString("brand"),
                        rs.getString("color"),rs.getString("miles"),rs.getString("price"),
                        rs.getString("user_id")));

            }

            return cars;
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return null;
    }


}
