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

## URL redirecting

![URL Redirection Browser](../assets/url_redirection_browser.png)

Once the server receives a tinyurl request, it changes the short URL to the long URL with 301 redirect.
