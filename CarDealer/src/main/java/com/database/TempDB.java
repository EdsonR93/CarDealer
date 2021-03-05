package com.database;

import com.Model.User;
import com.Model.Car;
//import java.util.function. TODO: see if there is a function I could use

import java.sql.*;

public class TempDB{

    Connection c1 = null;
    Statement statement = null;


    public TempDB(){
        try{
            Class.forName("org.postgresql.Driver");
            String pass = "GYBChicos";
            String user = "postgres";
            String url = "jdbc:postgresql://localhost:5432/CarDealer";

            c1 = DriverManager.getConnection(url, user, pass);
            statement = c1.createStatement();

        }catch(SQLException sqlEx){
            sqlEx.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean checkForUsername(String newUsername){
        try{
            ResultSet result = statement.executeQuery("SELECT username FROM users WHERE username='"+newUsername+"';");
            return result.next();

        }catch(SQLException sqlEx){
            System.out.println("Could not verify user, connection failed?");
            sqlEx.printStackTrace();
        }
        return true;
    }

    public boolean checkForCar(String SN){
        try{
            ResultSet result = statement.executeQuery("SELECT car_id FROM cars WHERE car_id='"+SN+"';");
            return result.next();

        }catch(SQLException ex){
            System.out.println("Could not verify user, connection failed?"); //TODO: write a better response
            ex.printStackTrace();
        }
        return true;
    }

    public User Login(String username, String pass) {
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password ='" + pass + "';");
            if(result.next()){
                System.out.println("Logged in successfully");
                return new User(result.getInt("user_id"), result.getBoolean("is_employee"),result.getString("username"),
                        result.getString("password"),result.getString("name"), result.getString("lastname"));

            }else{
                System.out.println("Username or name ar incorrect");
                return null;
            }
        }catch(SQLException sqlEx){
            System.out.println("Something went wrong at the connection");
            sqlEx.printStackTrace();
        }

        return null;
    }

    public void addNewUser(User u){
        try {
            statement.executeUpdate("INSERT INTO users (is_employee, username, password," +
                    " name, lastname ) values ('" + u.isEmployee() + "', '" +
                    u.getUsername() + "', '" + u.getPassword() + "', '" + u.getName() + "', '" +
                    u.getLastname() + "');");

        }catch(SQLException e){
            System.out.println("Something went wrong at the connection");
            e.printStackTrace();
        }
    }

    public void addNewCar(Car c){
        try{

            statement.executeUpdate("INSERT INTO cars (car_id, color, miles, model, brand, price, user_id) values(" +
                    "'" +c.getId() + "', ' " + c.getColor() + "', '" + c.getMiles() + "', '" +
                    c.getModel() + "', '" + c.getBrand() + "');");

        }catch (SQLException ex){
            System.out.println("Something wrong at SQL");
            //ex.printStackTrace();
        }catch(Exception ex){
            System.out.println("Something wrong while adding the car method");
            //ex.printStackTrace();
        }
    }

    public String deleteCarBySerialNumber(String SerialNumber){
        try {

            statement.executeUpdate("DELETE FROM cars WHERE car_id = '" + SerialNumber +"';");

            if(checkForCar(SerialNumber))
                return "Couldn't delete";
            else
                return  "Successfully deleted car";

        }catch(SQLException ex) {
            //ex.printStackTrace();
            System.out.println("SQL exception at deleting");
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "Couldn't delete, end of method.";
    }

    public ResultSet fetchAllCars(int i){
        try{
           return statement.executeQuery("Select * from cars where user_id = '"+i+"'");

        }catch(SQLException ex){
            System.out.println("could not fetch cars");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

}