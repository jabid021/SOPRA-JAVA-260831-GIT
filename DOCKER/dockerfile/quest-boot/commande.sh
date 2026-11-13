docker rm -f quest-boot

docker run -d --name quest-boot -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://quest-db/projet_quest --network quest quest:boot

docker logs quest-boot -f --since 2m