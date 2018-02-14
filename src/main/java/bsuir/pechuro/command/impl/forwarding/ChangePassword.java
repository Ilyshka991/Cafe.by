package bsuir.pechuro.command.impl.forwarding;

import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.enumeration.AttributeParameterName;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.enumeration.RedirectingCommandName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * class ChangePassword created to change accounts' passwords
 */
public class ChangePassword implements ICommand {
    private JspPageName jspPageName = JspPageName.CHANGE_PASSWORD;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("pageCommand", RedirectingCommandName.CHANGE_PASSWORD.getCommand());
        rewrite(request);
        return jspPageName.getPath();
    }

    /**
     * @param request
     */
    private void rewrite(HttpServletRequest request) {
        request.setAttribute(AttributeParameterName.CHANGE_PASSWORD_ERROR.getValue(), request.getSession().getAttribute(AttributeParameterName.CHANGE_PASSWORD_ERROR.getValue()));
        request.getSession().removeAttribute(AttributeParameterName.CHANGE_PASSWORD_ERROR.getValue());
    }
}