# Démarrer un Docker Compose
docker compose up

# Démarrer un Docker Compose avec le mode détaché
docker compose up -d

# Supprimer les containers
docker compose down

# Supprimer les containers (avec les volumes)
docker compose down -v

# Arrêter tous les containers
docker compose stop

# Démarrer tous les containers
docker compose start

# Rédémarrer tous les containers
docker compose restart

# Démarrer les containers avec le rebuild des images
docker compose up --build

# Exécuter un container du compose, en intéraction
docker compose run --rm -it nom_service
