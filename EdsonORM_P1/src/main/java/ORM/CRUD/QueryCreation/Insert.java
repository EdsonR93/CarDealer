package ORM.CRUD.QueryCreation;

import ORM.Anotations.Column;
import ORM.Anotations.ColumnNotRequired;
import ORM.Anotations.Table;
import ORM.CustomeExceptions.NoTableFoundException;
import ORM.CRUD.Interfaces.InsertQuery;
import ORM.CustomeExceptions.NotSameAmountOfColumnAndValuesException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Insert implements InsertQuery<Insert> {

    private String tableName;
    private List<String> columns;
    private List<String> values;

    public Insert(){
        values = new Vector<>();
        columns = new Vector<>();
    }
    public Insert(Object o) throws NoTableFoundException, IllegalAccessException {
        this();
        Class<?> cl = o.getClass();

        Table table = cl.getAnnotation(Table.class);
        if(table!=null)
            setTableName(table.name());

        Field[] fields = cl.getDeclaredFields();
        Column col;
        ColumnNotRequired notReqCol;

        for(Field var : fields){
            var.setAccessible(true);
            col = var.getAnnotation(Column.class);
            notReqCol = var.getAnnotation(ColumnNotRequired.class);
            if(col!=null && notReqCol==null){
                setColumn(col.name());
                setValue(var.get(o).toString());
            }
        }
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<String> getValues() {
        return values;
    }

    @Override
    synchronized public Insert setColumn(String column) {
        if(column!=null && !column.equals(""))
            columns.add(column);
        return this;
    }

    @Override
    synchronized public Insert setColumns(String... columns){
        for (String col : columns){
            if(col!=null && !col.equals("")){
                this.columns.add(col);
            }
        }
        return this;
    }

    @Override
    synchronized public Insert setTableName(String tableName) {
        if(tableName!=null && !tableName.equals(""))
            this.tableName = tableName;
        return this;
    }

    @Override
    synchronized public Insert setValue(String value) {
        if(value!=null && !value.equals(""))
            values.add(value);
        return this;
    }

    @Override
    synchronized public Insert setValues(String... values) {
        for(String val : values){
            if(val!=null && !val.equals(""))
                this.values.add(val);
        }

        return this;
    }

    @Override
    synchronized public String buildInsertQuery() throws NoTableFoundException, NotSameAmountOfColumnAndValuesException {
        if(tableName == null || tableName.equals("") )
            throw new NoTableFoundException();
        if(columns.size() != values.size())
            throw new NotSameAmountOfColumnAndValuesException();

        StringBuilder insertQuery = new StringBuilder();
        Iterator<String> iter = columns.iterator();
        insertQuery.append("INSERT INTO ").append(tableName);

        if(!columns.isEmpty()){
            insertQuery.append("(");
            while (iter.hasNext()){
                insertQuery.append(iter.next());
                if(iter.hasNext())
                    insertQuery.append(", ");
            }
            insertQuery.append(")");
        }

        iter = values.iterator();
        if(!values.isEmpty()){
            insertQuery.append(" VALUES (");
            while (iter.hasNext()){
                insertQuery.append("'").append(iter.next()).append("'");
                if(iter.hasNext())
                    insertQuery.append(", ");
            }
            insertQuery.append(");");
        }
//        else{
//            //TODO:Add implementation to insert default values in row
//        }
        return insertQuery.toString();
    }
}
