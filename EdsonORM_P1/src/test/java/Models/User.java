package Models;

import ORM.Anotations.Column;
import ORM.Anotations.ColumnNotRequired;
import ORM.Anotations.PrimaryKey;
import ORM.Anotations.Table;

@Table(name = "Users")
public class User {
    @PrimaryKey
    @Column(name = "user_id")
    @ColumnNotRequired
    private int id;
    @Column(name = "is_employee")
    @ColumnNotRequired
    private boolean isEmployee = false;
    @Column(name = "username")
    private String Username;
    @Column(name = "password")
    private String Password;
    @Column(name = "name")
    private String Name;
    @Column(name = "lastname")
    private String Lastname;

    public User(String username, String password, String name, String lastname) {
        this.Username = username;
        this.Password = password;
        this.Name = name;
        this.Lastname = lastname;
    }
    public User(int user_id, boolean isEmployee, String username, String password, String name, String lastname) {
        this.id = user_id;
        this.isEmployee = isEmployee;
        Username = username;
        Password = password;
        Name = name;
        Lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", isEmployee=" + isEmployee +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", Name='" + Name + '\'' +
                ", Lastname='" + Lastname + '\'' +
                '}';
    }
}
