package com.qulix.serovdo.api.dao;


import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.core.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    boolean create(String firstName, String lastName, String patronymic, String position) throws SQLException, DaoException;

    boolean updateEntity(Long id, String firstName, String lastName, String patronymic, String position) throws DaoException;

    boolean removeEntity(Long id) throws DaoException;

    List<Employee> findAll() throws DaoException;

    Optional<Employee> findById(Long id) throws DaoException;
}
