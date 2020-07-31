package aggregation.service.domain.model;

public class Aggregate {

    private String username;
    private String url;
    private String avatarUrl;
    private Long points;

    public Aggregate() {
    }

    public Aggregate(final String username, final String url, final String avatarUrl, final Long points) {
        this.username = username;
        this.url = url;
        this.avatarUrl = avatarUrl;
        this.points = points;
    }

    public Aggregate update(final Long points) {
        this.points += points;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(final String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(final Long points) {
        this.points = points;
    }
}
