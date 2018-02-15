package bsuir.pechuro.buisness.order.dao.impl;

import bsuir.pechuro.buisness.order.dao.IOrderDao;
import bsuir.pechuro.connectionpool.ConnectionPool;
import bsuir.pechuro.entity.Order;
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


public class OrderDAO implements IOrderDao {

    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);
    private static final String GET_ALL_ORDERED_ORDERS = "SELECT * FROM cafeby.order WHERE orderType='ordered' OR orderType='payment'";
    private static final String DELETE_ORDER = "DELETE FROM cafeby.order WHERE orderId=?";
    private static final String GET_ORDER_BY_ORDERID = "SELECT * FROM cafeby.order WHERE orderId=?";
    private static String ADD_ORDER = "INSERT INTO cafeby.order (orderType, orderCost,clientId) VALUES(?,?,?)";
    private static String PAYMENT_ORDER = "INSERT INTO cafeby.order (orderType,orderDate, orderCost,clientId) VALUES(?,?,?,?)";
    private static String FIND_LAST = "SELECT * FROM cafeby.order WHERE orderId=LAST_INSERT_ID()";
    private static String GET_ORDERID_BY_CLIENTID = "SELECT cafeby.order.orderId " +
            "FROM(client join cafeby.order ON client.clientId = cafeby.order.clientId)" +
            " WHERE client.clientId = ?;";
    private static String GET_ORDER_BY_CLIENTID = "SELECT * FROM(client join cafeby.order ON client.clientId = cafeby.order.clientId)" +
            " WHERE client.clientId = ?;";
    private static String EDIT_ORDER = "UPDATE cafeby.order SET cafeby.order.orderCost = (cafeby.order.orderCost + ?) " +
            "WHERE cafeby.order.orderId = ?";
    private static String CLEAR_ORDER = "UPDATE cafeby.order SET cafeby.order.orderCost=0.0 WHERE cafeby.order.orderId=?";
    private static String GET_ORDERS_BY_CLIENTID = " SELECT * FROM cafeby.order WHERE (cafeby.order.orderType = 'ordered' OR cafeby.order.orderType='payment') AND cafeby.order.clientId=?";
    private static String GET_ALL_ORDERS_BY_CLIENTID = "SELECT * FROM cafeby.order WHERE cafeby.order.clientId = ?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;


    @Override
    public boolean addOrder(Order order) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: Add order start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_ORDER);
            statement.setString(1, order.getType());
            statement.setDouble(2, order.getCost());
            statement.setInt(3, order.getClientId());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Add order success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Add order finish");
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
            LOGGER.log(Level.DEBUG, "Order DAO: Add order finish");
        }
    }


    @Override
    public Integer getOrderIdByClientId(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start get orderId by clientId");
        Integer orderId = -1;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ORDERID_BY_CLIENTID);
            statement.setInt(1, clientId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orderId = resultSet.getInt("orderId");
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
        LOGGER.log(Level.DEBUG, "Order DAO: Finish get orderId by clientId");
        return orderId;
    }


    @Override
    public boolean editOrder(Integer clientId, Double orderCost, Integer productCount) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: edit start");
        Integer orderId = getOrderIdByClientId(clientId);
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(EDIT_ORDER);
            statement.setDouble(1, (orderCost * productCount));
            statement.setInt(2, orderId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "edit order success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "edit order finish");
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
            LOGGER.log(Level.DEBUG, "Order DAO: edit finish");
        }
    }


    @Override
    public Order getOrderByClientId(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start get order by clientId");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ORDER_BY_CLIENTID);
            statement.setInt(1, clientId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createOrderByResultSet(resultSet);
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
        LOGGER.log(Level.DEBUG, "Order DAO: Finish get order by clientId");
        return null;
    }


    @Override
    public Integer paymentOrder(Order order) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: Payment order start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(PAYMENT_ORDER);
            statement.setString(1, order.getType());
            statement.setString(2, order.getDate());
            statement.setDouble(3, order.getCost());
            statement.setInt(4, order.getClientId());
            if (statement.executeUpdate() != 0) {
                statement = connection.prepareStatement(FIND_LAST);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("orderId");
                }
                LOGGER.log(Level.DEBUG, "Payment order success");
            } else {
                LOGGER.log(Level.DEBUG, "Payment order finish");
                return null;
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Product DAO: Payment order finish");
        }
        return null;
    }


    @Override
    public boolean clearOrderCost(Integer orderId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: clear order start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CLEAR_ORDER);
            statement.setInt(1, orderId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "clear order success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "clear order unsuccess");
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
            LOGGER.log(Level.DEBUG, "Order DAO: clear order finish");
        }
    }


    @Override
    public List<Order> getPaymentOrdersByClientId(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "order DAO: Start get orders by clientId");
        List<Order> orders;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ORDERS_BY_CLIENTID);
            statement.setInt(1, clientId);
            resultSet = statement.executeQuery();
            orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(createOrderByResultSet(resultSet));
            }
            LOGGER.log(Level.INFO, orders);
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "order DAO: finish get orders by clientId");
        return orders;
    }


    @Override
    public List<Order> getAllOrdersByClientId(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "order DAO: Start get all orders by clientId");
        List<Order> orders;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ALL_ORDERS_BY_CLIENTID);
            statement.setInt(1, clientId);
            resultSet = statement.executeQuery();
            orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(createOrderByResultSet(resultSet));
            }
            LOGGER.log(Level.INFO, orders);
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "order DAO: finish get all orders by clientId");
        return orders;
    }


    @Override
    public List<Order> getAllOrderedOrders() throws DaoException {
        LOGGER.log(Level.DEBUG, "order DAO: Start get all ordered orders");
        List<Order> orders = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ALL_ORDERED_ORDERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(createOrderByResultSet(resultSet));
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
        return orders;
    }


    @Override
    public boolean deleteOrder(Integer orderId) throws DaoException {
        LOGGER.log(Level.DEBUG, "order DAO: Start delete order");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_ORDER);
            statement.setInt(1, orderId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "order DAO: success delete order");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "order DAO: finish delete order");
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
        }
    }


    @Override
    public Order getOrderByOrderId(Integer orderId) throws DaoException {
        LOGGER.log(Level.DEBUG, "order DAO: Start getOrderByOrderId");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ORDER_BY_ORDERID);
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                LOGGER.log(Level.DEBUG, "order DAO: success getOrderByOrderId");
                return createOrderByResultSet(resultSet);
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
        LOGGER.log(Level.DEBUG, "order DAO: finish getOrderByOrderId");
        return null;
    }


    private Order createOrderByResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setClientId(resultSet.getInt("clientId"));
        order.setCost(resultSet.getDouble("orderCost"));
        order.setDate(resultSet.getString("orderDate"));
        order.setId(resultSet.getInt("orderId"));
        order.setType(resultSet.getString("orderType"));
        return order;
    }
}



