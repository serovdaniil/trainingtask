package com.qulix.serovdo.api.service;

import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    boolean create(String firstName, String lastName, String patronymic, String position) throws SQLException, ServiceException, ValidationException;

    boolean updateEntity(Long id, String firstName, String lastName, String patronymic, String position) throws ValidationException, ServiceException;

    boolean removeEntity(Long id) throws ValidationException, ServiceException;

    List<Employee> findAll() throws ServiceException;

    Optional<Employee> findById(Long id) throws ValidationException, ServiceException;
}
