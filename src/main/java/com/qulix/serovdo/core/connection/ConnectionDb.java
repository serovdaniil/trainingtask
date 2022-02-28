package com.qulix.serovdo.core.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
    private static final String DB_URL = "jdbc:hsqldb:hsql://localhost:9009/trainingtask";
    private static final String DB_NAME = "SA";
    private static final String DB_PASSWORD = "";

    public Connection gerConnection() throws SQLException {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (Exception e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
        }
        return DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);

    }
}
