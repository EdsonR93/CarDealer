package com.Model;

public class Car {
    private final int serialNum;
    private int model;
    private String brand;
    private String make;
    private int miles;
    private String color;
    private float price;
    private int ownerId;

    public Car(int serialNum, int model, String brand, String make, String color, int miles, float price, int ownerId) {
        this.serialNum = serialNum;
        this.color = color;
        this.miles = miles;
        this.model = model;
        this.brand = brand;
        this.make = make;
        this.price = price;
        this.ownerId = ownerId;
    }

    public int getSerialNum(){
        return serialNum;
    }

    public String getColor() {
        return color;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + serialNum +
                ", model=" + model +
                ", brand='" + brand + '\'' +
                ", make='" + make + '\'' +
                ", miles=" + miles +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", owner_id=" + ownerId +
                '}';
    }
}
