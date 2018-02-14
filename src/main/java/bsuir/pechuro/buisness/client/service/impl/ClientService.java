package bsuir.pechuro.buisness.client.service.impl;

import bsuir.pechuro.buisness.client.service.IClientService;
import bsuir.pechuro.entity.Client;
import bsuir.pechuro.entity.Order;
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
 * class ClientService created for preparation data before sending queries to database table "client"
 */
public class ClientService implements IClientService {
    private static final Logger LOGGER = Logger.getLogger(ClientService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Double DISCOUNT = 0.05;

    /**
     * @param order
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean countPoints(Order order) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: start countPoints");
        Double points = DISCOUNT * order.getCost();
        try {
            return daoFactory.getClientDao().addPoints(points, order.getClientId());
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param clientId
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean clearPoints(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: start clearPoints");
        try {
            return daoFactory.getClientDao().clearPoints(clientId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param name
     * @param surname
     * @param login
     * @param password
     * @param email
     * @return Client
     * @throws ServiceException
     */
    @Override
    public Client signUp(String name, String surname, String login, String password, String email) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Service: start SignUp");
        Client client;
        try {
            Validator.isNull(name, login, password, name, surname, email);
            Validator.isEmptyString(name, login, password, name, surname, email);
            Validator.matchProperName(name, surname);
            Validator.matchLogin(login);
            Validator.matchPassword(password);
            Validator.matchEmail(email);
            password = Hasher.hashBySha1(password);
            if (!daoFactory.getAdminDao().findAdminByLogin(login)) {
                client = new Client(name, surname, login, password, email, "active", 0.0);
                return daoFactory.getClientDao().addClient(client);
            }
        } catch (ValidatorException | NumberFormatException | DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Client Service: finish SignUp");
        return null;
    }

    /**
     * @param clientLogin
     * @param clientPassword
     * @return Client
     */
    @Override
    public Client signIn(String clientLogin, String clientPassword) {
        LOGGER.log(Level.DEBUG, "Client Service: start SignIn");
        try {
            Validator.isNull(clientLogin, clientPassword);
            Validator.isEmptyString(clientLogin, clientPassword);
            Validator.matchLogin(clientLogin);
            Validator.matchPassword(clientPassword);
            clientPassword = Hasher.hashBySha1(clientPassword);
            LOGGER.log(Level.DEBUG, "Client Service: finish SignIn");
            return daoFactory.getClientDao().signIn(clientLogin, clientPassword);
        } catch (DaoException | ValidatorException e) {
            return null;
        }
    }

    /**
     * @return List<Client>
     * @throws ServiceException
     */
    @Override
    public List<Client> getAllClients() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Service: Start get all clients");
        try {
            LOGGER.log(Level.DEBUG, "Client Service: Finish get all clients");
            return daoFactory.getClientDao().getAllClients();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param id
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean deleteClient(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Sevice: Delete client start");
        try {
            LOGGER.log(Level.DEBUG, "Client Service: Finish delete client");
            return daoFactory.getClientDao().deleteClient(id);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param clientId
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean changeClientStatus(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Sevice: Change client status start");
        try {
            LOGGER.log(Level.DEBUG, "Client Service: Finish change client status");
            return daoFactory.getClientDao().changeClientStatus(clientId);
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
        LOGGER.log(Level.DEBUG, "Client Service: check password start");
        try {
            Validator.isNull(password);
            Validator.isEmptyString(password);
            Validator.matchPassword(password);
            password = Hasher.hashBySha1(password);
            LOGGER.log(Level.DEBUG, "Client Service: finish check password");
            return daoFactory.getClientDao().checkPassword(password, id);
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
    public boolean findClientByLogin(String login) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Service:  find by login start");
        try {
            Validator.isNull(login);
            Validator.isEmptyString(login);
            Validator.matchLogin(login);
            LOGGER.log(Level.DEBUG, "Client Service:  find by login finish");
            return (daoFactory.getClientDao().getClientByLogin(login) != null);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param email
     * @return Client
     * @throws ServiceException
     */
    @Override
    public Client findClientByEmail(String email) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Service:  find by login start");
        try {
            Validator.isNull(email);
            Validator.isEmptyString(email);
            Validator.matchEmail(email);
            LOGGER.log(Level.DEBUG, "Client Service:  find by login finish");
            return daoFactory.getClientDao().getClientByEmail(email);
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
        LOGGER.log(Level.DEBUG, "Client Service: change password start");
        try {
            Validator.isNull(password);
            Validator.isEmptyString(password);
            Validator.matchPassword(password);
            password = Hasher.hashBySha1(password);
            LOGGER.log(Level.DEBUG, "Client Service: finish change password");
            return daoFactory.getClientDao().changePassword(password, id);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param clientId
     * @param clientPoint
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean editPoint(Integer clientId, Double clientPoint) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Service: edit points start");
        try {
            LOGGER.log(Level.DEBUG, "Client Service: finish edit points");
            return daoFactory.getClientDao().editPoint(clientId, clientPoint);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param clientId
     * @return Client
     * @throws ServiceException
     */
    @Override
    public Client getClientById(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Service: get client by id start");
        try {
            LOGGER.log(Level.DEBUG, "Client Service: finish get client by id");
            return daoFactory.getClientDao().getClientById(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }
}
