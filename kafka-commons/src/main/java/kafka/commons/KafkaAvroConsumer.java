package kafka.commons;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import kafka.commons.configuration.KafkaAvroConsumerConfiguration;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;

public class KafkaAvroConsumer<K, V> {

    private static final String KEY_DESERIALIZER = StringDeserializer.class.getCanonicalName();
    private static final String VALUE_DESERIALIZER = KafkaAvroDeserializer.class.getCanonicalName();

    protected final KafkaAvroConsumerConfiguration configuration;
    protected final KafkaConsumer<K, V> consumer;
    protected final ConsumerRebalanceListener rebalanceListener;

    public KafkaAvroConsumer(final KafkaAvroConsumerConfiguration configuration) {
        configuration.add(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KEY_DESERIALIZER);
        configuration.add(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, VALUE_DESERIALIZER);
        configuration.add(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        this.configuration = configuration;
        consumer = new KafkaConsumer<>(configuration.properties());
        rebalanceListener = null;
        subscribe();
    }

    public ConsumerRecords<K, V> poll() {
        return consumer.poll(configuration.pollTimeout());
    }

    protected void subscribe() {
        if (configuration.subscribeOnCreate()) {
            if (rebalanceListener == null) {
                consumer.subscribe(Collections.singleton(configuration.topic()));
            } else {
                consumer.subscribe(Collections.singleton(configuration.topic()), rebalanceListener);
            }
        }
    }

    protected void subscribeWithRebalanceListener(final ConsumerRebalanceListener rebalanceListener) {
        consumer.subscribe(Collections.singleton(configuration.topic()), rebalanceListener);
    }
}
