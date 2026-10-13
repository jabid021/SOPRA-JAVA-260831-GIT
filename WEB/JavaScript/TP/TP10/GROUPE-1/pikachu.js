//1) saisir le nom du pokemon et valider avec le bouton ou enter (le nom ne doit pas etre vide !)
//2) Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3) Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4) Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5) Verifier que pikachu ne sort pas de la div grass

var posPikaX=pikachu.style.width;
var posPikaY=pikachu.style.top;
var posMiaoussX=miaouss.style.width.content;
var posMiaoussY=miaouss.style.top.content;

var posPikaX=0;
var posPikaY=100;
var posMiaoussX=0;
var posMiaoussY=0;



var mouvement1=30;
var mouvement2=60;
var pokemon1="pikachu";
var pokemon2="miaouss";
var direction="Down";
imgPikachu.setAttribute("src","assets/img/"+pokemon1+direction+".png");
imgMiaouss.setAttribute("src","assets/img/"+pokemon2+direction+".png");

console.log(posPikaX);
console.log(posPikaY);
console.log(posMiaoussX);
console.log(posMiaoussY);

var varTimer;



btnStart.onclick=function()
{
  EnterPokemonName();
  varTimer = setTimeout(function()
  {

    posPikaX=330;
    posPikaY=330;
    posMiaoussX=posPikaX-30;
    posMiaoussY=posPikaY-30;

    pikachu.style.top=posPikaY+"px";
    pikachu.style.left=posPikaX+"px";
    miaouss.style.top=posMiaoussY+"px";
    miaouss.style.left=posMiaoussX+"px";

    imgPikachu.setAttribute("src","assets/img/"+pokemon1+direction+".png");
    imgMiaouss.setAttribute("src","assets/img/"+pokemon2+direction+".png");

    document.body.onkeydown = "none";

    DEATH.style.display="block";


  }, 10000);

}

formStart.onkeyup=function(event)
{
  btnStart.disabled=false;
    if(event.key=="Enter")
        {
          EnterPokemonName();
        }

}

function EnterPokemonName()
{
  document.body.onkeydown = deplacement;
  btnStart.disabled=true;
  grass.style.display="block";
  formStart.style.display="none";
  pikachu.title=inputNamePokemon1.value;
  miaouss.title=inputNamePokemon2.value;


}

function deplacement(event)
{

  if(event.key=="ArrowDown" || event.key=="s" )
  {
    posPikaY+=mouvement1;
    posMiaoussY+=mouvement2;
    direction="Down";

  }
  else if(event.key=="ArrowRight" || event.key=="d" )
  {
      posPikaX+=mouvement1;
      posMiaoussX+=mouvement2;
      direction="Right";

  }

  else if(event.key=="ArrowLeft" || event.key=="q")
  {
      posPikaX-=mouvement1;
      posMiaoussX-=mouvement2;
      direction="Left";

  }

  else if(event.key=="ArrowUp" || event.key=="z")
  {

      posPikaY-=mouvement1;
      posMiaoussY-=mouvement2;
      direction="Up";

  }
  if(posPikaY>=660)
  {
    posPikaY=660;
  }
  else if(posPikaY<=0)
  {posPikaY=0;}
  if(posPikaX>=660)
  {
    posPikaX=660;
  }
  else if (posPikaX<=0) {
      posPikaX=0;
  }

  if(posMiaoussY>=660)
  {
    posMiaoussY=660;
  }
  else if(posMiaoussY<=0)
  {posMiaoussY=0;}
  if(posMiaoussX>=660)
  {
    posMiaoussX=660;
  }
  else if (posMiaoussX<=0) {
      posMiaoussX=0;
  }

  pikachu.style.top=posPikaY+"px";
  pikachu.style.left=posPikaX+"px";
  miaouss.style.top=posMiaoussY+"px";
  miaouss.style.left=posMiaoussX+"px";
  imgPikachu.setAttribute("src","assets/img/"+pokemon1+direction+".png");
  imgMiaouss.setAttribute("src","assets/img/"+pokemon2+direction+".png");

}
