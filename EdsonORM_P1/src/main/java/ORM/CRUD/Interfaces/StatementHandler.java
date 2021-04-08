package ORM.CRUD.Interfaces;

import ORM.CRUD.QueryCreation.Delete;
import ORM.CRUD.QueryCreation.Insert;
import ORM.CRUD.QueryCreation.Select;
import ORM.CRUD.QueryCreation.Update;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface StatementHandler {
    public int insertQuery(Insert ins) throws SQLException;
    public ResultSet SelectQuery(Select sel) throws SQLException;
    public int updateQuery(Update upd) throws SQLException;
    public int deleteQuery(Delete del) throws SQLException;
}
