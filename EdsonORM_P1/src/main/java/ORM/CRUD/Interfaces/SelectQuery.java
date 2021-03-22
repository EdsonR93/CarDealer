package ORM.CRUD.Interfaces;
import ORM.CRUD.Implementations.Select;

import java.sql.ResultSet;

public interface SelectQuery<T> {
    public T setTableName(String tableName);
    public T setColumn(String column);
    public T setColumns(String... columnsArray);
    public T setWhereClause(String clause);
    public T and();
    public T or();
    public String buildSelectStatement() throws Exception;
}
