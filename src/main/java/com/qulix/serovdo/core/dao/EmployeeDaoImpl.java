package com.qulix.serovdo.core.dao;

import com.qulix.serovdo.api.dao.EmployeeDao;
import com.qulix.serovdo.api.entity.Employee;
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

public class  EmployeeDaoImpl implements EmployeeDao {
    private static final String CREATE_EMPLOYEE = "INSERT INTO EMPLOYEE(FIRTS_NAME, LAST_NAME, PATRONYMIC, POSITION) " +
            "values (?,?,?,?)";
    private static final String REMOVE_EMPLOYEE = "DELETE FROM EMPLOYEE WHERE ID_EMPLOYEE = ?";
    private static final String UPDATE_EMPLOYEE = "UPDATE employee SET FIRTS_NAME=?, " +
            "last_name=?, patronymic=?, position=? WHERE ID_EMPLOYEE=?";
    private static final String FIND_ALL_EMPLOYEE = "SELECT * FROM EMPLOYEE";
    private static final String FIND_EMPLOYEE_BY_ID = "SELECT * FROM EMPLOYEE WHERE ID_EMPLOYEE=?";

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    private final ConnectionDb connectionBd;

    public EmployeeDaoImpl(ConnectionDb connectionBd) {
        this.connectionBd = connectionBd;
    }
    @Override
    public boolean create(String firstName, String lastName, String patronymic,
                          String position) throws DaoException {
        boolean result = false;
        logger.info("DAO: Employee creation started.");
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_EMPLOYEE)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, patronymic);
            statement.setString(4, position);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
                logger.info("DAO: Employee creation completed successfully.");
            } else {
                logger.info("DAO: Employee creation completed unsuccessfully.");
            }
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred");
            logger.warning("DAO: sql: {}" + CREATE_EMPLOYEE);
            logger.info("DAO: Employee creation completed unsuccessfully.");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean updateEntity(Long id, String firstName, String lastName,
                                String patronymic, String position) throws DaoException {
        boolean result = false;
        logger.info("DAO: Employee update started.");
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, patronymic);
            statement.setString(4, position);
            statement.setLong(5, id);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
                logger.info("DAO: Employee update completed successfully.");
            } else {
                logger.info("DAO: Employee update completed unsuccessfully.");
            }
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred");
            logger.warning("DAO: sql: {}" + UPDATE_EMPLOYEE);
            logger.info("DAO: Employee update completed unsuccessfully.");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean removeEntity(Long id) throws DaoException {
        boolean result;
        logger.info("DAO: Employee removal started.");
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_EMPLOYEE)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
            if (result) {
                logger.info("DAO: Employee removal completed successfully.");
            } else {
                logger.info("DAO: Employee removal completed unsuccessfully.");
            }
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred");
            logger.warning("DAO: sql: {}" + REMOVE_EMPLOYEE);
            logger.info("DAO: Employee removal completed unsuccessfully.");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Employee> findAll() throws DaoException {
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_EMPLOYEE)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Employee> extractor = EmployeeDaoImpl::extractEmployee;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred" + e);
            logger.warning("DAO: sql: {}" + FIND_ALL_EMPLOYEE);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            logger.warning("could not extract entity" + e);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Employee> findById(Long id) throws DaoException {
        Optional<Employee> productOptional = Optional.empty();
        try (Connection connection = connectionBd.gerConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_EMPLOYEE_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Employee employee = extractEmployee(resultSet);
                productOptional = Optional.of(employee);
            }
        } catch (SQLException e) {
            logger.warning("DAO: sql exception occurred" + e);
            logger.warning("DAO: sql: {}" + FIND_EMPLOYEE_BY_ID);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            logger.warning("DAO: could not extract entity" + e);
        }
        return productOptional;
    }

    private static Employee extractEmployee(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return new Employee(
                    resultSet.getLong("ID_EMPLOYEE"),
                    resultSet.getString("FIRTS_NAME").trim(),
                    resultSet.getString("LAST_NAME").trim(),
                    resultSet.getString("PATRONYMIC").trim(),
                    resultSet.getString("POSITION").trim());
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        }
    }

    public static EmployeeDaoImpl getInstance() {
        return EmployeeDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final EmployeeDaoImpl INSTANCE = new EmployeeDaoImpl(new ConnectionDb());
    }
}
