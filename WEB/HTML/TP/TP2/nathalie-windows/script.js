/* ============================= */
/* SONS                          */
/* ============================= */

const sonsWindows = {
    "95": {
        entree: "windows95IN.mp3",
        sortie: "windows95OUT.mp3"
    },
    "xp": {
        entree: "windowsxpIN.mp3",
        sortie: "windowsxpOUT.mp3"
    },
    "vista": {
        entree: "windowsvistaIN.mp3",
        sortie: "windowsvistaOUT.mp3"
    },
    "10": {
        entree: "windows10IN.mp3",
        sortie: "windows10OUT.mp3"
    },
    "11": {
        entree: "windows11IN.mp3",
        sortie: "windows11OUT.mp3"
    }
};

let versionActuelle = null;

function jouerSon(fichier) {
    const audio = new Audio("sons/" + fichier);
    audio.volume = 0.3;

    audio.play().catch(erreur => {
        console.warn("Lecture du son impossible :", erreur);
    });
}



function jouerSonWindows(version) {
    const fichier = sonsWindows[version]?.entree;

    if (fichier) {
        jouerSon(fichier);
    }
}


/* ============================= */
/* CHANGER DE VERSION WINDOWS    */
/* ============================= */


function changerWindows(version) {
    versionActuelle = version;
    const pageTop = document.getElementById("page-top");
    const bureau = document.getElementById("bureau");
    const menu = document.getElementById("menu-demarrer");
    const texteInternet = document.getElementById("texte-internet");

    const noms = {
        "95": "The Internet",
        "xp": "Internet Explorer",
        "vista": "Internet",
        "10": "Chrome",
        "11": "Microsoft Edge"
    };

    texteInternet.textContent = noms[version];

    pageTop.style.display = "none";
    bureau.style.display = "block";

    bureau.classList.remove(
        "windows95",
        "windowsxp",
        "windowsvista",
        "windows10",
        "windows11"
    );

    bureau.classList.add("windows" + version);
    jouerSonWindows(version);

    // change nom du menu et bouton Démarrer
    //personnaliserWindows(version);

    menu.style.display = "none";
}


/* ============================= */
/* OUVRIR / FERMER LE MENU       */
/* ============================= */

function ouvrirMenu() {
    let menu = document.getElementById("menu-demarrer");
    //const menu = document.getElementById("menu-demarrer"); //son au clic ouverture
    if (menu.style.display === "block") {menu.style.display = "none"; }         // menu ouvert: on ferme
    else {
      menu.style.display = "block";                                        // menu fermé: on l'ouvre
      const son = new Audio("sons/erreur.mp3");
      son.volume = 0.3;
      son.play().catch(() => {});
      }
}

document.addEventListener("click", function(event) {
    const menu = document.getElementById("menu-demarrer");
    const bouton = document.getElementById("bouton-demarrer");

    if (
        menu.style.display === "block" &&
        !menu.contains(event.target) &&
        !bouton.contains(event.target)
    ) {
        menu.style.display = "none";
    }
});


/* ============================= */
/* RETOUR AU TOP 5               */
/* ============================= */

function retourTop() {
    let pageTop = document.getElementById("page-top");
    let bureau = document.getElementById("bureau");
    // console.log("Version quittée :", versionActuelle);
    if (
        versionActuelle !== null &&
        sonsWindows[versionActuelle] &&
        sonsWindows[versionActuelle].sortie
    ) {
        jouerSon(sonsWindows[versionActuelle].sortie);
        versionActuelle = null;
    }
    bureau.style.display = "none";    // cache le bureau
    pageTop.style.display = "block";  // affiche le top 5
    document.getElementById("menu-demarrer").style.display = "none"; // ferme le menu
}


/* ============================= */
/* HORLOGE                       */
/* ============================= */

function mettreAJourHeure() {
    let horloge = document.getElementById("horloge");
    let maintenant = new Date();
    let heures = maintenant.getHours();
    let minutes = maintenant.getMinutes();
    if (minutes < 10) {minutes = "0" + minutes;}     // ajoute un 0 devant les minutes si elles sont inférieures à 10
    horloge.textContent = heures + ":" + minutes;
}


// maj de l'heure
mettreAJourHeure();

// actualiser toutes les secondes
setInterval(mettreAJourHeure, 1000);

/* ============================= */
/* SONS la suite                 */
/* ============================= */

document.addEventListener("click", function(event) {
    if (
        event.target.closest(".icone") ||
        event.target.closest(".application")
    ) {
        const son = new Audio("sons/icone.mp3");
        son.volume = 0.3;
        son.play().catch(() => {});
    }
});
