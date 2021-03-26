package CRUD.Implementations;

import ORM.CRUD.QueryCreation.Update;
import ORM.CustomeExceptions.NoTableFoundException;
import ORM.CustomeExceptions.NoValuesFoundException;
import ORM.CustomeExceptions.NoWhereClauseFoundException;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class TestUpdate {
    @Test
    public void testBuildUpdateQuery_withValidData_shouldPass() throws NoWhereClauseFoundException, NoTableFoundException, NoValuesFoundException {
        String expected = "UPDATE Cars SET col1 = 'd' WHERE col2 = '8';";
        Update upd = new Update();
        upd.setTableName("Cars");
        upd.setValue("col1 = 'd'");
        upd.setWhereClause("col2 = '8'");
        String actual = upd.buildUpdateQuery();
        assertEquals(expected,actual);
    }
    @Test(expected = SQLException.class)
    public void testBuildUpdateQuery_withoutTableName_shouldThrowException() throws NoWhereClauseFoundException, NoTableFoundException, NoValuesFoundException {
        String expected = "UPDATE Cars SET col1 = 'd' WHERE col2 = '8';";
        Update upd = new Update();
        //upd.setTableName("Cars");
        upd.setValue("col1 = 'd'");
        upd.setWhereClause("col2 = '8'");
        String actual = upd.buildUpdateQuery();
        assertEquals(expected,actual);
    }
    @Test(expected = SQLException.class)
    public void testBuildUpdateQuery_withoutSetValue_shouldThrowException() throws NoWhereClauseFoundException, NoTableFoundException, NoValuesFoundException {
        String expected = "UPDATE Cars SET col1 = 'd' WHERE col2 = '8';";
        Update upd = new Update();
        upd.setTableName("Cars");
        //upd.setValue("col1 = 'd'");
        upd.setWhereClause("col2 = '8'");
        String actual = upd.buildUpdateQuery();
        assertEquals(expected,actual);
    }
    @Test(expected = SQLException.class)
    public void testBuildUpdateQuery_withoutWhereClause_shouldThrowException() throws NoWhereClauseFoundException, NoTableFoundException, NoValuesFoundException {
        String expected = "UPDATE Cars SET col1 = 'd' WHERE col2 = '8';";
        Update upd = new Update();
        upd.setTableName("Cars");
        upd.setValue("col1 = 'd'");
        //upd.setWhereClause("col2 = '8'");
        String actual = upd.buildUpdateQuery();
        assertEquals(expected,actual);
    }
}
