package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.client.service.IClientService;
import bsuir.pechuro.buisness.order.service.IOrderService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.entity.Client;
import bsuir.pechuro.entity.User;
import bsuir.pechuro.enumeration.AttributeParameterName;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.enumeration.RedirectingCommandName;
import bsuir.pechuro.factory.service.ServiceFactory;
import bsuir.pechuro.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SignUp implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignUp.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Start Sign Up");
        try {
            IClientService clientService = serviceFactory.getClientService();
            IOrderService orderService = serviceFactory.getOrderService();
            String login = request.getParameter(AttributeParameterName.SIGNUP_LOGIN.getValue());
            String password = request.getParameter(AttributeParameterName.SIGNUP_PASSWORD.getValue());
            String name = request.getParameter(AttributeParameterName.SIGNUP_NAME.getValue());
            String surname = request.getParameter(AttributeParameterName.SIGNUP_SURNAME.getValue());
            String email = request.getParameter(AttributeParameterName.SIGNUP_EMAIL.getValue());
            Client client = clientService.signUp(name, surname, login, password, email);
            if (client == null) {
                if (clientService.findClientByLogin(login)) {
                    diagnoseError(request);
                } else {
                    diagnoseCommonEmail(request);
                }
            } else {
                User user = new User(client.getId(), client.getLogin(), "client", client.getName(), client.getSurname(), client.getStatus(), false);
                orderService.addOrder("Not order", 0.0, client.getId());
                HttpSession session = request.getSession();
                session.setAttribute(AttributeParameterName.USER.getValue(), user);
                LOGGER.log(Level.INFO, "Successful sign in account as " + login);
            }
            response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
        } catch (Exception e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command: Finish Sign Up");
        return jspPageName.getPath();
    }


    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Пользовател с таким логином уже сужествует");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "User with such login already exists");
        }
    }


    private void diagnoseCommonEmail(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Пользовател с такой почтой уже существует");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "User with such mail already exists");
        }
    }
}
