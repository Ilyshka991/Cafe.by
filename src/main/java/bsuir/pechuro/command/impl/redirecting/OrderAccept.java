package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.client.service.IClientService;
import bsuir.pechuro.buisness.order.service.IOrderService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.enumeration.AttributeParameterName;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.enumeration.RedirectingCommandName;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class OrderAccept implements ICommand {
    private final static Logger LOGGER = Logger.getLogger(OrderDeny.class);
    private JspPageName jspPageName = JspPageName.INDEX;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.DEBUG, "start order accept");
        Integer orderId = Integer.valueOf(request.getParameter(AttributeParameterName.ORDER_ID.getValue()));
        IOrderService orderService = ServiceFactory.getInstance().getOrderService();
        IClientService clientService = ServiceFactory.getInstance().getClientService();
        try {
            clientService.countPoints(orderService.getOrderByOrderId(orderId));
            orderService.deleteOrder(orderId);
            response.sendRedirect(RedirectingCommandName.ORDER_SHOW.getCommand());
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.DEBUG, "finish order accept");
        return jspPageName.getPath();
    }
}
