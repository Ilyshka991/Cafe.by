package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.review.service.IReviewService;
import bsuir.pechuro.command.ICommand;
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
import java.io.IOException;


public class AddReview implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add review");
        try {
            IReviewService reviewService = serviceFactory.getReviewService();
            Integer clientId = ((User) request.getSession().
                    getAttribute(AttributeParameterName.USER.getValue())).getId();
            String text = request.getParameter(AttributeParameterName.REVIEW_TEXT.getValue());
            String mark = request.getParameter(AttributeParameterName.MARK_VALUE.getValue());
            if (mark != null) {
                reviewService.addReview(text, Double.parseDouble(mark), clientId);
            } else {
                diagnoseError(request);
            }
            response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish add review");
        return jspPageName.getPath();
    }


    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Пожалуйста, поставьте оценку!");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "You hadn't choose anything to add");
        }
    }
}