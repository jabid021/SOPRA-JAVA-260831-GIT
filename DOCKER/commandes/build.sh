# Builder une image à partir d'un Dockerfile (se placer dans le répertoire du Dockerfile)
docker build -t nom-image:version .

# Builder une image à partir d'un Dockerfile spécifique
docker build -t nom-image:version /chemin/vers/Dockerfile

# Nettoyer les images du build si besoin
docker image prune -f
