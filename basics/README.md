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

With growing traffic, we need multiple servers: one for web/mobile traffic, the other for the database. Separating web/mobile traffic (web tier) and database (data tier) allows them to be scaled independently.

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

A master database generally only supports write operations. A slave database gets copies of the data from the master database and only supports read operations. Most applications require a much higher ratio of reads to writes; thus, the number of slave databases in a system is usually larger than the number of master databases.

![Database Replication](../assets/database_replication.png)

- If only one slave database is available and it goes offline, read operations will be directed to the master database temporarily.
- If the master database goes offline, a slave database will be promoted to be the new master. All the database operations will be temporarily executed on the new master database.

![Multi Server Multi Database](../assets/multi_server_multi_database.png)

## Cache

A cache is a temporary storage area that stores the result of expensive responses or frequently accessed data in memory so that subsequent requests are served more quickly. The application performance is greatly affected by calling the database repeatedly. The cache can mitigate this problem.

![Cache](../assets/cache.png)

**Considerations for using cache**

- Mitigating failures: A single cache server represents a single point of failure (SPOF). As a result, multiple cache servers across different data centers are recommended to avoid SPOF. Another recommended approach is to overprovision the required memory by certain percentages.
- Eviction Policy: Once the cache is full, any requests to add items to the cache might cause existing items to be removed. This is called cache eviction. Least-recently used (LRU) is the most popular cache eviction policy. Other eviction policies, such as the Least Frequently Used (LFU) or First In First Out (FIFO), can be adopted to satisfy use cases.

## Content Delivery Network (CDN)

A CDN is a network of geographically dispersed servers used to deliver static content. CDN servers cache static content like images, videos, CSS, JavaScript files, etc.

![CDN Load Time](../assets/cdn_load_time.png)

![CDN Workflow](../assets/cdn_workflow.png)

- User A tries to get `image.png` by using an image URL. The URL's domain is provided by the CDN provider.
- If the CDN server does not have `image.png` in the cache, the CDN server requests the file from the origin, which can be a web server or online storage like AWS S3.
- The origin returns `image.png` to the CDN server, which includes optional HTTP header Time-to-Live (TTL) which describes how long the image is cached.
- The CDN caches the image and returns it to User A. The image remains cached in the CDN until the TTL expires.
- User B sends a request to get the same image.
- The image is returned from the cache as long as the TTL has not expired.

**Considerations for using a CDN**

- Cost: CDNs are run by third-party providers, and you are charged for data transfers in and out of the CDN. Caching infrequently used assets provides no significant benefits so you should consider moving them out of the CDN.
- Setting an appropriate cache expiry: The cache expiry time should neither be too long nor too short. If it is too long, the content might no longer be fresh. If it is too short, it can cause repeat reloading of content from origin servers to the CDN.
- CDN fallback: You should consider how your website/application copes with CDN failure. If there is a temporary CDN outage, clients should be able to detect the problem and request resources from the origin.
- Invalidating files: You can remove a file from the CDN before it expires by performing one of the following operations:
  - Invalidate the CDN object using APIs provided by CDN vendors.
  - Use object versioning to serve a different version of the object. 

## Stateless Web tier

A stateful server remembers client data (state) from one request to the next. A stateless server keeps no state information.

**Stateful architecture**

![Stateful Architecture](../assets/stateful_architecture.png)

User A's session data and profile image are stored in Server 1. To authenticate User A, HTTP requests must be routed to Server 1. If a request is sent to other servers like Server 2, authentication would fail because Server 2 does not contain User A's session data. 

The issue is that every request from the same client must be routed to the same server. This can be done with sticky sessions in most load balancers; however, this adds the overhead of adding and removing servers. It is also challenging to handle server failures.

**Stateless architecture**

![Stateless Architecture](../assets/stateless_architecture.png)

In stateless architecture, HTTP requests from users can be sent to any web servers, which fetch state data from a shared data store. State data is stored in a shared data store and kept out of web servers. 

