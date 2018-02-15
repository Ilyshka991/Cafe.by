package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.client.service.IClientService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.entity.Client;
import bsuir.pechuro.enumeration.AttributeParameterName;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.enumeration.RedirectingCommandName;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.service.ServiceFactory;
import bsuir.pechuro.utils.EmailSender;
import bsuir.pechuro.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ResetPassword implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ResetPassword.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Reset password start");
        try {
            String email = request.getParameter(AttributeParameterName.RESET_EMAIL.getValue());
            IClientService clientService = serviceFactory.getClientService();
            Client client = clientService.findClientByEmail(email);
            if (client != null) {
                int code = EmailSender.generateAndSendEmail(SessionElements.getLocale(request), email);
                request.getSession().setAttribute(AttributeParameterName.CODE.getValue(), code);
                request.getSession().setAttribute(AttributeParameterName.CLIENT.getValue(), client);
                jspPageName = JspPageName.RESET_FORM;
                response.sendRedirect(RedirectingCommandName.RESET_FORM.getCommand());
            } else {
                diagnoseError(request);
                response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
            }
        } catch (IOException | ServiceException | MessagingException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
            return jspPageName.getPath();
        }
        LOGGER.log(Level.INFO, "Reset password finish");
        return jspPageName.getPath();
    }


    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Пользователя с такой почтой не найдено");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "User with such email wasn't found");
        }
    }
}
