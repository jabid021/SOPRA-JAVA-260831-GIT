docker run -d --name quest-db -v quest-db-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root mysql:8.4.13

docker exec -it quest-db mysql -uroot -p

show databases;

create database projet_quest;

show databases;
