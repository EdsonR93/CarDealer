package ORM;

import ORM.CRUD.Implementations.*;
import ORM.Models.Car;

public class testingDriver {

    public static void main(String[] args) throws Exception {
        Car car = new Car(21212,2113,"Veloster","Hyundai","Orange",654564,15222f,0);

        Select sel = new Select(car);
        Insert ins = new Insert(car);
        Delete del = new Delete();
        Update upd = new Update();
        QueryBuilder qb = new QueryBuilder(car);


        System.out.println(qb.buildInsertQuery());
        System.out.println(qb.buildSelectStatement());

//      will give a NoWhereClauseFoundException because we haven't specify a where clause
//      preventing us from deleting everything
//        System.out.println(qb.buildUpdateQuery());
//        System.out.println(qb.buildDeleteQuery());


//        sel.setColumn("*");
//        sel.setTableName("users");
//        System.out.println(sel.buildSelectStatement());
//
//        sel.setColumns("1","2","3","4");
//        sel.setColumn("5");
//        sel.setTableName("user");
//        sel.setWhereClause("1 = 'sds'").and().setWhereClause("2 = '2323'");
//        System.out.println(sel.buildSelectStatement());
//
//
//        ins.setTable("cars");
//        ins.setColumns("col1","col2","col3","col4").setColumn("col5");
//        ins.setValues("val1","val2","val3","val4").setValue("val5");
//        System.out.println(ins.buildInsertQuery());
//
//
//        del.setTableName("Cars").setWhereClaus("Col1 = '1'").or().setWhereClaus(" col2 = '1'");
//        System.out.println(del.buildDeleteQuery());
//
//        upd.setTableName("Cars");
//        upd.setValue("F = 'd'");
//        upd.setWhereClause("f='8'");
//        System.out.println(upd.buildUpdateQuery());

    }


}
