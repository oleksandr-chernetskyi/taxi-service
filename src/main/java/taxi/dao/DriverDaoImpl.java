package taxi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.exception.DataProcessingException;
import taxi.lib.Dao;
import taxi.model.Driver;
import taxi.util.ConnectionUtil;

@Dao
public class DriverDaoImpl implements DriverDao {
    private static final Logger logger = LogManager.getLogger(DriverDaoImpl.class);

    @Override
    public Driver create(Driver driver) {
        String query = "INSERT INTO drivers (name, license_number, login, password) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNumber());
            statement.setString(3, driver.getLogin());
            statement.setString(4, driver.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                driver.setId(resultSet.getObject(1, Long.class));
            }
            logger.info("Driver {} successfully added to DB", driver);
            return driver;
        } catch (SQLException e) {
            logger.error(String.format("Can't add driver %s to DB", driver), e);
            throw new DataProcessingException("Can't create driver " + driver, e);
        }
    }

    @Override
    public Optional<Driver> get(Long id) {
        String query = "SELECT * FROM drivers WHERE id = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Driver driver = null;
            if (resultSet.next()) {
                driver = parseDriverFromResultSet(resultSet);
            }
            logger.info("Driver by id = {} was successfully retrieved from DB", id);
            return Optional.ofNullable(driver);
        } catch (SQLException e) {
            logger.error(String.format("Can't get driver by id = %s from DB", id), e);
            throw new DataProcessingException("Can't get driver by id " + id, e);
        }
    }

    @Override
    public List<Driver> getAll() {
        String query = "SELECT * FROM drivers WHERE is_deleted = FALSE";
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                drivers.add(parseDriverFromResultSet(resultSet));
            }
            logger.info("All drivers was successfully retrieved from DB");
            return drivers;
        } catch (SQLException e) {
            logger.error("Can't get all drivers from DB", e);
            throw new DataProcessingException("Can't get a list of drivers.", e);
        }
    }

    @Override
    public Driver update(Driver driver) {
        String query = "UPDATE drivers "
                + "SET name = ?, license_number = ?, login = ?, password = ?"
                + "WHERE id = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(query)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNumber());
            statement.setLong(3, driver.getId());
            statement.setString(4, driver.getPassword());
            statement.setString(5, driver.getLogin());
            statement.executeUpdate();
            logger.info("Driver {} updated successfully", driver.getId());
            return driver;
        } catch (SQLException e) {
            logger.error("Can't update driver in DB: {}", driver, e);
            throw new DataProcessingException("Can't update driver" + driver, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE drivers SET is_deleted = TRUE WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            logger.info(String.format("Driver by id = %s was successfully deleted from DB", id));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Can't delete driver by id: {}", id, e);
            throw new DataProcessingException("Can't delete driver with id " + id, e);
        }
    }

    private Driver parseDriverFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        String name = resultSet.getString("name");
        String licenseNumber = resultSet.getString("license_number");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        Driver driver = new Driver();
        driver.setId(id);
        driver.setName(name);
        driver.setLicenseNumber(licenseNumber);
        driver.setLogin(login);
        driver.setPassword(password);
        return driver;
    }

    @Override
    public Optional<Driver> findByLogin(String login) {
        String query =
                "SELECT * FROM drivers WHERE login = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            Driver driver = null;
            if (resultSet.next()) {
                driver = parseDriverFromResultSet(resultSet);
            }
            logger.info("Driver by login = {} found successfully", login);
            return Optional.ofNullable(driver);
        } catch (SQLException e) {
            logger.error(String.format("Can't find driver by login = %s from DB", login), e);
            throw new DataProcessingException("Can't find driver by login " + login, e);
        }
    }
}
