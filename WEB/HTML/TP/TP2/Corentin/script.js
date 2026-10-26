
const corps = document.getElementById("corps");
const CLE = "ordre-" + location.pathname;
let glisse = null;

function numeroter() {
    [...corps.rows].forEach((tr, i) => {
        tr.querySelector(".num span").textContent = i + 1;
        tr.dataset.rang = i + 1;
    });
}

function sauvegarder() {
    try {
        localStorage.setItem(CLE, JSON.stringify([...corps.rows].map(tr => tr.dataset.id)));
    } catch { }
}

// Applique l'ordre mémorisé ; les nouvelles lignes restent à la fin
function restaurer() {
    let ordre = [];
    try { ordre = JSON.parse(localStorage.getItem(CLE) || "[]"); } catch { }
    const lignes = new Map([...corps.rows].map(tr => [tr.dataset.id, tr]));
    ordre.forEach(id => { if (lignes.has(id)) corps.append(lignes.get(id)); });
    [...lignes].filter(([id]) => !ordre.includes(id)).forEach(([, tr]) => corps.append(tr));
    numeroter();
}

function nettoyer() {
    corps.querySelectorAll(".avant, .apres").forEach(tr => tr.classList.remove("avant", "apres"));
}

// La ligne n'est déplaçable que si on la saisit par la poignée
corps.addEventListener("mousedown", e => {
    const tr = e.target.closest("tr");
    if (tr) tr.draggable = !!e.target.closest(".poignee");
});

corps.addEventListener("dragstart", e => {
    glisse = e.target.closest("tr");
    glisse.classList.add("glisse");
    e.dataTransfer.effectAllowed = "move";
    e.dataTransfer.setData("text/plain", "");   // requis par Firefox
});

corps.addEventListener("dragover", e => {
    if (!glisse) return;
    e.preventDefault();
    const cible = e.target.closest("tr");
    nettoyer();
    if (!cible || cible === glisse) return;
    const r = cible.getBoundingClientRect();
    cible.classList.add(e.clientY > r.top + r.height / 2 ? "apres" : "avant");
});

corps.addEventListener("drop", e => {
    e.preventDefault();
    const cible = e.target.closest("tr");
    if (!glisse || !cible || cible === glisse) return;
    const r = cible.getBoundingClientRect();
    const apres = e.clientY > r.top + r.height / 2;
    corps.insertBefore(glisse, apres ? cible.nextSibling : cible);
    numeroter();
    sauvegarder();
});

corps.addEventListener("dragend", () => {
    if (glisse) { glisse.classList.remove("glisse"); glisse.draggable = false; }
    glisse = null;
    nettoyer();
});

document.getElementById("reinit").addEventListener("click", () => {
    try { localStorage.removeItem(CLE); } catch { }
    location.reload();
});

restaurer();


// ---- Notes modifiables localement ----
const CLE_NOTES = "notes-" + location.pathname;

function lireNotes() {
    try { return JSON.parse(localStorage.getItem(CLE_NOTES) || "{}"); }
    catch { return {}; }
}
function ecrireNotes(n) {
    try { localStorage.setItem(CLE_NOTES, JSON.stringify(n)); } catch { }
}

function editerNote(tr, td) {
    if (td.querySelector("input")) return;          // déjà en cours d'édition

    const avant = td.textContent;
    const input = document.createElement("input");
    input.type = "number";
    input.min = 0;
    input.max = 10;
    input.step = 0.1;
    input.value = avant === "-" ? "" : avant;

    td.textContent = "";
    td.append(input);
    input.focus();
    input.select();

    let fini = false;
    function terminer(annule) {
        if (fini) return;
        fini = true;
        const notes = lireNotes();
        const v = input.value.trim();

        if (annule) {
            td.textContent = avant;
        } else if (v === "") {                       // champ vide : retour à la note d'origine
            delete notes[tr.dataset.id];
            ecrireNotes(notes);
            td.textContent = td.dataset.origine;
        } else {
            const n = Math.max(0, Math.min(10, Number(v)));
            notes[tr.dataset.id] = n;
            ecrireNotes(notes);
            td.textContent = n.toFixed(1);
        }
    }

    input.addEventListener("keydown", e => {
        if (e.key === "Enter") terminer(false);
        if (e.key === "Escape") terminer(true);
    });
    input.addEventListener("blur", () => terminer(false));
}

(function () {
    const notes = lireNotes();
    [...corps.rows].forEach(tr => {
        const td = tr.querySelector(".note");
        td.dataset.origine = td.textContent;          // note venant de notes.json
        td.title = "Cliquer pour modifier";
        if (tr.dataset.id in notes) td.textContent = Number(notes[tr.dataset.id]).toFixed(1);
        td.addEventListener("click", () => editerNote(tr, td));
    });
})();

// Trie les lignes selon les notes affichées
function trierParNote() {
    const valeur = tr => {
        const t = tr.querySelector(".note").textContent;
        return t === "-" ? -1 : parseFloat(t);
    };
    [...corps.rows]
        .sort((a, b) => valeur(b) - valeur(a))        // tri stable : égalités gardent leur ordre
        .forEach(tr => corps.append(tr));
    numeroter();
    sauvegarder();
}
document.getElementById("trier").addEventListener("click", trierParNote);


// ---- Enregistrer les notes locales dans notes.json ----
let fichierNotes = null;   // mémorisé tant que la page reste ouverte

