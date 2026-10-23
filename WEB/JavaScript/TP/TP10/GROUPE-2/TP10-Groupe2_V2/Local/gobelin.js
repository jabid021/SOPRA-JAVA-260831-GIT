

var posX = 0;
var posY = 0;
var mouvement = 30;

var pokemon = "gob";
var direction = "Down";
var surnom = "";

var inputName = document.getElementById("inputName");
var btnStart = document.getElementById("btnStart");
var formStart = document.getElementById("formStart");
var grass = document.getElementById("grass");

var modalEchange = document.getElementById("modalEchange");
var modalAmelioration = document.getElementById("modalAmelioration");
var modalAttaque = document.getElementById("modalAttaque");
var formEchange = document.getElementById("formEchange");
var formAmelioration = document.getElementById("formAmelioration");
var formAttaque = document.getElementById("formAttaque");
var messageEchange = document.getElementById("messageEchange");
var messageAmelioration =
document.getElementById("messageAmelioration");
var messageAttaque = document.getElementById("messageAttaque");
var selectRoyaume = document.getElementById("royaume");

var royaumes = [
  "Bouboule - Royaume des canards",
  "PlumeDorée - Royaume des licornes",
  "TartifletteQuiPete - Royaume des teuteus",
  "Xx_GrukGruk_xX - Royaume des gobelins",
  "CoinCoinator - Royaume des canards",
  "ArcEnCiel77 - Royaume des licornes",
  "JeanDeLaMolleFesse - Royaume des teuteus",
  "TheRealGobDestructor - Royaume des gobelins",
  "CanardMasquéDu38 - Royaume des canards"
];

for (var i = 0; i < royaumes.length; i++) {
  var option = document.createElement("option");

  option.value = royaumes[i];
  option.textContent = royaumes[i];

  selectRoyaume.appendChild(option);
}


imgGobelin.style.width = "96px";
imgGobelin.style.height = "96px";

document.body.style.margin = "0";
document.body.style.width = "100%";
document.body.style.height = "100vh";
document.body.style.backgroundSize = "100% 100%";
document.body.style.backgroundRepeat = "no-repeat";
document.body.style.backgroundPosition = "center";

grass.style.backgroundSize = "100% 100%";
grass.style.backgroundRepeat = "no-repeat";
grass.style.backgroundPosition = "center";

imgGobelin.src = "assets/img/" + pokemon + direction + ".png";

inputName.addEventListener("input", function () {
  btnStart.disabled = inputName.value.trim() === "";
});

btnStart.addEventListener("click", startGame);

inputName.addEventListener("keydown", function (event) {
  if (event.key === "Enter" && inputName.value.trim() !== "") {
    startGame();
  }
});


function startGame() {
  surnom = inputName.value.trim();

  if (surnom !== "gobelin") {
    alert("Vous devez saisir 'gobelin' pour entrer dans le jeu.");
    return;
  }

  formStart.style.display = "none";
  grass.style.display = "block";
  gobelin.title = surnom;

  posX = 0;
  posY = 0;
  direction = "Down";

  gobelin.style.left = posX + "px";
  gobelin.style.top = posY + "px";

  imgGobelin.src =
  "assets/img/gob" + direction + ".png";

  document.addEventListener("keydown", deplacement);
}

function estSurLaTable(x, y) {
  var caseX = x / mouvement;
  var ligneY = y / mouvement;

  if (ligneY === 0 && caseX >= 7 && caseX <= 14) {
    return true;
  }


  if (ligneY === 5 && caseX >= 17 && caseX <= 20) {
    return true;
  }


  if (
    ligneY >= 6 &&
    ligneY <= 9 &&
    (
      (caseX >= 0 && caseX <= 2) ||
      (caseX >= 17 && caseX <= 20)
    )
  ) {
    return true;
  }

  return false;
}

