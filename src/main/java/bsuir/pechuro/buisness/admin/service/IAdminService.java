package bsuir.pechuro.buisness.admin.service;

import bsuir.pechuro.entity.Admin;
import bsuir.pechuro.exception.service.ServiceException;

import java.util.List;

public interface IAdminService {
    boolean signUp(String adminLogin, String adminPassword) throws ServiceException;

    boolean deleteAdmin(Integer id) throws ServiceException;

    Admin signIn(String adminLogin, String adminPassword);

    List<Admin> getAllAdmins() throws ServiceException;

    boolean checkPassword(String password, Integer id) throws ServiceException;

    boolean changePassword(String password, Integer id) throws ServiceException;

    boolean findAdminByLogin(String login) throws ServiceException;

}