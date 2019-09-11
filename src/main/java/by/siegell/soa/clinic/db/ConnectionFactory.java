package by.siegell.soa.clinic.db;

import by.siegell.soa.clinic.constants.DbConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static String jdbcUrl;
    private static String jdbcUser;
    private static String jdbcPassword;

    static {
        try {
            Class.forName(DbConstants.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        jdbcUrl = DbConstants.URL;
        jdbcUser = DbConstants.USERNAME;
        jdbcPassword = DbConstants.PASSWORD;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl,
                jdbcUser,
                jdbcPassword);
    }
}
