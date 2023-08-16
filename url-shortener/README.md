# URL Shortener

## Use Cases

- URL Shortening: Long URL => Short URL
- URL Redirecting: Short URL => Long URL
- High Availability, Scalability, and Fault Tolerance

## Back of the Envelope Estimation

- Write Operation: **100 million URLs/day**
- Write Operation/Sec: **100 million/24/3600 = 1160**
- Read Operation: Assuming ratio of Read to Write as 10:1, Read Operation/Sec: **1160 * 10 = 11600**
- Assuming service will run for 10 years, this means we must support **100 million * 365 * 10 = 365 billion records**
- Storage requirement over 10 years: **365 billion * 100 bytes = 36.5 TB** assuming average length of URL is 100

## API Endpoints

**URL Shortening**

```shell
POST api/v1/data/shorten

- Request Parameter: {longUrl: longURLString}
- Return shortURL
```

**URL Redirecting**

```shell
GET api/v1/shortURL

- Return longURL for HTTP redirection
```
