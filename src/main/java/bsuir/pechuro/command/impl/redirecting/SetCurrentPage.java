package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.command.impl.forwarding.Index;
import bsuir.pechuro.enumeration.AttributeParameterName;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class SetCurrentPage created for choosing page
 */
public class SetCurrentPage implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(Index.class);
    private JspPageName jspPageName = JspPageName.INDEX;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            LOGGER.log(Level.INFO, "Set current page command start");
            Integer currentPage = Integer.valueOf(request.getParameter(AttributeParameterName.CURRENT_PAGE.getValue()));
            request.getSession().setAttribute("currentPage", currentPage);
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Set current page command finish");
        return jspPageName.getPath();
    }
}
