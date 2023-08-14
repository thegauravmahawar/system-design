# URL Shortener

## Use Cases

- URL Shortening: Long URL => Short URL
- URL Redirecting: Short URL => Long URL
- High Availability, Scalability, and Fault Tolerance

## Back of the Envelope Estimation

- Write Operation: 100 million URLs/day
- Write Operation/sec: 100 million/24/3600 = 1160
- Read Operation: Assuming ratio of Read to Write as 10:1, read operation/sec: 1160 * 10 = 11600
- Assuming service will run for 10 years, this means we must support 100 million * 365 * 10 = 365 billion records.
- Assume average URL length is 100.
- Storage requirement over 10 years: 365 billion * 100 bytes = 36.5 TB.

## API Endpoints
