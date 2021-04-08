package ORM.CRUD.Interfaces;

import ORM.CustomeExceptions.*;

public interface StatementBuilder<T> extends SelectQuery<T>,InsertQuery<T>,UpdateQuery<T>,DeleteQuery<T>{

    @Override
    T setValue(String value);

    @Override
    T setValues(String... values);

    @Override
    T setTableName(String tableName);

    @Override
    T setColumn(String column);

    @Override
    T setColumns(String... columnsArray);

    @Override
    T setWhereClause(String clause);

    @Override
    T and();

    @Override
    T or();

    @Override
    String buildSelectStatement() throws NoColumnsFoundException, NoTableFoundException;

    @Override
    String buildUpdateQuery() throws NoTableFoundException, NoValuesFoundException, NoWhereClauseFoundException;

    @Override
    String buildInsertQuery() throws NoTableFoundException, NotSameAmountOfColumnAndValuesException;

    @Override
    String buildDeleteQuery() throws NoTableFoundException, NoWhereClauseFoundException;
}
