package bsuir.pechuro.buisness.orderproduct.dao.impl;

import bsuir.pechuro.buisness.order.dao.impl.OrderDAO;
import bsuir.pechuro.buisness.orderproduct.dao.IOrderProductDao;
import bsuir.pechuro.connectionpool.ConnectionPool;
import bsuir.pechuro.entity.OrderProduct;
import bsuir.pechuro.exception.dao.ConnectionException;
import bsuir.pechuro.exception.dao.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderProductDAO implements IOrderProductDao {

    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);
    private static String ADD_ORDER_PRODUCT = "INSERT INTO cafeby.orderproducts (orderId, productId, productCount) VALUES(?,?,?) ";
    private static String DELETE_ORDER_PRODUCT = "DELETE FROM cafeby.orderproducts WHERE (orderproducts.orderId, orderproducts.productId)=(?,?)";
    private static String EDIT_ORDER_PRODUCT = "UPDATE cafeby.orderproducts SET cafeby.orderproducts.productCount = (cafeby.orderproducts.productCount + ?) " +
            "WHERE cafeby.orderproducts.productId = ? AND cafeby.orderproducts.orderId=?";
    private static String FIND_ORDER_PRODUCT = "SELECT * FROM cafeby.orderproducts WHERE cafeby.orderproducts.productId=? AND cafeby.orderproducts.orderId=?";
    private static String FIND_ORDER_PRODUCT_COUNT = "SELECT cafeby.orderproducts.productCount FROM cafeby.orderproducts WHERE cafeby.orderproducts.productId=?";
    private static String EDIT_ORDER_PRODUCT_PAYMENT = "UPDATE cafeby.orderproducts SET cafeby.orderproducts.orderId=? WHERE cafeby.orderproducts.orderId=?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;


    @Override
    public boolean addOrderProduct(OrderProduct orderProduct) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: Add order start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_ORDER_PRODUCT);
            statement.setInt(1, orderProduct.getOrderId());
            statement.setInt(2, orderProduct.getProductId());
            statement.setInt(3, orderProduct.getProductCount());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Add order product success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Add order product finish");
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
            LOGGER.log(Level.DEBUG, "OrderProduct DAO: Add order product finish");
        }
    }


    @Override
    public boolean deleteOrderProduct(Integer orderId, Integer productId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order Product DAO: Delete orderProduct start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_ORDER_PRODUCT);
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Delete orderProduct success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Delete orderProduct finish");
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
            LOGGER.log(Level.DEBUG, "Order Product DAO: Delete orderProduct finish");
        }
    }


    @Override
    public boolean editOrderProduct(Integer productId, Integer productCount, Integer orderId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order product DAO: edit start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(EDIT_ORDER_PRODUCT);
            statement.setInt(1, productCount);
            statement.setInt(2, productId);
            statement.setInt(3, orderId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "edit order product success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "edit order product finish");
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
            LOGGER.log(Level.DEBUG, "Order product DAO: edit product finish");
        }
    }


    @Override
    public boolean findOrderProduct(Integer productId, Integer orderId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order product DAO: find start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_ORDER_PRODUCT);
            statement.setInt(1, productId);
            statement.setInt(2, orderId);
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
            LOGGER.log(Level.DEBUG, "Order product DAO: find product finish");
        }

    }

    @Override
    public Integer orderProductCount(Integer productId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order product DAO: find count start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_ORDER_PRODUCT_COUNT);
            statement.setInt(1, productId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("productCount");
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Order product DAO: find count finish");
        }
        return null;
    }


    @Override
    public boolean editOrderProductPayment(Integer orderIdNew, Integer orderId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order Product DAO: edit order product payment start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(EDIT_ORDER_PRODUCT_PAYMENT);
            statement.setInt(1, orderIdNew);
            statement.setInt(2, orderId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "edit order product payment success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "edit order product payment finish");
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
            LOGGER.log(Level.DEBUG, "Order Product DAO: edit order product payment finish");
        }
    }
}