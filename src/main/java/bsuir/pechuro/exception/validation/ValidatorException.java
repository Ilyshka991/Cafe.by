package bsuir.pechuro.exception.validation;

public class ValidatorException extends Exception {
    public ValidatorException() {
        super();
    }

    public ValidatorException(String msg) {
        super(msg);
    }

    public ValidatorException(String msg, Exception e) {
        super(msg, e);
    }

    public ValidatorException(Exception e) {
        super(e);
    }
}
