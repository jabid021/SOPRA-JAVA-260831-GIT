
let couleur = "lightgrey";

titrePrincipal.onmouseover = function()
{
  paragPrincipal.style.fontSize="35px";
  paragPrincipal.style.color="red";
};


titrePrincipal.onmouseout = function()
{
  paragPrincipal.style.fontSize="16px";
  paragPrincipal.style.color="black";
};

titrePrincipal.onmousemove = demoMove;


//La fonction demoMove est à l'ecoute de l'evenement mousemove sur le titre =>  Listener
function demoMove(event)
{
  console.log("move sur le titre");
}

document.querySelector("#content").ondblclick = function(event)
{
  console.log(event);
  couleur = (couleur=="lightgrey") ? "yellow" : "lightgrey";
  if(event.ctrlKey==true)
  {
    couleur="cyan";
  }
  content.style.backgroundColor=couleur;
}


choixColor.onfocus=function()
{
  pModifCouleur.style.border="solid black 2px";
}


choixColor.onblur=function()
{
  pModifCouleur.style.border="none";
}


choixColor.oninput=function()
{
  pModifCouleur.style.color=choixColor.value;
}

btnResetColor.onclick=function()
{
    pModifCouleur.style.color = "black";
    choixColor.value="#000000";
}
