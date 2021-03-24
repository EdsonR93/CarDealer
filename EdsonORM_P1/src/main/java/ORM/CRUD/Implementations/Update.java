package ORM.CRUD.Implementations;

import ORM.CustomeExceptions.NoTableFoundException;
import ORM.CustomeExceptions.NoValuesFoundException;
import ORM.CustomeExceptions.NoWhereClauseFoundException;
import ORM.CRUD.Interfaces.UpdateQuery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Update implements UpdateQuery<Update> {

    private String tableName;
    private List<String> whereClauses = new ArrayList<>();
    private List<String> values = new ArrayList<>();


    @Override
    public Update setTableName(String s) {
        tableName = s;
        return this;
    }

    @Override
    public Update setValue(String s) {
        if(s!=null)
            values.add(s);
        return this;
    }

    @Override
    public Update setValues(String... s) {
        for(String val : s){
            if(val!=null){
                values.add(val);
            }
        }
        return this;
    }

    @Override
    public Update and(){
        if(!whereClauses.isEmpty())
            whereClauses.add(" AND ");
        return this;
    }

    @Override
    public Update or(){
        if(!whereClauses.isEmpty())
            whereClauses.add(" OR ");
        return this;
    }

    @Override
    public Update setWhereClause(String clause) {
        if(clause!=null)
            whereClauses.add(clause);

        return this;
    }

    @Override
    public String buildUpdateQuery() throws NoTableFoundException, NoValuesFoundException, NoWhereClauseFoundException {
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

        return updateQuery.toString();
    }
}
