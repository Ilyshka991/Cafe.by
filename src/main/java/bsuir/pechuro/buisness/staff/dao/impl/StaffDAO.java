package bsuir.pechuro.buisness.staff.dao.impl;

import bsuir.pechuro.buisness.staff.dao.IStaffDao;
import bsuir.pechuro.connectionpool.ConnectionPool;
import bsuir.pechuro.entity.Staff;
import bsuir.pechuro.exception.dao.ConnectionException;
import bsuir.pechuro.exception.dao.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * class StaffDAO created for working with staff
 */
public class StaffDAO implements IStaffDao {
    private static final Logger LOGGER = Logger.getLogger(StaffDAO.class);
    private static String ADD_STAFF = "INSERT INTO cafeby.staff (staffLogin, staffPassword) VALUES(?,?)";
    private static String DELETE_STAFF = "DELETE FROM cafeby.staff WHERE staffId=?";
    private static String GET_STAFF_BY_LOGIN_AND_PASSWORD = "SELECT * FROM cafeby.staff WHERE staffLogin=? AND staffPassword=?";
    private static String GET_STAFF_BY_LOGIN = "SELECT * FROM cafeby.staff WHERE staffLogin=?";
    private static String GET_ALL_STAFF = "SELECT * FROM cafeby.staff";
    private static String CHECK_PASSWORD = "SELECT * FROM cafeby.staff WHERE cafeby.staff.staffId=? AND cafeby.staff.staffPassword=?";
    private static String CHANGE_PASSWORD = "UPDATE cafeby.staff SET cafeby.staff.staffPassword=? WHERE cafeby.staff.staffId=?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    /**
     * @param staff
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean addStaff(Staff staff) throws DaoException {
        LOGGER.log(Level.DEBUG, "Staff DAO: Add staff start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_STAFF);
            statement.setString(1, staff.getLogin());
            statement.setString(2, staff.getPassword());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Add staff success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Add staff finish");
                return false;
            }
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Staff DAO: Add staff finish");
        }
    }

    /**
     * @param id
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean deleteStaff(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Staff DAO: Delete staff start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_STAFF);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Delete staff success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Delete staff finish");
                return false;
            }
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Staff DAO: Delete staff finish");
        }
    }

    /**
     * @param login
     * @param password
     * @return Staff
     * @throws DaoException
     */
    @Override
    public Staff signIn(String login, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Staff DAO: start signIn");
        Staff staff = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_STAFF_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                staff = createStaffByResultSet(resultSet);
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Staff DAO: finish SignIn");
        }
        return staff;
    }

    /**
     * @param login
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean findStaffByLogin(String login) throws DaoException {
        LOGGER.log(Level.DEBUG, "Staff DAO: start Find");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_STAFF_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Staff DAO: finish Find");
        }
        return false;
    }

    /**
     * @return List<Staff>
     * @throws DaoException
     */
    @Override
    public List<Staff> getAllStaff() throws DaoException {
        LOGGER.log(Level.DEBUG, "Staff DAO: Start get all staff");
        List<Staff> staff = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ALL_STAFF);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                staff.add(createStaffByResultSet(resultSet));
            }
            LOGGER.log(Level.INFO, staff);
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "Staff DAO: Finish get all staff");
        return staff;
    }

    /**
     * @param password
     * @param id
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean checkPassword(String password, Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Staff DAO: Check password start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CHECK_PASSWORD);
            statement.setInt(1, id);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            LOGGER.log(Level.DEBUG, "Staff DAO: finish check password");
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Staff DAO: Check password finish");
        }
    }

    /**
     * @param password
     * @param id
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean changePassword(String password, Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Staff DAO: Change password start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CHANGE_PASSWORD);
            statement.setString(1, password);
            statement.setInt(2, id);
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Staff DAO: finish change password");
        }
        return false;
    }

    /**
     * @param resultSet
     * @return Staff
     * @throws DaoException
     */
    private Staff createStaffByResultSet(ResultSet resultSet) throws DaoException {
        Staff staff = new Staff();
        try {
            staff.setId(resultSet.getInt("staffId"));
            staff.setLogin(resultSet.getString("staffLogin"));
            staff.setPassword(resultSet.getString("staffPassword"));
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return staff;
    }
}