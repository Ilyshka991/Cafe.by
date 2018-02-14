package bsuir.pechuro.buisness.staff.service.impl;

import bsuir.pechuro.buisness.staff.dao.IStaffDao;
import bsuir.pechuro.buisness.staff.service.IStaffService;
import bsuir.pechuro.entity.Staff;
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
 * class StaffService created for preparation data before sending queries to database table "staff"
 */
public class StaffService implements IStaffService {
    private static final Logger LOGGER = Logger.getLogger(StaffService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * @param staffLogin
     * @param staffPassword
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean signUp(String staffLogin, String staffPassword) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Staff service: start addStaff");
        try {
            Validator.isNull(staffLogin, staffPassword);
            Validator.isEmptyString(staffLogin, staffPassword);
            Validator.matchPassword(staffPassword);
            Validator.matchLogin(staffLogin);
            staffPassword = Hasher.hashBySha1(staffPassword);
            if (daoFactory.getClientDao().getClientByLogin(staffLogin) == null &&
                    !daoFactory.getAdminDao().findAdminByLogin(staffLogin)) {
                Staff staff = new Staff(staffLogin, staffPassword);
                return daoFactory.getStaffDao().addStaff(staff);
            }
        } catch (ValidatorException | DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Admin service: finish addAdmin");
        return false;
    }

    /**
     * @param id
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean deleteStaff(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product DAO: Delete staff start");
        IStaffDao staffDao = daoFactory.getStaffDao();
        try {
            staffDao.deleteStaff(id);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "ProductService: finish staff client");
        return true;
    }

    /**
     * @param staffLogin
     * @param staffPassword
     * @return Staff
     */
    @Override
    public Staff signIn(String staffLogin, String staffPassword) {
        LOGGER.log(Level.DEBUG, "Staff service: start SignIn");
        try {
            Validator.isNull(staffLogin, staffPassword);
            Validator.isEmptyString(staffLogin, staffPassword);
            Validator.matchPassword(staffPassword);
            Validator.matchLogin(staffLogin);
            staffPassword = Hasher.hashBySha1(staffPassword);
            LOGGER.log(Level.DEBUG, "Staff service: finish SignIn");
            return daoFactory.getStaffDao().signIn(staffLogin, staffPassword);
        } catch (DaoException | ValidatorException e) {
            return null;
        }
    }

    /**
     * @return List<Staff>
     * @throws ServiceException
     */
    @Override
    public List<Staff> getAllStaff() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Staff Service: Start get all staff");
        try {
            LOGGER.log(Level.DEBUG, "Staff Service: Finish get all staff");
            return daoFactory.getStaffDao().getAllStaff();
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
        LOGGER.log(Level.DEBUG, "Staff Service: check password start");
        try {
            Validator.isNull(password);
            Validator.isEmptyString(password);
            Validator.matchPassword(password);
            password = Hasher.hashBySha1(password);
            IStaffDao staffDao = daoFactory.getStaffDao();
            LOGGER.log(Level.DEBUG, "Staff Service: finish check password");
            return staffDao.checkPassword(password, id);
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
        LOGGER.log(Level.DEBUG, "Staff Service: change password start");
        try {
            Validator.isNull(password);
            Validator.isEmptyString(password);
            Validator.matchPassword(password);
            password = Hasher.hashBySha1(password);
            LOGGER.log(Level.DEBUG, "Staff Service: finish change password");
            return daoFactory.getStaffDao().changePassword(password, id);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param login
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean findStaffByLogin(String login) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Admin Service: find by login start");
        try {
            Validator.isNull(login);
            Validator.isEmptyString(login);
            Validator.matchLogin(login);
            LOGGER.log(Level.DEBUG, "Admin Service: find by login finish");
            return daoFactory.getStaffDao().findStaffByLogin(login);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

}
