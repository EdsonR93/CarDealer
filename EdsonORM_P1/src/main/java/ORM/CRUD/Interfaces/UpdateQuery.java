package ORM.CRUD.Interfaces;

import ORM.CustomeExceptions.NoTableFoundException;
import ORM.CustomeExceptions.NoValuesFoundException;
import ORM.CustomeExceptions.NoWhereClauseFoundException;

public interface UpdateQuery<T> {
    public T setTableName(String s);
    public T setValue(String s);
    public T setValues(String... s);
    public T and();
    public T or();
    public T setWhereClause(String clause);
    public String buildUpdateQuery() throws NoTableFoundException, NoValuesFoundException, NoWhereClauseFoundException;

}
