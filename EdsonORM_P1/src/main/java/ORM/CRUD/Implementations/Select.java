package ORM.CRUD.Implementations;

import ORM.CRUD.CustomeExceptions.NoColumnsAddedException;
import ORM.CRUD.CustomeExceptions.NoTableAddedException;
import ORM.CRUD.Interfaces.SelectQuery;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Select implements SelectQuery<Select>{

    private List<String> tableNames = new ArrayList<>();
    private List<String> columns = new ArrayList<>();
    private List<String> whereClauses = new ArrayList<>();

    @Override
    public Select setTableName(String tableName){
        if(tableNames.isEmpty())
            tableNames.add(tableName);

        return this;
    }

    @Override
    public Select setColumn(String column){
        if(column!=null)
            columns.add(column);

        return this;
    }

    @Override
    public Select setColumns(String... columnsArray){
        for (String col: columnsArray) {
            if(col!=null)
                columns.add(col);
        }
        return this;
    }

    @Override
    public Select setWhereClause(String clause){
        if(clause!=null)
            whereClauses.add(clause);

        return this;
    }

    @Override
    public Select and(){
        if(!whereClauses.isEmpty())
            whereClauses.add(" AND ");
        return this;
    }

    @Override
    public Select or(){
        if(!whereClauses.isEmpty())
            whereClauses.add(" OR ");
        return this;
    }


    @Override
    public String buildSelectStatement() throws NoColumnsAddedException, NoTableAddedException {
        if(columns.isEmpty())
            throw new NoColumnsAddedException();
        if(tableNames.isEmpty())
            throw new NoTableAddedException();

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
