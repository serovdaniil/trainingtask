package com.qulix.serovdo.core.dao;

import com.qulix.serovdo.core.exception.DaoException;
import com.qulix.serovdo.core.validator.DateValidatorImpl;

import java.sql.*;

public class Runner {

    public static void main(String[] args) throws DaoException, SQLException {
//        try {
//            Class.forName("org.hsqldb.jdbc.JDBCDriver");
//            System.out.println("connection Ok");
//        } catch (Exception e) {
//            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
//            return;
//        }
//        Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9009/trainingtask", "SA", "");
//        if (connection != null) {
//            System.out.println("+++++");
//        }
//        PreparedStatement statement = connection.prepareStatement(REMOVE_EMPLOYEE1);
//        statement.setString(1, "asdv");
//        statement.setString(2, "asdv");
//        statement.setString(3, "asdv");
//        statement.setString(4, "asdv");
//        System.out.println(statement.executeUpdate() == 1);
        DateValidatorImpl dateValidator=new DateValidatorImpl();
        System.out.println(dateValidator.isNumberValidator((long)5.5));
    }

}
