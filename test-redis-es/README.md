Start application

1. Run mvn clean install in both spring boot application
2. From docker-compose.yml folder run command: `docker-compose up --build`.

You should see 3 containers up and running: 1 redis which can be accessed in redis-cli by typing the command: `docker exec -it <container_id> redis-cli`
and 2 containers for each application.  
