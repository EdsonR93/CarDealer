package ORM.CRUD.Interfaces;

import ORM.CRUD.CustomeExceptions.NoColumnsFoundException;
import ORM.CRUD.CustomeExceptions.NoTableFoundException;

public interface DeleteQuery<T> {

    public T setTableName(String name);
    public T setWhereClaus(String clause);
    public T and();
    public T or();
    public String buildDeleteQuery() throws NoColumnsFoundException, NoTableFoundException;
}
