package bsuir.pechuro.buisness.product.service.impl;

import bsuir.pechuro.buisness.product.dao.IProductDao;
import bsuir.pechuro.buisness.product.service.IProductService;
import bsuir.pechuro.entity.Product;
import bsuir.pechuro.exception.dao.DaoException;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.exception.service.ServiceLogicException;
import bsuir.pechuro.exception.validation.ValidatorException;
import bsuir.pechuro.factory.dao.DaoFactory;
import bsuir.pechuro.utils.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * class ProductService created for preparation data before sending queries to database table "product"
 */
public class ProductService implements IProductService {

    private static Logger LOGGER = Logger.getLogger(ProductService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private static final int BUFFER_LENGTH = 1024;

    /**
     * @return List<Product>
     * @throws ServiceException
     */
    @Override
    public List<Product> getAllProducts() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product Service: Start get all products");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish get all products");
            return daoFactory.getProductDao().getAllProducts();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param type
     * @return List<Product>
     * @throws ServiceException
     */
    @Override
    public List<Product> getProductByType(String type) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: start get product by type");
        try {
            Validator.isNull(type);
            Validator.isEmptyString(type);
            LOGGER.log(Level.DEBUG, "Product Service: Finish get products by type");
            return daoFactory.getProductDao().getProductByType(type);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param id
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean deleteProduct(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product DAO: Delete product start");
        try {
            LOGGER.log(Level.DEBUG, "ProductService: finish delete products");
            return daoFactory.getProductDao().deleteProduct(id);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param orderId
     * @return List<Product>
     * @throws ServiceException
     */
    @Override
    public List<Product> getProductByOrderId(Integer orderId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: start get product by clientId");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish get products by clientId");
            return daoFactory.getProductDao().getProductByOrderId(orderId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param id
     * @return Product
     * @throws ServiceException
     */
    @Override
    public Product getProductById(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: start get product by ID");
        try {
            LOGGER.log(Level.DEBUG, "ProductService: finish get product by ID");
            return daoFactory.getProductDao().getProductById(id);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param type
     * @param nameRu
     * @param nameEn
     * @param weight
     * @param cost
     * @param descriptionRu
     * @param descriptionEn
     * @param image
     * @param webPath
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean addProduct(String type, String nameRu, String nameEn, Integer weight, Double cost,
                              String descriptionRu, String descriptionEn, Part image, String webPath) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: addProduct start");
        Product product = new Product();
        IProductDao productDao = daoFactory.getProductDao();
        try {
            Validator.isNull(nameEn, nameRu, type);
            Validator.isEmptyString(nameEn, nameRu, type);
            Validator.matchProductName(nameRu, nameEn);
            product.setType(type);
            product.setNameRu(nameRu);
            product.setNameEn(nameEn);
            product.setWeight(weight);
            product.setCost(cost);
            product.setDescriptionRu(descriptionRu);
            product.setDescriptionEn(descriptionEn);
            String imageName = getImageName(image);
            if (!imageName.isEmpty()) {
                product.setImagePath(imageName);
            }
            String fileName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
            if (!fileName.isEmpty()) {
                uploadImage(image, fileName, webPath);
            }
            LOGGER.log(Level.DEBUG, "ProductService: addProduct finish");
            return productDao.addProduct(product);

        } catch (ValidatorException | NumberFormatException | DaoException | ServiceLogicException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param id
     * @param type
     * @param nameRu
     * @param nameEn
     * @param weight
     * @param cost
     * @param descriptionRu
     * @param descriptionEn
     * @param image
     * @param webPath
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean editProduct(Integer id, String type, String nameRu, String nameEn, Integer weight, Double cost,
                               String descriptionRu, String descriptionEn, Part image, String webPath) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: addProduct start");
        Product product = new Product();
        IProductDao productDao = daoFactory.getProductDao();
        try {
            Validator.isNull(nameEn, nameRu, type);
            Validator.isEmptyString(nameEn, nameRu, type);
            Validator.matchProductName(nameEn,nameRu);
            product.setId(id);
            product.setType(type);
            product.setNameRu(nameRu);
            product.setNameEn(nameEn);
            product.setWeight(weight);
            product.setCost(cost);
            product.setDescriptionRu(descriptionRu);
            product.setDescriptionEn(descriptionEn);
            String imageName = getImageName(image);
            if (!imageName.isEmpty()) {
                product.setImagePath(imageName);
            }
            String fileName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
            if (!fileName.isEmpty()) {
                uploadImage(image, fileName, webPath);
            }
            LOGGER.log(Level.DEBUG, "ProductService: addProduct finish");
            return productDao.editProduct(product);

        } catch (ValidatorException | NumberFormatException | DaoException | ServiceLogicException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param filePart
     * @param fileName
     * @param webInfPath
     * @throws ServiceLogicException
     */
    private void uploadImage(Part filePart, String fileName, String webInfPath) throws ServiceLogicException {
        try {
            LOGGER.log(Level.DEBUG, "ProductServer: upload start");
            File dir = new File(webInfPath + "images" + File.separator + "products");
            if (!dir.exists()) {
                Path path = Paths.get(webInfPath + "images" + File.separator + "products");
                Files.createDirectories(path);
            }
            File file = new File(dir, fileName);
            InputStream fileContent = filePart.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[BUFFER_LENGTH];
            int len = fileContent.read(buffer);
            while (len != -1) {
                fileOutputStream.write(buffer, 0, len);
                len = fileContent.read(buffer);
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (IOException e) {
            throw new ServiceLogicException("error with upload of image", e);
        }
        LOGGER.log(Level.DEBUG, "ProductServer: upload finish");
    }

    /**
     * @param filePart
     * @return String
     */
    private String getImageName(Part filePart) {
        return Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
    }
}
