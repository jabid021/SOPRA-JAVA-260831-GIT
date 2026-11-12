docker run -it --name quest-boot -p 8080:8080 -v D:/ajc-docker/quest-boot:/app -w /app eclipse-temurin:21-jdk bash

./mvnw clean package

export SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal/projet_quest

java -jar target/quest-boot-0.0.1-SNAPSHOT.jar
