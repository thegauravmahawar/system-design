package urlshortenerapi.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import urlshortenerapi.dao.Url;
import urlshortenerapi.exceptions.NotFoundException;
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

    @GetMapping(value = "/api/v1/{shortUrl}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void redirectUrl(@PathVariable(value = "shortUrl") String shortUrl,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws NotFoundException {
        Url url = urlService.getUrl(shortUrl);
        response.setStatus(302);
        response.setHeader("Location", url.getLongUrl());
    }
}
