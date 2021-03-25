package com.database;

import java.sql.ResultSet;

public enum LocalDatabase {
    INSTANCE;

    private int current = 0;
    private DBNode[] nodeArray = new DBNode[10];

    synchronized public ResultSet contains(String query){
        for(DBNode node : nodeArray){
            if(node!=null && node.query.equalsIgnoreCase(query)){
                return node.data;
            }
        }
        return null;
    }

    synchronized public void add(String query,ResultSet res){
        if(current<=9){
            nodeArray[current++] = new DBNode(query,res);
        }else{
            current = 0;
        }
    }

    synchronized public void flush(){
        nodeArray = new DBNode[10];
        current = 0;
    }
}


class DBNode{
    String query;
    ResultSet data;

    public DBNode(String s, ResultSet r){
        query = s;
        data = r;
    }
}