package bsuir.pechuro.buisness.staff.service;

import bsuir.pechuro.entity.Staff;
import bsuir.pechuro.exception.service.ServiceException;

import java.util.List;

public interface IStaffService {
    boolean signUp(String staffLogin, String staffPassword) throws ServiceException;

    boolean deleteStaff(Integer id) throws ServiceException;

    Staff signIn(String staffLogin, String staffPassword);

    List<Staff> getAllStaff() throws ServiceException;

    boolean checkPassword(String password, Integer id) throws ServiceException;

    boolean changePassword(String password, Integer id) throws ServiceException;

    boolean findStaffByLogin(String login) throws ServiceException;
}