## Data centers

![Two Data Centers Geo DNS](../assets/two_data_centers_geo_dns.png)

In normal operation, users are geoDNS-routed, also known as geo-routed, to the closest data center, with a split traffic of *x%* in US-East and *(100 - x)%* in US-West. geoDNS is a DNS service that allows domain names to be resolved to IP addresses based on the location of a user.

In the event of any significant data center outage, we direct all traffic to a healty data center.

Several technical challenges must be resolved to achieve multi-data center setup:

- Traffic redirection: Effective tools are needed to direct traffic to the correct data center. GeoDNS can be used to direct traffic to the nearest data center depending on where a user is located.
- Data synchronization: Users from different regions could use differnt local databases or caches. In failover cases, traffic might be routed to a data center where data is unavailable.
- Test and deployment: With multi-data center setup, it is important to test your website/application at different locations.

## Message Queues

A message queue is a durable component, stored in memory, that supports asynchronous communication. It serves as a buffer and distributes asynchronous requests. Input services, called producers/publishers, create messages, and publish them to a message queue. Other services or servers, called consumers/subscribers, connect to the queue, and perform actions defined by the messages.

![Message Queue](../assets/message_queue.png)

Decoupling makes the message queue a preferred architecture for building a scalable and reliable application. With the message queue, the producer can post a message to the queue when the consumer is unavailable to process it. The consumer can read messages from the queue even when the producer is unavailable. 

## Logging, Metrics, and Automation

**Logging**: Monitoring error logs is important because it helps to identify errors and problems in the system. You can monitor error logs at per server level or use tools to aggregate them to a centralized service for easy search and viewing.

**Metrics**: Collecting different types of metrics help us to gain business insights and understand the health status of the system. 

- Host level metrics: CPU, Memory, disk I/O, etc.
- Aggregated level metrics: for example, performance of the entire database tier, cache tier, etc.
- Key business metrics: daily active users, retention, revenue, etc.

**Automation**: When a system gets big and complex, we need to build or leverage automation tools to improve productivity. Continuous integration is a good practice, in which each code check-in is verified through automation, allowing teams to detect problems early.

## Database Scaling

**Vertical Scaling**

Vertical scaling, also known as scaling up, is the scaling by adding more power (CPU, RAM, disk, etc.) to an existing machine.

**Horizontal Scaling**

Horizontal scaling, also known as sharding, is the practice of adding more servers.

![Vertical vs Horizontal Scaling](../assets/vertical_vs_horizontal_scaling.png)

Sharding separates large databases into smaller, more easily managed parts called shards. Each shard shares the same schema, though the actual data on each shard is unique to the shard.

![Shard 1](../assets/shard_1.png)

User data is allocated to a database server based on user IDs. Anytime you access data, a hash function is used to find the corresponding shard. In the example, *user_id % 4* is used as the hash function. If the result is equal to 0, shard 0 is used to store and fetch data. If the result is 1, shard 1 is used.

![Shard 2](../assets/shard_2.png)

The most important factor to consider when implementing a sharding strategy is the choice of the sharding key. Sharding key (known as a partition key) consists of one or more columns that determine how data is distributed. 

**Resharding data**: Resharding data is needed when:

- A single shard could no longer hold more data due to rapid growth.
- Certain shards might experience shard exhaustion faster than others due to uneven data distribution. When shard exhaustion happens, it requires updating the sharding function and moving data around.

**Celebrity problem**: This is also called a hotspot key problem. Excessive access to a specific shard could cause server overload. For social applications, that shard will be overwhelmed with read operations. To solve this problem, we may need to allocate a shard for each celebrity. Each shard might even require further partition.

**Join and de-normalization**: Once a database has been sharded across multiple servers, it is hard to perform join operations across database shards. A common workaround is to de-normalize the database so that queries can be performed in a single table.

