package bsuir.pechuro.buisness.admin.dao.impl;

import bsuir.pechuro.buisness.admin.dao.IAdminDao;
import bsuir.pechuro.connectionpool.ConnectionPool;
import bsuir.pechuro.entity.Admin;
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
 * class AdminDAO created for working with administrators
 */
public class AdminDAO implements IAdminDao {
    private static final Logger LOGGER = Logger.getLogger(AdminDAO.class);
    private static String GET_ADMIN_BY_LOGIN_AND_PASSWORD = "SELECT * FROM cafeby.admin WHERE adminLogin=? AND adminPassword=?;";
    private static String ADD_ADMIN = "INSERT INTO admin (adminLogin, adminPassword) VALUES(?,?)";
    private static String DELETE_ADMIN = "DELETE FROM cafeby.admin WHERE adminId=?";
    private static String GET_ALL_ADMINS = "SELECT *FROM cafeby.admin";
    private static String GET_ADMIN_BY_LOGIN = "SELECT * FROM cafeby.admin WHERE admin.adminLogin=?";
    private static String CHECK_ADMIN_PASSWORD = "SELECT * FROM cafeby.admin WHERE cafeby.admin.adminId=? AND cafeby.admin.adminPassword=?";
    private static String CHANGE_PASSWORD = "UPDATE cafeby.admin SET cafeby.admin.adminPassword=? WHERE cafeby.admin.adminId=?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    /**
     * @param admin
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean addAdmin(Admin admin) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: Add admin start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_ADMIN);
            statement.setString(1, admin.getLogin());
            statement.setString(2, admin.getPassword());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Add admin success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Add admin finish");
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
            LOGGER.log(Level.DEBUG, "Admin DAO: Add admin finish");
        }
    }

    /**
     * @param id
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean deleteAdmin(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: Delete admin start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_ADMIN);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Delete admin success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Delete admin finish");
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
            LOGGER.log(Level.DEBUG, "Admin DAO: Delete admin finish");
        }
    }

    /**
     * @param login
     * @param password
     * @return Admin
     * @throws DaoException
     */
    @Override
    public Admin signIn(String login, String password) throws DaoException {
        Admin adminEntity = null;
        LOGGER.log(Level.DEBUG, "Admin DAO: start SignIn");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(GET_ADMIN_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                adminEntity = createAdminByResultSet(resultSet);
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Admin DAO: finish SignIn");
        }
        return adminEntity;
    }

    /**
     * @param login
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean findAdminByLogin(String login) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: start Find");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(GET_ADMIN_BY_LOGIN);
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
            LOGGER.log(Level.DEBUG, "Admin DAO: finish Find");
        }
        return false;
    }

    /**
     * @return List<Admin>
     * @throws DaoException
     */
    @Override
    public List<Admin> getAllAdmins() throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: Start get all admins");
        List<Admin> admins;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ALL_ADMINS);
            resultSet = statement.executeQuery();
            admins = new ArrayList<>();
            while (resultSet.next()) {
                admins.add(createAdminByResultSet(resultSet));
            }
            LOGGER.log(Level.INFO, admins);
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Admin DAO: Finish get all admins");
        }
        return admins;
    }

    /**
     * @param password
     * @param id
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean checkPassword(String password, Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: Check password start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CHECK_ADMIN_PASSWORD);
            statement.setInt(1, id);
            statement.setString(2, password);
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
            LOGGER.log(Level.DEBUG, "Admin DAO: finish check password");
        }
        return false;
    }

    /**
     * @param password
     * @param id
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean changePassword(String password, Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: Change password start");
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
        }
        LOGGER.log(Level.DEBUG, "Admin DAO: finish change password");
        return false;
    }

    /**
     * @param resultSet
     * @return Admin
     * @throws DaoException
     */
    private Admin createAdminByResultSet(ResultSet resultSet) throws DaoException {
        Admin admin = new Admin();
        try {
            admin.setId(resultSet.getInt("adminId"));
            admin.setLogin(resultSet.getString("adminLogin"));
            admin.setPassword(resultSet.getString("adminPassword"));
            admin.setIsMain(resultSet.getInt("isMain"));
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return admin;
    }
}
