package bsuir.pechuro.command.impl.forwarding;

import bsuir.pechuro.buisness.admin.service.IAdminService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.entity.Admin;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.enumeration.RedirectingCommandName;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class AdminList implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(StaffList.class);
    private JspPageName jspPageName = JspPageName.ADMIN;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Start admin command");
        try {
            IAdminService adminService = ServiceFactory.getInstance().getAdminService();
            List<Admin> allAdmins = adminService.getAllAdmins();
            request.setAttribute("allAdmins", allAdmins);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.ADMIN_LIST.getCommand());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command: Finish admin command");
        return jspPageName.getPath();
    }
}