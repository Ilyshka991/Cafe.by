package bsuir.pechuro.command.impl.forwarding;

import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.enumeration.JspPageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ResetForm implements ICommand {
    private JspPageName jspPageName = JspPageName.RESET_FORM;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return jspPageName.getPath();
    }
}
