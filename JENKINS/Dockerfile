FROM jenkins/jenkins:lts-jdk21

# Utilisation de l'utilisateur root
USER root

# Mise à jour du gestionnaire de package système
RUN apt update

# ----- Installation de Docker (dans le container -> docker-in-docker -> dind)
RUN apt install ca-certificates curl -y
RUN install -m 0755 -d /etc/apt/keyrings
RUN curl -fsSL https://download.docker.com/linux/debian/gpg -o /etc/apt/keyrings/docker.asc
RUN chmod a+r /etc/apt/keyrings/docker.asc

RUN tee /etc/apt/sources.list.d/docker.sources <<EOF
Types: deb
URIs: https://download.docker.com/linux/debian
Suites: $(. /etc/os-release && echo "$VERSION_CODENAME")
Components: stable
Signed-By: /etc/apt/keyrings/docker.asc
EOF

RUN apt update
RUN apt install docker-ce -y
# ----- Fin d'installation de Docker

RUN ssh-keyscan github.com >> ~/.ssh/known_hosts

EXPOSE 8080
