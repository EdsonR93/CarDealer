package ORM.CRUD.Interfaces;

import ORM.CustomeExceptions.NoTableFoundException;
import ORM.CustomeExceptions.NotSameAmountOfColumnAndValuesException;

public interface InsertQuery<T> {

    public T setColumn(String column);
    public T setColumns(String... columns);
    public T setTableName(String tableName);
    public T setValue(String value);
    public T setValues(String... values);
    public String buildInsertQuery() throws NoTableFoundException, NotSameAmountOfColumnAndValuesException;

}
