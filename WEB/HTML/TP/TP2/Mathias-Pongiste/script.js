"use strict";

const joueurs = [
    {
        id: "felix",
        categorie: "hommes",
        national: 1,
        mondial: 2,
        nom: "LEBRUN",
        prenom: "Félix",
        photo: "img/felix.jpg",
        photoHover: "img/felix-drole.jpg",
        fft: "https://www2.fftt.com/site/personnes/by-number?number_id=3421810"
    },
    {
        id: "alexis",
        categorie: "hommes",
        national: 2,
        mondial: 8,
        nom: "LEBRUN",
        prenom: "Alexis",
        photo: "img/alexis.jpg",
        photoHover: "img/alexis-drole.jpg",
        fft: "https://www2.fftt.com/site/personnes/by-number?number_id=3418930"
    },
    {
        id: "simon",
        categorie: "hommes",
        national: 3,
        mondial: 31,
        nom: "GAUZY",
        prenom: "Simon",
        photo: "img/simon.webp",
        photoHover: "img/simon-drole.jpg",
        fft: "https://www2.fftt.com/site/personnes/by-number?number_id=319001"
    },
    {
        id: "flavien",
        categorie: "hommes",
        national: 4,
        mondial: 23,
        nom: "COTON",
        prenom: "Flavien",
        photo: "img/flavien.jpg",
        photoHover: "img/flavien-drole.webp",
        fft: "https://www2.fftt.com/site/personnes/by-number?number_id=5969105"
    },
    {
        id: "thibault",
        categorie: "hommes",
        national: 5,
        mondial: 22,
        nom: "PORET",
        prenom: "Thibault",
        photo: "img/thibault.jpg",
        photoHover: "img/thibault-drole.webp",
        fft: "https://www2.fftt.com/site/personnes/by-number?number_id=7631897"
    },
    {
        id: "jianan",
        categorie: "femmes",
        national: 1,
        mondial: 33,
        nom: "YUAN",
        prenom: "Jianan",
        photo: "img/jianan.png",
        photoHover: "img/jianan-drole.webp",
        fft: "https://www2.fftt.com/site/personnes/by-number?number_id=5318122"
    },
    {
        id: "prithika",
        categorie: "femmes",
        national: 2,
        mondial: 41,
        nom: "PAVADE",
        prenom: "Prithika",
        photo: "img/prithika.png",
        photoHover: "img/prithika-drole.webp",
        fft: "https://www2.fftt.com/site/personnes/by-number?number_id=9316896"
    },
    {
        id: "charlotte",
        categorie: "femmes",
        national: 3,
        mondial: 66,
        nom: "LUTZ",
        prenom: "Charlotte",
        photo: "img/charlotte.png",
        photoHover: "img/charlotte-drole.jpg",
        fft: "https://www2.fftt.com/site/personnes/by-number?number_id=6720747"
    },
    {
        id: "he",
        categorie: "femmes",
        national: 4,
        mondial: null,
        mondialTexte: "Non classée",
        nom: "LI",
        prenom: "He",
        photo: "img/he.jpg",
        photoHover: "img/he-drole.webp",
        fft: "https://www2.fftt.com/site/personnes/by-number?number_id=7636536"
    },
    {
        id: "camille",
        categorie: "femmes",
        national: 5,
        mondial: 104,
        nom: "LUTZ",
        prenom: "Camille",
        photo: "img/camille.png",
        photoHover: "img/camille-drole.jpg",
        fft: "https://www2.fftt.com/site/personnes/by-number?number_id=6717679"
    }
];

let categorieActuelle = "hommes";

const tbody = document.getElementById("players-table-body");
const titreClassement = document.getElementById("ranking-title");
const descriptionClassement = document.getElementById("ranking-description");

const boutonHommes = document.getElementById("btn-hommes");
const boutonFemmes = document.getElementById("btn-femmes");

function afficherClassement() {
    const joueursFiltres = joueurs
        .filter(joueur => joueur.categorie === categorieActuelle)
        .sort((a, b) => a.national - b.national);

    tbody.innerHTML = "";

    joueursFiltres.forEach(joueur => {
        const ligne = document.createElement("tr");

        if (joueur.national <= 3) {
            ligne.classList.add("top-three");
        }

        let medaille = "";

        if (joueur.national === 1) medaille = "🥇";
        if (joueur.national === 2) medaille = "🥈";
        if (joueur.national === 3) medaille = "🥉";

        const rangMondial = joueur.mondialTexte ?? joueur.mondial;

        ligne.innerHTML = `
            <td class="rank-cell">
                <span class="rank-medal">${medaille}</span>
                ${joueur.national}
            </td>

            <td>
                <span class="world-rank">${rangMondial}</span>
            </td>

            <td class="player-cell">
                <span class="player-name">${joueur.nom}</span>
                <span class="player-first-name">${joueur.prenom}</span>
            </td>

            <td>
                <img
                    src="${joueur.photo}"
                    data-original="${joueur.photo}"
                    data-hover="${joueur.photoHover}"
                    alt="Portrait de ${joueur.prenom} ${joueur.nom}"
                    class="photo-joueur"
                    width="168"
                    height="111"
                    loading="lazy"
                >
            </td>

            <td>
                <a
                    class="fft-link"
                    href="${joueur.fft}"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Voir la fiche ↗
                </a>
            </td>
        `;

        tbody.appendChild(ligne);
    });


    boutonHommes.classList.toggle(
        "active",
        categorieActuelle === "hommes"
    );

    boutonFemmes.classList.toggle(
        "active",
        categorieActuelle === "femmes"
    );

    boutonHommes.setAttribute(
        "aria-pressed",
        String(categorieActuelle === "hommes")
    );

    boutonFemmes.setAttribute(
        "aria-pressed",
        String(categorieActuelle === "femmes")
    );
    tbody.querySelectorAll(".photo-joueur").forEach(initialiserPhoto);
}


boutonHommes.addEventListener("click", () => {
    categorieActuelle = "hommes";
    afficherClassement();
});

boutonFemmes.addEventListener("click", () => {
    categorieActuelle = "femmes";
    afficherClassement();
});


function initialiserPhoto(photo) {
    const imageOriginale = photo.dataset.original;
    const imageHover = photo.dataset.hover;

    const imageTest = new Image();

    imageTest.onload = () => {
        photo.dataset.hoverDisponible = "true";
    };

    imageTest.onerror = () => {
        photo.dataset.hoverDisponible = "false";
    };

    imageTest.src = imageHover;

    photo.addEventListener("mouseenter", () => {
        if (photo.dataset.hoverDisponible === "true") {
            photo.src = imageHover;
        }
    });

    photo.addEventListener("mouseleave", () => {
        photo.src = imageOriginale;
    });
}

afficherClassement();