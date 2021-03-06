package com.qulix.serovdo.core.dao;

import com.qulix.serovdo.api.dao.TaskDao;
import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.api.entity.StatusTask;
import com.qulix.serovdo.api.entity.Task;
import com.qulix.serovdo.core.connection.ConnectionDb;
import com.qulix.serovdo.core.exception.DaoException;
import com.qulix.serovdo.core.exception.EntityExtractionFailedException;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TaskDaoImpl implements TaskDao {
    private static final String CREATE_TASK = "INSERT INTO TASK(STATUS_TASK, NAME_TASK, PROJECT_ID," +
            " NAME_JOB,START_DATE,FINISH_DATE,EMPLOYEE_ID) values(?,?,?,?,?,?,?)";

    private static final String REMOVE_TASK = "DELETE FROM TASK WHERE ID_TASK=?";

    private static final String UPDATE_TASK = "UPDATE TASK SET STATUS_TASK=?, NAME_TASK=?, PROJECT_ID=?, " +
            "NAME_JOB=?,START_DATE=?,FINISH_DATE=?,EMPLOYEE_ID=? WHERE ID_TASK=?";

    private static final String FIND_ALL_TASK = "SELECT * FROM TASK JOIN PROJECT P " +
            "on TASK.PROJECT_ID = P.ID_PROJECT JOIN STATUS_TASK ST " +
            "on TASK.STATUS_TASK = ST.ID_STATUS JOIN EMPLOYEE E on TASK.EMPLOYEE_ID = E.ID_EMPLOYEE";

    private static final String FIND_TASK_BY_ID = "SELECT * FROM TASK JOIN PROJECT P " +
            "on TASK.PROJECT_ID = P.ID_PROJECT JOIN STATUS_TASK ST " +
            "on TASK.STATUS_TASK = ST.ID_STATUS JOIN EMPLOYEE E " +
            "on TASK.EMPLOYEE_ID = E.ID_EMPLOYEE WHERE ID_TASK=?";

    private static final String FIND_ALL_TASK_IN_PROJECT = "SELECT * FROM TASK JOIN PROJECT P " +
            "on TASK.PROJECT_ID = P.ID_PROJECT JOIN STATUS_TASK ST " +
            "on TASK.STATUS_TASK = ST.ID_STATUS JOIN EMPLOYEE E " +
            "on TASK.EMPLOYEE_ID = E.ID_EMPLOYEE WHERE TASK.PROJECT_ID=?";

    private static final Logger logger = Logger.getLogger(TaskDaoImpl.class.getName());

    private final ConnectionDb connectionBd;

    public TaskDaoImpl(ConnectionDb connectionBd) {
        this.connectionBd = connectionBd;
    }

    @Override
    public boolean create(Long idStatus, String name, Long idProject,
                          Long job, String startDate, String finishDate, Long idEmployee) throws DaoException {
        boolean result = false;
        logger.info("DAO: Task creation started.");
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_TASK)) {
            extracted(idStatus, name, idProject, job, startDate,
                    finishDate, idEmployee, statement);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
                logger.info("DAO: Task creation completed successfully.");
            } else {
                logger.info("DAO: Task creation completed unsuccessfully.");
            }
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred");
            logger.warning("DAO: sql: {}" + CREATE_TASK);
            logger.info("DAO: Task creation completed unsuccessfully.");
            throw new DaoException(e);
        }
        return result;
    }

    private void extracted(Long idStatus, String name, Long idProject, Long job,
                           String startDate, String finishDate, Long idEmployee,
                           PreparedStatement statement) throws SQLException {
        statement.setLong(1, idStatus);
        statement.setString(2, name);
        statement.setLong(3, idProject);
        statement.setLong(4, job);
        statement.setDate(5, Date.valueOf(startDate));
        statement.setDate(6, Date.valueOf(finishDate));
        statement.setLong(7, idEmployee);
    }

    @Override
    public boolean updateEntity(Long id, Long idStatus, String name, Long idProject,
                                Long job, String startDate, String finishDate, Long idEmployee) throws DaoException {
        boolean result = false;
        logger.info("DAO: Task update started.");
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TASK)) {
            extracted(idStatus, name, idProject, job, startDate,
                    finishDate, idEmployee, statement);
            statement.setLong(8, id);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
                logger.info("DAO: Task update completed successfully.");
            } else {
                logger.info("DAO: Task update completed unsuccessfully.");
            }
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred");
            logger.warning("DAO: sql: {}" + UPDATE_TASK);
            logger.info("DAO: Task update completed unsuccessfully.");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean removeEntity(Long id) throws DaoException {
        boolean result;
        logger.info("DAO: Task removal started.");
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_TASK)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
            if (result) {
                logger.info("DAO: Task removal completed successfully.");
            } else {
                logger.info("DAO: Task removal completed unsuccessfully.");
            }
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred");
            logger.warning("DAO: sql: {}" + REMOVE_TASK);
            logger.info("DAO: Task removal completed unsuccessfully.");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Task> findAll() throws DaoException {
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_TASK)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Task> extractor = TaskDaoImpl::extractTask;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred" + e);
            logger.warning("DAO: sql: {}" + FIND_ALL_TASK);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            logger.warning("DAO:could not extract entity" + e);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Task> findById(Long id) throws DaoException {
        Optional<Task> productOptional = Optional.empty();
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TASK_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Task task = extractTask(resultSet);
                productOptional = Optional.of(task);
            }
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred" + e);
            logger.warning("DAO: sql: {}" + FIND_TASK_BY_ID);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            logger.warning("DAO: could not extract entity" + e);
        }
        return productOptional;
    }

    @Override
    public List<Task> findAllTaskInProject(Long id) throws DaoException {
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_TASK_IN_PROJECT)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Task> extractor = TaskDaoImpl::extractTask;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred" + e);
            logger.warning("DAO: sql: {}" + FIND_ALL_TASK_IN_PROJECT);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            logger.warning("DAO:could not extract entity" + e);
        }
        return Collections.emptyList();
    }

    private static Task extractTask(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return new Task(
                    resultSet.getLong("ID_TASK"),
                    new StatusTask(
                            resultSet.getLong("ID_STATUS"),
                            resultSet.getString("NAME_STATUS").trim()),
                    resultSet.getString("NAME_TASK").trim(),
                    new Project(resultSet.getLong("ID_PROJECT"),
                            resultSet.getString("NAME_PROJECT"),
                            resultSet.getString("DESCRIPTION").trim()),
                    resultSet.getLong("NAME_JOB"),
                    resultSet.getDate("START_DATE"),
                    resultSet.getDate("FINISH_DATE"),
                    new Employee(resultSet.getLong("ID_EMPLOYEE"),
                            resultSet.getString("FIRST_NAME").trim(),
                            resultSet.getString("LAST_NAME").trim(),
                            resultSet.getString("PATRONYMIC").trim(),
                            resultSet.getString("POSITION").trim()));
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        }
    }

    public static TaskDaoImpl getInstance() {
        return TaskDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final TaskDaoImpl INSTANCE = new TaskDaoImpl(new ConnectionDb());
    }
}
