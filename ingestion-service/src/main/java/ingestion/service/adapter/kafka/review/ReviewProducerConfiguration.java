package ingestion.service.adapter.kafka.review;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import kafka.commons.configuration.KafkaAvroProducerConfiguration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("kafka.producers.review")
public class ReviewProducerConfiguration extends KafkaAvroProducerConfiguration {

}
