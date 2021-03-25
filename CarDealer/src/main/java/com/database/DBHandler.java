package com.database;

import ORM.CRUD.DatabaseServices.QueryHandler;
import ORM.CRUD.QueryCreation.Delete;
import ORM.CRUD.QueryCreation.Insert;
import ORM.CRUD.QueryCreation.Select;
import ORM.CRUD.QueryCreation.Update;
import com.Model.*;

import java.sql.*;

public enum DBHandler {
    INSTANCE;

    QueryHandler queryHandler;


    DBHandler(){
        try{
            queryHandler = new QueryHandler(DBServices.INSTANCE.getConnection());

        }catch(SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }

    synchronized public boolean CheckForUsername(String newUsername){
        try{
            Select sel = new Select();
            sel.setColumn("username").setTableName("users").setWhereClause("username='"+newUsername+"'");
            ResultSet result = queryHandler.SelectQuery(sel);
            return result.next();

        }catch(SQLException sqlEx){
            System.out.println("Could not verify user, connection failed?");
            sqlEx.printStackTrace();
        }
        return true;
    }

    synchronized public boolean CheckForCar(int SN){
        try{
            Select sel = new Select();
            sel.setColumn("serial_num").setTableName("cars").setWhereClause("serial_num='"+SN+"'");
            ResultSet result = queryHandler.SelectQuery(sel);
            return result.next();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return true;
    }
    synchronized public ResultSet FetchPaymentPlans (int userId){
        try{
            Select sel = new Select();
            sel.setColumn("*").setTableName("payment_plan").setWhereClause("user_id ='"+userId+"'").and()
                    .setWhereClause("months_left > '0'");
            return queryHandler.SelectQuery(sel);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    synchronized public User Login(String username, String pass) {
        try {
            Select sel = new Select();
            sel.setColumn("*").setTableName("users").setWhereClause("username='" + username + "'").and()
                    .setWhereClause("password ='" + pass + "'");
            ResultSet result = queryHandler.SelectQuery(sel);
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

    synchronized public void AddNewUser(User u){
        try {
            Insert ins = new Insert(u);
            queryHandler.insertQuery(ins);
        }catch(SQLException e){
            System.out.println("Something went wrong at the connection");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public void AddNewCar(Car c){
        try{
            Insert ins = new Insert(c);
            queryHandler.insertQuery(ins);
        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Something wrong at SQL");
        }catch(Exception ex){
            System.out.println("Something wrong while adding the car method");
        }
    }

    synchronized public boolean DeleteCarBySerialNumber(int SerialNumber){
        try {
            Delete del = new Delete();
            del.setTableName("cars").setWhereClause("serial_num = '" + SerialNumber +"'");
            queryHandler.deleteQuery(del);
            if(!CheckForCar(SerialNumber))
                return true;

        }catch(SQLException ex) {
            System.out.println("SQL exception at deleting");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    synchronized public ResultSet FetchAllCars(int i){
        try{
            Select sel = new Select();
            sel.setColumn("*").setTableName("cars").setWhereClause("owner_id = '"+i+"'");

           return queryHandler.SelectQuery(sel);

        }catch(SQLException ex){
            System.out.println("could not fetch cars");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    synchronized public ResultSet FetchAllOffers(){
        try{
            Select sel = new Select();
            sel.setColumn("*").setTableName("offers").setWhereClause("accepted ='false'").and()
                    .setWhereClause("pending = 'true'");
            return queryHandler.SelectQuery(sel);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    synchronized public ResultSet FetchOffers(int i){
        try{
            Select sel = new Select();
            sel.setColumn("*").setTableName("offers").setWhereClause("user_id='"+i+"'");
            return queryHandler.SelectQuery(sel);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    synchronized public ResultSet FetchPayments(int id){
        try{
            Select sel = new Select();
            sel.setColumn("*").setTableName("payments").setWhereClause("user_id='"+id+"'").setWhereClause("order by payment_date desc LIMIT 5");
            return queryHandler.SelectQuery(sel);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    synchronized public boolean AddNewOffer(int carId, int userId, double offer, int months){
        try{
            Insert ins = new Insert();
            ins.setTableName("offers").setColumns("car_serial_num","user_id","offer","months")
                    .setValues(String.valueOf(carId),String.valueOf(userId),String.valueOf(offer),String.valueOf(months));
            queryHandler.insertQuery(ins);
            //TODO: Add a way to verify
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    synchronized public boolean AcceptOffer(Offer offer){
        try{
            double monthlyPayment = offer.getAmountOffered() / offer.getMonths();

            Update upd = new Update();
            upd.setTableName("offers").setValues("pending=false").setWhereClause("car_serial_num ='"+offer.getCarSerialNum()+"'");
            queryHandler.updateQuery(upd);

            upd = new Update();
            upd.setTableName("offers").setValues("accepted=true","pending=false")
                    .setWhereClause("offer_id ='"+offer.getOfferId()+"'");
            queryHandler.updateQuery(upd);

            upd = new Update();
            upd.setTableName("cars").setValues("owner_id = '"+offer.getUserId()+"'","price = '"+offer.getAmountOffered()+"'")
                    .setWhereClause("serial_num ='"+offer.getCarSerialNum()+ "'");
            queryHandler.updateQuery(upd);

            Insert ins = new Insert(offer);
            queryHandler.insertQuery(ins);
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    synchronized public boolean RejectOffer(Offer offer){
        try{
            Update upd = new Update();
            upd.setTableName("offers").setValues("accepted=false","pending=false")
                    .setWhereClause("offer_id ='"+offer.getOfferId()+"'");
            queryHandler.updateQuery(upd);
            return true;

        } catch (SQLException ex) {
        ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
            return false;
    }

    synchronized public boolean RegisterPayment(PaymentPlan p){
        try{
            Update upd = new Update(p);
            queryHandler.updateQuery(upd);
            Insert ins = new Insert(new Payment(p.getUserId(),p.getCarSerialNum(),p.getMonthlyPayment()));
            queryHandler.insertQuery(ins);
            return true;
        }catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

}