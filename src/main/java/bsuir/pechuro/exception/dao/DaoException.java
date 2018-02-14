package bsuir.pechuro.exception.dao;

public class DaoException extends Exception {
    private static final long serialVersionUID = 1L;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Exception exception) {
        super(exception);
    }

    public DaoException(String message, Exception exception) {
        super(message, exception);
    }
}

