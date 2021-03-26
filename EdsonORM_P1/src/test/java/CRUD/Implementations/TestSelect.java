package CRUD.Implementations;

import ORM.CRUD.QueryCreation.Select;
import ORM.CustomeExceptions.NoColumnsFoundException;
import ORM.CustomeExceptions.NoTableFoundException;
import org.junit.Test;
import java.sql.SQLException;
import static org.junit.Assert.*;



public class TestSelect {

    @Test(expected = SQLException.class)
    public void testBuildSelectStatement_withoutColumns_shouldThrowException() throws NoColumnsFoundException, NoTableFoundException {
        String expected = "SELECT col1 FROM users WHERE id='1';";
        Select sel = new Select();
        sel.setTableName("users");
        //sel.setColumn("col1");
        sel.setWhereClause("id='1'");
        String actual = sel.buildSelectStatement();

        assertEquals(expected,actual);
    }

    @Test(expected = SQLException.class)
    public void testBuildSelectStatement_withoutTableName_shouldThrowException() throws NoColumnsFoundException, NoTableFoundException {
        String expected = "SELECT col1 FROM users WHERE id='1';";
        Select sel = new Select();
        //sel.setTableName("users");
        sel.setColumn("col1");
        sel.setWhereClause("id='1'");
        String actual = sel.buildSelectStatement();

        assertEquals(expected,actual);
    }

    @Test
    public void testBuildSelectStatement_withoutWhereClause_shouldPass() throws NoColumnsFoundException, NoTableFoundException {
        String expected = "SELECT col1 FROM users;";
        Select sel = new Select();
        sel.setTableName("users");
        sel.setColumn("col1");
        //sel.setWhereClause("id='1'");
        String actual = sel.buildSelectStatement();

        assertEquals(expected,actual);
    }
    @Test
    public void testBuildSelectStatement_withAllData_shouldPass() throws NoColumnsFoundException, NoTableFoundException {
        String expected = "SELECT col1 FROM users WHERE id='1';";
        Select sel = new Select();
        sel.setTableName("users");
        sel.setColumn("col1");
        sel.setWhereClause("id='1'");
        String actual = sel.buildSelectStatement();

        assertEquals(expected,actual);
    }
}
