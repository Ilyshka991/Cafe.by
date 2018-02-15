package bsuir.pechuro.buisness.account.dao.impl;

import bsuir.pechuro.buisness.account.dao.IAccountDao;
import bsuir.pechuro.connectionpool.ConnectionPool;
import bsuir.pechuro.entity.Account;
import bsuir.pechuro.exception.dao.ConnectionException;
import bsuir.pechuro.exception.dao.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * class AccountDAO created for working with clients' accounts
 */
public class AccountDAO implements IAccountDao {

    private static final Logger LOGGER = Logger.getLogger(AccountDAO.class);
    public static String ADD_ACCOUNT = "INSERT INTO cafeby.account " +
            "(accountNumber,accountCredit,clientId) VALUES(?,?,?)";
    public static String CHECK_ACCOUNT_NUMBER = "SELECT * FROM cafeby.account WHERE accountNumber = ?";
    public static String EDIT_ACCOUNT = "UPDATE cafeby.account SET cafeby.account.accountCredit = " +
            "(cafeby.account.accountCredit - ?) WHERE cafeby.account.clientId = ?";
    public static String FIND_ACCOUNT = "SELECT * FROM cafeby.account WHERE cafeby.account.clientId=?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    /**
     * @param clientId
     * @return Double
     * @throws DaoException
     */
    @Override
    public Double getCashById(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: get Cash start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(FIND_ACCOUNT);
            statement.setInt(1, clientId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("accountCredit");
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Account DAO: get Cash finish");
        }
        return null;
    }


    /**
     * @param account
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean addAccount(Account account) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: Add account start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_ACCOUNT);
            statement.setInt(1, account.getAccountNumber());
            statement.setDouble(2, account.getAccountCredit());
            statement.setInt(3, account.getClientId());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Add account success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Add account finish");
                return false;
            }
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Account DAO: Add account finish");
        }
    }

    /**
     * @param accountNumber
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean checkAccountNumber(Integer accountNumber) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: check account number start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CHECK_ACCOUNT_NUMBER);
            statement.setInt(1, accountNumber);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                LOGGER.log(Level.DEBUG, "check account number success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "check account number finish");
                return false;
            }
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }

            LOGGER.log(Level.DEBUG, "Account DAO: check account number finish");
        }
    }

    /**
     * @param clientId
     * @param orderCostNew
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean editAccount(Integer clientId, Double orderCostNew) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: edit start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(EDIT_ACCOUNT);
            statement.setDouble(1, orderCostNew);
            statement.setInt(2, clientId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "edit account success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "edit account finish");
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
            LOGGER.log(Level.DEBUG, "Account DAO: edit finish");
        }
    }

    /**
     * @param clientId
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean findAccountByClientId(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: find start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_ACCOUNT);
            statement.setInt(1, clientId);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Account DAO: find finish");
        }
    }
}