package ORM.CRUD.Implementations;

public class testingDriver {

    public static void main(String[] args) throws Exception {
        Select sel = new Select();
        Insert ins = new Insert();
        Delete del = new Delete();

        sel.setColumns("1","2","3","4");
        sel.setColumn("5");
        sel.setTableName("user");
        sel.setWhereClause("1 = 'sds'").and().setWhereClause("2 = '2323'");
        System.out.println(sel.buildSelectStatement());


        ins.setTable("cars");
        ins.setColumns("col1","col2","col3","col4").setColumn("col5");
        ins.setValues("val1","val2","val3","val4").setValue("val5");
        System.out.println(ins.buildInsertQuery());


        del.setTableName("Cars").setWhereClaus("Col1 = '1'").or().setWhereClaus(" col2 = '1'");
        System.out.println(del.buildDeleteQuery());

    }


}
