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
 * class DeleteStaff created to delete staff
 */
public class DeleteStaff implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(DeleteStaff.class);
    private JspPageName jspPageName = JspPageName.STAFF;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start delete staff");
        try {
            IStaffService staffService = ServiceFactory.getInstance().getStaffService();
            Integer staffId = Integer.valueOf(request.getParameter(AttributeParameterName.STAFF_ID.getValue()));
            staffService.deleteStaff(staffId);
            response.sendRedirect(RedirectingCommandName.STAFF_LIST.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish delete staff");
        return jspPageName.getPath();
    }
}
