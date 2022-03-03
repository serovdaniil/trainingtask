package com.qulix.serovdo.core.service;

import com.qulix.serovdo.api.entity.Task;
import com.qulix.serovdo.api.service.TaskService;
import com.qulix.serovdo.core.connection.ConnectionDb;
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

    private static final Logger logger = Logger.getLogger(TaskServiceImpl.class.getName());

    public TaskServiceImpl(TaskDaoImpl taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public boolean create(Long idStatus, String name, Long idProject,
                          Long job, String startDate, String finishDate, Long idEmployee)
            throws ValidationException, ServiceException {
        try {
            if (!validator.isNumberValidator(idStatus)
                    || !validator.isStringValidator(name)
                    || !validator.isNumberValidator(idProject)
                    || !validator.isNumberValidator(job)
                    || !validator.isDateValidator(startDate, finishDate)
                    || !validator.isNumberValidator(idEmployee)) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return taskDao.create(idStatus, name, idProject, job, startDate, finishDate, idEmployee);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateEntity(Long id, Long idStatus, String name, Long idProject,
                                Long job, String startDate, String finishDate, Long idEmployee)
            throws ValidationException, ServiceException {
        try {
            if (!validator.isNumberValidator(id)
                    || !validator.isNumberValidator(idStatus)
                    || !validator.isStringValidator(name)
                    || !validator.isNumberValidator(idProject)
                    || !validator.isNumberValidator(job)
                    || !validator.isNumberValidator(idEmployee)
                    || !validator.isDateValidator(startDate, finishDate)) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return taskDao.updateEntity(id, idStatus, name, idProject, job, startDate, finishDate, idEmployee);
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

    @Override
    public List<Task> findAllTaskInProject(Long id) throws ValidationException, ServiceException {
        try {
            if (!validator.isNumberValidator(id)) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return taskDao.findAllTaskInProject(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public static TaskServiceImpl getInstance() {
        return TaskServiceImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final TaskServiceImpl INSTANCE = new TaskServiceImpl(new TaskDaoImpl(new ConnectionDb()));
    }
}
