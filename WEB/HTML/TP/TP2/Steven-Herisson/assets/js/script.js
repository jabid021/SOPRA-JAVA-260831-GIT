
// ==================================================
// NAVBAR MOBILE
// ==================================================
const links = document.querySelectorAll("nav li");

icons.addEventListener("click", () => {
    nav.classList.toggle("active");
});

links.forEach((li) => {
    li.addEventListener("click", (event) => {
        const a = li.querySelector("a");

        if (!a) return;

        nav.classList.remove("active");

        // Si le clic est directement sur le lien, le navigateur gère
        if (event.target.closest("a")) return;

        // Si le clic est sur le <li>, le lien le plus proche est déclenché.
        a.click();
    });
});

// Fermeture de la navbar si l'utilisateur cliquer à l'extérieur
document.addEventListener("click", (event) => {
    if (
        nav.classList.contains("active") &&
        !nav.contains(event.target) &&
        !icons.contains(event.target)
    ) {
        nav.classList.remove("active");
    }
});

const header = document.querySelector("header");
const headerHeight = header.getBoundingClientRect().height;

document.documentElement.style.setProperty(
    "--header-height",
    `${headerHeight}px`
);


// ==================================================
// LIGHTBOX
// ==================================================
const lightbox = document.getElementById("lightbox");
const lightboxImg = document.getElementById("lightbox-img");
const lightboxDesc = document.getElementById("lightbox-description");


document.addEventListener("click", event => {
    const img = event.target.closest("img.zoomable");
    
    if (!img) return;
    lightboxImg.src = img.src;
    lightboxImg.alt = img.alt;
    lightboxDesc.textContent = img.alt;
    
    lightboxImg.classList.toggle(
        "transparent",
        new URL(img.src).pathname.toLowerCase().endsWith(".png")
    );
    
    lightbox.classList.add("active");
});


lightbox.addEventListener("click", () => {
    lightbox.classList.remove("active");
});

document.addEventListener("keydown", (event) => {
    if (event.key === "Escape") {
        lightbox.classList.remove("active");
    }
});

// ==================================================
// MESSAGE POUR LES CURIEUX
// ==================================================
console.log(
    "%c🦔 Bienvenue dans les coulisses du Royaume des Hérissons !",
    "color: #4cb53c; font-size: 18px; font-weight: bold;"
);


// ==================================================
// LIEN VERS L'ACCUEIL
// ==================================================
const headerIconTitle = document.querySelectorAll("#nav-hedgehog, nav h1, #end-title");

const homeURL = window.location.hostname.endsWith(".github.io")
? "/hedgehog-kingdom/"
: new URL(
    window.location.pathname.includes("/pages/")
    ? "../index.html"
    : "index.html",
    window.location.href
).href;

headerIconTitle.forEach(element => {
    element.addEventListener("click", () => {
        window.location.assign(homeURL);
    });
});


// ==================================================
// ANIMATION DU HÉRISSON
// ==================================================
const hedgehog = document.querySelector("#footer-hedgehog");
const message = document.querySelector("#footer-message");
const container = document.querySelector("#hedge-text");
const sonicSound = document.querySelector("#sonic-ring");
sonicSound.volume = 0.08;

const messages = [
    "Parce que l'on a tous besoin d'un peu de douceur dans la vie",
    "Un petit hérisson peut cacher de grandes surprises !",
    "La vie est plus douce avec quelques piquants",
    "Attention, hérisson en vadrouille !"
];

const STORAGE_KEY = "hedgehog-message-index";

// Récupération du message sauvegardé s'il existe et qu'il est valide, sinon attribution du premier message (index 0)
let messageIndex = Number(localStorage.getItem(STORAGE_KEY) ?? 0);

if (!Number.isInteger(messageIndex) || messageIndex < 0 || messageIndex >= messages.length) {
    messageIndex = 0;
}

message.textContent = messages[messageIndex];

let isAnimating = false;

