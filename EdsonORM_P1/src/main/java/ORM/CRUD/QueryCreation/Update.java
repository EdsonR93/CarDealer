package ORM.CRUD.QueryCreation;

import ORM.Anotations.Column;
import ORM.Anotations.ColumnNotRequired;
import ORM.Anotations.PrimaryKey;
import ORM.Anotations.Table;
import ORM.CustomeExceptions.NoTableFoundException;
import ORM.CustomeExceptions.NoValuesFoundException;
import ORM.CustomeExceptions.NoWhereClauseFoundException;
import ORM.CRUD.Interfaces.UpdateQuery;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Update implements UpdateQuery<Update> {

    private String tableName;
    private List<String> whereClauses;
    private List<String> values;

    public Update(){
        whereClauses = new Vector<>();
        values = new Vector<>();
    }

    public Update(Object obj) throws IllegalAccessException {
        this();
        Class<?> cl = obj.getClass();

        Table table = cl.getAnnotation(Table.class);
        if(table!=null)
            setTableName(table.name());

        Field[] fields = cl.getDeclaredFields();
        Column col;
        ColumnNotRequired notReqCol;
        PrimaryKey pk;

        for(Field var : fields){
            var.setAccessible(true);
            col = var.getAnnotation(Column.class);
            notReqCol = var.getAnnotation(ColumnNotRequired.class);
            pk = var.getAnnotation(PrimaryKey.class);
            if(col!=null && notReqCol==null){
                setValue(col.name() +" = '" +var.get(obj).toString() +"'");
            }
            if(pk!=null && col!=null)
                setWhereClause(col.name() +" = '" +var.get(obj).toString() +"'");
        }


    }


    @Override
    synchronized public Update setTableName(String s) {
        tableName = s;
        return this;
    }

    @Override
    synchronized public Update setValue(String s) {
        if(s!=null)
            values.add(s);
        return this;
    }

    @Override
    synchronized public Update setValues(String... s) {
        for(String val : s){
            if(val!=null){
                values.add(val);
            }
        }
        return this;
    }

    @Override
    synchronized public Update and(){
        if(!whereClauses.isEmpty())
            whereClauses.add(" AND ");
        return this;
    }

    @Override
    synchronized public Update or(){
        if(!whereClauses.isEmpty())
            whereClauses.add(" OR ");
        return this;
    }

    @Override
    synchronized public Update setWhereClause(String clause) {
        if(clause!=null)
            whereClauses.add(clause);

        return this;
    }

    @Override
    synchronized public String buildUpdateQuery() throws NoTableFoundException, NoValuesFoundException, NoWhereClauseFoundException {
        if(tableName==null || tableName.equals(""))
            throw new NoTableFoundException();
        if(values.isEmpty())
            throw new NoValuesFoundException();
        if(whereClauses.isEmpty())
            throw new NoWhereClauseFoundException();

        StringBuilder updateQuery = new StringBuilder();

        updateQuery.append("UPDATE ").append(tableName).append(" SET ");

        Iterator<String> iter = values.iterator();

        while (iter.hasNext()){
            while (iter.hasNext()){
                updateQuery.append(iter.next());
                if(iter.hasNext())
                    updateQuery.append(", ");
            }
        }
        updateQuery.append(" WHERE ");
        iter = whereClauses.iterator();
        while (iter.hasNext()){
            while (iter.hasNext()){
                updateQuery.append(iter.next());
                if(iter.hasNext())
                    updateQuery.append(", ");
            }
        }
        updateQuery.append(";");
        return updateQuery.toString();
    }
}
