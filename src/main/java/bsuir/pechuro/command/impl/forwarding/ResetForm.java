package bsuir.pechuro.command.impl.forwarding;

import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.enumeration.JspPageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * class ResetForm created to Reset password if you're forgot him
 */
public class ResetForm implements ICommand {
    private JspPageName jspPageName = JspPageName.RESET_FORM;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return jspPageName.getPath();
    }
}