async function animateHedgehog() {
    
    if (isAnimating) return;
    
    isAnimating = true;
    
    hedgehog.classList.add("animating");
    hedgehog.setAttribute("aria-disabled", "true");
    
    const duration = 2000;
    const turnDuration = 350;
    
    try {
        
        // Mesure de la taille du conteneur avant animation
        const oldWidth = message.getBoundingClientRect().width;
        const computedGap = parseFloat(getComputedStyle(container).columnGap);
        const gap = Number.isFinite(computedGap) ? computedGap : 5;
        
        // Fixation de la largeur du conteneur
        container.style.width = `${oldWidth + hedgehog.offsetWidth + gap}px`;
        
        // Déplacement vers la gauche
        sonicSound.play();
        
        const moveAnimation = hedgehog.animate(
            [
                { transform: "translateX(0) scaleX(1)" },
                { transform: `translateX(-${oldWidth + gap}px) scaleX(1)` }
            ],
            {
                duration,
                easing: "linear",
                fill: "forwards"
            }
        );
        
        // Effacement progressif du texte
        const hideAnimation = animateMask(message, 100, 0, duration);
        
        await Promise.all([
            moveAnimation.finished,
            hideAnimation
        ]);
        
        // Conserver la position finale du hérisson
        hedgehog.style.transform = `translateX(-${oldWidth + gap}px) scaleX(1)`;
        
        // Supprimer l'animation devenue inutile
        moveAnimation.cancel();
        
        // Changement du texte
        messageIndex = (messageIndex + 1) % messages.length;
        message.textContent = messages[messageIndex];
        
        const newWidth = message.getBoundingClientRect().width;
        
        // Adaptation de la largeur du conteneur
        container.style.width = `${newWidth + hedgehog.offsetWidth + gap}px`;
        
        // Demi-tour
        sonicSound.play();
        await hedgehog.animate(
            [
                { transform: `translateX(-${newWidth + gap}px) scaleX(1)` },
                { transform: `translateX(-${newWidth + gap}px) scaleX(-1)` }
            ],
            {
                duration: turnDuration,
                fill: "forwards"
            }
        ).finished;
        
        // Retour vers la droite + apparition du nouveau texte
        const returnAnimation = hedgehog.animate(
            [
                { transform: `translateX(-${newWidth + gap}px) scaleX(-1)` },
                { transform: "translateX(0) scaleX(-1)" }
            ],
            {
                duration,
                easing: "linear",
                fill: "forwards"
            }
        );
        
        const showAnimation = animateMask(message, 0, 100, duration);
        
        await Promise.all([
            returnAnimation.finished,
            showAnimation
        ]);
        
        await Promise.all([
            returnAnimation.finished,
            showAnimation.finished
        ]);
        
        hedgehog.style.transform = "scaleX(-1)";
        returnAnimation.cancel();
        
        // Remise du hérisson dans son orientation initiale
        await hedgehog.animate(
            [
                { transform: "scaleX(-1)" },
                { transform: "scaleX(1)" }
            ],
            {
                duration: turnDuration,
                fill: "forwards"
            }
        ).finished;
        
        // Sauvegarde du nouveau message
        localStorage.setItem(STORAGE_KEY, messageIndex);
        
    } catch (error) {
        
        console.error("Erreur durant l'animation du hérisson :", error);
        
    } finally {
        hedgehog.getAnimations().forEach(animation => animation.cancel());
        
        hedgehog.style.transform = "";
        message.style.removeProperty("--mask-progress");
        container.style.width = "";
        
        hedgehog.classList.remove("animating");
        hedgehog.setAttribute("aria-disabled", "false");
        
        isAnimating = false;
    }
}

function animateMask(element, from, to, duration) {
    return new Promise(resolve => {
        const start = performance.now();
        
        function frame(now) {
            const progress = Math.min((now - start) / duration, 1);
            
            const value = from + (to - from) * progress;
            
            element.style.setProperty("--mask-progress", `${value}%`);
            
            if (progress < 1) {
                requestAnimationFrame(frame);
            } else {
                resolve();
            }
        }
        
        requestAnimationFrame(frame);
    });
}


// ==================================================
// ANIMATION HÉRISSON - ÉVÉNEMENTS 
// ==================================================

hedgehog.addEventListener("click", animateHedgehog);

hedgehog.addEventListener("keydown", event => {
    if (event.key === "Enter" || event.key === " ") {
        event.preventDefault();
        animateHedgehog();
    }
});