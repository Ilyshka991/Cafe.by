package bsuir.pechuro.command.impl.redirecting;

import bsuir.pechuro.buisness.product.service.IProductService;
import bsuir.pechuro.command.ICommand;
import bsuir.pechuro.enumeration.AttributeParameterName;
import bsuir.pechuro.enumeration.JspPageName;
import bsuir.pechuro.enumeration.RedirectingCommandName;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.factory.service.ServiceFactory;
import bsuir.pechuro.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


public class EditProduct implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            IProductService productService = serviceFactory.getProducteService();
            Integer productId = new Integer(request.getParameter(AttributeParameterName.PRODUCT_ID.getValue()));
            String productType = request.getParameter(AttributeParameterName.PRODUCT_TYPE.getValue());
            String nameRu = request.getParameter(AttributeParameterName.NAME_RU.getValue());
            String nameEn = request.getParameter(AttributeParameterName.NAME_EN.getValue());
            Integer weight = new Integer(request.getParameter(AttributeParameterName.VALUE.getValue()));
            Double cost = new Double(request.getParameter(AttributeParameterName.COST.getValue()));
            String descriptionRu = request.getParameter(AttributeParameterName.DESCRIPTION_RU.getValue());
            String descriptionEn = request.getParameter(AttributeParameterName.DESCRIPTION_EN.getValue());
            Part part = request.getPart(AttributeParameterName.IMAGE.getValue());
            String webPath = request.getServletContext().getRealPath("/");
            if (!productService.editProduct(productId, productType, nameRu, nameEn, weight, cost, descriptionRu, descriptionEn, part, webPath)) {
                diagnoseError(request);
            }
            response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
        } catch (ServletException | IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        return jspPageName.getPath();
    }


    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Ошибка! Продукт не изменен");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Error! Product wasn't edited");
        }
    }
}
