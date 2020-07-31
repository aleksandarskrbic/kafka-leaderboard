package ingestion.service.adapter.kafka.comment;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import kafka.commons.configuration.KafkaAvroProducerConfiguration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("kafka.producers.comment")
public class CommentProducerConfiguration extends KafkaAvroProducerConfiguration {

}
