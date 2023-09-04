package urlshortenerapi.resources;

import urlshortenerapi.dao.Url;

import java.time.format.DateTimeFormatter;

public class UrlResource extends Resource<Url> {

    public UrlResource(Url url) {
        super(url);
    }

    public UrlResource() {
        super(new Url());
    }

    public String getShortUrl() {
        return getModel().getShortUrl();
    }

    public String getLongUrl() {
        return getModel().getLongUrl();
    }

    public String getCreatedAt() {
        return getModel().getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
