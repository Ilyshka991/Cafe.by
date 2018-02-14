package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.client.service.IClientService;
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

/**
 * class DeleteClient created to delete clients
 */
public class DeleteClient implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private JspPageName jspPageName = JspPageName.CLIENTS;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start delete client");
        try {
            IClientService clientService = ServiceFactory.getInstance().getClientService();
            Integer clientId = Integer.valueOf(request.getParameter(AttributeParameterName.CLIENT_ID.getValue()));
            clientService.deleteClient(clientId);
            response.sendRedirect(RedirectingCommandName.EDIT_CLIENTS.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish delete client");
        return jspPageName.getPath();
    }
}
