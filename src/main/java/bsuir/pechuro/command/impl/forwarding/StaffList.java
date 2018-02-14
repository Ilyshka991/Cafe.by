package bsuir.pechuro.command.impl.forwarding;

import bsuir.pechuro.buisness.staff.service.IStaffService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.entity.Staff;
import bsuir.pechuro.enumeration.AttributeParameterName;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.enumeration.RedirectingCommandName;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * class StaffList created to get and display data about staff
 */
public class StaffList implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(StaffList.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.STAFF;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Start staff command");
        try {
            IStaffService staffService = serviceFactory.getStaffService();
            List<Staff> allStaff = staffService.getAllStaff();
            request.setAttribute("allStaff", allStaff);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.STAFF_LIST.getCommand());
            rewrite(request);
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command: Finish staff command");
        return jspPageName.getPath();
    }

    /**
     * @param request
     */
    private void rewrite(HttpServletRequest request) {
        request.setAttribute(AttributeParameterName.ADD_STAFF_ERROR.getValue(), request.getSession().getAttribute(AttributeParameterName.ADD_STAFF_ERROR.getValue()));
        request.getSession().removeAttribute(AttributeParameterName.ADD_STAFF_ERROR.getValue());
    }
}