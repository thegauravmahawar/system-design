package urlshortenerapi.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import urlshortenerapi.dao.Url;
import urlshortenerapi.exceptions.NotFoundException;
import urlshortenerapi.repositories.UrlRepository;
import urlshortenerapi.resources.UrlResource;

import java.util.Objects;

import static java.lang.String.format;

@Service
public class UrlService {

    private static final String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final UrlRepository urlRepository;
    private final UniqueIdService uniqueIdService;

    public UrlService(UrlRepository urlRepository, UniqueIdService uniqueIdService) {
        this.urlRepository = urlRepository;
        this.uniqueIdService = uniqueIdService;
    }

    @Transactional(rollbackFor = {Throwable.class})
    public UrlResource shorten(String longUrl) {
        Url url = urlRepository.findByLongUrl(longUrl);
        if (Objects.nonNull(url)) {
            return new UrlResource(url);
        }
        Integer id = uniqueIdService.getId();
        String shortUrl = shorten(id);
        url = new Url(id, shortUrl, longUrl);
        url = urlRepository.save(url);
        return new UrlResource(url);
    }

    private static String shorten(long id) {
        if (id < 62) {
            return Character.toString(CHARACTERS.charAt((int) id));
        }

        long value = id;
        StringBuilder sb = new StringBuilder();

        while (value != 0) {
            int remainder = (int) (value % 62);
            value = (value - remainder) / 62;
            sb.append(CHARACTERS.charAt(remainder));
        }

        return sb.toString();
    }

    public Url getUrl(String shortUrl) throws NotFoundException {
        Url url = urlRepository.findByShortUrl(shortUrl);
        if (Objects.isNull(url)) {
            throw new NotFoundException(format("Short URL %s is invalid.", shortUrl), "NOT FOUND");
        }
        return url;
    }
}
