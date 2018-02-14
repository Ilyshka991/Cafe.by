package bsuir.pechuro.buisness.orderproduct.service.impl;

import bsuir.pechuro.buisness.order.service.impl.OrderService;
import bsuir.pechuro.buisness.orderproduct.service.IOrderProductService;
import bsuir.pechuro.entity.OrderProduct;
import bsuir.pechuro.exception.dao.DaoException;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.dao.DaoFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * class OrderProductService created for preparation data before sending queries to database table "orderproduct"
 */
public class OrderProductService implements IOrderProductService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * @param clientId
     * @param productId
     * @param productCount
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean addOrderProduct(Integer clientId, Integer productId, Integer productCount) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Product service: start addOrderProducts");
        try {
            Integer orderId = daoFactory.getOrderDao().getOrderIdByClientId(clientId);
            OrderProduct orderProduct = new OrderProduct(orderId, productId, productCount);
            LOGGER.log(Level.DEBUG, "Order Product service: finish addOrderProducts");
            return daoFactory.getOrderProductDao().addOrderProduct(orderProduct);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param clientId
     * @param productId
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean deleteOrderProduct(Integer clientId, Integer productId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product DAO: Delete orderProduct start");
        try {
            Integer orderId = daoFactory.getOrderDao().getOrderIdByClientId(clientId);
            LOGGER.log(Level.DEBUG, "ProductService: finish orderProduct client");
            return daoFactory.getOrderProductDao().deleteOrderProduct(orderId, productId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param orderIdNew
     * @param orderId
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean editOrderProductPayment(Integer orderIdNew, Integer orderId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderProduct Service: edit orderProduct start");
        try {
            LOGGER.log(Level.DEBUG, "OrderProduct Service: edit orderProduct finish");
            return daoFactory.getOrderProductDao().editOrderProductPayment(orderIdNew, orderId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

}

