package ORM.CRUD.Interfaces;

import ORM.CRUD.CustomeExceptions.NoTableFoundException;

public interface InsertQuery<T> {

    public T setColumn(String column);
    public T setColumns(String... columns);
    public T setTable(String tableName);
    public T setValue(String value);
    public T setValues(String... values);
    public String buildInsertQuery() throws NoTableFoundException;

}
