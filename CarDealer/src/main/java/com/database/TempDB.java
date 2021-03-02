package com.database;

import com.Model.Offer;
import com.Model.User;
import com.Model.Car;
//import java.util.function. TODO: see if there is a function I could use

import java.sql.*;
import java.util.Scanner;

public class TempDB{

    Connection c1 = null;
    Statement statement = null;
    Scanner scan = new Scanner(System.in); //TODO: Just for debugging, remove later


    public TempDB(){
        try{
            Class.forName("org.postgresql.Driver");
            String pass = "GYBChicos";
            String user = "postgres";
            String url = "jdbc:postgresql://localhost:5432/CarDealer";

            c1 = DriverManager.getConnection(url, user, pass);
            statement = c1.createStatement();

        }catch(SQLException sqlex){

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean checkForUsername(String newUsername){
        try{
            ResultSet result = statement.executeQuery("SELECT username FROM users WHERE username=\'"+newUsername+"\';");
            return result.next();

        }catch(SQLException sqlex){
            System.out.println("Could not verify user, connection failed?");
            sqlex.printStackTrace();
        }
        return true;
    }

    private boolean checkForCar(String SN){
        try{
            ResultSet result = statement.executeQuery("SELECT car_id FROM cars WHERE car_id='"+SN+"';");
            return result.next();

        }catch(SQLException ex){
            System.out.println("Could not verify user, connection failed?"); //TODO: write a better response
            ex.printStackTrace();
        }
        return true;
    }

    public boolean[] Login(String username, String pass) {
        try {
            ResultSet result = statement.executeQuery("SELECT is_employee,username,password FROM users WHERE username='" + username + "' AND password ='" + pass + "';");
            if(result.next()){
                System.out.println("Logged in successfully");
                //System.out.println(result.getString("username")+" "+result.getString("password")); Debugging line
                return new boolean[]{true, result.getBoolean("is_employee")};
            }else{
                System.out.println("Username or name ar incorrect");
                return new boolean[]{false,false};
            }
        }catch(SQLException sqlex){
            System.out.println("Something went wrong at the connection");
            sqlex.printStackTrace();
        }

        return new boolean[]{false,false};
    }

    public String addNewUser(User u){
        try {
            if (c1 != null) {
                statement.execute("INSERT INTO users (is_employee, username, password," +
                        " name, lastname ) values ('" + u.isEmployee() + "', '" +
                        u.getUsername() + "', '" + u.getPassword() + "', '" + u.getName() + "', '" +
                        u.getLastname() + "');");

                if(checkForUsername(u.getUsername()))
                    return "Successful";
                else
                    return  "Could not add to DB";

            }
            else
                System.out.println("Connection not found!!");

        }catch(SQLException e){
            System.out.println("Something went wrong at the connection");
            e.printStackTrace();
        }

        return "Reached end of addNewUser, I don't know what happen";
    }

    public String addNewCar(Car c){
        try{
            if(c1 != null){
                statement.execute("INSERT INTO cars (car_id, color, miles, model, brand) values(" +
                        "'" +c.getId() + "', ' " + c.getColor() + "', '" + c.getMiles() + "', '" +
                        c.getModel() + "', '" + c.getBrand() + "');");

                if(checkForCar(c.getId()))
                    return "Successful";
                else
                    return  "Could not add to DB";

            }
        }catch (SQLException ex){
            System.out.println("Something wrong at SQL");
            //ex.printStackTrace();
        }catch(Exception ex){
            System.out.println("Something wrong while adding the car method");
            //ex.printStackTrace();
        }

        return "Reached end of addNewCar, I don't know what happen";
    }

    public String deleteCarBySerialNumber(String SerialNumber){
        try {
            if(c1 != null) {
                statement.execute("DELETE FROM cars WHERE car_id = '" + SerialNumber +"';");

                if(checkForCar(SerialNumber))
                    return "Couldn't delete";
                else
                    return  "Successfully deleted car";

            }

        }catch(SQLException ex) {
            //ex.printStackTrace();
            System.out.println("SQL exception at deleting");
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "Couldn't delete, end of method.";
    }

}