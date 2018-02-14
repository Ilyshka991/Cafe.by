package bsuir.pechuro.command.impl.forwarding;

import bsuir.pechuro.buisness.product.service.IProductService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.entity.Product;
import bsuir.pechuro.enumeration.AttributeParameterName;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.enumeration.RedirectingCommandName;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.service.ServiceFactory;
import bsuir.pechuro.utils.Common;
import bsuir.pechuro.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * class Index created to prepare information before displaying it on certain homepage
 */
public class Index implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(Index.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Index command start");
        try {
            setPageProduct(request);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.INDEX.getCommand());
            request.getSession().setAttribute("locale", SessionElements.getLocale(request));
            rewrite(request);
            Common.setReview(request);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Index command finish");
        return jspPageName.getPath();
    }

    /**
     * @param request
     */
    private void rewrite(HttpServletRequest request) {
        request.setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), request.getSession().getAttribute(AttributeParameterName.HEADER_ERROR.getValue()));
        request.getSession().removeAttribute(AttributeParameterName.HEADER_ERROR.getValue());
    }

    /**
     * @param request
     * @throws ServiceException
     */
    private void setPageProduct(HttpServletRequest request) throws ServiceException {
        IProductService productService = serviceFactory.getProducteService();
        List<Product> allProducts = productService.getAllProducts();
        System.out.println(allProducts);
        for (Product product : allProducts) {
            if (product.getType().equals("false")) {
                allProducts.remove(product);
            }
        }
        Common.calculatePageNumber(request, allProducts);
    }
}
