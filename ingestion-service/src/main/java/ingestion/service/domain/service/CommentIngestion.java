package ingestion.service.domain.service;

import java.util.ArrayList;
import java.util.List;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;
import avro.events.CommentCreated;
import ingestion.service.adapter.http.HttpClient;
import ingestion.service.domain.exception.RateLimitException;
import ingestion.service.domain.parser.JsonParser;
import ingestion.service.domain.publisher.EventPublisher;

public class CommentIngestion {

    private static final String COMMENTS_URL = "https://api.github.com/repos/apache/kafka/pulls/comments?per_page=100&page=%d";

    private int page = 1;
    private final JsonParser parser;
    private final HttpClient httpClient;
    private final EventPublisher<CommentCreated> commentPublisher;

    public CommentIngestion(
        final int page,
        final JsonParser parser,
        final HttpClient httpClient,
        final EventPublisher<CommentCreated> commentPublisher
    ) {
        this.page = page;
        this.parser = parser;
        this.httpClient = httpClient;
        this.commentPublisher = commentPublisher;
    }

    public void run() throws RateLimitException {
        while (true) {
            final String url = String.format(COMMENTS_URL, page);

            final List<CommentCreated> comments = httpClient.get(url)
                .map(HttpResponse::getBody)
                .map(JsonNode::getArray)
                .map(this::extractComments)
                .getOrElseThrow(() -> new RateLimitException("API rate limit exceeded!"));

            comments.forEach(commentPublisher::publish);

            page++;
        }
    }

    private List<CommentCreated> extractComments(final JSONArray commentsJson) {
        final List<CommentCreated> comments = new ArrayList<>();
        for (int i = 0; i < commentsJson.length(); i++) {
            final JSONObject jsonObject = commentsJson.getJSONObject(i);
            final CommentCreated commentCreated = parser.commentCreated(jsonObject);
            comments.add(commentCreated);
        }

        return comments;
    }
}
