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
import java.util.ArrayList;
import java.util.List;

/**
 * class SearchProduct created to search information about products
 */
public class SearchProduct implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(Index.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;
    private String productName;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Search command start");
        try {
            String name = request.getParameter(AttributeParameterName.SEARCH_NAME.getValue());
            if (name != null) {
                productName = name;
                request.getSession().setAttribute("currentPage", 1);
            }
            setPageProduct(request);
            Common.setReview(request);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.SEARCH_PRODUCT.getCommand());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Search command finish");
        return jspPageName.getPath();
    }

    /**
     * @param request
     * @throws ServiceException
     */
    private void setPageProduct(HttpServletRequest request) throws ServiceException {
        IProductService productService = serviceFactory.getProducteService();
        List<Product> products = productService.getAllProducts();
        List<Product> allProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getNameEn().toLowerCase().contains(productName.toLowerCase())
                    || product.getNameRu().toLowerCase().contains(productName.toLowerCase())) {
                allProducts.add(product);
            }
        }
        Common.calculatePageNumber(request, allProducts);
    }
}