async function enregistrerNotes() {
    const statut = document.getElementById("statut-notes");

    if (!window.showOpenFilePicker) {
        statut.textContent = "Navigateur non compatible (utilise Chrome ou Edge)";
        return;
    }

    try {
        if (!fichierNotes) {
            [fichierNotes] = await showOpenFilePicker({
                types: [{ description: "JSON", accept: { "application/json": [".json"] } }]
            });
        }
        if ((await fichierNotes.requestPermission({ mode: "readwrite" })) !== "granted") return;

        // on relit le fichier pour conserver les votes existants
        let json = {};
        try { json = JSON.parse(await (await fichierNotes.getFile()).text()); } catch {}

        const locales = lireNotes();
        const restantes = { ...locales };
        const ajoutes = [];

        [...corps.rows].forEach(tr => {
            if (!(tr.dataset.id in locales)) return;
            const prenom = tr.querySelector(".prenom").textContent;
            const note = Number(locales[tr.dataset.id]);

            // normalise l'existant en liste : absent -> [], nombre -> [nombre], liste -> telle quelle
            let votes = json[prenom];
            if (votes === undefined || votes === null) votes = [];
            else if (!Array.isArray(votes)) votes = [votes];

            votes.push(note);
            json[prenom] = votes;
            ajoutes.push([tr, votes]);
            delete restantes[tr.dataset.id];   // déjà enregistrée : on ne la rajoutera pas deux fois
        });

        const w = await fichierNotes.createWritable();
        await w.write(JSON.stringify(json, null, 2));
        await w.close();

        // la note locale devient un vote du fichier : on efface le brouillon
        // et on affiche la moyenne, comme le fera Reunion.py
        ecrireNotes(restantes);
        ajoutes.forEach(([tr, votes]) => {
            const td = tr.querySelector(".note");
            const moy = votes.reduce((a, b) => a + b, 0) / votes.length;
            td.textContent = moy.toFixed(1);
            td.dataset.origine = td.textContent;
        });

        statut.textContent = ajoutes.length
            ? `${ajoutes.length} vote(s) ajouté(s)`
            : "Aucune note modifiée";
    } catch (e) {
        if (e.name !== "AbortError") statut.textContent = "Échec de l'enregistrement";
        fichierNotes = null;
    }
}
document.getElementById("enregistrer").addEventListener("click", enregistrerNotes);




const NB_CANARDS = 6;
const ECHELLE = 3;   // taille d'un "pixel" du canard

const GRILLE = [
    "......YYY...",
    ".....YYYYY..",
    ".....YKYYOOO",
    ".....YYYYOO.",
    "Y....YYYY...",
    "YY.YYYYYYY..",
    "YYYYYDDYYYY.",
    ".YYYYDDDYYY.",
    "..YYYYYYYY..",
    "....YYYY...."
];
const COULEURS = { Y: "#f5c542", D: "#d9a21b", O: "#e67e22", K: "#1f2933" };

function imageCanard() {
    let rects = "";
    GRILLE.forEach((ligne, y) => {
        [...ligne].forEach((c, x) => {
            if (COULEURS[c]) rects += `<rect x="${x}" y="${y}" width="1" height="1" fill="${COULEURS[c]}"/>`;
        });
    });
    const svg = `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 12 10" shape-rendering="crispEdges">${rects}</svg>`;
    return `url("data:image/svg+xml,${encodeURIComponent(svg)}")`;
}

(function () {
    const carte = document.querySelector(".carte");
    const entete = carte.querySelector("thead");
    const mare = document.createElement("div");
    mare.id = "mare";
    carte.prepend(mare);

    const image = imageCanard();
    const L = 12 * ECHELLE, H = 10 * ECHELLE;
    let largeur = 0, hauteur = 0;

    function mesurer() {
        largeur = carte.clientWidth;
        hauteur = entete.offsetHeight;
        mare.style.height = hauteur + "px";
        mare.style.marginBottom = -hauteur + "px";   // superpose la mare à l'en-tête
    }
    mesurer();
    window.addEventListener("resize", mesurer);

    const canards = Array.from({ length: NB_CANARDS }, (_, i) => {
        const el = document.createElement("div");
        el.className = "canard";
        el.style.backgroundImage = image;
        mare.append(el);
        return {
            el,
            x: Math.random() * Math.max(1, largeur - L),
            vitesse: 20 + Math.random() * 30,          // px par seconde
            dir: Math.random() < 0.5 ? 1 : -1,
            phase: Math.random() * Math.PI * 2,
            pause: 0
        };
    });

    function dessiner(c, t) {
        const base = (hauteur - H) / 2;
        const y = base + Math.sin(t * 2 + c.phase) * 2;   // flottement
        c.el.style.transform = `translate(${c.x}px, ${y}px) scaleX(${c.dir})`;
    }

    // Respecte le réglage "réduire les animations" du système
    if (window.matchMedia("(prefers-reduced-motion: reduce)").matches) {
        canards.forEach(c => dessiner(c, 0));
        return;
    }

    let dernier = performance.now();
    function boucle(maintenant) {
        const dt = (maintenant - dernier) / 1000;
        dernier = maintenant;
        const t = maintenant / 1000;

        canards.forEach(c => {
            if (c.pause > 0) {
                c.pause -= dt;
            } else {
                c.x += c.dir * c.vitesse * dt;
                if (c.x <= 0) { c.x = 0; c.dir = 1; }
                if (c.x >= largeur - L) { c.x = largeur - L; c.dir = -1; }

                // de temps en temps : demi-tour ou petite pause
                if (Math.random() < 0.003) c.dir *= -1;
                if (Math.random() < 0.002) c.pause = 0.5 + Math.random() * 1.5;
            }
            dessiner(c, t);
        });
        requestAnimationFrame(boucle);
    }
    requestAnimationFrame(boucle);
})();


