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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * class FindByType created to display products' with certain type
 */
public class FindByType implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(Index.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;
    private String productType;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Find command start");
        try {
            String type = request.getParameter(AttributeParameterName.PRODUCT_TYPE.getValue());
            if (type != null) {
                productType = type;
                request.getSession().setAttribute("currentPage", 1);
            }
            setPageProduct(request);
            Common.setReview(request);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.FIND_BY_TYPE.getCommand());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Find command finish");
        return jspPageName.getPath();
    }

    /**
     * @param request
     * @throws ServiceException
     */
    private void setPageProduct(HttpServletRequest request) throws ServiceException {
        IProductService productService = serviceFactory.getProducteService();
        List<Product> allProducts = productService.getProductByType(productType);
        Common.calculatePageNumber(request, allProducts);
    }
}
