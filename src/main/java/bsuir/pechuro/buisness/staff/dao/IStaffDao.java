package bsuir.pechuro.buisness.staff.dao;

import bsuir.pechuro.entity.Staff;
import bsuir.pechuro.exception.dao.DaoException;

import java.util.List;

public interface IStaffDao {
    boolean addStaff(Staff staff) throws DaoException;

    boolean deleteStaff(Integer id) throws DaoException;

    Staff signIn(String login, String password) throws DaoException;

    boolean findStaffByLogin(String login) throws DaoException;

    List<Staff> getAllStaff() throws DaoException;

    boolean checkPassword(String password, Integer id) throws DaoException;

    boolean changePassword(String password, Integer id) throws DaoException;
}
