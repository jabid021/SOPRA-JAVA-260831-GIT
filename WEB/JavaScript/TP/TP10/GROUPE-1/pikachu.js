//1) saisir le nom du pokemon et valider avec le bouton ou enter (le nom ne doit pas etre vide !)
//2) Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3) Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4) Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5) Verifier que pikachu ne sort pas de la div grass

var posPikaX=pikachu.style.width;
var posPikaY=pikachu.style.top;
var posMiaoussX=miaouss.style.width.content;
var posMiaoussY=miaouss.style.top.content;

//console.log(posPikaX);

var posPikaX=0;
var posPikaY=50;
var posMiaoussX=0;
var posMiaoussY=0;
 var  nbPikachu=15;
 var step=46;
 var pix=0;

 var pikaImgLoaded;

  var themeplayed;


var mouvement=30;
var mouvement_terrassement=10;
var pokemon1="pikachu";
var pokemon2="miaouss";
var direction="Down";
imgPikachu.setAttribute("src","assets/img/"+pokemon1+direction+".png");
imgMiaouss.setAttribute("src","assets/img/"+pokemon2+direction+".png");



var varTimer;

function Game_Over()
{
  themePokemon.pause();
  clearInterval(varInterval);
  posPikaX=330;
  posPikaY=330;
  posMiaoussX=posPikaX;
  posMiaoussY=posPikaY;

  pikachu.style.top=posPikaY+"px";
  pikachu.style.left=posPikaX+"px";
  miaouss.style.top=posMiaoussY+"px";
  miaouss.style.left=posMiaoussX+"px";

  imgPikachu.setAttribute("src","assets/img/"+pokemon1+direction+".png");
  imgMiaouss.setAttribute("src","assets/img/"+pokemon2+direction+".png");

  document.body.onkeydown = "none";


  btnSubtitution.style.display="block";

  imgMiaouss.setAttribute("src","assets/img/"+pokemon2+"Up.png");






  //  pika=document.getElementsByClassName("pikachu");
    //console.log(pika);





    /*for(pika of document.getElementsByClassName("pikachu"))
    {


    }*/


}

function clonagePikachu()
 {


   for (let i=1; i<=nbPikachu; i++)
   {
     pikachu.innerHTML+=`<img class="Pikachu" src="assets/img/pikachuDownLeftFoot.png" style="left:${pix}px;">`;
     pix+=step;

   }


   //varTimer = setTimeout({}, 5000);
   varPikachus = setInterval(grandTerrassement,1000);
   varstopPikachus = setTimeout(() =>{
     if(posMiaoussY==posPikaY)
   {
     if (themeplayed==="SNK")
     {
       themeSNK.pause();
     }
     else if (themeplayed==="naruto") {
       themeNaruto.pause();
     }
     alert("FIN");
     alert("GOLEM");
     clearInterval(varPikachus);
     pikachu.innerHTML="";
     miaouss.innerHTML="";
   }},66000);
 }

function changechrono()
{

   let [minute,seconde]=chrono.textContent.split(":").map(Number);
   seconde--;
   if (minute ==0 && seconde ==0)
   {

     minute =0;
    seconde =0;
   }
   else {
    if(seconde==00)
    {
      minute--;
      seconde=59;
    }
    if(minute==0)
    {
      minute=0;

    }
   }

   secondes=seconde;
   minutes=minute;
     if(seconde<10)
     {
       secondes=`0${seconde}`;
     }
     if (minute<10)
     {
       minutes=`0${minute}`;
     }

  chrono.innerHTML=`${minutes}:${secondes}`;
  let [min,sec]=chrono.textContent.split(":").map(Number);
}

btnStart.onclick=function()
{
  EnterPokemonName();
}

btnTerra.onclick=function()
{
  themeplayed="SNK";
  themeSNK.play();
  btnTerra.style.display="none";
  btnMulticlonage.style.display="none";
  clonagePikachu();
}

btnMulticlonage.onclick=function()
{
  themeplayed="naruto";
  btnMulticlonage.style.display="none";
  btnTerra.style.display="none";
  multiclonage.play();
  multiclonage.addEventListener("ended", (event) => {
    clonagePikachu();
    themeNaruto.play();
});
  

}


btnSubtitution.onclick=function()
{
  pikachu.innerHTML="";
  chrono.style.display="none";
  pikachu.style.top=0+"px";
  pikachu.style.left=0+"px";

  pikachu.innerHTML+=`<img class="Pikachu" src="assets/img/pikachuDownLeftFoot.png">`;

  pikaImgLoaded="pikachuDownLeftFoot";
  //imgPikachu.setAttribute("src","assets/img/pikachuDownLeftFoot.png");

  btnSubtitution.style.display="none";
  btnTerra.style.display="block";
  btnMulticlonage.style.display="block";

  //varTimer = setTimeout({}, 5000);

}

