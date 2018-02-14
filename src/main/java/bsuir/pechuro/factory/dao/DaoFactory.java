package bsuir.pechuro.factory.dao;

import bsuir.pechuro.buisness.account.dao.IAccountDao;
import bsuir.pechuro.buisness.account.dao.impl.AccountDAO;
import bsuir.pechuro.buisness.admin.dao.IAdminDao;
import bsuir.pechuro.buisness.admin.dao.impl.AdminDAO;
import bsuir.pechuro.buisness.client.dao.IClientDao;
import bsuir.pechuro.buisness.client.dao.impl.ClientDAO;
import bsuir.pechuro.buisness.order.dao.IOrderDao;
import bsuir.pechuro.buisness.order.dao.impl.OrderDAO;
import bsuir.pechuro.buisness.orderproduct.dao.IOrderProductDao;
import bsuir.pechuro.buisness.orderproduct.dao.impl.OrderProductDAO;
import bsuir.pechuro.buisness.product.dao.IProductDao;
import bsuir.pechuro.buisness.product.dao.impl.ProductDAO;
import bsuir.pechuro.buisness.review.dao.IReviewDao;
import bsuir.pechuro.buisness.review.dao.impl.ReviewDao;
import bsuir.pechuro.buisness.staff.dao.IStaffDao;
import bsuir.pechuro.buisness.staff.dao.impl.StaffDAO;

/**
 * class DaoFactory created to choose factory for working
 */
public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final IClientDao clientDao = new ClientDAO();
    private final IProductDao productDao = new ProductDAO();
    private final IAdminDao adminDao = new AdminDAO();
    private final IOrderDao orderDao = new OrderDAO();
    private final IOrderProductDao orderProductDao = new OrderProductDAO();
    private final IAccountDao accountDao = new AccountDAO();
    private final IStaffDao staffDao = new StaffDAO();
    private final IReviewDao reviewDao = new ReviewDao();


    private DaoFactory() {
    }

    /**
     * @return DaoFactory
     */
    public static DaoFactory getInstance() {
        return instance;
    }

    /**
     * @return IClientDao
     */
    public IClientDao getClientDao() {
        return clientDao;
    }

    /**
     * @return IProductDao
     */
    public IProductDao getProductDao() {
        return productDao;
    }

    /**
     * @return IAdminDao
     */
    public IAdminDao getAdminDao() {
        return adminDao;
    }

    /**
     * @return IOrderDao
     */
    public IOrderDao getOrderDao() {
        return orderDao;
    }

    /**
     * @return IOrderProductDao
     */
    public IOrderProductDao getOrderProductDao() {
        return orderProductDao;
    }

    /**
     * @return IAccountDao
     */
    public IAccountDao getAccountDao() {
        return accountDao;
    }

    /**
     * @return IStaffDao
     */
    public IStaffDao getStaffDao() {
        return staffDao;
    }

    /**
     * @return IReviewDao
     */
    public IReviewDao getReviewDao() {
        return reviewDao;
    }

}
