package prolist.logics;

/**
 * Created by yli on 25/02/2016.
 * Updated by yqtian for version 2.0.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable e) {
        super(message, e);
    }
}
