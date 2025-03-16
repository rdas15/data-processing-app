# data-processing-app
**Description**: A sample event based data processing and search app

- This app has 3 main components
  - Data Ingestion
  - Data Processor
  - Notification Service
  - Search Service
- For simplicity all have been clubbed into 1 spring boot application. Ideally they should be separate microservices.
- Refer to the swagger definition for the API details (http://localhost:8080/swagger-ui/index.html)
  - Data ingestion is via a POST api (api/v1/data/ingest). Samples of the data to post can be found in data folder.
  - After ingestion the ingestion service sends a message to activemq (queue) to notify data processor
  - Data processor processes the data and sends a message to activemq (queue) to notify notification service
  - Notification Service just logs the notification
  - Data is saved in a in memory database (H2) and OpenSearch
  - The search service can be used to search the data in OpenSearch (/api/v1/search/fts)
  - docker-compose file consists of 
    - activemq
    - opensearch
    - data-processor-app
  - Unit tests have been written for few of the services
  
**Steps to run the application:**
- Clone the repository
- run docker compose up --build 
- **I faced issues with the data-processor-app not starting up inside docker container, as it wasn't able to connect to OpenSearch. I tried many things but couldn't fix this issue. It runs fine if run outside the container. If you face the same issue then comment the data-processor-app service from docker-compose.yml.** 
- To run spring boot app outside docker container uncomment 2 lines from application.yml
  - `uris: ${OPENSEARCH_URL} //comment this line`
  - `# uris: https://localhost:9200 //uncomment this line`
  - `host: ${ACTIVEMQ_HOST} //comment this line`
  - `# host: localhost //uncomment this line`
- The data-processor-app can be run separately using the below command 
  - `mvn clean install
  - `java -jar target/data-processor-app-0.0.1-SNAPSHOT.jar`
- The application can be accessed at http://localhost:8080/swagger-ui/index.html
- The docker compose also contains an open search dashboard which can be accessed at http://localhost:5601/app/dev_tools#/console. You can use it to browse through indices and query data like this -
    - `GET /company-index/_search`
    - `{
        "query": {
        "match_all": {}
        }
    }`
    - `GET /company-index/_search
        {
        "query": {
            "match": {
            "name": "SAM"
            }
        }
    }`


**Tech stack used**
  - Spring Boot
  - ActiveMQ
  - OpenSearch
  - H2
  - Docker
  - Maven
  - Swagger
  - Junit
  - Mockito
  - OpenSearch
  - OpenSearch Dashboard
  - Docker Compose