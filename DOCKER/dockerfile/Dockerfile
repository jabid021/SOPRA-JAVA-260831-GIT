
# Image de base à partir de laquelle notre nouvelle image sera créée
# FROM nom-image:tag
FROM nginx

# Exécuter des commandes au moment de la fabrication de l'image
# !!! Il ne faut pas que ces commandes cherchent à entrer en interaction avec l'utilisateur : sinon ça marche pas
# RUN commande

RUN apt update
RUN apt install -y vim nano

# Répertoire dans lequel on se place
# WORKDIR repertoire_travail
WORKDIR /usr/share/nginx/html

# Copier un ou plusieurs fichiers / répertoires du local vers l'image
# COPY source_relative_locale destination_image

# Copier TOUT le répertoire (Dockerfile compris) vers la destination
# COPY . .

# COPY index.html /usr/share/nginx/html/
# COPY index.html /usr/share/nginx/html/index.html
# COPY index.html index.html
COPY index.html .


# ENTRYPOINT ET CMD sont des commandes qui seront exécutées au moment du démarrage du container

# ENTRYPOINT ne PEUT PAS être modifié dans la commande docker run
# ENTRYPOINT [ "commande", "-option1", "-option2", "arg3" ]

# CMD PEUT être modifié dans la commande docker run
# CMD [ "commande", "-option1", "-option2", "arg3" ]

# CMD [ "java", "-jar", "blabla.jar" ]

# CMD [ "nginx", "-g", "daemon off;" ]

# Description Docker pour prévenir quel(s) port(s) sont utilisé(s) dans le container
# EXPOSE port

EXPOSE 80
