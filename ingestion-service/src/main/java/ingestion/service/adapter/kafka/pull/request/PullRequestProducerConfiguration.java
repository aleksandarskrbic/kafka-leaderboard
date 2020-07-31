package ingestion.service.adapter.kafka.pull.request;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import kafka.commons.configuration.KafkaAvroProducerConfiguration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("kafka.producers.pull-request")
public class PullRequestProducerConfiguration extends KafkaAvroProducerConfiguration {

}
