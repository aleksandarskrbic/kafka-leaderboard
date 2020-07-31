package ingestion.service.domain.exception;

public class RateLimitException extends RuntimeException {

    public RateLimitException(final String message) {
        super(message);
    }
}
