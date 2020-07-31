package ingestion.service.adapter.kafka.comment;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import avro.events.CommentCreated;
import ingestion.service.adapter.kafka.KafkaChannels;
import ingestion.service.adapter.kafka.pull.request.PullRequestProducerConfiguration;
import ingestion.service.domain.publisher.EventPublisher;
import kafka.commons.KafkaAvroProducer;

@Component
public class CommentProducer extends KafkaAvroProducer<String, CommentCreated> implements EventPublisher<CommentCreated> {

    private KafkaChannels channels;

    @Autowired
    public CommentProducer(final KafkaChannels channels, final PullRequestProducerConfiguration configuration) {
        super(configuration);
        this.channels = channels;
    }

    @Override
    public String channel() {
        return channels.commentChannel();
    }

    @Override
    public void publish(final CommentCreated event) {
        producer.send(new ProducerRecord<>(channel(), event));
    }
}
