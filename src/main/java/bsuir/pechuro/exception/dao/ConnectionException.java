package bsuir.pechuro.exception.dao;

public class ConnectionException extends DaoException {
    public static final long serialVersionUID = 12L;

    public ConnectionException() {
        super();
    }

    public ConnectionException(String msg) {
        super(msg);
    }

    public ConnectionException(Exception e) {
        super(e);
    }

    public ConnectionException(String msg, Exception e) {
        super(msg, e);
    }
}
