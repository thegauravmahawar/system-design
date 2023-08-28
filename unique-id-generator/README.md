# Unique ID Generator

## Requirements

- IDs must be unique.
- IDs are numerical values only.
- IDs fit into 64-bit.
- IDs are ordered by date.
- Ability to generate over 10000 unique IDs per second.

## High-Level Design

Multiple options can be used to generate unique IDs in distributed systems.

**Multi-master replication**

![Multi-master Replication](../assets/multi_master_replication.png)

This approach uses the databases' *auto_increment* feature. Instead of increasing the next ID by 1, we increase it by *k*, where k is the number of database servers in use. This solves some scalability issues because IDs can scale with the number of database servers. However, this strategy has some major drawbacks:

- Hard to scale with multiple data centers.
- IDs do not go up with time across multiple servers.
- It does not scale well when a server is added or removed.

**UUID**
