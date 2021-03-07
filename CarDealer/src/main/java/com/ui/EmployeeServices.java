package com.ui;

import com.Collection.OfferHashSet;
import com.Model.Offer;
import com.database.DataBaseServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class EmployeeServices {
    Scanner scan = new Scanner(System.in);

    DataBaseServices DB = new DataBaseServices();
    public OfferHashSet getOffers(){
        ResultSet rs = DB.fetchAllOffers();
        OfferHashSet offers = new OfferHashSet();

        try{
            while(rs.next()){
                offers.add(new Offer(rs.getInt("offer_id"),rs.getString("user_id"),
                        rs.getString("car_id"), rs.getFloat("offer")));
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
            Offer of = offers.getById(String.valueOf(offerId));

            if(of !=null){
                System.out.println(DB.acceptOffer(of));
                keepAsking = false;
            }else{
                System.out.println("Offer id does not exist");
            }

        }while(keepAsking);
    }
}