function estSurLaTapisserie(x, y) {
  var caseX = x / mouvement;
  var ligneY = y / mouvement;


  if (ligneY === 0 && caseX === 1) {
    return true;
  }


  if (
    ligneY >= 1 &&
    ligneY <= 4 &&
    (
      caseX === 1 ||caseX === 2 ||caseX === 3 ||caseX === 4 ||caseX === 5 ||caseX === 6 ||
      caseX === 7 ||
      caseX === 13
    )
  ) {
    return true;
  }

  if (ligneY === 5 && caseX === 13) {
    return true;
  }
  if (ligneY === 6 && caseX === 13) {
    return true;
  }

  if (
    ligneY >= 7 &&
    ligneY <= 9 &&
    (  caseX === 3 ||caseX === 4 ||caseX === 5 ||caseX === 6 ||
      caseX === 7 ||
      caseX === 13 || caseX === 14  || caseX === 15 || caseX === 16 || caseX === 17  )
    ) {
      return true;
    }

    if (ligneY >= 14) {
      return true;
    }
    return false;
  }

  function retourMenu() {
  grass.style.display = "none";
  formStart.style.display = "flex";

  inputName.value = "";
  btnStart.disabled = true;

  posX = 0;
  posY = 0;
  direction = "Down";

  document.removeEventListener("keydown", deplacement);
}

  function deplacement(event) {
    var touche = event.key.toLowerCase();

    if (event.code === "Space") {
      event.preventDefault();
      interaction();
      return;
    }

    if (
    posX === 0 &&
    posY === 0 &&
    (
      event.key === "ArrowUp" ||
      touche === "z"

    )
  ) {
    retourMenu();
    return;
  }

    var positionMaxX = 610;
    var positionMaxY = 610;
    var nouvellePosX = posX;
    var nouvellePosY = posY;



    if (event.key === "ArrowDown" || touche === "s") {
      direction = "Down";
      nouvellePosY += mouvement;
    }

    else if (event.key === "ArrowRight" || touche === "d") {
      direction = "Right";
      nouvellePosX += mouvement;
    }

    else if (event.key === "ArrowLeft" || touche === "q") {
      direction = "Left";
      nouvellePosX -= mouvement;
    }

    else if (event.key === "ArrowUp" || touche === "z") {
      direction = "Up";
      nouvellePosY -= mouvement;
    }

    else {
      return;
    }

    event.preventDefault();

    var dansLaMap =
    nouvellePosX >= 0 &&
    nouvellePosX <= positionMaxX &&
    nouvellePosY >= 0 &&
    nouvellePosY <= positionMaxY;

    if (dansLaMap && !estSurLaTable(nouvellePosX, nouvellePosY)&&
    !estSurLaTapisserie(nouvellePosX, nouvellePosY)) {
      posX = nouvellePosX;
      posY = nouvellePosY;
    }

    gobelin.style.left = posX + "px";
    gobelin.style.top = posY + "px";

    imgGobelin.src =
    "assets/img/gob" + direction + ".png";
  }

  function estAdjacentAZone(caseGobelin, ligneGobelin,caseDebut,caseFin,ligneDebut,ligneFin) {
    for (var ligne = ligneDebut; ligne <= ligneFin; ligne++) {
      for (var cellule = caseDebut; cellule <= caseFin; cellule++) {
        var distance =
        Math.abs(caseGobelin - cellule) +
        Math.abs(ligneGobelin - ligne);

        if (distance === 1) {
          return true;
        }
      }
    }

    return false;
  }

  function interaction() {
    var caseGobelin = posX / mouvement;
    var ligneGobelin = posY / mouvement;

    // Table : ligne 0, cases 7 à 14
    var presDeLaTable = estAdjacentAZone(caseGobelin,ligneGobelin,7,14,0,0);

    // Carte : lignes 6 à 9, cases 1 à 3
    var presDeLaCarte = estAdjacentAZone(caseGobelin,ligneGobelin,0,3,6,9);

    // Armes : lignes 5 à 9, cases 17 à 20
    var presDesArmes = estAdjacentAZone(caseGobelin,ligneGobelin,17,20,5,9);

    if (presDeLaTable) {
      ouvrirModal(modalEchange);
    }

    else if (presDeLaCarte) {
      ouvrirModal(modalAmelioration);
    }

    else if (presDesArmes) {
      ouvrirModal(modalAttaque);
    }
  }
  function ouvrirModal(modal) {
    modal.style.display = "flex";
  }

  function fermerModal(modal) {
    modal.style.display = "none";
  }
  var boutonsFermer = document.querySelectorAll(".fermer");


  //fermer les fenetres

  for (var i = 0; i < boutonsFermer.length; i++) {
    boutonsFermer[i].addEventListener("click", function () {
      this.closest(".modal").style.display = "none";
    });
  }

  document.addEventListener("keydown", function (event) {
    if (event.key === "Escape") {
      modalEchange.style.display = "none";
      modalAmelioration.style.display = "none";
      modalAttaque.style.display = "none";
    }
  });

  formEchange.addEventListener("submit", function (event) {
    event.preventDefault();

    var ressourceDemandee =
    document.getElementById("ressourceDemandee").value;

    var quantiteDemandee =
    document.getElementById("quantiteDemandee").value;

    var ressourceProposee =
    document.getElementById("ressourceProposee").value;

    var quantiteProposee =
    document.getElementById("quantiteProposee").value;



    messageEchange.textContent =
    "Annonce publiée : " +
    quantiteDemandee + " " +
    ressourceDemandee +
    " contre " +
    quantiteProposee + " " +
    ressourceProposee + ".";
  });

  formAmelioration.addEventListener("submit", function (event) {
    event.preventDefault();

    var batiment = document.getElementById("batiment").value;

    messageAmelioration.textContent =
    "Amélioration de " + batiment + " demandée.";
  });

  formAttaque.addEventListener("submit", function (event) {
    event.preventDefault();

    var royaumeChoisi = selectRoyaume.value;

    messageAttaque.textContent =
    "L'attaque contre " +
    royaumeChoisi +
    " a été lancée fictivement.";
  });
