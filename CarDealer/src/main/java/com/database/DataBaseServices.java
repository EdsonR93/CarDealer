package com.database;

import com.Model.Offer;
import com.Model.User;
import com.Model.Car;
//import java.util.function. TODO: see if there is a function I could use

import java.sql.*;

public class DataBaseServices {

    Connection c1 = null;
    Statement statement = null;

    private DataBaseServices(){
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

    private static DataBaseServices instance;

    public static DataBaseServices getInstance(){
        if(instance == null){
            instance = new DataBaseServices();
        }
        return instance;
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

    public boolean checkForCar(int SN){
        try{
            ResultSet result = statement.executeQuery("SELECT car_id FROM cars WHERE car_id='"+SN+"';");
            return result.next();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return true;
    }

    public User Login(String username, String pass) {
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password ='" + pass + "';");
            if(result.next()){
                return new User(result.getInt("user_id"), result.getBoolean("is_employee"),result.getString("username"),
                        result.getString("password"),result.getString("name"), result.getString("lastname"));

            }else{
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
                    "'" +c.getSerialNum() + "', ' " + c.getColor() + "', '" + c.getMiles() + "', '" +
                    c.getModel() + "', '" + c.getBrand() + "');");

        }catch (SQLException ex){
            System.out.println("Something wrong at SQL");
        }catch(Exception ex){
            System.out.println("Something wrong while adding the car method");
        }
    }

    public boolean deleteCarBySerialNumber(int SerialNumber){
        try {
            statement.executeUpdate("DELETE FROM cars WHERE car_id = '" + SerialNumber +"';");
            if(!checkForCar(SerialNumber))
                return true;

        }catch(SQLException ex) {
            System.out.println("SQL exception at deleting");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
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

    public ResultSet fetchAllOffers (){
        try{
            return statement.executeQuery("Select * from offers where accepted ='false' and pending = 'true';");
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public boolean addNewOffer(int carId, int userId, float offer, int months){
        try{
            statement.executeUpdate("INSERT INTO offers VALUES ('"+carId+"','"+userId+"','"+offer+"','"+months+"');");
            //TODO: Add a way to verify
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    public boolean acceptOffer(Offer offer){
        try{
            statement.executeUpdate("UPDATE offers SET accepted=true, pending=false WHERE offer_id ='"+offer.getOfferId()+"';");
            statement.executeUpdate("UPDATE cars SET user_id = '"+offer.getUserId()+"', price = '"+offer.getAmountOffered()+"' WHERE car_id ='"+offer.getCarSerialNum()+ "';");

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

}