package dongho.classflix.domain;

public class NotEnoughReviewException extends RuntimeException {
    public NotEnoughReviewException() {
        super();
    }

    public NotEnoughReviewException(String message) {
        super(message);
    }

    public NotEnoughReviewException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughReviewException(Throwable cause) {
        super(cause);
    }
}
