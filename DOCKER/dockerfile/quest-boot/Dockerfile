FROM eclipse-temurin:21-jdk

WORKDIR /app

ENV SPRING_DATASOURCE_URL=jdbc:mysql://quest-db/projet_quest

COPY ./target/*.jar quest-boot.jar

CMD [ "java", "-jar", "quest-boot.jar" ]

EXPOSE 8080
