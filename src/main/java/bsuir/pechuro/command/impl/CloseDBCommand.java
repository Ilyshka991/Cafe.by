package bsuir.pechuro.command.impl;

import bsuir.pechuro.buisness.database.ICloseDB;
import bsuir.pechuro.buisness.database.impl.CloseDB;
import bsuir.pechuro.command.ICloseDBCommand;
import bsuir.pechuro.exception.service.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class CloseDBCommand implements ICloseDBCommand {
    private static final Logger LOGGER = Logger.getLogger(CloseDBCommand.class);


    @Override
    public void closeDB() {
        try {
            ICloseDB closeDB = new CloseDB();
            closeDB.closeConnections();
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, "Problem with closing Data Base:" + e.getMessage());
        }
    }
}
