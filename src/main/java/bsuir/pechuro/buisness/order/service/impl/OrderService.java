package bsuir.pechuro.buisness.order.service.impl;

import bsuir.pechuro.buisness.order.service.IOrderService;
import bsuir.pechuro.entity.Order;
import bsuir.pechuro.exception.dao.DaoException;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.exception.validation.ValidatorException;
import bsuir.pechuro.factory.dao.DaoFactory;
import bsuir.pechuro.utils.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;


public class OrderService implements IOrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public boolean addOrder(String orderType, Double orderCost, Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: start addOrder");
        try {
            Validator.isNull(orderType);
            Validator.isEmptyString(orderType);
            LOGGER.log(Level.DEBUG, "Order service: finish addOrder");
            Order order = new Order(orderType, orderCost, clientId);
            return daoFactory.getOrderDao().addOrder(order);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    @Override
    public boolean editOrder(Integer clientId, Double orderCost, Integer productCount) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: start edit order");
        try {
            LOGGER.log(Level.DEBUG, "Order service: finish edit order");
            return daoFactory.getOrderDao().editOrder(clientId, orderCost, productCount);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    @Override
    public Double getOrderCost(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: get Order cost start");
        try {
            LOGGER.log(Level.DEBUG, "Order service: get Order cost finish");
            return daoFactory.getOrderDao().getOrderByClientId(clientId).getCost();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    @Override
    public Integer paymentOrder(String orderType, String orderData, Double orderCost, Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: start payment order");
        try {
            Validator.isEmptyString(orderType, orderData);
            Validator.isNull(orderData, orderType);
            Order order = new Order(orderType, orderData, orderCost, clientId);
            LOGGER.log(Level.DEBUG, "Order service: finish payment order");
            return daoFactory.getOrderDao().paymentOrder(order);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    @Override
    public boolean clearOrderCost(Integer orderId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: start clear order");
        try {
            LOGGER.log(Level.DEBUG, "Order service: finish clear order");
            return daoFactory.getOrderDao().clearOrderCost(orderId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    @Override
    public List<Order> getPaymentOrdersByClientId(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start get orders by clientId");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: Finish get orders by clientId");
            return daoFactory.getOrderDao().getPaymentOrdersByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    @Override
    public Integer getOrderIdByClientId(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start get order id");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: finish get order id");
            return daoFactory.getOrderDao().getOrderIdByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    @Override
    public List<Order> getAllOrdersByClientId(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start get all orders by clientId");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: Finish get all orders by clientId");
            return daoFactory.getOrderDao().getAllOrdersByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    @Override
    public List<Order> getAllOrderedOrders() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start get all ordered orders");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: Success get all orders by clientId");
            return daoFactory.getOrderDao().getAllOrderedOrders();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    @Override
    public boolean deleteOrder(Integer orderId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start delete order");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: finish get all orders by clientId");
            return daoFactory.getOrderDao().deleteOrder(orderId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    @Override
    public Order getOrderByOrderId(Integer orderId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start getOrderByOrderId");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: Success getOrderByOrderId");
            return daoFactory.getOrderDao().getOrderByOrderId(orderId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }
}

