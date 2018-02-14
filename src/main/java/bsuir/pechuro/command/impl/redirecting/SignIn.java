package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.account.service.IAccountService;
import bsuir.pechuro.buisness.admin.service.IAdminService;
import bsuir.pechuro.buisness.client.service.IClientService;
import bsuir.pechuro.buisness.staff.service.IStaffService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.entity.Admin;
import bsuir.pechuro.entity.Client;
import bsuir.pechuro.entity.Staff;
import bsuir.pechuro.entity.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * class SignIn created to sign in account
 */
public class SignIn implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignIn.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;
    private User user;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Sign in command start");
        user = null;
        String login = request.getParameter(AttributeParameterName.SIGNIN_LOGIN.getValue());
        String password = request.getParameter(AttributeParameterName.SIGNIN_PASSWORD.getValue());

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorData", "введите логин или пароль");
            jspPageName = JspPageName.ERROR;
            return jspPageName.getPath();
        }

        IClientService clientService = serviceFactory.getClientService();
        Client client = clientService.signIn(login, password);

        IAdminService adminService = serviceFactory.getAdminService();
        Admin admin = adminService.signIn(login, password);

        IStaffService staffService = serviceFactory.getStaffService();
        Staff staff = staffService.signIn(login, password);

        IAccountService accountService = serviceFactory.getAccountService();
        LOGGER.log(Level.INFO, client);
        try {
            if (client != null) {
                if (!client.getStatus().equals("banned")) {
                    user = new User(client.getId(), client.getLogin(), "client", client.getName(), client.getSurname(), client.getStatus(), accountService.findAccountByClientId(client.getId()), client.getPoint());
                    HttpSession session = request.getSession();
                    session.setAttribute(AttributeParameterName.USER.getValue(), user);
                    LOGGER.log(Level.INFO, "Successful sign in account as " + login);
                    response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
                } else {
                    diagnoseBan(request);
                    response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
                }
            } else {
                if (admin != null) {
                    user = new User(admin.getId(), admin.getLogin(), "admin", admin.getIsMain());
                    HttpSession session = request.getSession();
                    session.setAttribute(AttributeParameterName.USER.getValue(), user);
                    LOGGER.log(Level.INFO, "Successful sign in account as " + login);
                    response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
                } else {
                    if (staff != null) {
                        user = new User(staff.getId(), staff.getLogin(), "staff");
                        request.getSession().setAttribute(AttributeParameterName.USER.getValue(), user);
                        LOGGER.log(Level.INFO, "Successful sign in account as " + login);
                        response.sendRedirect(RedirectingCommandName.ORDER_SHOW.getCommand());
                    } else {
                        if (clientService.findClientByLogin(login) || adminService.findAdminByLogin(login) || staffService.findStaffByLogin(login)) {
                            diagnoseIncorrectPassword(request);
                        } else {
                            diagnoseError(request);
                        }
                        response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
                    }
                }
            }
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }

        LOGGER.log(Level.INFO, "Sign in command finish");
        return jspPageName.getPath();
    }

    /**
     * @param request
     */
    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Пользователя с таким логином не существует");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "User with such login not exists");
        }
    }

    /**
     * @param request
     */
    private void diagnoseIncorrectPassword(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Неверный пароль");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Incorrect password");
        }
    }

    /**
     * @param request
     */
    private void diagnoseBan(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Вы заблокированы. Пожалуйста, обратитесь к администратору");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "You're was blocked. Please, get in touch with administrator");
        }
    }
}