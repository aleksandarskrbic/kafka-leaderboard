package ingestion.service.domain.parser;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import avro.events.CommentCreated;
import avro.events.PullRequestCreated;
import avro.events.ReviewCreated;
import avro.events.User;
import io.vavr.control.Try;

@Service
public class JsonParser {

    public PullRequestCreated pullRequestCreated(final JSONObject json) {
        final JSONObject jsonUser = json.getJSONObject("user");

        final User user = User.newBuilder()
            .setUsername(jsonUser.getString("login"))
            .setUrl(jsonUser.getString("url"))
            .setAvatarUrl(jsonUser.getString("avatar_url"))
            .build();

        return PullRequestCreated.newBuilder()
            .setNumber(json.getLong("number"))
            .setMerged(Try.of(() -> json.getString("merged_at")).fold(f -> false, s -> true))
            .setTitle(json.getString("title"))
            .setUser(user)
            .build();
    }

    public ReviewCreated reviewCreated(final Long pullRequestNumber, final JSONObject json) {
        return ReviewCreated.newBuilder()
            .setId(json.getLong("id"))
            .setReviewer(json.getJSONObject("user").getString("login"))
            .setPullRequestNumber(pullRequestNumber)
            .build();
    }

    public CommentCreated commentCreated(final JSONObject json) {
        return CommentCreated.newBuilder()
            .setId(json.getLong("id"))
            .setUser(json.getJSONObject("user").getString("login"))
            .build();
    }
}
