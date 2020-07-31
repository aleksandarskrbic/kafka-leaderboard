package ingestion.service.domain.publisher;

public interface EventPublisher<R> {

    String channel();

    void publish(R event);
}
