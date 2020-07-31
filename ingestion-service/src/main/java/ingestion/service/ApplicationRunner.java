package ingestion.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import avro.events.CommentCreated;
import avro.events.PullRequestCreated;
import avro.events.ReviewCreated;
import ingestion.service.adapter.http.HttpClient;
import ingestion.service.domain.exception.RateLimitException;
import ingestion.service.domain.parser.JsonParser;
import ingestion.service.domain.publisher.EventPublisher;
import ingestion.service.domain.service.CommentIngestion;
import ingestion.service.domain.service.PullRequestIngestion;

@Service
public class ApplicationRunner {

    private final JsonParser parser;
    private final HttpClient httpClient;
    private final EventPublisher<PullRequestCreated> pullRequestPublisher;
    private final EventPublisher<ReviewCreated> reviewPublisher;
    private final EventPublisher<CommentCreated> commentPublisher;

    @Autowired
    public ApplicationRunner(
        final JsonParser parser,
        final HttpClient httpClient,
        final EventPublisher<PullRequestCreated> pullRequestPublisher,
        final EventPublisher<ReviewCreated> reviewPublisher,
        final EventPublisher<CommentCreated> commentPublisher
    ) {
        this.parser = parser;
        this.httpClient = httpClient;
        this.pullRequestPublisher = pullRequestPublisher;
        this.reviewPublisher = reviewPublisher;
        this.commentPublisher = commentPublisher;
    }

    public void run() {
        final ExecutorService executorService = Executors.newFixedThreadPool(3);

        final PullRequestIngestion closedPullRequestIngestion = new PullRequestIngestion(
            "closed",
            1,
            parser,
            httpClient,
            pullRequestPublisher,
            reviewPublisher
        );

        final PullRequestIngestion openedPullRequestIngestion = new PullRequestIngestion(
            "opened",
            1,
            parser,
            httpClient,
            pullRequestPublisher,
            reviewPublisher
        );
        
        final CommentIngestion commentIngestion = new CommentIngestion(1, parser, httpClient, commentPublisher);

        executorService.submit(() -> {
            try {
                closedPullRequestIngestion.run();
            } catch (final RateLimitException exception) {
                System.exit(1);
            }
        });

        executorService.submit(() -> {
            try {
                openedPullRequestIngestion.run();
            } catch (final RateLimitException exception) {
                System.exit(1);
            }
        });

        executorService.submit(() -> {
            try {
                commentIngestion.run();
            } catch (final RateLimitException exception) {
                System.exit(1);
            }
        });
    }
}
