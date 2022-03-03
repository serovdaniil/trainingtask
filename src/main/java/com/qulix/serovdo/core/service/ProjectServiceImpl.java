package com.qulix.serovdo.core.service;

import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.api.service.ProjectService;
import com.qulix.serovdo.core.connection.ConnectionDb;
import com.qulix.serovdo.core.dao.ProjectDaoImpl;
import com.qulix.serovdo.core.exception.DaoException;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;
import com.qulix.serovdo.core.validator.DateValidatorImpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectDaoImpl projectDao;

    private final DateValidatorImpl validator = new DateValidatorImpl();

    private static final Logger logger = Logger.getLogger(ProjectServiceImpl.class.getName());

    public ProjectServiceImpl(ProjectDaoImpl projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public boolean create(String name, String description) throws ValidationException, ServiceException {
        try {
            if (!validator.isStringValidator(name)
                    || !validator.isStringValidator(description)) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return projectDao.create(name, description);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateEntity(Long id, String name, String description) throws ValidationException, ServiceException {
        try {
            if (!validator.isNumberValidator(id)
                    || !validator.isStringValidator(name)
                    || !validator.isStringValidator(description)) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return projectDao.updateEntity(id, name, description);
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
            return projectDao.removeEntity(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Project> findAll() throws ServiceException {
        try {
            return projectDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Project> findById(Long id) throws ValidationException, ServiceException {
        try {
            if (!validator.isNumberValidator(id)) {
                logger.warning("Service: The entered data is not correct!");
                throw new ValidationException("Service: The entered data is not correct!");
            }
            return projectDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public static ProjectServiceImpl getInstance() {
        return ProjectServiceImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ProjectServiceImpl INSTANCE = new ProjectServiceImpl(new ProjectDaoImpl(new ConnectionDb()));
    }
}
