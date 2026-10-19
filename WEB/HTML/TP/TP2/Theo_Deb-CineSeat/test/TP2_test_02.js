// Variables globales
let logoClickCount = 0;
let clickTimeout = null; // Stocke le minuteur de réinitialisation

function createPopcornShower() {
    // 1. Annuler le minuteur précédent si l'utilisateur clique à nouveau à temps
    clearTimeout(clickTimeout);

    // 2. Incrémenter le compteur
    logoClickCount++;

    // 3. Lancer un minuteur pour réinitialiser à 0 après 2 secondes (2000 ms) d'inactivité
    clickTimeout = setTimeout(() => {
        logoClickCount = 0;
        console.log("Compteur réinitialisé par manque de rapidité !");
    }, 2000); // Vous pouvez modifier 2000 par le temps souhaité en millisecondes

    // 4. Si on n'a pas encore atteint les 5 clics, on s'arrête là
    if (logoClickCount < 5) {
        return; 
    }

    // --- DÉCLENCHEMENT DE LA PLUIE (5 clics atteints !) ---
    
    // On annule le minuteur en cours et on remet le compteur à zéro pour la suite
    clearTimeout(clickTimeout);
    logoClickCount = 0;

    const popcornImages = [
        'popcorn1.png',
        'popcorn2.png',
        'popcorn3.png',
        'popcorn4.png'
    ];

    const totalPopcorns = 60; 

    for (let i = 0; i < totalPopcorns; i++) {
        const popcorn = document.createElement('img');
        const randomImage = popcornImages[Math.floor(Math.random() * popcornImages.length)];
        
        popcorn.src = randomImage;
        popcorn.classList.add('popcorn-particle');

        const randomX = Math.random() * window.innerWidth;
        popcorn.style.left = `${randomX}px`;

        const duration = Math.random() * 2 + 1.5;
        const delay = Math.random() * 1;
        const drift = (Math.random() - 0.5) * 200;
        const rotation = (Math.random() - 0.5) * 720;

        popcorn.style.animationDuration = `${duration}s`;
        popcorn.style.animationDelay = `${delay}s`;
        popcorn.style.setProperty('--drift', `${drift}px`);
        popcorn.style.setProperty('--rotation', `${rotation}deg`);

        document.body.appendChild(popcorn);

        setTimeout(() => {
            popcorn.remove();
        }, (duration + delay) * 1000);
    }
}
