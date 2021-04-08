package CRUD.Implementations;

import Models.Car;
import Models.User;
import ORM.CRUD.QueryCreation.Insert;
import ORM.CustomeExceptions.NoTableFoundException;
import ORM.CustomeExceptions.NotSameAmountOfColumnAndValuesException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestInsert {

    @Test
    public void testSetTableName_validValue_shouldBeEquals(){
        String expected = "Table1";
        Insert ins = new Insert();
        ins.setTableName(expected);
        String actual = ins.getTableName();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testSetTableName_emptyValue_shouldBeNotEquals(){
        String expected = "Table1";
        Insert ins = new Insert();
        ins.setTableName("");
        String actual = ins.getTableName();
        assertNotEquals(expected,actual);
    }

    @Test
    public void testSetTableName_nullValue_shouldBeNotEquals(){
        String expected = "Table1";
        Insert ins = new Insert();
        ins.setTableName(null);
        String actual = ins.getTableName();
        assertNotEquals(expected,actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetColumn_nullValue_shouldBeNotEquals(){
        String expected = "Column1";
        Insert ins = new Insert();
        ins.setColumn(null);
        String actual = ins.getColumns().get(0);
        assertNotEquals(expected,actual);
    }

    @Test
    public void testSetColumn_emptyValue_shouldBeEquals(){
        int expected = 0;
        Insert ins = new Insert();
        ins.setColumn("");
        int actual = ins.getColumns().size();
        assertEquals(expected,actual);
    }

    @Test
    public void testSetColumn_validValue_shouldBeEquals(){
        String expected = "Column1";
        Insert ins = new Insert();
        ins.setColumn("Column1");
        String actual = ins.getColumns().get(0);
        assertEquals(expected,actual);
    }

    @Test
    public void testSetColumns_nullValue_shouldBeNotEquals(){
        int expected = 4;
        Insert ins = new Insert();
        ins.setColumns(null,null,null,null);
        int actual = ins.getColumns().size();
        assertNotEquals(expected,actual);
    }

    @Test
    public void testSetColumns_emptyValue_shouldBeNotEquals(){
        int expected = 4;
        Insert ins = new Insert();
        ins.setColumns("","","","");
        int actual = ins.getColumns().size();
        assertNotEquals(expected,actual);
    }

    @Test
    public void testSetColumns_validValue_shouldBeEquals(){
        int expected = 4;
        Insert ins = new Insert();
        ins.setColumns("c1","c2","c3","c4");
        int actual = ins.getColumns().size();
        assertEquals(expected,actual);
    }

    //test values

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetValue_nullValue_shouldBeNotEquals(){
        String expected = "value1";
        Insert ins = new Insert();
        ins.setValue(null);
        String actual = ins.getValues().get(0);
        assertNotEquals(expected,actual);
    }

    @Test
    public void testSetValue_emptyValue_shouldBeEquals(){
        int expected = 0;
        Insert ins = new Insert();
        ins.setValue("");
        int actual = ins.getValues().size();
        assertEquals(expected,actual);
    }

    @Test
    public void testSetValue_validValue_shouldBeEquals(){
        String expected = "value1";
        Insert ins = new Insert();
        ins.setValue("value1");
        String actual = ins.getValues().get(0);
        assertEquals(expected,actual);
    }

    @Test
    public void testSetValues_nullValue_shouldBeNotEquals(){
        int expected = 4;
        Insert ins = new Insert();
        ins.setValues(null,null,null,null);
        int actual = ins.getValues().size();
        assertNotEquals(expected,actual);
    }

    @Test
    public void testSetValues_emptyValue_shouldBeNotEquals(){
        int expected = 4;
        Insert ins = new Insert();
        ins.setValues("","","","");
        int actual = ins.getValues().size();
        assertNotEquals(expected,actual);
    }

    @Test
    public void testSetValues_validValue_shouldBeEquals(){
        int expected = 4;
        Insert ins = new Insert();
        ins.setValues("c1","c2","c3","c4");
        int actual = ins.getValues().size();
        assertEquals(expected,actual);
    }
    //Test building query
    @Test(expected = NoTableFoundException.class)
    public void testBuildInsertQuery_withNoTableName_shouldThrowException() throws NoTableFoundException, NotSameAmountOfColumnAndValuesException {
        String expected = "INSERT INTO cars(col1, col2, col3) VALUES ('val1', 'val2', 'val3');";
        Insert ins = new Insert();

        ins.setColumns("col1","col2","col3");
        ins.setValues("val1","val2","val3");
        String actual = ins.buildInsertQuery();
        assertEquals(expected,actual);
    }

    @Test(expected = NotSameAmountOfColumnAndValuesException.class)
    public void testBuildInsertQuery_withDifferentAmountOfColAndVal_shouldThrowException() throws NoTableFoundException, NotSameAmountOfColumnAndValuesException {
        String expected = "INSERT INTO cars(col1, col2, col3) VALUES ('val1', 'val2', 'val3');";
        Insert ins = new Insert();
        ins.setTableName("cars");
        ins.setColumns("col1","col2");
        ins.setValues("val1","val2","val3");
        String actual = ins.buildInsertQuery();
        assertEquals(expected,actual);
    }

    @Test
    public void testBuildInsertQuery_withValidData_shouldBeEquals() throws NoTableFoundException, NotSameAmountOfColumnAndValuesException {
        String expected = "INSERT INTO cars(col1, col2, col3) VALUES ('val1', 'val2', 'val3');";
        Insert ins = new Insert();
        ins.setTableName("cars");
        ins.setColumns("col1","col2","col3");
        ins.setValues("val1","val2","val3");
        String actual = ins.buildInsertQuery();
        assertEquals(expected,actual);
    }

    @Test
    public void testInsertObject_withColumnAnnotation_shouldBeEquals() throws NoTableFoundException, IllegalAccessException, NotSameAmountOfColumnAndValuesException {
        String expected = "INSERT INTO cars(model, brand, make, miles, color, price, owner_id) VALUES ('2113', 'Veloster', 'Hyundai', '654564', 'Orange', '15222.0', '0');";
        Car car = new Car(21212,2113,"Veloster","Hyundai","Orange",654564,15222f,0);
        Insert ins = new Insert(car);
        String actual = ins.buildInsertQuery();
        assertEquals(expected,actual);
    }

    @Test
    public void testInsertObject_withColumnNotRequiredAnnotation_shouldBeEquals() throws NoTableFoundException, IllegalAccessException, NotSameAmountOfColumnAndValuesException {
        //Fields user_id and isEmployee of model User has annotation @ColumnNotRequired
        String expected = "INSERT INTO Users(username, password, name, lastname) VALUES ('username', 'pass', 'name', 'lastname');";
        User user = new User(2,false,"username","pass","name","lastname");
        Insert ins = new Insert(user);
        String actual = ins.buildInsertQuery();
        assertEquals(expected,actual);
    }

}
