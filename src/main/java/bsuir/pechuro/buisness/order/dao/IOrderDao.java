package bsuir.pechuro.buisness.order.dao;

import bsuir.pechuro.entity.Order;
import bsuir.pechuro.exception.dao.DaoException;

import java.util.List;

public interface IOrderDao {

    boolean addOrder(Order order) throws DaoException;

    Integer getOrderIdByClientId(Integer clientId) throws DaoException;

    boolean editOrder(Integer clientId, Double orderCost, Integer productCount) throws DaoException;

    Order getOrderByClientId(Integer clientId) throws DaoException;

    Integer paymentOrder(Order order) throws DaoException;

    boolean clearOrderCost(Integer orderId) throws DaoException;

    List<Order> getPaymentOrdersByClientId(Integer clientId) throws DaoException;

    List<Order> getAllOrdersByClientId(Integer clientId) throws DaoException;

    List<Order> getAllOrderedOrders() throws DaoException;

    boolean deleteOrder(Integer orderId) throws DaoException;

    Order getOrderByOrderId(Integer orderId) throws DaoException;
}
