FROM openjdk:8-alpine
WORKDIR /opt/redis
COPY target/spring-redis-0.0.1-SNAPSHOT.jar ./spring-redis.jar
CMD ["java", "-jar", "spring-redis.jar", "-Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=10020"]
