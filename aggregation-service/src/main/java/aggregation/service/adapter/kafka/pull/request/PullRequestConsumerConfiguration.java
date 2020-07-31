package aggregation.service.adapter.kafka.pull.request;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import kafka.commons.configuration.KafkaAvroConsumerConfiguration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("kafka.consumers.pull-request")
public class PullRequestConsumerConfiguration extends KafkaAvroConsumerConfiguration {

}
