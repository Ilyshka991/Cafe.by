package bsuir.pechuro.buisness.order.service;

import bsuir.pechuro.entity.Order;
import bsuir.pechuro.exception.service.ServiceException;

import java.util.List;

public interface IOrderService {

    boolean addOrder(String orderType, Double orderCost, Integer clientId) throws ServiceException;

    boolean editOrder(Integer clientId, Double orderCost, Integer productCount) throws ServiceException;

    Double getOrderCost(Integer clientId) throws ServiceException;

    Integer paymentOrder(String orderType, String orderData, Double orderCost, Integer clientId) throws ServiceException;

    boolean clearOrderCost(Integer orderId) throws ServiceException;

    List<Order> getPaymentOrdersByClientId(Integer clientId) throws ServiceException;

    Integer getOrderIdByClientId(Integer clientId) throws ServiceException;

    List<Order> getAllOrdersByClientId(Integer clientId) throws ServiceException;

    List<Order> getAllOrderedOrders() throws ServiceException;

    boolean deleteOrder(Integer orderId) throws ServiceException;

    Order getOrderByOrderId(Integer orderId) throws ServiceException;
}
