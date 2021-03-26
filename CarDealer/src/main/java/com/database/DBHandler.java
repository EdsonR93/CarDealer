package com.database;

import ORM.CRUD.DatabaseServices.QueryHandler;
import ORM.CRUD.QueryCreation.Delete;
import ORM.CRUD.QueryCreation.Insert;
import ORM.CRUD.QueryCreation.Select;
import ORM.CRUD.QueryCreation.Update;
import com.Model.*;

import java.sql.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public enum DBHandler {
    INSTANCE;

    QueryHandler queryHandler;
    LocalDatabase localDatabase = LocalDatabase.INSTANCE;
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
        }
        return null;
    }

    synchronized public Boolean AddNewOffer(final int carId, final int userId, final double offer, final int months) {
        Callable<Boolean> call = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                final Insert ins = new Insert();
                ins.setTableName("offers").setColumns("car_serial_num","user_id","offer","months")
                        .setValues(String.valueOf(carId),String.valueOf(userId),String.valueOf(offer),String.valueOf(months));
                int i = queryHandler.insertQuery(ins);
                localDatabase.flush();
                return i > 0;
            }
        };

        try{
            Future<Boolean> f = DBServices.INSTANCE.getThreadActivator().submit(call);
            return f.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    synchronized public Boolean AcceptOffer(final Offer offer){
        Callable<Boolean> call = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
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

                localDatabase.flush();
                return true;
            }
        };

        Future<Boolean> f = DBServices.INSTANCE.getThreadActivator().submit(call);
        try {
            return f.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    synchronized public Boolean RejectOffer(final Offer offer){
        Callable<Boolean> call = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Update upd = new Update();
                upd.setTableName("offers").setValues("accepted=false","pending=false")
                        .setWhereClause("offer_id ='"+offer.getOfferId()+"'");
                int i = queryHandler.updateQuery(upd);
                localDatabase.flush();
                return i>0;
            }
        };
        Future<Boolean> f = DBServices.INSTANCE.getThreadActivator().submit(call);
        try {
            return f.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    synchronized public Boolean RegisterPayment(final PaymentPlan p){
        Callable<Boolean> call = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Update upd = new Update(p);
                int i = 0;
                i+= queryHandler.updateQuery(upd);
                Insert ins = new Insert(new Payment(p.getUserId(),p.getCarSerialNum(),p.getMonthlyPayment()));
                i+= queryHandler.insertQuery(ins);

                localDatabase.flush();
                return i>1;
            }
        };
        Future<Boolean> f = DBServices.INSTANCE.getThreadActivator().submit(call);
        try {
            return f.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }

    synchronized public Boolean DeleteCarBySerialNumber(final int SerialNumber){
        Callable<Boolean> call = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                final Delete del = new Delete();
                del.setTableName("cars").setWhereClause("serial_num = '" + SerialNumber +"'");
                int i = queryHandler.deleteQuery(del);
                localDatabase.flush();
                return i>0;
            }
        };
        Future<Boolean> f = DBServices.INSTANCE.getThreadActivator().submit(call);
        try {
            return f.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    synchronized public void AddNewUser(final User u){
        DBServices.INSTANCE.getThreadActivator().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Insert ins = new Insert(u);
                    queryHandler.insertQuery(ins);
                    localDatabase.flush();
                } catch (SQLException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    synchronized public boolean AddNewCar(final Car c){
        Callable<Boolean> call = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Insert ins = new Insert(c);
                int i = queryHandler.insertQuery(ins);
                localDatabase.flush();
                return i>0;
            }
        };
        Future<Boolean> f = DBServices.INSTANCE.getThreadActivator().submit(call);
        try {
            return f.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}