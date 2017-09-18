package ro.sci.hotel.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Abstract class for database connection
 */
public abstract class BaseRepository {

    /**
     * Connection to database
     * @return Connection
     */
    Connection newConnection() {
        loadDriver();
        DriverManager.setLoginTimeout(60);

        try {
            String url = "jdbc:" + "postgresql" + "://" + "localhost" + ":" + "5432" + "/" + "hotel" + "?user=" + "admin2" + "&password=" + "admin2";

            return DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.err.println("Cannot connect to the database: " + e.getMessage());
        }

        return null;
    }

    /**
     * Load postgresql JDBC driver
     */
    private void loadDriver() {

        try {
            Class.forName("org.postgresql.Driver")
                 .newInstance();

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.err.println("Can’t load driver. Verify CLASSPATH");
            System.err.println(e.getMessage());
        }
    }
}
