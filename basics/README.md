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

- Your application requires super-low latency.
- Your data is unstructured, or you do not have any relational data.
- You only need to serialize and deserialize data. 

## Vertical scaling vs. Horizontal scaling

Vertical scaling, means the process of adding more power (CPU, RAM, etc.) to your servers. Horizontal scaling, allows you to scale by adding more servers into your pool of resources.

When traffic is low, vertical scaling is a great option, and the simplicity of vertical scaling is its main advantage. However, there are limitations.

- Vertical scaling has a hard limit. It is impossible to add unlimited CPU and memory to a single server.
- Vertical scaling does not have failover and redundancy. If one server goes down, the website/app goes down with it completely.

Horizontal scaling is more desirable for large scale applications due to the limitations of vertical scaling.

Users will be unable to access the website if the web server is offline. In another scenario, if many users access the web server simultaneously and it reaches the web server's load limit, users generally experience higher response time or fail to connect to the server. 

## Load Balancer

A load balancer evenly distributes incoming traffic among web servers that are defined in a load-balanced set. 

![Load Balancer](../assets/load_balancer.png)

Users connect to the public IP of the load balancer directly. With this setup, web servers are unreachable directly by clients anymore. For better security, private IPs are used for communication between servers. A private IP is an IP address reachable only between servers in the same network; however, it is unreachable over the internet. The load balancer communicates with web servers through private IPs. 

- If server 1 goes offline, all the traffic will be routed to server 2. This prevents the website from going offline. We will also add a new healthy web server to the server pool to balance the load.
- If the website traffic grows rapidly, and two servers are not enough to handle the traffic, the load balancer can handle this problem gracefully. You only need to add more servers to the web server pool, and the load balancer automatically starts to send requests to them.

## Database replication

## Cache

## Content Delivery Network (CDN)

## Stateless Web tier

## Data centers

## Message Queues

## Logging, Metrics, and Automation

## Database Scaling

## Millions of users and beyond 
