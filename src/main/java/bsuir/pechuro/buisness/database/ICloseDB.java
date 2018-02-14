package bsuir.pechuro.buisness.database;

import bsuir.pechuro.exception.service.ServiceException;

public interface ICloseDB {

    void closeConnections() throws ServiceException;
}
