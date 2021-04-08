package ORM.CRUD.QueryCreation;

import ORM.CustomeExceptions.NoTableFoundException;
import ORM.CustomeExceptions.NoWhereClauseFoundException;
import ORM.CRUD.Interfaces.DeleteQuery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Delete implements DeleteQuery<Delete> {
    private String tableName;
    private List<String> whereClauses = new Vector<>();

    public String getTableName() {
        return tableName;
    }

    public List<String> getWhereClauses(){
        return whereClauses;
    }

    @Override
    synchronized public Delete setTableName(String name) {
        if(name!=null && !name.equals(""))
            tableName = name;
        return this;
    }

    @Override
    synchronized public Delete setWhereClause(String clause) {
        if(clause!=null && !clause.equals(""))
            whereClauses.add(clause);
        return this;
    }

    @Override
    synchronized public Delete and(){
        if(!whereClauses.isEmpty())
            whereClauses.add(" AND ");
        return this;
    }

    @Override
    synchronized public Delete or(){
        if(!whereClauses.isEmpty())
            whereClauses.add(" OR ");
        return this;
    }

    @Override
    synchronized public String buildDeleteQuery() throws NoTableFoundException, NoWhereClauseFoundException {
        if(tableName==null || tableName.equals(""))
            throw new NoTableFoundException();
        if(whereClauses.isEmpty())
            throw new NoWhereClauseFoundException();

        StringBuilder deleteQuery = new StringBuilder();

        deleteQuery.append("DELETE FROM ").append(tableName);
        Iterator<String> iter;

        iter = whereClauses.iterator();
        deleteQuery.append(" WHERE ");
        while (iter.hasNext()){
            deleteQuery.append(iter.next());
        }
         deleteQuery.append(";");

        return deleteQuery.toString();
    }
}
