import html
import json
import os
from pathlib import Path
from fnmatch import fnmatch

# ---- Réglages ----
CADRE_L, CADRE_H = 800, 300
PAGE_L = 1200
TOP = None
INCLURE_MON_DOSSIER = False   # True pour afficher aussi les .html de Corentin/
# ------------------

SCALE = CADRE_L / PAGE_L
PAGE_H = round(CADRE_H / SCALE)

dossier_page = Path(__file__).resolve().parent   # Corentin/
racine = dossier_page.parent                     # dossier du git du professeur
modele = dossier_page / "modele.html"
sortie = dossier_page / "FichierFinal.html"
fichier_notes = dossier_page / "notes.json"
IGNORES = {".git", ".venv", "node_modules"}

# Fichiers générés ou de support, à ne jamais prendre pour un tableau
# Noms exacts à exclure (minuscules)
EXCLUS = {"fichierfinal.html", "modele.html", "objets.html", "home.html"}

# Motifs sur le NOM du fichier (minuscules, * autorisé)
MOTIFS_NOMS = [
    "*personnage*.html",
    "*test*.html",
    "*cartes.html",
]

# Motifs sur le CHEMIN relatif (minuscules, avec des "/")
EXCLUS_CHEMINS = [
    "*/test/*",
    "omb_crabe/animate/*",
]

# 1. Parcours du repo : on regroupe les .html par dossier d'élève (1er niveau)
par_eleve = {}
for f in racine.rglob("*.html"):
    rel = f.relative_to(racine)
    if len(rel.parts) < 2:
        continue
    if rel.parts[0] == dossier_page.name and not INCLURE_MON_DOSSIER:
        continue                              # ignore Corentin/ (modele, FichierFinal...)
    chemin_rel = rel.as_posix().lower()
    nom = f.name.lower()
    if (IGNORES & set(rel.parts)
            or nom in EXCLUS
            or any(fnmatch(nom, m) for m in MOTIFS_NOMS)
            or any(fnmatch(chemin_rel, m) for m in EXCLUS_CHEMINS)):
        continue
    par_eleve.setdefault(rel.parts[0], []).append(f)
    

fichiers = []
for eleve, liste in par_eleve.items():
    index = [f for f in liste if f.name.lower() == "index.html"]
    if index:
        # un index existe : on ne garde que lui (le moins profond s'il y en a plusieurs)
        fichiers.append(min(index, key=lambda f: len(f.parts)))
    else:
        # pas d'index : on prend tous les .html de l'élève
        fichiers.extend(sorted(liste))

def eleve_de(f):
    return f.relative_to(racine).parts[0]


# 2. Notes (un nombre, ou une liste de votes dont on fait la moyenne)
brut = json.loads(fichier_notes.read_text(encoding="utf-8")) if fichier_notes.exists() else {}

def moyenne(v):
    return sum(v) / len(v) if isinstance(v, list) else v

notes = {prenom: moyenne(v) for prenom, v in brut.items() if v != []}

# 3. Tri : meilleure note d'abord, non notés à la fin
fichiers.sort(key=lambda f: (-notes.get(eleve_de(f), -1), eleve_de(f), f.name))
if TOP:
    fichiers = fichiers[:TOP]

# 4. Lignes du tableau
sections = []
for rang, fichier in enumerate(fichiers, start=1):
    prenom = eleve_de(fichier)
    chemin = Path(os.path.relpath(fichier, dossier_page)).as_posix()
    note = notes.get(prenom)
    texte = f"{note:.1f}" if note is not None else "-"
    sections.append(
        f'        <tr data-id="{html.escape(chemin)}">\n'
        f'            <td class="poignee" title="Glisser pour déplacer">⠿</td>\n'
        f'            <td class="num"><span>{rang}</span></td>\n'
        f'            <td class="prenom">{html.escape(prenom)}</td>\n'
        f'            <td class="note">{texte}</td>\n'
        f'            <td>\n'
        f'                <div class="vignette">\n'
        f'                    <iframe src="{html.escape(chemin)}" scrolling="no"></iframe>\n'
        f'                    <a class="lien" href="{html.escape(chemin)}" target="_blank" rel="noopener"></a>\n'
        f'                </div>\n'
        f'            </td>\n'
        f'        </tr>'
    )
    print(f"#{rang} {chemin} ({texte})")

# 5. Insertion dans le modèle
variables = (
    f"--cadre-l: {CADRE_L}px; --cadre-h: {CADRE_H}px; "
    f"--page-l: {PAGE_L}px; --page-h: {PAGE_H}px; --scale: {SCALE:.4f};"
)

contenu = (
    modele.read_text(encoding="utf-8")
    .replace("/* VARIABLES */", variables)
    .replace("<!-- VIGNETTES -->", "\n".join(sections))
)

sortie.write_text(contenu, encoding="utf-8")
print(f"{sortie.relative_to(racine)} généré ({len(sections)} tableau(x)).")