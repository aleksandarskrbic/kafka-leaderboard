package ingestion.service.adapter.http;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.springframework.stereotype.Component;
import io.vavr.control.Try;

@Component
public class HttpClient {

    private final String username;
    private final String password;

    public HttpClient(final HttpClientConfiguration configuration) {
        this.username = configuration.username();
        this.password = configuration.password();
    }

    public Try<HttpResponse<JsonNode>> get(final String url) {
        if (username != null && password != null) {
            return Try.of(() -> Unirest.get(url).basicAuth(username, password).asJson());
        }

        return Try.of(() -> Unirest.get(url).asJson());
    }
}
