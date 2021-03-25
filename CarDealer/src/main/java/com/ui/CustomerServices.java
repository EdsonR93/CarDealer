package com.ui;

import com.Collection.OfferHashSet;
import com.Collection.PaymentPlanHashSet;
import com.Collection.PaymentsHashSet;
import com.Model.*;
import com.database.DBHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerServices {
    Scanner scan = new Scanner(System.in);
    Menus menus = Menus.getInstance();
    private final DBHandler DB = DBHandler.INSTANCE;

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

        menus.printCarsTable();
        System.out.println(car.toString());
        System.out.println("How much do you want to offer?");
        offer = scan.nextFloat();
        scan.nextLine();
        System.out.println("How many months do you want to set the lease to?");
        months = scan.nextInt();
        scan.nextLine();


        if(DB.AddNewOffer(car.getSerialNum(),user.getId(),offer,months)){
            System.out.println("Offer made successfully");
            System.out.println("Pending for approval");
        }else{
            System.out.println("failed to save Offer, please try again");
        }
    }

    public void ReviewOffers(User u){
        //TODO: Customer and Employee services have a get Offer method, Refactor to reuse only one method

        OfferHashSet offers = new OfferHashSet();
        try{
            ResultSet rs = DB.FetchOffers(u.getId());
            while(rs.next()){
                offers.Add(new Offer(rs.getInt("offer_id"),rs.getInt("user_id"),rs.getInt("car_serial_num"),
                        rs.getFloat("offer"), rs.getInt("months"),rs.getBoolean("accepted"),
                        rs.getBoolean("pending")));

            }
            //TODO: Refactor to show a more user friendly screen
            if(offers.Size()>0) {
                StringBuilder accepted = new StringBuilder();
                StringBuilder rejected = new StringBuilder();
                StringBuilder pendingForReview = new StringBuilder();

                Offer current = offers.Next();

                while (current!=null){
                    if(current.isAccepted()){
                        accepted.append(current).append("\n");
                    }else if(current.isPending()){
                        pendingForReview.append(current).append("\n");
                    }else{
                        rejected.append(current).append("\n");
                    }
                    current = offers.Next();
                }
                System.out.println("Offers Rejected");
                menus.printOffersTable();
                System.out.println(rejected);
                System.out.println("Offers Pending of review from the dealer");
                menus.printOffersTable();
                System.out.println(pendingForReview);
                System.out.println("Offers Accepted");
                menus.printOffersTable();
                System.out.println(accepted);
            }else{
                System.out.println("--- ---");
                System.out.println("No offers to show");
                System.out.println("--- ---\n");
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    //TODO: working on payments
    public PaymentsHashSet getPayments(int userId){

        PaymentsHashSet payments = new PaymentsHashSet();

        try{
            ResultSet rs = DB.FetchPayments(userId);
            while(rs.next()){
                payments.Add(new Payment(rs.getInt("payment_id"),rs.getInt("user_id"),
                        rs.getInt("car_serial_num"),rs.getDouble("payment_amount"),
                        rs.getDate("payment_date")));
            }

            return payments;

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;

    }

    public PaymentPlanHashSet getPaymentPlans(int userId){
        PaymentPlanHashSet paymentPlans = new PaymentPlanHashSet();

        try{
            ResultSet rs = DB.FetchPaymentPlans(userId);
            while(rs.next()){
                paymentPlans.Add(new PaymentPlan(rs.getInt("plan_id"),rs.getInt("user_id"),
                        rs.getInt("car_serial_num"),rs.getDouble("monthly_payment"),
                        rs.getInt("total_months"),rs.getInt("months_left"),rs.getDate("purchase_date")));
            }
            return paymentPlans;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public boolean MakePayment(PaymentPlan p){

        return DB.RegisterPayment(p);
    }
}
