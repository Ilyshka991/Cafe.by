package bsuir.pechuro.buisness.client.dao.impl;

import bsuir.pechuro.buisness.client.dao.IClientDao;
import bsuir.pechuro.connectionpool.ConnectionPool;
import bsuir.pechuro.entity.Client;
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
 * class ClientDAO created for working with clients
 */
public class ClientDAO implements IClientDao {
    private final static String SET_POINTS = "UPDATE cafeby.client SET clientPoint=? WHERE clientId=?";
    private final static String GET_POINTS = "SELECT * FROM cafeby.client WHERE clientId=?";
    private final static String CLEAR_POINTS = "UPDATE cafeby.client SET clientPoint=NULL WHERE clientId=?";
    private static String GET_CLIENT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM cafeby.client WHERE clientLogin=? AND clientPassword=?;";
    private static String GET_CLIENT_BY_LOGIN = "SELECT * FROM cafeby.client WHERE clientLogin=?";
    private static String GET_CLIENT_BY_ID = "SELECT * FROM cafeby.client WHERE clientId=?";
    private static String ADD_CLIENT = "INSERT INTO client (clientName,clientSurname,clientLogin,clientPassword,clientEmail," +
            "clientStatus,clientPoint) VALUES(?,?,?,?,?,?,?);";
    private static String DELETE_CLIENT = "DELETE FROM cafeby.client WHERE clientId=?";
    private static String GET_ALL_CLIENTS = "SELECT * FROM cafeby.client";
    private static String CHANGE_STATUS = " UPDATE cafeby.client SET cafeby.client.clientStatus=? WHERE cafeby.client.clientId=?";
    private static String CHECK_PASSWORD = "SELECT * FROM cafeby.client WHERE cafeby.client.clientId=? AND cafeby.client.clientPassword=?";
    private static String CHANGE_PASSWORD = "UPDATE cafeby.client SET cafeby.client.clientPassword=? WHERE cafeby.client.clientId=?";
    private static String EDIT_POINT = "UPDATE cafeby.client SET cafeby.client.clientPoint=(cafeby.client.clientPoint-?) WHERE cafeby.client.clientId=?";
    private static String GET_CLIENT_BY_EMAIL = "SELECT * FROM cafeby.client WHERE client.clientEmail=?";
    private static final Logger LOGGER = Logger.getLogger(ClientDAO.class);
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    /**
     * @param points
     * @param clientId
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean addPoints(Double points, Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "ClientDao: start addPoints");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_POINTS);
            statement.setInt(1, clientId);
            resultSet = statement.executeQuery();
            Double existingPoints;
            if (resultSet.next()) {
                existingPoints = resultSet.getDouble("clientPoint");
            } else {
                return false;
            }
            statement = connection.prepareStatement(SET_POINTS);
            statement.setDouble(1, existingPoints + points);
            statement.setInt(2, clientId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "ClientDao: success addPoints");
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
        LOGGER.log(Level.DEBUG, "ClientDao: finish addPoints");
        return false;
    }

    /**
     * @param clientId
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean clearPoints(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "ClientDao: start clearPoints");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CLEAR_POINTS);
            statement.setInt(1, clientId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "ClientDao: success clearPoints");
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
        LOGGER.log(Level.DEBUG, "ClientDao: finish clearPoints");
        return false;
    }

    /**
     * @param email
     * @return Client
     * @throws DaoException
     */
    @Override
    public Client getClientByEmail(String email) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start Find");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(GET_CLIENT_BY_EMAIL);
            statement.setString(1, email);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createClientByResultSet(resultSet);
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Client DAO: finish Find");
        }
        return null;
    }

    /**
     * @param client
     * @return Client
     * @throws DaoException
     */
    @Override
    public Client addClient(Client client) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start add");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_CLIENT);
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getLogin());
            statement.setString(4, client.getPassword());
            statement.setString(5, client.getEmail());
            statement.setString(6, client.getStatus());
            statement.setDouble(7, client.getPoint());
            if (statement.executeUpdate() != 0) {
                return getClientByLogin(client.getLogin());
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "Client DAO: finish add");
        return null;
    }

    /**
     * @param id
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean deleteClient(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: Delete client start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_CLIENT);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Delete client success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Delete client finish");
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
            LOGGER.log(Level.DEBUG, "Client DAO: Delete client finish");
        }
    }

    /**
     * @param login
     * @param password
     * @return Client
     * @throws DaoException
     */
    @Override
    public Client signIn(String login, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start SignIn");
        Client clientEntity = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_CLIENT_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                clientEntity = createClientByResultSet(resultSet);
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "Client DAO: finish SignIn");
        return clientEntity;
    }

    /**
     * @param clientLogin
     * @return Client
     * @throws DaoException
     */
    @Override
    public Client getClientByLogin(String clientLogin) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start get Client by login");
        Client clientEntity = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_CLIENT_BY_LOGIN);
            statement.setString(1, clientLogin);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                clientEntity = createClientByResultSet(resultSet);
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Client DAO: finish get Client by login");
        }
        return clientEntity;
    }

    /**
     * @param clientId
     * @return Client
     * @throws DaoException
     */
    @Override
    public Client getClientById(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start get Client by id");
        Client clientEntity = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_CLIENT_BY_ID);
            statement.setInt(1, clientId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                clientEntity = createClientByResultSet(resultSet);
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Client DAO: finish get Client by id");
        }
        return clientEntity;
    }

    /**
     * @return List<Client>
     * @throws DaoException
     */
    @Override
    public List<Client> getAllClients() throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: Start get all clients");
        List<Client> clients;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ALL_CLIENTS);
            resultSet = statement.executeQuery();
            Client clientEntity;
            clients = new ArrayList<>();
            if (!resultSet.next()) {
                return null;
            }
            do {
                clientEntity = createClientByResultSet(resultSet);
                clients.add(clientEntity);
            } while (resultSet.next());
            LOGGER.log(Level.INFO, clients);
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "Client DAO: Finish get all clients");
        return clients;
    }

    /**
     * @param clientId
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean changeClientStatus(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: Change status start");
        try {
            boolean status = false;
            if (getClientById(clientId).getStatus().equals("banned")) {
                status = true;
            }
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CHANGE_STATUS);
            statement.setInt(2, clientId);
            if (status) {
                statement.setString(1, "active");
            } else {
                statement.setString(1, "banned");
            }
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Change status success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Change status finish");
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
            LOGGER.log(Level.DEBUG, "Client DAO: Change status finish");
        }
    }

    /**
     * @param password
     * @param id
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean checkPassword(String password, Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: Check password start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CHECK_PASSWORD);
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
            LOGGER.log(Level.DEBUG, "Client DAO: finish check password");
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
        LOGGER.log(Level.DEBUG, "Client DAO: Change password start");
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
            LOGGER.log(Level.DEBUG, "Client DAO: finish change password");
        }
        return false;
    }

    /**
     * @param clientId
     * @param clientPoint
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean editPoint(Integer clientId, Double clientPoint) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: edit points start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(EDIT_POINT);
            statement.setDouble(1, clientPoint);
            statement.setInt(2, clientId);
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
            LOGGER.log(Level.DEBUG, "Client DAO: finish edit points");
        }
        return false;
    }

    /**
     * @param resultSet
     * @return Client
     * @throws DaoException
     */
    private Client createClientByResultSet(ResultSet resultSet) throws DaoException {
        Client client = new Client();
        try {
            client.setId(resultSet.getInt("clientId"));
            client.setName(resultSet.getString("clientName"));
            client.setSurname(resultSet.getString("clientSurname"));
            client.setLogin(resultSet.getString("clientLogin"));
            client.setPassword(resultSet.getString("clientPassword"));
            client.setEmail(resultSet.getString("clientEmail"));
            client.setStatus(resultSet.getString("clientStatus"));
            client.setPoint(resultSet.getDouble("clientPoint"));
            return client;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
