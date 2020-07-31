package aggregation.service.adapter.kafka.comment;

import java.util.stream.StreamSupport;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Component;
import aggregation.service.domain.pipeline.ops.Source;
import avro.events.CommentCreated;
import io.vavr.collection.Stream;
import kafka.commons.KafkaAvroConsumer;

@Component
public class CommentConsumer extends KafkaAvroConsumer<String, CommentCreated> implements Source<CommentCreated> {

    public CommentConsumer(final CommentConsumerConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Stream<CommentCreated> emit() {
        final ConsumerRecords<String, CommentCreated> poll = poll();
        return Stream.ofAll(StreamSupport.stream(poll.spliterator(), false)).map(ConsumerRecord::value);
    }
}
