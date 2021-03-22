package com.ui;

import com.Collection.OfferHashSet;
import com.Model.Car;
import com.Model.Offer;
import com.database.DataBaseServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class EmployeeServices {
    Scanner scan = new Scanner(System.in);
    private final DataBaseServices DB = DataBaseServices.getInstance();


    private EmployeeServices(){}
    private static EmployeeServices instance;
    public static EmployeeServices getInstance(){
        if(instance == null){
            instance = new EmployeeServices();
        }
        return instance;
    }

    public OfferHashSet getOffers(){
        ResultSet rs = DB.FetchAllOffers();
        OfferHashSet offers = new OfferHashSet();

        try{
            while(rs.next()){
                offers.Add(new Offer(rs.getInt("offer_id"),rs.getInt("user_id"),
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

    public boolean TakeOffer(OfferHashSet offers, boolean b){ //TODO: Refactor to show customers info and cars info to the employee
        String msg;
        if(b)
            msg= "Enter offerId to accept offer:";
        else
            msg = "Enter offerId of the offer to Reject:";
        do{
            try{
                System.out.println(msg);
                System.out.println("Enter 0 (zero) to exit");
                int offerId = scan.nextInt();
                scan.nextLine();

                if(offerId != 0){
                    Offer of = offers.getById(offerId);

                    if(of !=null && b){
                        return DB.AcceptOffer(of);
                    }else if(!b){
                        return DB.RejectOffer(of);
                    }else{
                        System.out.println("Offer id does not exist");
                    }
                }else{
                    return false;
                }
            }catch (InputMismatchException ex){
                System.out.println("Not a number, try again or enter 0 to exit");
                scan.nextLine();
            }

        }while(true);
    }

    public boolean AddNewCar(Car car){
        DB.AddNewCar(car);
        return DB.CheckForCar(car.getSerialNum());
    }

    public boolean DeleteCar(int SN){

        return DB.DeleteCarBySerialNumber(SN);
    }
}
