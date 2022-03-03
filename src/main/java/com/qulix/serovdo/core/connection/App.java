package com.qulix.serovdo.core.connection;

import com.qulix.serovdo.core.exception.DaoException;

import java.sql.*;
import java.util.logging.Logger;

public class App {
    private static final String QUERY_URL = "create table EMPLOYEE" +
            "(" +
            "    ID_EMPLOYEE INTEGER identity," +
            "    FIRST_NAME  CHARACTER(40)," +
            "    LAST_NAME   CHARACTER(40) not null," +
            "    PATRONYMIC  CHARACTER(40) not null," +
            "    POSITION    CHARACTER(40)," +
            "    constraint EMPLOYEE_PK" +
            "        primary key (ID_EMPLOYEE)" +
            ");" +
            "create table PROJECT" +
            "(" +
            "    ID_PROJECT   INTEGER identity," +
            "    NAME_PROJECT CHARACTER(40) not null," +
            "    DESCRIPTION  CHARACTER(40)," +
            "    constraint PROJECT_PK" +
            "        primary key (ID_PROJECT)" +
            ");" +
            "create table STATUS_TASK" +
            "(" +
            "    ID_STATUS   INTEGER identity," +
            "    NAME_STATUS CHARACTER(40) not null," +
            "    constraint STATUS_TASK_PK" +
            "        primary key (ID_STATUS)" +
            ");" +
            "create table TASK" +
            "(" +
            "    ID_TASK     INTEGER identity," +
            "    STATUS_TASK INTEGER       not null," +
            "    NAME_TASK   CHARACTER(40) not null," +
            "    PROJECT_ID  INTEGER       not null," +
            "    NAME_JOB    INTEGER       not null," +
            "    START_DATE  DATE," +
            "    FINISH_DATE DATE," +
            "    EMPLOYEE_ID INTEGER       not null," +
            "    constraint TASK_PK" +
            "        primary key (ID_TASK)," +
            "    constraint \"Employee\"" +
            "        foreign key (EMPLOYEE_ID) references EMPLOYEE" +
            "            on delete cascade," +
            "    constraint NAME_PROJECT" +
            "        foreign key (PROJECT_ID) references PROJECT" +
            "            on delete cascade," +
            "    constraint STATUS_TASK" +
            "        foreign key (STATUS_TASK) references STATUS_TASK" +
            "            on delete cascade" +
            ");";
    private static final String INSERT_STATUS = "INSERT INTO STATUS_TASK (ID_STATUS,NAME_STATUS) values (1,'\u041d\u0435 \u043d\u0430\u0447\u0430\u0442\u0430'), " +
            "(2,'\u0412 \u043f\u0440\u043e\u0446\u0435\u0441\u0441\u0435'), " +
            "(3,'\u0417\u0430\u0432\u0435\u0440\u0448\u0435\u043d\u0430'), " +
            "(4,'\u041e\u0442\u043b\u043e\u0436\u0435\u043d\u0430')";

    private static final Logger logger = Logger.getLogger(App.class.getName());
    private static final String DB_URL = "jdbc:hsqldb:hsql://localhost:9009/trainingtask";
    private static final String DB_NAME = "SA";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) throws DaoException {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (Exception e) {
            logger.warning("ERROR: failed to load HSQLDB JDBC driver.");
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY_URL);
             ResultSet insertStatus = stmt.executeQuery(INSERT_STATUS)) {
        } catch (SQLException e) {
            logger.warning("sql: {}" + QUERY_URL);
            throw new DaoException(e);
        }
    }
}
