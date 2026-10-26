// Les textes des fiches sont regroupés ici pour être faciles à modifier.
const characters = {
  peter: {
    name: 'SPIDER-MAN', identity: 'Peter Parker', label: 'DOSSIER 001 / L’ORIGINAL',
    image: 'assets/spider-man.png', alt: 'Les trois Spider-Man réunis dans No Way Home',
    caption: 'Les trois incarnations de Peter Parker réunies dans Spider-Man : No Way Home. La fiche présente ici le personnage des comics.',
    description: 'Peter Parker jongle entre ses études, sa vie personnelle et son rôle de Spider-Man. Sous le masque, il apprend qu’un grand pouvoir implique de grandes responsabilités.',
    debut: 'Amazing Fantasy #15 · août 1962 (date de couverture)',
    source: 'https://www.marvel.com/characters/spider-man-peter-parker/in-comics'
  },
  ham: {
    name: 'SPIDER-HAM', identity: 'Peter Porker', label: 'DOSSIER 002 / SPIDER-COCHON',
    image: 'assets/spider-ham.png', alt: 'Spider-Ham, le cochon super-héros',
    description: 'Peter Porker vient d’un univers où les héros sont des animaux. À l’origine, c’est une araignée : la morsure d’un cochon radioactif le transforme en cochon, tout en lui laissant ses pouvoirs arachnéens ! Cette parodie de Spider-Man associe les aventures de super-héros à la logique déjantée des cartoons.',
    debut: 'Marvel Tails Starring Peter Porker, The Spectacular Spider-Ham #1 · novembre 1983 (date de couverture)',
    source: 'https://www.marvel.com/amp/articles/comics/spider-ham-finally-has-his-own-series-again?linkId=77120228'
  },
  hulk: {
    name: 'SPIDER-HULK', identity: 'Peter Parker', label: 'DOSSIER 003 / ALERTE GAMMA',
    image: 'assets/spider-hulk.jpg', alt: 'Couverture de Web of Spider-Man numéro 70',
    description: 'Et si Peter Parker avait aussi la puissance de Hulk ? Après une aventure impliquant Hulk et une exposition aux radiations gamma, Spider-Man subit une transformation spectaculaire. Sa peau devient verte, ses muscles se développent et sa force explose. Cette version de Spider-Hulk est une transformation temporaire de Peter Parker, et non une nouvelle identité secrète.',
    debut: 'Web of Spider-Man #70 · novembre 1990 (date de couverture)',
    source: 'https://spiderfan.org/review/comics/spiderman_web/070.html'
  },

  "miles": {
    "name": "MILES MORALES",
    "identity": "Miles Morales",
    "label": "DOSSIER 004 / LE HÉROS DE BROOKLYN",
    "image": "assets/miles-morales.png",
    "alt": "Miles Morales en costume noir et rouge",
    "description": "Miles Morales est un adolescent de Brooklyn devenu Spider-Man après la morsure d’une araignée génétiquement modifiée. En plus de son agilité et de son sens d’araignée, il peut se camoufler et produire des décharges bioélectriques. Héritier du masque dans son univers, Miles apprend à tracer sa propre voie.",
    "debut": "Ultimate Fallout #4 · août 2011 (date de couverture)",
    "source": "https://www.marvel.com/characters/spider-man-miles-morales/in-comics"
  },
  "gwen": {
    "name": "SPIDER-GWEN",
    "identity": "Gwen Stacy",
    "label": "DOSSIER 005 / ALIAS GHOST-SPIDER",
    "image": "assets/spider-gwen.png",
    "alt": "Gwen Stacy dans son costume blanc et noir de Spider-Gwen",
    "description": "Sur la Terre-65, c’est Gwen Stacy, et non Peter Parker, qui est mordue par l’araignée. Connue sous les noms de Spider-Woman, Spider-Gwen et Ghost-Spider, elle protège New York tout en jouant de la batterie avec les Mary Janes. Son histoire explore la responsabilité, la perte et le regard des autres.",
    "debut": "Edge of Spider-Verse #2 · novembre 2014 (date de couverture)",
    "source": "https://www.marvel.com/characters/spider-woman-gwen-stacy"
  },
  "miguel": {
    "name": "SPIDER-MAN 2099",
    "identity": "Miguel O’Hara",
    "label": "DOSSIER 006 / LE SPIDER DU FUTUR",
    "image": "assets/spider-man-2099.png",
    "alt": "Miguel O’Hara en costume bleu et rouge de Spider-Man 2099",
    "description": "Miguel O’Hara est un généticien du futur dont l’ADN est mêlé à celui d’une araignée. Il devient le Spider-Man de Nueva York en 2099, avec des griffes, des crocs et des capacités extraordinaires. Il apparaît d’abord dans un aperçu publié dans Amazing Spider-Man #365, avant le lancement de sa propre série Spider-Man 2099.",
    "debut": "Amazing Spider-Man #365 · août 1992 (date de couverture) · aperçu ; série Spider-Man 2099 #1 : novembre 1992",
    "source": "https://www.marvel.com/characters/spider-man-2099-miguel-o-hara"
  },
  spooder: {
    "name": "SPOODER-MAN",
    "identity": "Péter Parkour ? (alias inventé pour ce dossier)",
    "label": "DOSSIER 007 / ANOMALIE DU MULTIVERS",
    "image": "assets/spooder-man.png",
    "alt": "Spooder-Man, dessin parodique de Spider-Man",
    "caption": "Mème Internet parodique. Cette fiche humoristique ne décrit pas un personnage officiel de Marvel.",
    "description": "Le multivers a visiblement raté une mise à jour. Voici Spooder-Man : un masque approximatif, un dessin de travers et une confiance absolument injustifiée. Pouvoir spécial, selon ce dossier totalement non scientifique : transformer une entrée héroïque en moment gênant. Une parodie de Spider-Man, ajoutée ici pour la blague.",
    "debut": "Mème Internet · aucune date de parution comics retenue",
    "source": "https://giphy.com/gifs/zVvIet5Odn81a"
  }
};

