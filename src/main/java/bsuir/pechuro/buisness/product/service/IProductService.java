package bsuir.pechuro.buisness.product.service;

import bsuir.pechuro.entity.Product;
import bsuir.pechuro.exception.service.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

public interface IProductService {
    List<Product> getAllProducts() throws ServiceException;

    List<Product> getProductByType(String type) throws ServiceException;

    boolean deleteProduct(Integer id) throws ServiceException;

    List<Product> getProductByOrderId(Integer orderId) throws ServiceException;

    Product getProductById(Integer id) throws ServiceException;

    boolean addProduct(String type, String nameRu, String nameEn, Integer weight, Double cost,
                       String descriptionRu, String descriptionEn, Part image, String webPath) throws ServiceException;

    boolean editProduct(Integer productId, String productType, String nameRu, String nameEn, Integer weight, Double cost, String descriptionRu, String descriptionEn, Part part, String webPath) throws ServiceException;
}
