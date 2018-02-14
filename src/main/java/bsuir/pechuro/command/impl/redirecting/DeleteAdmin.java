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

/**
 * class DeleteAdmin created to delete administrators
 */
public class DeleteAdmin implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(DeleteAdmin.class);
    private JspPageName jspPageName = JspPageName.ADMIN;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start delete admin");
        try {
            IAdminService adminService = ServiceFactory.getInstance().getAdminService();
            Integer adminId = Integer.valueOf(request.getParameter(AttributeParameterName.ADMIN_ID.getValue()));
            adminService.deleteAdmin(adminId);
            response.sendRedirect(RedirectingCommandName.ADMIN_LIST.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish delete admin");
        return jspPageName.getPath();
    }
}
