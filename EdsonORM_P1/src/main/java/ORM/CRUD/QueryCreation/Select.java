package ORM.CRUD.QueryCreation;

import ORM.Anotations.Column;
import ORM.Anotations.ColumnNotRequired;
import ORM.Anotations.Table;
import ORM.CustomeExceptions.NoColumnsFoundException;
import ORM.CustomeExceptions.NoTableFoundException;
import ORM.CRUD.Interfaces.SelectQuery;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Select implements SelectQuery<Select>{

    private List<String> tableNames;
    private List<String> columns;
    private List<String> whereClauses;

    public Select(){
        tableNames = new Vector<>();
        columns = new Vector<>();
        whereClauses = new Vector<>();
    }

    public Select(Object obj){
        this();
        Class<?> cl = obj.getClass();

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
            }
        }
    }

    @Override
    synchronized public Select setTableName(String tableName){
        if(tableNames.isEmpty())
            tableNames.add(tableName);

        return this;
    }

    @Override
    synchronized public Select setColumn(String column){
        if(column!=null)
            columns.add(column);

        return this;
    }

    @Override
    synchronized public Select setColumns(String... columnsArray){
        for (String col: columnsArray) {
            if(col!=null)
                columns.add(col);
        }
        return this;
    }

    @Override
    synchronized public Select setWhereClause(String clause){
        if(clause!=null)
            whereClauses.add(clause);

        return this;
    }

    @Override
    synchronized public Select and(){
        if(!whereClauses.isEmpty())
            whereClauses.add(" AND ");
        return this;
    }

    @Override
    synchronized public Select or(){
        if(!whereClauses.isEmpty())
            whereClauses.add(" OR ");
        return this;
    }


    @Override
    synchronized public String buildSelectStatement() throws NoColumnsFoundException, NoTableFoundException {
        if(columns.isEmpty())
            throw new NoColumnsFoundException();
        if(tableNames.isEmpty())
            throw new NoTableFoundException();

        StringBuilder select = new StringBuilder();

        select.append("SELECT ");
        Iterator<String> iter = columns.iterator();
        while (iter.hasNext()){
            select.append(iter.next());
            if(iter.hasNext())
               select.append(", ");
        }

        select.append(" FROM ");

        iter = tableNames.iterator();
        while (iter.hasNext()){
            select.append(iter.next());
            if(iter.hasNext())
                select.append(", ");
        }

        if(!whereClauses.isEmpty()){
            select.append(" WHERE ");
            iter = whereClauses.iterator();
            while (iter.hasNext()){
                select.append(iter.next());
            }
        }


        select.append(";");

        return select.toString();
    }
}
