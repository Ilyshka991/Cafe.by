package bsuir.pechuro.buisness.account.dao;

import bsuir.pechuro.entity.Account;
import bsuir.pechuro.exception.dao.DaoException;

public interface IAccountDao {

    Double getCashById(Integer clientId) throws DaoException;

    boolean addAccount(Account account) throws DaoException;

    boolean checkAccountNumber(Integer accountNumber) throws DaoException;

    boolean editAccount(Integer clientId, Double orderCostNew) throws DaoException;

    boolean findAccountByClientId(Integer clientId) throws DaoException;
}
