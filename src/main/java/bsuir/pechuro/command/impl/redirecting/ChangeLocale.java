package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.command.impl.CloseDBCommand;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChangeLocale implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(CloseDBCommand.class);
    private JspPageName jspPageName = JspPageName.INDEX;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.DEBUG, "Change locale start");
        try {
            String locale = (String) request.getSession().getAttribute("locale");
            if (locale == "ru") {
                request.getSession().setAttribute("locale", "en");
            } else {
                request.getSession().setAttribute("locale", "ru");
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.DEBUG, "Change locale finish");
        return jspPageName.getPath();
    }
}
