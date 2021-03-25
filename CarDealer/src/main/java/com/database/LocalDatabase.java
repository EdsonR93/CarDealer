package com.database;

import java.sql.ResultSet;
import java.util.Collections;


public class LocalDatabase {
    int current = 0;
    DBNode<String,ResultSet>[] nodeArray = new DBNode[10];

    public ResultSet contains(String query){
        for(DBNode<String,ResultSet> node : nodeArray){
            if(node.query.equalsIgnoreCase(query)){
                return node.data;
            }
        }
        return null;
    }

    public void add(String query,ResultSet res){
        if(current<=9){
            nodeArray[current++] = new DBNode<>(query,res);
        }else{
            current = 0;
        }
    }
}


class DBNode<T, V>{
    T query;
    V data;

    public DBNode(T t, V v){
        query = t;
        data = v;
    }
}