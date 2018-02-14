package bsuir.pechuro.buisness.client.dao;

import bsuir.pechuro.entity.Client;
import bsuir.pechuro.exception.dao.DaoException;

import java.util.List;

public interface IClientDao {
    Client addClient(Client client) throws DaoException;

    boolean deleteClient(Integer id) throws DaoException;

    Client signIn(String login, String password) throws DaoException;

    Client getClientByLogin(String clientLogin) throws DaoException;

    Client getClientById(Integer clientId) throws DaoException;

    List<Client> getAllClients() throws DaoException;

    boolean changeClientStatus(Integer clientId) throws DaoException;

    boolean checkPassword(String password, Integer clientId) throws DaoException;

    boolean changePassword(String password, Integer clientId) throws DaoException;

    boolean editPoint(Integer clientId, Double clientPoint) throws DaoException;

    boolean addPoints(Double points, Integer clientId) throws DaoException;

    boolean clearPoints(Integer clientId) throws DaoException;

    Client getClientByEmail(String email) throws DaoException;
}
