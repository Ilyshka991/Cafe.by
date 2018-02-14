package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.staff.service.IStaffService;
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
 * class ChangeStaff created to change staff
 */
public class ChangeStaff implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.INDEX;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IStaffService staffService = serviceFactory.getStaffService();
        String password = request.getParameter(AttributeParameterName.NEW_PASSWORD.getValue());
        Integer staffId = new Integer(request.getParameter(AttributeParameterName.STAFF_ID.getValue()));
        LOGGER.log(Level.INFO, "Change staff start");
        try {
            staffService.changePassword(password, staffId);
            response.sendRedirect(RedirectingCommandName.STAFF_LIST.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Change staff finish");
        return jspPageName.getPath();
    }
}
