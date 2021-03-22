package ORM.CRUD.Implementations;

import ORM.CRUD.CustomeExceptions.NoColumnsFoundException;
import ORM.CRUD.CustomeExceptions.NoTableFoundException;
import ORM.CRUD.Interfaces.DeleteQuery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Delete implements DeleteQuery<Delete> {
    private String tableName;
    private List<String> whereClauses = new ArrayList<>();

    @Override
    public Delete setTableName(String name) {
        if(name!=null)
            tableName = name;
        return this;
    }

    @Override
    public Delete setWhereClaus(String clause) {
        if(clause!=null)
            whereClauses.add(clause);
        return this;
    }

    @Override
    public Delete and(){
        if(!whereClauses.isEmpty())
            whereClauses.add(" AND ");
        return this;
    }

    @Override
    public Delete or(){
        if(!whereClauses.isEmpty())
            whereClauses.add(" OR ");
        return this;
    }

    @Override
    public String buildDeleteQuery() throws NoTableFoundException {
        if(tableName==null || tableName.equals(""))
            throw new NoTableFoundException();

        StringBuilder deleteQuery = new StringBuilder();

        deleteQuery.append("DELETE FROM ").append(tableName);
        Iterator<String> iter = whereClauses.iterator();
         if(!whereClauses.isEmpty()){
             deleteQuery.append(" WHERE ");
            while (iter.hasNext()){
                deleteQuery.append(iter.next());
            }
        }

         deleteQuery.append(";");

        return deleteQuery.toString();
    }
}
