# Voir la liste des réseaux
docker network ls

# Créer un nouveau réseau
docker network create nom-reseau

# Connecter un container existant à un réseau
docker network connect nom-reseau nom-container

# Déconnecter un container d'un réseau
docker network disconnect nom-reseau nom-container

# Supprimer un réseau
docker network rm nom-reseau