// Une image fixe au repos, puis le GIF uniquement au survol ou au focus clavier.
const reducedMotion = window.matchMedia('(prefers-reduced-motion: reduce)');
const portraits = document.querySelectorAll('img[data-gif]');
portraits.forEach((image) => {
  const button = image.closest('button');
  const animate = () => { if (!reducedMotion.matches) image.src = image.dataset.gif; };
  const freeze = () => { image.src = image.dataset.still; };
  button.addEventListener('pointerenter', animate);
  button.addEventListener('pointerleave', freeze);
  button.addEventListener('focus', animate);
  button.addEventListener('blur', freeze);
  image.addEventListener('error', () => {
    if (image.getAttribute('src') !== image.dataset.still) freeze();
  });
});
reducedMotion.addEventListener('change', () => {
  if (reducedMotion.matches) portraits.forEach((image) => { image.src = image.dataset.still; });
});

// Une seule fenêtre réutilisée pour tous les personnages.
const dialog = document.querySelector('#character-dialog');
let lastButton;
document.querySelectorAll('[data-open]').forEach((button) => {
  button.addEventListener('click', () => {
    const character = characters[button.dataset.open];
    lastButton = button;
    // Le CSS choisit la couleur de la fiche à partir de ce personnage.
    dialog.dataset.character = button.dataset.open;
    const caption = document.querySelector('#dialog-caption');
    caption.textContent = character.caption || '';
    caption.hidden = !character.caption;
    document.querySelector('#dialog-label').textContent = character.label;
    document.querySelector('#dialog-title').textContent = character.name;
    document.querySelector('#dialog-identity').textContent = character.identity;
    document.querySelector('#dialog-description').textContent = character.description;
    document.querySelector('#dialog-debut').textContent = character.debut;
    document.querySelector('#dialog-source').href = character.source;
    const image = document.querySelector('#dialog-image');
    image.src = character.image;
    image.alt = character.alt;
    portraits.forEach((portrait) => { portrait.src = portrait.dataset.still; });
    dialog.showModal();
  });
});
document.querySelector('.close-button').addEventListener('click', () => dialog.close());
// Échap est géré nativement par dialog ; le clic à l’extérieur ferme aussi la fiche.
dialog.addEventListener('click', (event) => {
  if (event.target !== dialog) return;
  const box = dialog.getBoundingClientRect();
  if (event.clientX < box.left || event.clientX > box.right || event.clientY < box.top || event.clientY > box.bottom) dialog.close();
});
dialog.addEventListener('close', () => {
  if (lastButton) lastButton.focus({ preventScroll: true });
  portraits.forEach((portrait) => { portrait.src = portrait.dataset.still; });
});

// Recherche et tri : on réutilise les lignes HTML existantes.
const tableBody = document.querySelector('#character-rows');
const originalRows = Array.from(tableBody.querySelectorAll('.character-row'));
const searchInput = document.querySelector('#character-search');
const sortSelect = document.querySelector('#character-sort');
const resultCount = document.querySelector('#result-count');
const noResults = document.querySelector('#no-results');

// Ignore les accents, les espaces et les tirets : « spiderman » trouve « Spider-Man ».
function normalizeName(text) {
  return text.toLowerCase().normalize('NFD')
    .replace(/[\u0300-\u036f]/g, '')
    .replace(/[^a-z0-9]/g, '');
}

function updateTable() {
  const query = normalizeName(searchInput.value);
  let visibleCount = 0;

  originalRows.forEach((row) => {
    const name = row.querySelector('.character-name').textContent;
    const identity = row.querySelector('.identity').textContent;
    const alias = row.querySelector('.character-tag').textContent;
    const matches = normalizeName(name + ' ' + identity + ' ' + alias).includes(query);
    row.hidden = !matches;
    if (matches) visibleCount += 1;
    // Arrête le GIF d’une ligne masquée par la recherche.
    if (!matches) {
      const image = row.querySelector('img[data-still]');
      if (image) image.src = image.dataset.still;
    }
  });

  // On copie l’ordre initial pour pouvoir le restaurer sans perdre les lignes.
  const sortedRows = [...originalRows];
  if (sortSelect.value !== 'original') {
    sortedRows.sort((first, second) => {
      const firstDate = first.querySelector('time')?.dateTime;
      const secondDate = second.querySelector('time')?.dateTime;
      // Les mèmes sans date officielle restent à la fin, dans les deux sens.
      if (!firstDate && !secondDate) return 0;
      if (!firstDate) return 1;
      if (!secondDate) return -1;
      const comparison = firstDate.localeCompare(secondDate);
      return sortSelect.value === 'oldest' ? comparison : -comparison;
    });
  }
  sortedRows.forEach((row) => tableBody.appendChild(row));

  resultCount.textContent = visibleCount + ' personnage' + (visibleCount > 1 ? 's' : '')
    + ' sur ' + originalRows.length;
  noResults.hidden = visibleCount !== 0;
}

searchInput.addEventListener('input', updateTable);
sortSelect.addEventListener('change', updateTable);
document.querySelector('#reset-table').addEventListener('click', () => {
  searchInput.value = '';
  sortSelect.value = 'original';
  updateTable();
  searchInput.focus();
});
document.querySelector('#table-tools').hidden = false;
updateTable();
