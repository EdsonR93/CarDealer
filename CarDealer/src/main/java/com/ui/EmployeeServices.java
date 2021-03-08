package com.ui;

import com.Collection.OfferHashSet;
import com.Model.Car;
import com.Model.Offer;
import com.database.DataBaseServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class EmployeeServices {
    Scanner scan = new Scanner(System.in);
    DataBaseServices DB = DataBaseServices.getInstance();


    private EmployeeServices(){}
    private static EmployeeServices instance;
    public static EmployeeServices getInstance(){
        if(instance == null){
            instance = new EmployeeServices();
        }
        return instance;
    }

    public OfferHashSet getOffers(){
        ResultSet rs = DB.fetchAllOffers();
        OfferHashSet offers = new OfferHashSet();

        try{
            while(rs.next()){
                offers.add(new Offer(rs.getInt("offer_id"),rs.getInt("user_id"),
                        rs.getInt("car_serial_num"), rs.getFloat("offer"),
                        rs.getInt("months"), rs.getBoolean("accepted"), rs.getBoolean("pending")));
            }

            return offers;
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public void takeOffer(OfferHashSet offers){

        boolean keepAsking = true;
        do{
            System.out.println("Enter Offer ID to accept offer:");
            int offerId = scan.nextInt();
            scan.nextLine();
            Offer of = offers.getById(offerId);

            if(of !=null){
                System.out.println(DB.acceptOffer(of));
                keepAsking = false;
            }else{
                System.out.println("Offer id does not exist");
            }

        }while(keepAsking);
    }

    public boolean AddNewCar(Car car){
        DB.addNewCar(car);
        return DB.checkForCar(car.getSerialNum());
    }

    public boolean DeleteCar(int SN){

        return DB.deleteCarBySerialNumber(SN);
    }
}
