package urlshortenerapi.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import urlshortenerapi.models.ApiResponseSuccess;
import urlshortenerapi.resources.UrlResource;
import urlshortenerapi.services.UrlService;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/api/v1/data/shorten", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseSuccess shortenUrl(@RequestParam String longUrl,
                                         HttpServletRequest request) {
        UrlResource url = urlService.shorten(longUrl);
        return new ApiResponseSuccess(url);
    }
}
