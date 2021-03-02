package com.Model;

public class Car {
    //private static int NextId = 1;
    private String id;
    private String color;
    private String miles;
    private String model;
    private String brand;
    private String price;

    public Car(String id, String model, String brand, String color, String miles ) {
        this.id = id;
        this.color = color;
        this.miles = miles;
        this.model = model;
        this.brand = brand;
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

    @Override
    public String toString() {
        return "Car{" +
                "Serial Number ='" + id + '\'' +
                ", Color='" + color + '\'' +
                ", Miles='" + miles + '\'' +
                ", Model='" + model + '\'' +
                ", Brand='" + brand + '\'' +
                '}';
    }
}
