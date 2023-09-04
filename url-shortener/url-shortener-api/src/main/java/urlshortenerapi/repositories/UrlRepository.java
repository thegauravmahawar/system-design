package urlshortenerapi.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import urlshortenerapi.dao.Url;

@Repository
public interface UrlRepository extends ListCrudRepository<Url, Long> {

    Url findByLongUrl(String longUrl);

    Url findByShortUrl(String shortUrl);
}
