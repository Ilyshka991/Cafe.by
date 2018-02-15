package bsuir.pechuro.command.impl.forwarding;

import bsuir.pechuro.buisness.client.service.IClientService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.entity.Client;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.enumeration.RedirectingCommandName;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class ClientList implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ClientList.class);
    private JspPageName jspPageName = JspPageName.CLIENTS;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start edit clients");
        try {
            IClientService clientService = ServiceFactory.getInstance().getClientService();
            List<Client> clients = clientService.getAllClients();
            request.setAttribute("clients", clients);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.EDIT_CLIENTS.getCommand());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }

        LOGGER.log(Level.INFO, "Finish edit clients");
        return jspPageName.getPath();
    }
}