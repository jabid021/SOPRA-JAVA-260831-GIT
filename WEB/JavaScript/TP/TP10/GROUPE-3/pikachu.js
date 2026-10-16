//1) saisir le nom du pokemon et valider avec le bouton ou enter (le nom ne doit pas etre vide !)
//2) Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3) Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4) Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5) Verifier que pikachu ne sort pas de la div grass


document.getElementById("themePokemon").volume = 0.08;
document.getElementById("raycri").volume = 0.3;
document.getElementById("pikacri").volume = 1;
document.getElementById("psychocri").volume = 0.5;

var posX=0;
var posY=0;
var mouvement=30;
var pokemon="rayquaza";
var direction="Down";
imgPikachu.setAttribute("src","assets/img/"+pokemon+direction+".png");
grass.style.display = "none"

inputName.onkeyup=function() //quand on ecrit dans le champ inputName
{
  btnStart.disabled=(inputName.value==""); //btn start désactivé si le champ est vide et activé si contient

  if(inputName.value!="") //si champs pas vide et entrer actionné (la suite) on lance le jeu
  {
    if(event.key=="Enter"){startGame();} //
  }
}


btnStart.onclick=startGame;

//u clic sur Start, on appelle startGame. Il n’y a pas de parenthèses : on passe la fonction elle-même,
//sans l’exécuter tout de suite. Avec startGame(), elle s’exécuterait immédiatement au chargement de la page.

function startGame()
{
  formStart.style.display="none"; //Cacche le formulaire
  grass.style.display="block"; //Affiche zone d'herbe
  pikachu.setAttribute("title",inputName.value); //Ajoute à l’image de Pikachu l’attribut title avec le nom saisi. Ce nom apparaît en infobulle au survol de l’image.
  document.body.onkeydown = deplacement; //À chaque appui sur une touche la fonction deplacement est appelée.
  themePokemon.play(); // lance la musique
}

function deplacement(event)
{
  //touche a : change de pokemon (!event.repeat = un seul changement même si on garde la touche appuyée)
  if(event.key.toLowerCase()=="a" && !event.repeat)
  {
    if(pokemon=="rayquaza") { pokemon = "pikachu"; }

    else if(pokemon=="pikachu") { pokemon = "psycho"; } //la taille de psycho est faite exprès

    else { pokemon = "rayquaza"; }
  }
  //touche e des attaques lol
  if(event.key.toLowerCase()=="e" && !event.repeat && pokemon=="pikachu")
  {
    attaqueElectrique();
  }
  if(event.key.toLowerCase()=="e" && !event.repeat && pokemon=="rayquaza")
  {
    attaqueDragon();
  }

  if(event.key.toLowerCase()=="e" && !event.repeat && pokemon=="psycho")
  {
    attaqueEau();
  }


  if(event.key=="ArrowDown" || event.key=="s" )
  {
    posY += mouvement;
    direction = "Down";
  }
  else if(event.key=="ArrowRight" || event.key=="d" )
  {
    posX += mouvement;
    direction = "Right";
  }

  else if(event.key=="ArrowLeft" || event.key=="q")
  {
    posX -= mouvement;
    direction = "Left";
  }

  else if(event.key=="ArrowUp" || event.key=="z")
  {
    posY -= mouvement;
    direction = "Up";
  }

  //if(posX < -6)   posX = -6;
  //if(posX > 560) posX = 560;
  //if(posY < 0)   posY = 0;
  //if(posY > 570) posY = 570;

  let maxX = grass.clientWidth  - imgPikachu.width;
  let maxY = grass.clientHeight - imgPikachu.height;

  if(posX < 0)    posX = 0;
  if(posX > maxX) posX = maxX;
  if(posY < 0)    posY = 0;
  if(posY > maxY) posY = maxY;


  pikachu.style.top=posY+"px";
  pikachu.style.left=posX+"px";
  imgPikachu.setAttribute("src","assets/img/"+pokemon+direction+".png"); //le plus important

}

//grossit le pokemon en double click

imgPikachu.ondblclick = function()
{
  var cri;
  if(pokemon=="rayquaza") { cri = raycri; }
  else if(pokemon=="psycho") { cri = psychocri; }
  else { cri = pikacri; }

  cri.currentTime = 0;   // revient au début du son
  cri.play(); //rayquaza crit


  if(pokemon=="psycho")
  {
    pluieDeCanards(); //pour le petit troll
  }

  imgPikachu.style.transform = "scale(1.5)"; //en vrai x1.5 la taille c ok

  setTimeout(function()
  {
    imgPikachu.style.transform = "scale(1)";
  }, 500); //voir css #imagePikachu pour rtransition plus fluide
}


