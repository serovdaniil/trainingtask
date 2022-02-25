package com.qulix.serovdo.core.dao;

import com.qulix.serovdo.api.dao.ProjectDao;
import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.api.entity.Task;
import com.qulix.serovdo.core.connection.ConnectionDb;
import com.qulix.serovdo.core.exception.DaoException;
import com.qulix.serovdo.core.exception.EntityExtractionFailedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ProjectDaoImpl implements ProjectDao {
    private static final String CREATE_PROJECT = "INSERT INTO PROJECT(NAME_PROJECT, DESCRIPTION) " +
            "values(?,?)";
    private static final String REMOVE_PROJECT = "DELETE FROM PROJECT WHERE ID_PROJECT=?";
    private static final String UPDATE_PROJECT = "UPDATE PROJECT SET NAME_PROJECT=?, " +
            "DESCRIPTION=? WHERE ID_PROJECT=?";
    private static final String FIND_PROJECT_BY_ID = "SELECT * FROM PROJECT WHERE ID_PROJECT=?";
    private static final String FIND_ALL_PROJECT = "SELECT * FROM PROJECT";
    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    private final ConnectionDb connectionBd;

    public ProjectDaoImpl(ConnectionDb connectionBd) {
        this.connectionBd = connectionBd;
    }
    @Override
    public boolean create(String name, String description) throws DaoException {
        boolean result = false;
        logger.info("DAO: Project creation started.");
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_PROJECT)) {
            statement.setString(1, name);
            statement.setString(2, description);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
                logger.info("DAO: Project creation completed successfully.");
            } else {
                logger.info("DAO: Project creation completed unsuccessfully.");
            }
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred");
            logger.warning("DAO: sql: {}" + CREATE_PROJECT);
            logger.info("DAO: Project creation completed unsuccessfully.");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean updateEntity(Long id, String name, String description) throws DaoException {
        boolean result = false;
        logger.info("DAO: Project update started.");
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PROJECT)) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setLong(3, id);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
                logger.info("DAO: Project update completed successfully.");
            } else {
                logger.info("DAO: Project update completed unsuccessfully.");
            }
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred");
            logger.warning("DAO: sql: {}" + UPDATE_PROJECT);
            logger.info("DAO: Project update completed unsuccessfully.");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean removeEntity(Long id) throws DaoException {
        boolean result;
        logger.info("DAO: Project removal started.");
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_PROJECT)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
            if (result) {
                logger.info("DAO: Project removal completed successfully.");
            } else {
                logger.info("DAO: Project removal completed unsuccessfully.");
            }
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred");
            logger.warning("DAO: sql: {}" + REMOVE_PROJECT);
            logger.info("DAO: Project removal completed unsuccessfully.");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Project> findAll() throws DaoException {
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PROJECT)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Project> extractor = ProjectDaoImpl::extractProject;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred" + e);
            logger.warning("DAO: sql: {}" + FIND_ALL_PROJECT);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            logger.warning("DAO: could not extract entity" + e);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Project> findById(Long id) throws DaoException {
        Optional<Project> productOptional = Optional.empty();
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PROJECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Project project = extractProject(resultSet);
                productOptional = Optional.of(project);
            }
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred" + e);
            logger.warning("DAO: sql: {}" + FIND_PROJECT_BY_ID);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            logger.warning("DAO: could not extract entity" + e);
        }
        return productOptional;
    }

    private static Project extractProject(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return new Project(
                    resultSet.getLong("id_project"),
                    resultSet.getString("name_project").trim(),
                    resultSet.getString("description").trim());
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        }
    }

    public static ProjectDaoImpl getInstance() {
        return ProjectDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ProjectDaoImpl INSTANCE = new ProjectDaoImpl(new ConnectionDb());
    }
}
