import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum DBServices {
    INSTANCE;

    public static final String URL = "jdbc:postgresql://project0.cbtacfjqzce9.us-west-2.rds.amazonaws.com:5432/carDealer?currentSchema=public";
    public static final String USERNAME = "Edson";
    public static final String PASSWORD = "MyPassword";

    final int MAX_THREADS = 4;
    ExecutorService threadActivator = Executors.newFixedThreadPool(MAX_THREADS);

    public ExecutorService getThreadActivator() {
        return threadActivator;
    }

    public final Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
