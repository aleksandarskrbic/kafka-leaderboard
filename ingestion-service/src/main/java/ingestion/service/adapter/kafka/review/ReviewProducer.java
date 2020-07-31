package ingestion.service.adapter.kafka.review;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import avro.events.ReviewCreated;
import ingestion.service.adapter.kafka.KafkaChannels;
import ingestion.service.adapter.kafka.pull.request.PullRequestProducerConfiguration;
import ingestion.service.domain.publisher.EventPublisher;
import kafka.commons.KafkaAvroProducer;

@Component
public class ReviewProducer extends KafkaAvroProducer<String, ReviewCreated> implements EventPublisher<ReviewCreated> {

    private KafkaChannels channels;

    @Autowired
    public ReviewProducer(final KafkaChannels channels, final PullRequestProducerConfiguration configuration) {
        super(configuration);
        this.channels = channels;
    }

    @Override
    public String channel() {
        return channels.reviewChannel();
    }

    @Override
    public void publish(final ReviewCreated event) {
        producer.send(new ProducerRecord<>(channel(), event));
    }
}
