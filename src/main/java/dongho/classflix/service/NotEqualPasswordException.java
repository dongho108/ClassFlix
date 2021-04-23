package dongho.classflix.service;

public class NotEqualPasswordException extends RuntimeException {
    public NotEqualPasswordException() {
        super();
    }

    public NotEqualPasswordException(String message) {
        super(message);
    }

    public NotEqualPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEqualPasswordException(Throwable cause) {
        super(cause);
    }
}
