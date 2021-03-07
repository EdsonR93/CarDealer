package com.Model;

public class Car {
    //private static int NextId = 1;
    private String id;
    private String color;
    private String miles;
    private String model;
    private String brand;
    private String price;
    private String user_id;

    public Car(String id, String model, String brand, String color, String miles, String price, String user_id ) {
        this.id = id;
        this.color = color;
        this.miles = miles;
        this.model = model;
        this.brand = brand;
        //TODO: ADD Make to model and DB table
        this.price = price;
        this.user_id = user_id;
    }

    public String getId(){
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Serial Number ='" + id + '\'' +
                ", Color='" + color + '\'' +
                ", Miles='" + miles + '\'' +
                ", Model='" + model + '\'' +
                ", Brand='" + brand + '\'' +
                ", Price='" + price + '\'' +
                '}';
    }
}
