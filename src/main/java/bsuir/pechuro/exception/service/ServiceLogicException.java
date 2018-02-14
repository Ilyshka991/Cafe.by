package bsuir.pechuro.exception.service;

public class ServiceLogicException extends Exception {
    public ServiceLogicException() {
        super();
    }

    public ServiceLogicException(String string, Exception e) {
        super(string, e);
    }

    public ServiceLogicException(String string) {
        super(string);
    }

    public ServiceLogicException(Exception e) {
        super(e);
    }
}
