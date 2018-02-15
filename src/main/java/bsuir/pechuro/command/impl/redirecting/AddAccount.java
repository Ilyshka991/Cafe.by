package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.account.service.IAccountService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.entity.User;
import bsuir.pechuro.enumeration.AttributeParameterName;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.service.ServiceFactory;
import bsuir.pechuro.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddAccount implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add account");
        try {
            IAccountService accountService = serviceFactory.getAccountService();
            User user = ((User) request.getSession().getAttribute(AttributeParameterName.USER.getValue()));
            if (!accountService.addAccount(user.getId())) {
                diagnoseError(request);
            } else {
                user.setAccount(true);
                request.getSession().setAttribute(AttributeParameterName.USER.getValue(), user);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish add account");
        return jspPageName.getPath();
    }


    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Ошибка! Аккаунт не добавлен");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Error! Account wasn't added");
        }
    }
}