formStart.onkeyup=function(event)
{
  if (inputNamePokemon1.value!="" && inputNamePokemon2.value!="" && chronoTime.value!="")
  {
    btnStart.disabled=false;
    if(event.key=="Enter")
        {
          EnterPokemonName();
        }
      }

}

function timeToMs(min, sec)
{
  return (min*60+sec)*1000;
}


function EnterPokemonName()
{
  const [m,s]=chronoTime.value.split(":").map(Number);
  chrono.innerHTML=`${m}:${s}`;
  let totaltime=timeToMs(m,s);
  varInterval = setInterval(changechrono,1000);
  varTimer = setTimeout(Game_Over, totaltime);

  themePokemon.play();

  document.body.onkeydown = deplacement;
  btnStart.disabled=true;
  grass.style.display="block";
  chrono.style.display="block";
  formStart.style.display="none";
  pikachu.title=inputNamePokemon1.value;
  miaouss.title=inputNamePokemon2.value;

}


function grandTerrassement()
{

  pikaImgLoaded = (pikaImgLoaded==="assets/img/pikachuDownLeftFoot.png") ? "assets/img/pikachuDownRightFoot.png" : "assets/img/pikachuDownLeftFoot.png";


posPikaY=parseInt(pikachu.style.top)
posMiaoussY=parseInt(miaouss.style.top)

console.log(`la pose pikacchu y enter : ${posPikaY}`);
console.log(`la pose miaouss y enter : ${posMiaoussY}`);

    posPikaY+=mouvement_terrassement;
    posMiaoussY+=mouvement_terrassement;
    direction_pika="Down";
    direction_miaouss="Up";

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
  console.log(posPikaY);
  console.log(posMiaoussY);
  /*pikachu.style.top=posPikaY+"px";
  pikachu.style.left=posPikaX+"px";
  miaouss.style.top=posMiaoussY+"px";
  miaouss.style.left=posMiaoussX+"px";
  imgPikachu.setAttribute("src","assets/img/"+pokemon1+direction+".png");
  imgMiaouss.setAttribute("src","assets/img/"+pokemon2+direction+".png");*/


  //pikachu.style.top=(parseInt(pikachu.style.top)+posPikaY)+"px";
  //miaouss.style.top=(parseInt(miaouss.style.top)+posMiaoussY)+"px";

  pikachu.style.top=posPikaY+"px";
  miaouss.style.top=posMiaoussY+"px";




  console.log(`la pose pikacchu y after : ${pikachu.style.top}`);
  console.log(`la pose miaouss y after : ${miaouss.style.top}`);

  var list = document.getElementsByTagName("img");
  for (pika of list)
  {
  pika.setAttribute("src",pikaImgLoaded);
  }

  //imgPikachu.setAttribute("src",pikaImgLoaded);
  imgMiaouss.setAttribute("src","assets/img/"+pokemon2+direction_miaouss+".png");



}

function deplacement(event)
{
  if(event.key=="ArrowDown" || event.key=="s" )
  {
    posPikaY+=mouvement;
    posMiaoussY+=mouvement;
    direction="Down";

  }
  else if(event.key=="ArrowRight" || event.key=="d" )
  {
      posPikaX+=mouvement;
      posMiaoussX+=mouvement;
      direction="Right";

  }

  else if(event.key=="ArrowLeft" || event.key=="q")
  {
      posPikaX-=mouvement;
      posMiaoussX-=mouvement;
      direction="Left";

  }

  else if(event.key=="ArrowUp" || event.key=="z")
  {

      posPikaY-=mouvement;
      posMiaoussY-=mouvement;
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

  if(posMiaoussY>=600)
  {
    posMiaoussY=600;
  }
  else if(posMiaoussY<=10)
  {posMiaoussY=10;}
  if(posMiaoussX>=600)
  {
    posMiaoussX=600;
  }
  else if (posMiaoussX<=10) {
      posMiaoussX=10;
  }

  /*pikachu.style.top=posPikaY+"px";
  pikachu.style.left=posPikaX+"px";
  miaouss.style.top=posMiaoussY+"px";
  miaouss.style.left=posMiaoussX+"px";
  imgPikachu.setAttribute("src","assets/img/"+pokemon1+direction+".png");
  imgMiaouss.setAttribute("src","assets/img/"+pokemon2+direction+".png");*/


  pikachu.style.top=posPikaY+"px";
  pikachu.style.left=posPikaX+"px";
  miaouss.style.top=posMiaoussY+"px";
  miaouss.style.left=posMiaoussX+"px";


  imgPikachu.setAttribute("src","assets/img/"+pokemon1+direction+".png");
  imgMiaouss.setAttribute("src","assets/img/"+pokemon2+direction+".png");

}
