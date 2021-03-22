package ORM.CRUD.Implementations;

import ORM.CRUD.Interfaces.SelectQuery;
import com.Collection.CarHashSet;
import com.Model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Select{

    List<String> tableNames = new ArrayList<>();
    List<String> columns = new ArrayList<>();
    List<String> whereClauses = new ArrayList<>();

    public Select setTableName(String tableName){
        if(tableNames.isEmpty())
            tableNames.add(tableName);
        //else
            //TODO: Check select syntax for multiple tables

        return this;
    }

    public Select setColumn(String column){
        if(column!=null)
            columns.add(column);

        return this;
    }

    public Select setColumns(String... columnsArray){
        for (String col: columnsArray) {
            if(col!=null)
                columns.add(col);
        }
        return this;
    }

    public Select setWhereClause(String clause){
        if(clause!=null)
            whereClauses.add(clause);

        return this;
    }

    public Select and(){
        whereClauses.add(" AND ");
        return this;
    }

    public Select or(){
        whereClauses.add(" OR ");
        return this;
    }


    public String buildSelectStatement(){
        StringBuilder select = new StringBuilder();
        select.append("SELECT ");
        Iterator<String> iter = columns.iterator();
        while (iter.hasNext()){
            select.append(iter.next());
        }

        return select.toString();
    }
}
