package ORM.CRUD.DatabaseServices;

import ORM.CRUD.Interfaces.StatementHandler;
import ORM.CRUD.QueryCreation.Delete;
import ORM.CRUD.QueryCreation.Insert;
import ORM.CRUD.QueryCreation.Select;
import ORM.CRUD.QueryCreation.Update;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryHandler implements StatementHandler{

    private final Connection conn;
    private Statement statement;

    public QueryHandler(Connection conn) throws SQLException {
        this.conn = conn;
        statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    }

    @Override
    synchronized public int insertQuery(Insert ins) throws SQLException {
        return statement.executeUpdate(ins.buildInsertQuery());
    }

    @Override
    synchronized public ResultSet SelectQuery(Select sel) throws SQLException {
        return statement.executeQuery(sel.buildSelectStatement());
    }

    @Override
    synchronized public int updateQuery(Update upd) throws SQLException {
        return statement.executeUpdate(upd.buildUpdateQuery());
    }

    @Override
    synchronized public int deleteQuery(Delete del) throws SQLException {
        return statement.executeUpdate(del.buildDeleteQuery());
    }
}
