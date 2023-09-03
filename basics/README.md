# Basics

## Single Server Setup

![Single Server Setup](../assets/single_server_setup.png)

Everything running on a single server: web app, database, cache, etc.

- Users access websites through domain names, such as `api.mysite.com`.
- The IP address is returned to the browser or mobile app.
- Once the IP address is obtained, HTTP requests are sent directly to your web server. 
- The web server returns HTML pages or JSON response for rendering.

The traffic to the web server comes from the web application or the mobile application.

## An example of API response in JSON format

```json
{
    "id": 12,
    "name": "John",
    "lastName": "Smith",
    "address": {
        "city": "New York",
        "state": "NY"
    },
    "phoneNumbers": [
        "212 555-1234",
        "646 555-4567"
    ]
}
```

## Database

With growing traffic, we need multiple servers: one for web/mobile traffic, the other for the database. Separating web/mobile traffic (web tier) and database (date tier) allows them to be scaled independently.

![Web Server Database Server Setup](../assets/web_server_database_server.png)

<b>Which database to use?</b>

Non-relational databases might be the right choice if:

- Your data is unstructured, or you do not have any relational data.
- You only need to serialize and deserialize data. 
