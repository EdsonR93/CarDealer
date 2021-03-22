package ORM.CRUD.Interfaces;
import java.sql.ResultSet;

public interface SelectQuery<Object> {
    public void select (String... columns);
    public void whereColumnEqual(String column, String data);
    public void whereColumnNotEqual(String column, String data);
    public ResultSet executeQuery();
}
