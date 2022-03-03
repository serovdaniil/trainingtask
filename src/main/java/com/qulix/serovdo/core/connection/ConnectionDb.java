package com.qulix.serovdo.core.connection;

import com.qulix.serovdo.core.command.function.employee.CreateEmployeeCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionDb {
    private static final String DB_URL = "jdbc:hsqldb:hsql://localhost:9009/trainingtask";
    private static final String DB_NAME = "SA";
    private static final String DB_PASSWORD = "";

    private static final Logger logger = Logger.getLogger(ConnectionDb.class.getName());

    public Connection gerConnection() throws SQLException {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (Exception e) {
            logger.warning("ERROR: failed to load HSQLDB JDBC driver.");
        }
        return DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);

    }
}
