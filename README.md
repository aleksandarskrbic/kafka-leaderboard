# kafka-leaderboard

System responsible for data ingestion from GitHub API and feeding it into Kafka, data agregation and providing HTTP API for dashboard-ui.

The system consists of 3 services:
1. [Ingestion Service](https://github.com/aleksandarskrbic/kafka-leaderboard/tree/master/ingestion-service) that pulls data from GitHub API, process it, transform into Avro format and publish it to 3 Kafka topics: ```ingestion.comment```, ```ingestion.pull-request```, and ```ingestion.review```.
2. [Aggregation Service](https://github.com/aleksandarskrbic/kafka-leaderboard/tree/master/aggregation-service) consumes all Kafka topics in parallel, do some processing and aggregations, and write it into in-memory repository. Data is exposed via HTTP API. To get top 100 contrubutors sorted in descending order by points use http://localhost:8080/api/aggregate, to get data about specific contirbutor use http://localhost:8080/api/aggregate/{username}. Since non-persistent in-memory repositry is used, consumers won't commiting any offsets, so when service is restarted it will rebuild state again from all Kafka Topics from the beggining.
3. [Dashboard](https://github.com/aleksandarskrbic/kafka-leaderboard/tree/master/dashboard-ui) is simple frontend application written in React.js, that displays top 100 Apache Kafla contributors.

## How to run:
1. Run Zookeper, Kafka, and Schema Registry: ```docker-compose up``` make sure these are started properly, if not just kill process(ctr+c) and run script again
2. Run ```./run-ui.sh``` in order to build and run ```dashboard-io``` (may a few minutes)
3. In order to use GitHub API you need to set your username and access-token in ```ingestion-service/src/main/resources/application.yml``` http.client.username=your-github-username and http.client.password = your-access-token.
4. Run ```./run-services.sh``` to run ```ingestion-service``` and ```aggregation-service```
5. Go to http://localhost:3000 to see Leaderboard of ```Apache Kafka``` contributors
