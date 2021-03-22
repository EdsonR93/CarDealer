package com.database;

import com.Model.Car;
import com.Model.Offer;
import com.Model.PaymentPlan;
import com.Model.User;

import java.sql.*;

public class DataBaseServices {

    Connection c1 = null;
    Statement statement = null;

    private DataBaseServices(){
        try{
            Class.forName("org.postgresql.Driver");
            String pass = "";
            String user = "";
            String url = "";

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

    public boolean CheckForUsername(String newUsername){
        try{
            ResultSet result = statement.executeQuery("SELECT username FROM users WHERE username='"+newUsername+"';");
            return result.next();

        }catch(SQLException sqlEx){
            System.out.println("Could not verify user, connection failed?");
            sqlEx.printStackTrace();
        }
        return true;
    }

    public String getTestUsername(){
        try{
            ResultSet result = statement.executeQuery("SELECT name,lastname FROM users WHERE username='test';");
            if (result.next())
                return result.getString("name") +" "+result.getString("lastname");
        }catch(SQLException sqlEx){
            System.out.println("connection failed?");
            sqlEx.printStackTrace();
        }
        return "";
    }


    public boolean CheckForCar(int SN){
        try{
            ResultSet result = statement.executeQuery("SELECT serial_num FROM cars WHERE serial_num='"+SN+"';");
            return result.next();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return true;
    }
    public ResultSet FetchPaymentPlans (int userId){
        try{
            return statement.executeQuery("SELECT * FROM payment_plan WHERE user_id='"+userId+"' AND months_left > '0';");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
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

    public void AddNewUser(User u){
        try {
            statement.executeUpdate("INSERT INTO users (username, password," +
                    " name, lastname ) values ('" + u.getUsername() + "', '" + u.getPassword()
                    + "', '" + u.getName() + "', '" + u.getLastname() + "');");

        }catch(SQLException e){
            System.out.println("Something went wrong at the connection");
            e.printStackTrace();
        }
    }

    public void AddNewCar(Car c){
        try{
            statement.executeUpdate("INSERT INTO cars (serial_num, color, miles, model, brand, make, price, owner_id) values(" +
                    "'" +c.getSerialNum() + "', ' " + c.getColor() + "', '" + c.getMiles() + "', '" +
                    c.getModel() + "', '" + c.getBrand() + "','"+c.getMake() +"', '"+ c.getPrice()+"','"+c.getOwnerId()+"');");

        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Something wrong at SQL");
        }catch(Exception ex){
            System.out.println("Something wrong while adding the car method");
        }
    }

    public boolean DeleteCarBySerialNumber(int SerialNumber){
        try {
            statement.executeUpdate("DELETE FROM cars WHERE serial_num = '" + SerialNumber +"';");
            if(!CheckForCar(SerialNumber))
                return true;

        }catch(SQLException ex) {
            System.out.println("SQL exception at deleting");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public ResultSet FetchAllCars(int i){
        try{
           return statement.executeQuery("Select * from cars where owner_id = '"+i+"'");

        }catch(SQLException ex){
            System.out.println("could not fetch cars");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public ResultSet FetchAllOffers(){
        try{
            return statement.executeQuery("Select * from offers where accepted ='false' and pending = 'true';");
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public ResultSet FetchOffers(int i){
        try{
            return statement.executeQuery("Select * from offers where user_id='"+i+"';");
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public ResultSet FetchPayments(int id){
        try{
            return statement.executeQuery("Select * from payments where user_id='"+id+"' order by payment_date desc LIMIT 5;");
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public boolean AddNewOffer(int carId, int userId, double offer, int months){
        try{
            statement.executeUpdate("INSERT INTO offers(car_serial_num,user_id,offer,months) VALUES ('"+carId+"','"+userId+"','"+offer+"','"+months+"');");
            //TODO: Add a way to verify
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    public boolean AcceptOffer(Offer offer){
        try{
            double monthlyPayment = offer.getAmountOffered() / offer.getMonths();
            PreparedStatement ps = c1.prepareStatement("SELECT accept_offer(?,?);");
            ps.setInt(1,offer.getOfferId());
            ps.setInt(2,offer.getCarSerialNum());
            ps.executeQuery();
            statement.executeUpdate("UPDATE cars SET owner_id = '"+offer.getUserId()+"', price = '"+offer.getAmountOffered()+"' WHERE serial_num ='"+offer.getCarSerialNum()+ "';");
            statement.executeUpdate("INSERT INTO payment_plan(user_id,car_serial_num,monthly_payment,total_months,months_left)" +
                    " VALUES('"+offer.getUserId()+"','"+offer.getCarSerialNum()+"','"+monthlyPayment+"','"+offer.getMonths()+"','"+offer.getMonths()+"')");
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean RejectOffer(Offer offer){
        try{
            statement.executeUpdate("UPDATE offers SET accepted=false, pending=false WHERE offer_id ='"+offer.getOfferId()+"';");
            return true;

        } catch (SQLException ex) {
        ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
            return false;
    }

    public boolean RegisterPayment(PaymentPlan p){
        try{
            statement.executeUpdate("UPDATE payment_plan SET months_left = '"+(p.getMonthsLeft()-1)+"';");
            statement.executeUpdate("INSERT INTO payments(user_id,car_serial_num,payment_amount)" +
                    " VALUES ('"+p.getUserId()+"','"+p.getCarSerialNum()+"','"+p.getMonthlyPayment()+"');");
            return true;
        }catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

}