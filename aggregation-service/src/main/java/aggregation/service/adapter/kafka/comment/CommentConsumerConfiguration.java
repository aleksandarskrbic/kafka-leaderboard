package aggregation.service.adapter.kafka.comment;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import kafka.commons.configuration.KafkaAvroConsumerConfiguration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("kafka.consumers.comment")
public class CommentConsumerConfiguration extends KafkaAvroConsumerConfiguration {

}
