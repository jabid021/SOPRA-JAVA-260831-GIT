# Builder l'image Docker
docker build -t jenkins .

# Exécuter le container avec le mapping du connecteur Docker de notre machine
docker run -d --name jenkins -p 8085:8080 -v /var/run/docker.sock:/var/run/docker.sock jenkins

# Se connecter au container
docker exec -it jenkins bash

# Vérifier que la commande docker est disponible
docker ps

# Récupérer le mot de passe admin (sur le container jenkins)
cat /var/jenkins_home/secrets/initialAdminPassword


-------------

# Générer la clé SSH pour l'associer au compte GitHub
ssh-keygen -t ed25519

# Copier la clé publiquer et l'associer au compte Github
cat ~/.ssh/id_ed25519.pub

# Récupérer la clé privée et la garder de côté pour le moment
cat ~/.ssh/id_ed25519

# On peut vérifier si la connexion SSH est OK avec github.com
ssh -T git@github.com

# (si vous avez changé le chemin par défaut)
ssh -i chemin/vers/cle_ssh_privee -T git@github.com
