docker ps

docker start quest-boot quest-db quest-phpmyadmin

docker network inspect quest

docker exec -it quest-boot bash

export SPRING_DATASOURCE_URL=jdbc:mysql://quest-db/projet_quest

java -jar target/quest-boot-0.0.1-SNAPSHOT.jar

-----

docker exec -it quest-db mysql -uroot -p

use projet_quest;

show tables;

desc matiere;
