package com.database;

import com.Collection.GenCollection;

public enum LocalDatabase {
    INSTANCE;

    private int current = 0;
    private DBNode[] nodeArray = new DBNode[10];

    synchronized public GenCollection contains(String query){
        for(DBNode node : nodeArray){
            if(node!=null && node.query.equalsIgnoreCase(query)){
                return node.data;
            }
        }
        return null;
    }

    synchronized public void add(String query,GenCollection col){
        if(current<=9){
            nodeArray[current++] = new DBNode(query,col);
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
    GenCollection data;

public DBNode(String s, GenCollection c){
        query = s;
        data = c;
    }
}