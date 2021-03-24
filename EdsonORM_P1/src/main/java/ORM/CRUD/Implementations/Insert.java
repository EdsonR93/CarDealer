package ORM.CRUD.Implementations;

import ORM.Anotations.Column;
import ORM.Anotations.ColumnNotRequired;
import ORM.Anotations.Table;
import ORM.CustomeExceptions.NoTableFoundException;
import ORM.CRUD.Interfaces.InsertQuery;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Insert implements InsertQuery<Insert> {

    private String tableName;
    private List<String> columns;
    private List<String> values;

    public Insert(){
        values = new ArrayList<>();
        columns = new ArrayList<>();
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

    @Override
    public Insert setColumn(String column) {
        if(column!=null)
            columns.add(column);
        return this;
    }

    @Override
    public Insert setColumns(String... columns){
        for (String col : columns){
            if(col!=null){
                this.columns.add(col);
            }
        }
        return this;
    }

    @Override
    public Insert setTableName(String tableName) {
        if(tableName!=null)
            this.tableName = tableName;
        return this;
    }

    @Override
    public Insert setValue(String value) {
        if(value!=null)
            values.add(value);
        return this;
    }

    @Override
    public Insert setValues(String... values) {
        for(String val : values){
            if(val!=null)
                this.values.add(val);
        }

        return this;
    }

    @Override
    public String buildInsertQuery() throws NoTableFoundException {
        if(tableName == null || tableName.equals("") )
            throw new NoTableFoundException();
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
