//1) saisir le nom du pokemon et valider avec le bouton ou enter (le nom ne doit pas etre vide !)
//2) Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3) Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4) Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5) Verifier que pikachu ne sort pas de la div grass

var posX=0;
var posY=0;
var mouvement=30;
var pokemon="pikachu";
var direction="Down";
imgPikachu.setAttribute("src","assets/img/"+pokemon+direction+".png");
let inputname = document.getElementById("inputName")
let btnStart = document.getElementById("btnStart");
let idSalle = document.getElementById("salle");
let btnValidePlaces = document.getElementById("btnValiderPlaces");
let places = [];

inputname.onkeyup = function(event) {
    if (inputname.value.trim() == "") {
        btnStart.disabled = true;
    } else {
        btnStart.disabled = false;
    }

    if (event.key == "Enter" && !btnStart.disabled) {
        start();
    }
};

btnStart.onclick = start;

function start(){
  let start_form= document.getElementById("formStart");
  start_form.style.display="none";
  let grass = document.getElementById('grass');
  grass.style.display="block";
  let pika= document.getElementById("pikachu")
  pika.title =  inputname.value;
  document.body.onkeydown = deplacement;
  miseEnPlaceStandart();
 
}


function deplacement(event)
{
  if(event.key=="ArrowDown" || event.key=="s" )
  {
    if (posY<660){
      posY+= mouvement;

    }
    
    direction="down"
    
  }
  else if(event.key=="ArrowRight" || event.key=="d" )
  {
    if (posX<660){
      posX+=mouvement;

    }
    
    
    direction="Right"
  }

  else if(event.key=="ArrowLeft" || event.key=="q")
  {
    if (posX>0){
      posX-= mouvement;

    }
    
    
    direction="Left"
  }

  else if(event.key=="ArrowUp" || event.key=="z")
  {
    if (posY>0){
      posY-= mouvement;

    }
    
    
    direction="Up"
  }
  else if(event.key==" "){
    let existe = places.some(s => s.x === posX && s.y === posY);
    if (existe){
      let place = places.find(s => s.x === posX && s.y === posY);
      
      console.log('place')
      changerStatueSiege(place)
    }
    else{
      
      console.log('pas de place ')
    }

  }
  pikachu.style.top=posY+"px";
  pikachu.style.left=posX+"px";
  
  imgPikachu.setAttribute("src","assets/img/"+pokemon+direction+".png");

}
function miseEnPlaceStandart() {
    
    let startY = 240;
    let id=0;
    for (let i = 0; i < 7; i++) {
      let startX = 60;
      for (let k = 0;k<10;k++){
        id++;
        let place = document.createElement("div");
        let img = document.createElement("img");

        place.id = "place" + id;
        img.className = "place";
        img.src = "assets/img/place.png";

        place.style.position = "absolute";
        place.style.left = startX + "px";
        place.style.top = startY + "px";

        place.appendChild(img);
        idSalle.appendChild(place);
        console.log(idSalle);
        places.push({id:id,x:startX,y:startY,libre:true,select:false})
        startX += 60;
        
    }
    startY += 60;
    
  }
  console.log(places)
}
function changerStatueSiege(place){
  
  let div = document.getElementById(`place${place.id}`);
  let img = div.querySelector("img");
  if (place.libre){
  if (place.select){
        img.src="assets/img/place.png";
        place.select=false;
  }
  else{
    img.src= "assets/img/placeSelect.png";
    place.select=true;
  }} 
  
}
btnValidePlaces.addEventListener("click", () => {
    validerPlaces();
    btnValidePlaces.blur();
});
function validerPlaces(){
  console.log("on valide ")
    places.forEach(p => {
      if (p.select){
        p.libre=false;
        p.select = false;
        let div = document.getElementById(`place${p.id}`);
        let img = div.querySelector("img");
        img.src="assets/img/placeprise.png"
      }
      
    });   
}




