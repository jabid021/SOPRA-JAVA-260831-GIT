# Récupérer une image du Registry Docker (Docker Hub) en local (sans tag, c'est "latest" qui sera utilisé)
docker pull nom-image:tag

# Exécuter un nouveau container à partir d'une image (sans tag, "latest" sera utilisé)
# > Si l'image n'existe pas sur le poste local, Docker fera un pull
docker run nom-image:tag

# Exécuter un nouveau container avec un binding de port
docker run --publish port_local:port_container nom-image:tag
docker run -p port_local:port_container nom-image:tag

# Exécuter un nouveau container en lui donnant un nom
docker run --name nom-container nom-image:tag

# Exécuter un nouveau container en arrière-plan (détaché du terminal)
docker run -d nom-image:tag

# Exécuter un nouveau container avec un binding de volume
docker run --volume nom-volume:/chemin/vers/repertoire/container nom-image:tag
docker run -v nom-volume:/chemin/vers/repertoire/container nom-image:tag

docker run --volume /chemin/local:/chemin/vers/repertoire/container nom-image:tag
docker run -v /chemin/local:/chemin/vers/repertoire/container nom-image:tag

# Exécuter un nouveau container avec un réseau
docker run --network nom-reseau nom-image:tag

# Copier un fichier du disque local vers le container
docker cp /chemin/local/fichier.txt nom-container:/chemin/vers/destination

# Copier un fichier du container vers le disque local
docker cp nom-container:/chemin/du/fichier.txt /chemin/local

# Exécuter une commande sur un container
docker exec nom-container command
docker exec id-container command

# Exécuter la commande bash (ou sh ou autre) sur un container, en interaction utilisateur
docker exec -it nom-container sh
docker exec -it id-container sh

# Inspecter un container
docker inspect nom-container
docker inspect id-container

# Arrêter un container
docker stop nom-container
docker stop id-container

# Démarrer un container
docker start nom-container
docker start id-container

# Redémarrer un container
docker restart nom-container
docker restart id-container

# Supprimer un container
docker rm nom-container
docker rm id-container

# Supprimer un container démarré
docker rm nom-container -f
docker rm id-container -f
