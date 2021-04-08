package ORM.CRUD.QueryCreation;

import ORM.Anotations.Column;
import ORM.Anotations.ColumnNotRequired;
import ORM.Anotations.Table;
import ORM.CustomeExceptions.*;
import ORM.CRUD.Interfaces.StatementBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class QueryBuilder implements StatementBuilder<QueryBuilder> {
    private List<String> tableNames;
    private List<String> columns;
    private List<String> values;
    private List<String> whereClauses;

    public QueryBuilder(){
        tableNames = new Vector<>();
        columns = new Vector<>();
        values = new Vector<>();
        whereClauses = new Vector<>();
    }

    public QueryBuilder(Object obj) throws IllegalAccessException {
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
                setValue(var.get(obj).toString());
            }
        }
    }

    @Override
    synchronized public QueryBuilder setValue(String value) {
        if(value!=null && !value.equals(""))
            values.add(value);
        return this;
    }

    @Override
    synchronized public QueryBuilder setValues(String... values) {
        for(String val : values){
            if(val!=null && !val.equals(""))
                this.values.add(val);
        }
        return this;
    }

    @Override
    synchronized public QueryBuilder setTableName(String tableName) {
        if(tableName!=null && !tableName.equals(""))
            this.tableNames.add(tableName);
        return this;
    }

    @Override
    synchronized public QueryBuilder setColumn(String column) {
        if(column!=null && !column.equals(""))
            columns.add(column);
        return this;
    }

    @Override
    synchronized public QueryBuilder setColumns(String... columnsArray) {
        for (String col : columnsArray){
            if(col!=null && !col.equals("")){
                this.columns.add(col);
            }
        }
        return this;
    }

    @Override
    synchronized public QueryBuilder setWhereClause(String clause) {
        if(clause!=null && !clause.equals(""))
            whereClauses.add(clause);
        return this;
    }

    @Override
    synchronized public QueryBuilder and() {
        if(!whereClauses.isEmpty())
            whereClauses.add(" AND ");
        return this;
    }

    @Override
    synchronized public QueryBuilder or() {
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

    @Override
    synchronized public String buildUpdateQuery() throws NoTableFoundException, NoValuesFoundException, NoWhereClauseFoundException {
        if(tableNames.get(0)==null || tableNames.get(0).equals(""))
            throw new NoTableFoundException();
        if(values.isEmpty())
            throw new NoValuesFoundException();
        if(whereClauses.isEmpty())
            throw new NoWhereClauseFoundException();

        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append("UPDATE ").append(tableNames.get(0)).append(" SET ");
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

    @Override
    synchronized public String buildInsertQuery() throws NoTableFoundException, NotSameAmountOfColumnAndValuesException {
        if(tableNames.get(0) == null || tableNames.get(0).equals("") )
            throw new NoTableFoundException();
        if(columns.size() != values.size())
            throw new NotSameAmountOfColumnAndValuesException();

        StringBuilder insertQuery = new StringBuilder();
        Iterator<String> iter = columns.iterator();
        insertQuery.append("INSERT INTO ").append(tableNames.get(0));

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

    @Override
    synchronized public String buildDeleteQuery() throws NoTableFoundException, NoWhereClauseFoundException {
        if(tableNames.get(0)==null || tableNames.get(0).equals(""))
            throw new NoTableFoundException();
        if(whereClauses.isEmpty())
            throw new NoWhereClauseFoundException();

        StringBuilder deleteQuery = new StringBuilder();
        deleteQuery.append("DELETE FROM ").append(tableNames.get(0));
        Iterator<String> iter = whereClauses.iterator();
        deleteQuery.append(" WHERE ");
        while (iter.hasNext()){
            deleteQuery.append(iter.next());
        }
        deleteQuery.append(";");

        return deleteQuery.toString();
    }
}
