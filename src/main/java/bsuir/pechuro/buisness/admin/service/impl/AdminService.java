package bsuir.pechuro.buisness.admin.service.impl;

import bsuir.pechuro.buisness.admin.dao.IAdminDao;
import bsuir.pechuro.buisness.admin.service.IAdminService;
import bsuir.pechuro.entity.Admin;
import bsuir.pechuro.exception.dao.DaoException;
import bsuir.pechuro.exception.service.ServiceException;
import bsuir.pechuro.exception.validation.ValidatorException;
import bsuir.pechuro.factory.dao.DaoFactory;
import bsuir.pechuro.utils.Hasher;
import bsuir.pechuro.utils.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;
/**
 * class AdminService created for preparation data before sending queries to database table "admin"
 */
public class AdminService implements IAdminService {
    private static final Logger LOGGER = Logger.getLogger(AdminService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * @param adminLogin
     * @param adminPassword
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean signUp(String adminLogin, String adminPassword) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Admin service: start signUp");
        try {
            Validator.isEmptyString(adminLogin, adminPassword);
            Validator.isNull(adminLogin, adminPassword);
            Validator.matchPassword(adminPassword);
            Validator.matchLogin(adminLogin);
            adminPassword = Hasher.hashBySha1(adminPassword);
            if (daoFactory.getClientDao().getClientByLogin(adminLogin) == null && !daoFactory.getStaffDao().findStaffByLogin(adminLogin)) {
                Admin admin = new Admin(adminLogin, adminPassword);
                return (daoFactory.getAdminDao().addAdmin(admin));
            }
        } catch (ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Admin service: finish signUp");
        return false;

    }

    /**
     * @param id
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean deleteAdmin(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product DAO: Delete admin start");
        try {
            LOGGER.log(Level.DEBUG, "ProductService: finish delete admin");
            return daoFactory.getAdminDao().deleteAdmin(id);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param adminLogin
     * @param adminPassword
     * @return Admin
     */
    @Override
    public Admin signIn(String adminLogin, String adminPassword) {
        LOGGER.log(Level.DEBUG, "Admin service: start SignIn");
        try {
            Validator.isEmptyString(adminLogin, adminPassword);
            Validator.isNull(adminLogin, adminPassword);
            Validator.matchLogin(adminLogin);
            Validator.matchPassword(adminPassword);
            adminPassword = Hasher.hashBySha1(adminPassword);
            LOGGER.log(Level.DEBUG, "Admin service: finish SignIn");
            return daoFactory.getAdminDao().signIn(adminLogin, adminPassword);
        } catch (DaoException | ValidatorException e) {
            return null;
        }
    }

    /**
     * @return List<Admin>
     * @throws ServiceException
     */
    @Override
    public List<Admin> getAllAdmins() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product Service: Start get all admins");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish get all admins");
            IAdminDao adminDao = daoFactory.getAdminDao();
            return adminDao.getAllAdmins();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param password
     * @param id
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean checkPassword(String password, Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Admin Service: check password start");
        try {
            Validator.isNull(password);
            Validator.isEmptyString(password);
            Validator.matchPassword(password);
            password = Hasher.hashBySha1(password);
            LOGGER.log(Level.DEBUG, "Admin Service: finish check password");
            return daoFactory.getAdminDao().checkPassword(password, id);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param password
     * @param id
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean changePassword(String password, Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Admin Service: change password start");
        try {
            Validator.isNull(password);
            Validator.isEmptyString(password);
            Validator.matchPassword(password);
            password = Hasher.hashBySha1(password);
            LOGGER.log(Level.DEBUG, "Admin Service: finish change password");
            return daoFactory.getAdminDao().changePassword(password, id);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param login
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean findAdminByLogin(String login) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Admin Service: find by login start");
        try {
            Validator.isNull(login);
            Validator.isEmptyString(login);
            Validator.matchLogin(login);
            LOGGER.log(Level.DEBUG, "Admin Service: find by login finish");
            return daoFactory.getAdminDao().findAdminByLogin(login);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }
}