function attaqueElectrique()
{
  // Pikachu brille en jaune
  imgPikachu.style.filter = "drop-shadow(0 0 15px yellow) brightness(1.5)";
  setTimeout(function()
  {
    imgPikachu.style.filter = "none";
  }, 500);


var numero = 0;

var elect = document.createElement("img");
elect.setAttribute("src","assets/elec/Explosion_1.png"); //je lui donne sa première image
elect.style.position = "absolute"; //permet d eplace rl'image ou l'on veut dans la grass
elect.style.left = (posX + imgPikachu.width/2) + "px";
elect.style.top = (posY + imgPikachu.height/2) + "px";
elect.style.transform = "translate(-50%, -50%)"; // centre l'éclair sur ce point
elect.style.width = "150px";
grass.appendChild(elect); // ajoute elect dans grass

var animation = setInterval(function()
{
  numero++;

  if(numero > 9)
  {
    clearInterval(animation);
    elect.remove();
  }
  else
  {
    elect.setAttribute("src","assets/elec/Explosion_" + String(numero)+ ".png");
  }
}, 100);
}

function attaqueDragon()
{

  imgPikachu.style.filter = "drop-shadow(0 0 15px darkblue) brightness(1.5)";

  setTimeout(function()
  {
    imgPikachu.style.filter = "none";
  }, 1700);

  var numero = 0;

  var souffle = document.createElement("img"); //juste je crée une balise image que je range dans souffle
  //je crée une balise car ici ma flamme apparaît que quand j'attaque
  souffle.setAttribute("src","assets/feu/efecto_fuego_00000.png"); //je lui donne sa première image
  souffle.style.position = "absolute"; //permet de place rl'image ou l'on veut dans la grass
  souffle.style.left = (posX + imgPikachu.width - 10) + "px";
  // posX : coté gauche rayquaza, imgPikachu.width : on decal de toute sa largeur donc on arrive coté droit
  //px juste l'unité
  souffle.style.top = (posY + imgPikachu.height / 2) + "px"; //posY : haut de rayquaza
  souffle.style.width = "150px"; // car rayquaza fait environ 150 px
  grass.appendChild(souffle); // ajoute souffle dans grass

  var animation = setInterval(function()
  {
    numero++;

    if(numero > 49) //car 50 images lol
    {
      clearInterval(animation); // arrête l'animation
      souffle.remove();         // enlève la flamme
    }
    else
    {
      souffle.setAttribute("src","assets/feu/efecto_fuego_" + String(numero).padStart(5,"0") + ".png");
    }
  }, 40); //délai entre deux image environ 2 sec ici 40x49 =2000
}

function pluieDeCanards()
{
  var canards = []; // je garde toutes les images pour les supprimer ensuite

  for(var i = 0; i < 60; i++) // 60 images je pense c ok
  {
    var canard = document.createElement("img");
    canard.setAttribute("src", "assets/imgpsycho/psycho1.png"); // adapte le chemin si besoin
    canard.style.position = "absolute";
    canard.style.width = "60px";
    canard.style.left = Math.random() * (grass.clientWidth - 60) + "px";
    canard.style.top  = Math.random() * (grass.clientHeight - 60) + "px"; //pos aléatoire de l'image
    grass.appendChild(canard);
    canards.push(canard); //canard est écrasé a chaque tour, le push le garde en reférence ou sinon
    //je devrais avoir 60 images png du même canard
  }

  setTimeout(function()
  {
    for(var i = 0; i < canards.length; i++)
    {
      canards[i].remove();
    }
  }, 3000);
}


function attaqueEau()
{

  imgPikachu.style.filter = "drop-shadow(0 0 15px blue) brightness(1.5)";
  setTimeout(function()
  {
    imgPikachu.style.filter = "none";
  }, 500);


var numero = 0;

var hydro = document.createElement("img");
hydro.setAttribute("src","assets/Eau/Png/water40000.png");
hydro.style.position = "absolute";
hydro.style.left = (posX + imgPikachu.width/2) + "px";
hydro.style.top = (posY + imgPikachu.height-50) + "px";
hydro.style.transform = "translate(-50%, -50%)";

hydro.style.width = "500px";
grass.appendChild(hydro);

var animation = setInterval(function()
{
  numero++;

  if(numero > 13)
  {
    clearInterval(animation);
    hydro.remove();
  }
  else
  {
    hydro.setAttribute("src","assets/Eau/png/Water4" + String(numero).padStart(4,"0")+ ".png");
  }
}, 100);
}
