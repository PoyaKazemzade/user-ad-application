# Simple classifieds platform 

## Overview

The **simple classifieds platform** is a simple application designed allow users to publish and manage ads for items they want to sell. The system is built using a microservices architecture, ensuring scalability, modularity, and resilience. The front-end is developed using Vue 3.

Each microservices has its own database. DBMS used is H2 with file based approach for simplicity and lightweight during development.

The system handles asynchronous communication between `adservice` (provider) and `listingservice` (consumer) using the message broker ActiveMQ.

## Prerequisites
- Java 17+
- ActiveMQ

## Features

### 1. Microservices Architecture

The application is composed of three microservices, each responsible for a specific domain of the application:
  
- **User Service (`userservice`)**
  - Manages user registration and user data.
  
- **Ad Service (`adservice`)**
  - Creation and management of ads for items (create and delete ads) aswell as ad categories.
  
- **Listing Service (`listingservice`)**
  - Management of lists of ads aswell as enabling searching and filtering for ads. For its db, it prioritizes read performance and may sacrifice some normalization and strict referential integrity in favor of speed and scalability. Keeps an updated count on how many ads exist in each ad category.

### 2. Front-End: Vue UI

The front-end is developed using Vue 3, Bootstrap-vue-next and Typescript. It aims to showcase what this project could be if it was developed further. This is a WIP.
- **Top ad categories**
  - Interface to display the top 3 ad categories with highest ad count in them, sorted in descending order.

- **List all ads**
  - Interface to display all ads for the user.
  
### 3. Deployment

#### 3.1. Backend Services (Microservices)

Each service is built using Spring Boot. To deploy them, you must run them independently on localhost.  
*Note!* Make sure ActiveMQ is up and running locally. You must then run the services in the following order:
1. `userservice`
2. `adservice`
3. `listingservice`

The services are configured to run on the following server ports:
- `userservice` = port 8085
- `adservice` = port 8189
- `listingservice` = port 8190  
  
When you start up the services in this order, the first two will generate some example data and insert them to their respective db.
  
To run:
- `mvn install`
- `mvn package`
- `mvn springboot:run`  

Swagger UI is available for `listingservice` at [http://localhost:8190/listing-service/swagger-ui/index.html](http://localhost:8190/listing-service/swagger-ui/index.html).


#### 3.2. Front-End Deployment

The Vue UI is served using Vite. 

#### Running the React UI
From the frontend directory run:

	1.	Development Mode

    ```bash
    npm install
    npm run dev
    ```

This will start the development server at http://localhost:5173 (probably, port may vary...).

### 4. Screenshots frontend
![image](https://github.com/user-attachments/assets/dd6be129-0d27-437b-8076-ea0ca7880a01)

### 5. Microservices Architecture with Messaging

    
    +--------------------+
    |                    |
    |      Vue UI        |
    |    (frontend)      |
    |                    |
    +---------+----------+
            |
            | 1. UI sends a request to service's REST API (via HTTP).
            |
            v
    +---------+----------+
    |                    |
    |   Microservice A   |  
    |   (e.g., Ad        |
    |   Service)         |
    |                    |
    +---------+----------+
            |
            | 3. Microservice A processes the request (e.g., saves data to DB).
            | 4. Microservice A publishes a message to Message Broker (ActiveMQ).
            |
            v
    +---------+----------+             +--------------------+
    |                    | 5. Message |                     |
    |  Message Broker    +------------>   Microservice B    |
    | (ActiveMQ)         |   sent to  |   (e.g., Listing    |
    |                    |  listening |   Service)          |
    +--------------------+  service   +---------+----------+
                                            6. Listening service receives message from broker.
                                            7. Processes the message and performs operations.
                                                    
    

#### 4.1 Microservices Event-driven Design (EDD)
The `listing-servicec`'s db stores the count of ads per category based on ad creation and deletion events in the `ad-service`. It uses a message queue as an asynchronous way to update (synchronize) the `listing-service` whenever data in the `ad-service` changes. That provdes these key benefits:
- Event-Driven Updates: Ideal for keeping the `listing-service`'s local db in sync with minimal delay.
- Decoupled Communication: The `ad-service` and `listing-service` don't need to be tightly coupled in terms of uptime.

The `listing-service`'s db essentially acts as a local copy of the ads data in `ad-service`, related to individual ads. It caches the ad details to provide faster access and reduce dependencies on the `ad-service` for every ad query. This can be considered a read-only copy of the ad details.
