package urlshortenerapi.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

//@Data
//@Entity
//@Table(name = "url")
public class Url {

    private Integer id;
    private String shortUrl;
    private String longUrl;
    private LocalDateTime createdAt;
}
