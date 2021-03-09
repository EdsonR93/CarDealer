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
        System.out.println("3.- Exit");
        System.out.println("Option (1,2 or 3): ");
    }

    public void printUserMenu(){
        System.out.println("1.- Show available cars");
        System.out.println("2.- Show owned cars");
        System.out.println("3.- Review offers made");
        System.out.println("4.- Payments menu");
        System.out.println("5.- Sign out");
        System.out.println("Option (1,2,3,4 or 5): ");
    }

    public void printUserCarsMenu(){
        System.out.println("1.- Make an Offer for a car");
        System.out.println("2.- Go Back");
        System.out.println("Option (1 or 2):");
    }

    public void printEmployeeMenu(){
        System.out.println("1.- Add new car");
        System.out.println("2.- delete a car");
        System.out.println("3.- Review offers");
        System.out.println("4.- Exit");
        System.out.println("Option (1,2,3 or 4): ");
    }
}
