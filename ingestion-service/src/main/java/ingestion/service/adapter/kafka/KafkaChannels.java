package ingestion.service.adapter.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("kafka.channels")
public class KafkaChannels {

    private String commentChannel;
    private String pullRequestChannel;
    private String reviewChannel;

    public String commentChannel() {
        return commentChannel;
    }

    public void setCommentChannel(final String commentChannel) {
        this.commentChannel = commentChannel;
    }

    public String pullRequestChannel() {
        return pullRequestChannel;
    }

    public void setPullRequestChannel(final String pullRequestChannel) {
        this.pullRequestChannel = pullRequestChannel;
    }

    public String reviewChannel() {
        return reviewChannel;
    }

    public void setReviewChannel(final String reviewChannel) {
        this.reviewChannel = reviewChannel;
    }
}
