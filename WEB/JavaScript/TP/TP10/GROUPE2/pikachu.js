//1) saisir le nom du pokemon et valider avec le bouton ou enter (le nom ne doit pas etre vide !)
//2) Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3) Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4) Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5) Verifier que pikachu ne sort pas de la div grass

var grass = document.getElementById("grass");
var form = document.getElementById("formStart");
var themePokemon = document.getElementById("themePokemon");

var posX=0;
var posY=0;
var mouvement=30;
var pokemon="pikachu";
var direction="Down";
imgPikachu.setAttribute("src","assets/img/"+pokemon+direction+".png");


var inputName = document.getElementById("inputName");
var startButton = document.getElementById("btnStart");



/*
---------------------------- START ---------------------------
*/

inputName.oninput = function() {
  startButton.disabled = inputName.value.trim() === "";
}

startButton.onclick = start;


inputName.onkeydown = function (event) {
  if (event.key === "Enter") {
    start();
  }
};


function start() {
  if (inputName.value != "") {
    grass.style.display = "inherit";
    form.style.display = "none"

    imgPikachu.title = inputName.value;

    themePokemon.currentTime = 3;
    themePokemon.loop = true;
    themePokemon.volume = 0.1;
    themePokemon.play();
  }
}

/*
------------------------ DEPLACEMENTS---------------------------------
*/

window.onkeydown = deplacement;



function deplacement(event)
{

  // Si la grass n'est pas affichée, on sort de la fonction direct, pas de pikachu à bouger
  if (grass.style.display == "none") {
    return;
  }

  // On définie des x et y qu'on va valider avant de les assigner
  var nextX = posX;
  var nextY = posY;

  if(event.key=="ArrowDown" || event.key=="s" )
  {
    nextY += mouvement;
    direction = "Down";
  }
  else if(event.key=="ArrowRight" || event.key=="d" )
  {
    nextX += mouvement;
    direction = "Right";
  }

  else if(event.key=="ArrowLeft" || event.key=="q")
  {
    nextX -= mouvement;
    direction = "Left";
  }

  else if(event.key=="ArrowUp" || event.key=="z")
  {
    nextY -= mouvement;
    direction = "Up";
  } else {
    return;
  }


  // J'ai ajouté 15 parce que les images ne faisaient pas toutes la même taille et parfois ça ne passait pas
  var maxX = grass.clientWidth - pikachu.offsetWidth + 15;
  var maxY = grass.clientHeight - pikachu.offsetHeight + 15;

  
  if (nextX >= 0 && nextX <= maxX && nextY >= 0 && nextY <= maxY) {
    posX = nextX;
    posY = nextY;
  }



  pikachu.style.top=posY+"px";
  pikachu.style.left=posX+"px";
  imgPikachu.setAttribute("src","assets/img/"+pokemon+direction+".png");

}


/*
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
tpt
GROUPE 2 > ALL
*/