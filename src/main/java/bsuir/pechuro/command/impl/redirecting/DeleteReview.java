package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.review.service.IReviewService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.enumeration.AttributeParameterName;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.enumeration.RedirectingCommandName;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class DeleteReview created to delete reviews
 */
public class DeleteReview implements ICommand{
    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private JspPageName jspPageName = JspPageName.INDEX;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start delete client");
        try {
            IReviewService reviewService = ServiceFactory.getInstance().getReviewService();
            Integer reviewId = Integer.valueOf(request.getParameter(AttributeParameterName.REVIEW_ID.getValue()));
            reviewService.deleteReview(reviewId);
            response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish delete client");
        return jspPageName.getPath();
    }
}
