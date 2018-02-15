package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.admin.service.IAdminService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.enumeration.AttributeParameterName;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.enumeration.RedirectingCommandName;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.service.ServiceFactory;
import bsuir.pechuro.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddAdmin implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.ADMIN;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add admin");
        try {
            IAdminService adminService = ServiceFactory.getInstance().getAdminService();
            String adminLogin = request.getParameter(AttributeParameterName.ADMIN_LOGIN.getValue());
            String adminPassword = request.getParameter(AttributeParameterName.ADMIN_PASSWORD.getValue());
            if (!adminService.signUp(adminLogin, adminPassword)) {
                diagnoseError(request);
            }
            response.sendRedirect(RedirectingCommandName.ADMIN_LIST.getCommand());
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish add admin");
        return jspPageName.getPath();
    }


    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.ADD_ADMIN_ERROR.getValue(), "Пользователь с таким логиом уже существует");
        } else {
            request.getSession().setAttribute(AttributeParameterName.ADD_ADMIN_ERROR.getValue(), "User with this nickname already exist");
        }
    }
}