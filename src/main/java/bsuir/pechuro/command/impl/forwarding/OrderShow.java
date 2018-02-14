package bsuir.pechuro.command.impl.forwarding;

import bsuir.pechuro.buisness.order.service.IOrderService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.entity.Order;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.enumeration.RedirectingCommandName;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * class OrderShow created to show orders
 */
public class OrderShow implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(OrderShow.class);

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.DEBUG, "start order show");
        IOrderService orderService = ServiceFactory.getInstance().getOrderService();
        List<Order> orders;
        try {
            orders = orderService.getAllOrderedOrders();
            Collections.sort(orders, new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    for (int i = 0; i < 5; i++) {
                        int a = new Integer(o1.getYear()[i]);
                        int b = new Integer(o2.getYear()[i]);
                        if (a != b) {
                            return a - b;
                        }
                    }
                    return 0;
                }
            });
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            return JspPageName.ERROR.getPath();
        }
        request.setAttribute("orders", orders);
        request.getSession().setAttribute("pageCommand", RedirectingCommandName.ORDER_SHOW.getCommand());
        LOGGER.log(Level.DEBUG, "success order show");
        return JspPageName.INDEX.getPath();
    }
}
