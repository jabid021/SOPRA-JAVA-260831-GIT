# Builder l'image Docker
docker build -t jenkins .

# Exécuter le container avec le mapping du connecteur Docker de notre machine
docker run -d --name jenkins -p 8085:8080 -v /var/run/docker.sock:/var/run/docker.sock jenkins

# Se connecter au container
docker exec -it jenkins bash

# Vérifier que la commande docker est disponible
docker ps
