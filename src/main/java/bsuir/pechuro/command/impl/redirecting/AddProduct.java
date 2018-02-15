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

public class AddProduct implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add product");
        try {
            IProductService productService = serviceFactory.getProducteService();
            String productType = request.getParameter(AttributeParameterName.PRODUCT_TYPE.getValue());
            String nameRu = request.getParameter(AttributeParameterName.NAME_RU.getValue());
            String nameEn = request.getParameter(AttributeParameterName.NAME_EN.getValue());
            Integer weight = new Integer(request.getParameter(AttributeParameterName.VALUE.getValue()));
            Double cost = new Double(request.getParameter(AttributeParameterName.COST.getValue()));
            String descriptionRu = request.getParameter(AttributeParameterName.DESCRIPTION_RU.getValue());
            String descriptionEn = request.getParameter(AttributeParameterName.DESCRIPTION_EN.getValue());
            Part part = request.getPart(AttributeParameterName.IMAGE.getValue());
            String webPath = request.getServletContext().getRealPath("/");
            if (!productService.addProduct(productType, nameRu, nameEn, weight, cost, descriptionRu, descriptionEn, part, webPath)) {
                diagnoseError(request);
            }
            response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
        } catch (IOException | ServletException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish add product");
        return jspPageName.getPath();
    }


    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Ошибка! Продукт не добавлен");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Error! Product wasn't added");
        }
    }
}
