package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.client.service.IClientService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.entity.Client;
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

/**
 * class ResetPasswordConfirm created to confirm password reset
 */
public class ResetPasswordConfirm implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ResetPasswordConfirm.class);
    private JspPageName jspPageName = JspPageName.INDEX;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Reset password confirm start");
        String new_password = request.getParameter(AttributeParameterName.RESET_NEW_PASSWORD.getValue());
        Client client = (Client)request.getSession().getAttribute("client");
        IClientService clientService = ServiceFactory.getInstance().getClientService();
        try {
            if(!clientService.changePassword(new_password,client.getId())){
                diagnoseError(request);
            }
            request.getSession().removeAttribute("client");
            request.getSession().removeAttribute("code");
            response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Reset password confirm finish");
        return jspPageName.getPath();
    }

    /**
     * @param request
     */
    protected void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Не удалось изменить пароль");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Password wasn't changed");
        }
    }
}
