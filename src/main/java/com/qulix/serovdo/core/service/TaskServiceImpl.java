package com.qulix.serovdo.core.service;

import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.api.entity.StatusTask;
import com.qulix.serovdo.api.entity.Task;
import com.qulix.serovdo.api.service.TaskService;
import com.qulix.serovdo.core.dao.TaskDaoImpl;
import com.qulix.serovdo.core.exception.DaoException;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;
import com.qulix.serovdo.core.validator.DateValidatorImpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TaskServiceImpl implements TaskService {

    private final TaskDaoImpl taskDao;

    private final DateValidatorImpl validator = new DateValidatorImpl();

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    public TaskServiceImpl(TaskDaoImpl taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public boolean create(StatusTask status, String name, Project nameProject,
                          String job, String startDate, String finishDate, Employee employee)
            throws ValidationException, ServiceException {
        try {
            if (!validator.isNumberValidator(status.getId())
                    || !validator.isStringValidator(status.getNameTask())
                    || !validator.isStringValidator(name)
                    || !validator.isStringValidator(nameProject.getName())
                    || !validator.isStringValidator(job)
                    || !validator.isNumberValidator(employee.getId())
                    || !validator.isStringValidator(employee.getFirstName())
                    || !validator.isStringValidator(employee.getLastName())
                    || !validator.isStringValidator(employee.getPatronymic())
                    || !validator.isStringValidator(employee.getPosition())) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return taskDao.create(status, name, nameProject, job, startDate, finishDate, employee);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateEntity(Long id, StatusTask status, String name, Project nameProject,
                                String job, String startDate, String finishDate, Employee employee)
            throws ValidationException, ServiceException {
        try {
            if (!validator.isNumberValidator(id)
                    || !validator.isNumberValidator(status.getId())
                    || !validator.isStringValidator(status.getNameTask())
                    || !validator.isStringValidator(name)
                    || !validator.isStringValidator(nameProject.getName())
                    || !validator.isStringValidator(job)
                    || !validator.isNumberValidator(employee.getId())
                    || !validator.isStringValidator(employee.getFirstName())
                    || !validator.isStringValidator(employee.getLastName())
                    || !validator.isStringValidator(employee.getPatronymic())
                    || !validator.isStringValidator(employee.getPosition())) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return taskDao.updateEntity(id, status, name, nameProject, job, startDate, finishDate, employee);
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
            return taskDao.removeEntity(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Task> findAll() throws ServiceException {
        try {
            return taskDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Task> findById(Long id) throws ValidationException, ServiceException {
        try {
            if (!validator.isNumberValidator(id)) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return taskDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
