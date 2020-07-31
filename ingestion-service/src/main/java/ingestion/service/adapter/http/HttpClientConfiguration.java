package ingestion.service.adapter.http;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("http.client")
public class HttpClientConfiguration {

    private String username;
    private String password;

    public String username() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String password() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
