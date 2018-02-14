package bsuir.pechuro.connectionpool;

import bsuir.pechuro.exception.dao.ConnectionException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.MissingResourceException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * class ConnectionPool created for working with pool of connections
 */
public class ConnectionPool implements ICloseConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;
    private static Lock instanceLock = new ReentrantLock();
    private static Lock getConnectionLock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean();

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    private int waitingTime;


    /**
     * @throws ConnectionException
     */
    private ConnectionPool() throws ConnectionException {
        String driverName = null;
        try {
            DBResourceManager dbResourceManager = DBResourceManager.getInstance();
            LOGGER.log(Level.INFO, "System found database property file");
            driverName = dbResourceManager.getValue(DBParametr.DB_DRIVER);
            String user = dbResourceManager.getValue(DBParametr.DB_USER);
            String url = dbResourceManager.getValue(DBParametr.DB_URL);
            String password = dbResourceManager.getValue(DBParametr.DB_PASSWORD);
            int poolSize;
            try {
                poolSize = Integer.parseInt(dbResourceManager.getValue((DBParametr.DB_POOL_SIZE)));
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARN, "No correct value in database property file");
                poolSize = 5;
            }
            this.waitingTime = Integer.parseInt(dbResourceManager.getValue((DBParametr.DB_WAITING_TIME)));
            Class.forName(driverName);
            LOGGER.log(Level.INFO, "Database driver was loaded");
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionQueue.add(connection);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.FATAL, "Driver load exception: " + driverName, e);
            throw new ConnectionException("Driver load exception: " + driverName, e);
        } catch (MissingResourceException e) {
            LOGGER.log(Level.FATAL, "Error of upload config: " + e);
            throw new ConnectionException("Error of upload config: " + e);
        } catch (SQLException e) {
            throw new ConnectionException("Error of upload config: " + e);
        }
    }

    /**
     * @return ConnectionPool
     * @throws ConnectionException
     */
    public static ConnectionPool getInstance() throws ConnectionException {//Высокая производительность
        if (!instanceCreated.get()) {
            try {
                instanceLock.lock();
                if (instance == null && !instanceCreated.get()) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            }  finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    /**
     * @return Connection
     */
    public Connection getConnection() {
        Connection connection;
        try {
            if (getConnectionLock.tryLock(waitingTime, TimeUnit.SECONDS)) {
                connection = connectionQueue.poll();
                givenAwayConQueue.add(connection);
                return connection;
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Getting connection error");
        } finally {
            getConnectionLock.unlock();
        }
        return null;
    }

    /**
     * @param connection
     * @throws NullPointerException
     */
    private void putBack(Connection connection) throws NullPointerException {
        if (connection != null) {
            givenAwayConQueue.remove(connection);
            connectionQueue.add(connection);
        } else {
            throw new NullPointerException("Connection is null");
        }
    }

    /**
     * @param con
     * @param st
     * @param resultSet
     */
    public void putBackConnection(Connection con, Statement st, ResultSet resultSet) {
        try {
            this.putBack(con);
        } catch (NullPointerException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }
    }

    @Override
    public void releasePool() {
        while (!givenAwayConQueue.isEmpty()) {
            try {
                Connection connection = givenAwayConQueue.take();
                connection.close();
            } catch (InterruptedException | SQLException e) {
                LOGGER.log(Level.ERROR, "Can't close connection" + e);
            }
        }
        while (!connectionQueue.isEmpty()) {
            try {
                Connection connection = connectionQueue.take();
                connection.close();
            } catch (InterruptedException | SQLException e) {
                LOGGER.log(Level.ERROR, "Can't close connection" + e);
            }
        }
    }
}
