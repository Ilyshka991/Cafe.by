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


    public static DaoFactory getInstance() {
        return instance;
    }


    public IClientDao getClientDao() {
        return clientDao;
    }

    public IProductDao getProductDao() {
        return productDao;
    }

    public IAdminDao getAdminDao() {
        return adminDao;
    }


    public IOrderDao getOrderDao() {
        return orderDao;
    }


    public IOrderProductDao getOrderProductDao() {
        return orderProductDao;
    }


    public IAccountDao getAccountDao() {
        return accountDao;
    }


    public IStaffDao getStaffDao() {
        return staffDao;
    }


    public IReviewDao getReviewDao() {
        return reviewDao;
    }

}
