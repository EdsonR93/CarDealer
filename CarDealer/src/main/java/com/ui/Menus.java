package com.ui;

public class Menus {
    private Menus(){}

    private static Menus instance;

    public static Menus getInstance(){
        if(instance == null){
            instance = new Menus();
        }
        return instance;
    }

    public void printWelcomeMenu(){
        System.out.println("---Welcome---");
        System.out.println("1.- Login");
        System.out.println("2.- Register");
        System.out.println("0.- Close application");
        System.out.println("Option:");
    }

    public void printUserMenu(){
        System.out.println("\n---Main menu---");
        System.out.println("1.- Show available cars");
        System.out.println("2.- Show owned cars");
        System.out.println("3.- Review offers made");
        System.out.println("4.- Payments menu");
        System.out.println("0.- Sign out");
        System.out.println("Option:");
    }

    public void printUserCarsMenu(){
        System.out.println("--- ---");
        System.out.println("1.- Make an Offer for a car");
        System.out.println("0.- Go Back");
        System.out.println("Option:");
    }

    public void printEmployeeMenu(){
        System.out.println("---Main menu---");
        System.out.println("1.- Add new car");
        System.out.println("2.- delete a car");
        System.out.println("3.- Review offers");
        System.out.println("0.- Exit");
        System.out.println("Option:");
    }

    public void printCarsTable(){
        System.out.println(String.format("%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s", "Serial Num", "Model", "Brand", "Make", "Miles", "Color", "Price", "Owner Id"));
        System.out.println("_______________________________________________________________________________________________");
    }

    public void printOffersTable(){
        System.out.println(String.format("%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s","Offer id","User id","Car serial num","Amount offered","Months","Accepted","Pending"));
        System.out.println("_______________________________________________________________________________________________");
    }

    public void printPaymentsTable(){
        System.out.println(String.format("%10s\t%10s\t%10s\t%10s\t%10s\t","Payment id","User id","Car serial num","Payment amount","Payment date"));
        System.out.println("_____________________________________________________________________");
    }

    public void printPaymentPlanTable(){
        System.out.println(String.format("%7s\t%9s\t%12s\t%12s\t%15s\t%10s\t%10s\t","Plan id","User id","Car serial num","Monthly payment","Total months","Remaining","Purchase date"));
        System.out.println("_______________________________________________________________________________________________");
    }
}
