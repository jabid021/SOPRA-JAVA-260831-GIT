# Voir la liste des images présentes
docker image ls
docker images

# Supprimer une image
docker image rm nom_image:tag
docker image rm id_image
docker image rmi nom_image:tag
docker image rmi id_image

# Récupérer une image du Docker Hub
docker pull image:tag

# Envoyer une image sur le Docker Hub
docker push image:tag
