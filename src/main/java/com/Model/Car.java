package com.Model;

public class Car {
    //private static int NextId = 1;
    private int id;
    private String Color;
    private String Miles;
    private String Model;
    private String Brand;

    public Car(int id, String color, String miles, String model, String brand) {
        this.id = id;
        this.Color = color;
        this.Miles = miles;
        this.Model = model;
        this.Brand = brand;
    }

    public int getId(){
        return id;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        this.Color = color;
    }

    public String getMiles() {
        return Miles;
    }

    public void setMiles(String miles) {
        this.Miles = miles;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        this.Model = model;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        this.Brand = brand;
    }
}
