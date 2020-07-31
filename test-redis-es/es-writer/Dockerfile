FROM openjdk:8-alpine
WORKDIR /opt/redis
COPY target/es-writer-0.0.1-SNAPSHOT.jar ./es-writer.jar
CMD ["java", "-jar", "es-writer.jar", "-Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=10021"]
