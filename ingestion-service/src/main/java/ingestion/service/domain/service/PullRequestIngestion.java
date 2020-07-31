package ingestion.service.domain.service;

import java.util.ArrayList;
import java.util.List;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;
import avro.events.PullRequestCreated;
import avro.events.ReviewCreated;
import ingestion.service.adapter.http.HttpClient;
import ingestion.service.domain.exception.RateLimitException;
import ingestion.service.domain.parser.JsonParser;
import ingestion.service.domain.publisher.EventPublisher;

public class PullRequestIngestion {

    private static final String SINCE = "2018-01-01T00:00:00Z";
    private static final String PULLS_URL = "https://api.github.com/repos/apache/kafka/pulls?state=%sd&since=%s&per_page=100&page=%d";
    private static final String REVIEW_URL = "https://api.github.com/repos/apache/kafka/pulls/%s/reviews";

    private int page = 1;
    private final String status;
    private final JsonParser parser;
    private final HttpClient httpClient;
    private final EventPublisher<PullRequestCreated> pullRequestPublisher;
    private final EventPublisher<ReviewCreated> reviewPublisher;

    public PullRequestIngestion(
        final String status,
        final int page,
        final JsonParser parser,
        final HttpClient httpClient,
        final EventPublisher<PullRequestCreated> pullRequestPublisher,
        final EventPublisher<ReviewCreated> reviewPublisher
    ) {
        this.status = status;
        this.page = page;
        this.parser = parser;
        this.httpClient = httpClient;
        this.pullRequestPublisher = pullRequestPublisher;
        this.reviewPublisher = reviewPublisher;
    }

    public void run() throws RateLimitException {
        while (true) {
            final String url = String.format(PULLS_URL, status, SINCE, page);

            final List<PullRequestCreated> pullRequests = httpClient.get(url)
                .map(HttpResponse::getBody)
                .map(JsonNode::getArray)
                .map(this::extractPullRequests)
                .getOrElseThrow(() -> new RateLimitException("API rate limit exceeded!"));

            pullRequests.forEach(pullRequest -> {
                pullRequestPublisher.publish(pullRequest);
                extractAndPublishReviews(pullRequest.getNumber());
            });

            page++;
        }
    }

    private List<PullRequestCreated> extractPullRequests(final JSONArray pullRequestsJson) {
        final List<PullRequestCreated> pullRequests = new ArrayList<>();
        for (int i = 0; i < pullRequestsJson.length(); i++) {
            final JSONObject jsonObject = pullRequestsJson.getJSONObject(i);
            final PullRequestCreated pullRequest = parser.pullRequestCreated(jsonObject);
            pullRequests.add(pullRequest);
        }

        return pullRequests;
    }

    private void extractAndPublishReviews(final Long pullRequestNumber) {
        final String reviewsUrl = String.format(REVIEW_URL, pullRequestNumber);
        httpClient.get(reviewsUrl)
            .map(HttpResponse::getBody)
            .map(JsonNode::getArray)
            .forEach(reviewArray -> {
                for (int j = 0; j < reviewArray.length(); j++) {
                    final JSONObject jsonReview = reviewArray.getJSONObject(j);
                    final ReviewCreated reviewCreated = parser.reviewCreated(pullRequestNumber, jsonReview);
                    reviewPublisher.publish(reviewCreated);
                }
            });
    }
}
