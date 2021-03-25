package CRUD.Implementations;

import ORM.CRUD.QueryCreation.Delete;
import ORM.CustomeExceptions.NoTableFoundException;
import ORM.CustomeExceptions.NoWhereClauseFoundException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestDelete {

    @Test
    public void testSetTableName_validValue_shouldBeEquals(){
        String expected = "Table1";
        Delete del = new Delete();
        del.setTableName(expected);
        String actual = del.getTableName();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testSetTableName_emptyValue_shouldBeNotEquals(){
        String expected = "Table1";
        Delete del = new Delete();
        del.setTableName("");
        String actual = del.getTableName();
        assertNotEquals(expected,actual);
    }

    @Test
    public void testSetTableName_nullValue_(){
        String expected = "Table1";
        Delete del = new Delete();
        del.setTableName(null);
        String actual = del.getTableName();
        assertNotEquals(expected,actual);
    }

    @Test
    public void testAnd_whileWhereClauseIsEmpty_ShouldNotAddAnd(){
        int expected = 0;

        Delete delete = new Delete();
        delete.and();

        int actual = delete.getWhereClauses().size();

        assertEquals(expected,actual);
    }

    @Test
    public void testAnd_withWhereClause_ShouldAddAnd(){
        int expected = 2;

        Delete delete = new Delete();
        delete.setWhereClause("x='5'");
        delete.and();

        int actual = delete.getWhereClauses().size();

        assertEquals(expected,actual);
    }

    @Test
    public void testOr_whileWhereClauseIsEmpty_ShouldNotAddAnd(){
        int expected = 0;

        Delete delete = new Delete();
        delete.or();

        int actual = delete.getWhereClauses().size();

        assertEquals(expected,actual);
    }

    @Test
    public void testOr_withWhereClause_ShouldAddAnd(){
        int expected = 2;

        Delete delete = new Delete();
        delete.setWhereClause("x='5'");
        delete.or();

        int actual = delete.getWhereClauses().size();

        assertEquals(expected,actual);
    }

    @Test(expected = NoTableFoundException.class)
    public void testBuildDeleteQuery_withNoTableName_shouldThrowException() throws NoTableFoundException, NoWhereClauseFoundException {
        String expected = "DELETE FROM Cars WHERE Col1 = '1';";
        Delete del = new Delete();
        del.setWhereClause("Col1 = '1'");
        String actual = del.buildDeleteQuery();
        assertEquals(expected,actual);
    }

    @Test(expected = NoWhereClauseFoundException.class)
    public void testBuildDeleteQuery_withNoWhereClause_shouldThrowException() throws NoTableFoundException, NoWhereClauseFoundException {
        String expected = "DELETE FROM Cars WHERE Col1 = '1';";
        Delete del = new Delete();
        del.setTableName("Cars");
        String actual = del.buildDeleteQuery();
        assertEquals(expected,actual);
    }

    @Test
    public void testBuildDeleteQuery_withValidData_shouldPass() throws NoTableFoundException, NoWhereClauseFoundException {
        String expected = "DELETE FROM Cars WHERE Col1 = '1';";
        Delete del = new Delete();
        del.setTableName("Cars").setWhereClause("Col1 = '1'");
        String actual = del.buildDeleteQuery();
        assertEquals(expected,actual);
    }

}
