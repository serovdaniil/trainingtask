package com.qulix.serovdo.api.service;

import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;

import java.sql.SQLException;

public interface EmployeeService extends EntityService<Employee> {
    boolean create(String firstName, String lastName, String patronymic, String position) throws SQLException, ServiceException, ValidationException;

    boolean updateEntity(Long id, String firstName, String lastName, String patronymic, String position) throws ValidationException, ServiceException;
}
