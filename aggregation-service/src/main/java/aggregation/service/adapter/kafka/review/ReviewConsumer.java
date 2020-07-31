package aggregation.service.adapter.kafka.review;

import java.util.stream.StreamSupport;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Component;
import aggregation.service.domain.pipeline.ops.Source;
import avro.events.ReviewCreated;
import io.vavr.collection.Stream;
import kafka.commons.KafkaAvroConsumer;

@Component
public class ReviewConsumer extends KafkaAvroConsumer<String, ReviewCreated> implements Source<ReviewCreated> {

    public ReviewConsumer(final ReviewConsumerConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Stream<ReviewCreated> emit() {
        final ConsumerRecords<String, ReviewCreated> poll = poll();
        return Stream.ofAll(StreamSupport.stream(poll.spliterator(), false)).map(ConsumerRecord::value);
    }
}
