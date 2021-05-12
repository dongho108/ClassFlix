package dongho.classflix.domain;

public class NotEqualReviewNum extends RuntimeException {
    public NotEqualReviewNum() {
        super();
    }

    public NotEqualReviewNum(String message) {
        super(message);
    }

    public NotEqualReviewNum(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEqualReviewNum(Throwable cause) {
        super(cause);
    }
}
