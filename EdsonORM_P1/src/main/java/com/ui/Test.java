package com.ui;

import com.database.DataBaseServices;
import com.enterprise.annotations.TestClass;
import com.enterprise.annotations.TestMethod;

@TestClass
public class Test {
    DataBaseServices DB = DataBaseServices.getInstance();
    public Test(){

    }
    @TestMethod(expected = "test test")
    public String callTestMethod(){
        return DB.getTestUsername();
    }
}
