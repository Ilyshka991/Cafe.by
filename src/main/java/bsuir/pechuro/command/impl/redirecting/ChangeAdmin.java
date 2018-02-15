package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.admin.service.IAdminService;
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


public class ChangeAdmin implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IAdminService adminService = serviceFactory.getAdminService();
        String password = request.getParameter(AttributeParameterName.NEW_PASSWORD.getValue());
        Integer adminId = new Integer(request.getParameter(AttributeParameterName.ADMIN_ID.getValue()));
        LOGGER.log(Level.INFO, "Change admin start");
        try {
            adminService.changePassword(password, adminId);
            response.sendRedirect(RedirectingCommandName.ADMIN_LIST.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Change admin finish");
        return jspPageName.getPath();
    }
}
