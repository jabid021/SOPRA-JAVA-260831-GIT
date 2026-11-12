docker network create quest

docker network connect quest quest-db
docker network connect quest quest-boot

docker run -d --name quest-phpmyadmin -p 81:80 --network quest -e PMA_HOST=quest-db phpmyadmin
