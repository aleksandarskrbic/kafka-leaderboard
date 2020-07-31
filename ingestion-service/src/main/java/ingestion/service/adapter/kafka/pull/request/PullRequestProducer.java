package ingestion.service.adapter.kafka.pull.request;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import avro.events.PullRequestCreated;
import ingestion.service.adapter.kafka.KafkaChannels;
import ingestion.service.domain.publisher.EventPublisher;
import kafka.commons.KafkaAvroProducer;

@Component
public class PullRequestProducer extends KafkaAvroProducer<String, PullRequestCreated> implements EventPublisher<PullRequestCreated> {

    private KafkaChannels channels;

    @Autowired
    public PullRequestProducer(final KafkaChannels channels, final PullRequestProducerConfiguration configuration) {
        super(configuration);
        this.channels = channels;
    }

    @Override
    public String channel() {
        return channels.pullRequestChannel();
    }

    @Override
    public void publish(final PullRequestCreated event) {
        producer.send(new ProducerRecord<>(channel(), event));
    }
}