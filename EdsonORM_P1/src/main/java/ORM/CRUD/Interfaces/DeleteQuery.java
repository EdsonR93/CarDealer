package ORM.CRUD.Interfaces;

import ORM.CustomeExceptions.NoTableFoundException;
import ORM.CustomeExceptions.NoWhereClauseFoundException;

public interface DeleteQuery<T> {

    public T setTableName(String name);
    public T setWhereClause(String clause);
    public T and();
    public T or();
    public String buildDeleteQuery() throws NoTableFoundException, NoWhereClauseFoundException;
}
