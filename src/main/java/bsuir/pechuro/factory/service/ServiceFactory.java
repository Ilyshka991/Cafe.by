package bsuir.pechuro.factory.service;

import bsuir.pechuro.buisness.account.service.IAccountService;
import bsuir.pechuro.buisness.account.service.impl.AccountService;
import bsuir.pechuro.buisness.admin.service.IAdminService;
import bsuir.pechuro.buisness.admin.service.impl.AdminService;
import bsuir.pechuro.buisness.client.service.IClientService;
import bsuir.pechuro.buisness.client.service.impl.ClientService;
import bsuir.pechuro.buisness.order.service.IOrderService;
import bsuir.pechuro.buisness.order.service.impl.OrderService;
import bsuir.pechuro.buisness.orderproduct.service.IOrderProductService;
import bsuir.pechuro.buisness.orderproduct.service.impl.OrderProductService;
import bsuir.pechuro.buisness.product.service.IProductService;
import bsuir.pechuro.buisness.product.service.impl.ProductService;
import bsuir.pechuro.buisness.review.service.IReviewService;
import bsuir.pechuro.buisness.review.service.impl.ReviewService;
import bsuir.pechuro.buisness.staff.service.IStaffService;
import bsuir.pechuro.buisness.staff.service.impl.StaffService;

/**
 * class ServiceFactory created to choose service for working
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final IAdminService adminService = new AdminService();
    private final IProductService producteService = new ProductService();
    private final IClientService clientService = new ClientService();
    private final IOrderProductService orderProductService = new OrderProductService();
    private final IAccountService accountService = new AccountService();
    private final IOrderService orderService = new OrderService();
    private final IReviewService reviewService = new ReviewService();
    private final IStaffService staffService = new StaffService();

    private ServiceFactory() {
    }

    /**
     * @return ServiceFactory
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    /**
     * @return IAdminService
     */
    public IAdminService getAdminService() {
        return adminService;
    }

    /**
     * @return IOrderProductService
     */
    public IOrderProductService getOrderProductService() {
        return orderProductService;
    }

    /**
     * @return IProductService
     */
    public IProductService getProducteService() {
        return producteService;
    }

    /**
     * @return IClientService
     */
    public IClientService getClientService() {
        return clientService;
    }

    /**
     * @return IAccountService
     */
    public IAccountService getAccountService() {
        return accountService;
    }

    /**
     * @return IOrderService
     */
    public IOrderService getOrderService() {
        return orderService;
    }

    /**
     * @return IReviewService
     */
    public IReviewService getReviewService() {
        return reviewService;
    }

    /**
     * @return IStaffService
     */
    public IStaffService getStaffService() {
        return staffService;
    }
}
