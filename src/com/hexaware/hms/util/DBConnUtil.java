package com.hexaware.hms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {

    private static Connection connection;

    private DBConnUtil() {}

    public static Connection getConnection(String fileName) throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties props = DBPropertyUtil.getPropertyString(fileName);
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            String driver = props.getProperty("db.driver");

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new SQLException("JDBC Driver not found.", e);
            }

            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
