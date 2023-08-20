# URL Shortener

## Use Cases

- URL Shortening: Long URL => Short URL
- URL Redirecting: Short URL => Long URL
- High Availability, Scalability, and Fault Tolerance

## Back of the Envelope Estimation

- Write Operation: **100 million URLs/day**
- Write Operation/Sec: **100 million/24/3600 = 1160**
- Read Operation/Sec (assuming ratio of Read to Write as 10:1): **1160 * 10 = 11600**
- Number of Records (assuming service will run for 10 years): **100 million * 365 * 10 = 365 billion records**
- Storage requirement (assuming average length of URL is 100 bytes): **365 billion * 100 bytes = 36.5 TB**

## API Endpoints

**URL Shortening**

```text
POST api/v1/data/shorten

- Request Parameter: {longUrl: longURLString}
- Return shortURL
```

**URL Redirecting**

```text
GET api/v1/{shortURL}

- Return longURL for HTTP redirection
```

## URL Redirecting

![URL Redirection Browser](../assets/url_redirection_browser.png)

Once the server receives a tinyurl request, it changes the short URL to the long URL with 301 redirect.

**301 redirect**

A 301 redirect shows that the requested URL is "permanently" moved to the long URL. Since it is permanently redirected, the browser caches the response, and subsequent requests for the same URL will not be sent to the URL shortening service. Instead, requests are redirected to the long URL directly.

If the priority is to reduce the server load, using 301 redirect makes sense as only the first request of the same URL is sent to URL shortening servers.

**302 redirect**

A 302 redirect means that the URL is "temporarily" moved to the long URL, meaning that subsequent requests for the same URL will be sent to the URL shortening service first. 

If analytics is important, 302 redirect is a better choice as it can track click rate and source of the click more easily.

## URL Shortening

## Data Model
