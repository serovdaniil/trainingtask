package com.qulix.serovdo.core.service;

import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.api.service.EmployeeService;
import com.qulix.serovdo.core.connection.ConnectionDb;
import com.qulix.serovdo.core.dao.EmployeeDaoImpl;
import com.qulix.serovdo.core.exception.DaoException;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;
import com.qulix.serovdo.core.validator.DateValidatorImpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDaoImpl employeeDao;

    private final DateValidatorImpl validator = new DateValidatorImpl();

    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());

    public EmployeeServiceImpl(EmployeeDaoImpl employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public boolean create(String firstName, String lastName,
                          String patronymic, String position) throws ServiceException, ValidationException {
        try {
            if (!validator.isStringValidator(firstName)
                    || !validator.isStringValidator(lastName)
                    || !validator.isStringValidator(patronymic)
                    || !validator.isStringValidator(position)) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return employeeDao.create(firstName, lastName, patronymic, position);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateEntity(Long id, String firstName, String lastName, String patronymic, String position) throws ValidationException, ServiceException {
        try {
            if (!validator.isNumberValidator(id)
                    || !validator.isStringValidator(firstName)
                    || !validator.isStringValidator(lastName)
                    || !validator.isStringValidator(patronymic)
                    || !validator.isStringValidator(position)) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return employeeDao.updateEntity(id, firstName, lastName, patronymic, position);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean removeEntity(Long id) throws ValidationException, ServiceException {
        try {
            if (!validator.isNumberValidator(id)) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return employeeDao.removeEntity(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Employee> findAll() throws ServiceException {
        try {
            return employeeDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Employee> findById(Long id) throws ValidationException, ServiceException {
        try {
            if (!validator.isNumberValidator(id)) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return employeeDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    public static EmployeeServiceImpl getInstance() {
        return EmployeeServiceImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final EmployeeServiceImpl INSTANCE = new EmployeeServiceImpl(new EmployeeDaoImpl(new ConnectionDb()));
    }
}

