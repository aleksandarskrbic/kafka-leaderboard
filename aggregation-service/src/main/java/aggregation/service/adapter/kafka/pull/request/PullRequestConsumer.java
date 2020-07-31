package aggregation.service.adapter.kafka.pull.request;

import java.util.stream.StreamSupport;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Component;
import aggregation.service.domain.pipeline.ops.Source;
import avro.events.PullRequestCreated;
import io.vavr.collection.Stream;
import kafka.commons.KafkaAvroConsumer;

@Component
public class PullRequestConsumer extends KafkaAvroConsumer<String, PullRequestCreated> implements Source<PullRequestCreated> {

    public PullRequestConsumer(final PullRequestConsumerConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Stream<PullRequestCreated> emit() {
        final ConsumerRecords<String, PullRequestCreated> poll = poll();
        return Stream.ofAll(StreamSupport.stream(poll.spliterator(), false)).map(ConsumerRecord::value);
    }
}