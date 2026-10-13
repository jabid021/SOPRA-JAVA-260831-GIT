let inputId = document.getElementById("input-id");
let inputPrenom = document.getElementById("input-prenom");
let inputNom = document.getElementById("input-nom");

function affichePersonne(index)
{

let id = idTab.rows[index].cells[0].textContent;
let prenom = idTab.rows[index].cells[1].textContent;
let nom = idTab.rows[index].cells[2].textContent;

affichePersonneH1.innerHTML = `${id} - ${prenom} ${nom}`;
}


btnAjouter.onclick = ajouterLigneTableau;

for(balise of document.getElementsByClassName("input-tab"))
{
  balise.onkeyup=function(event)
  {
    if(inputId.value=="" || inputPrenom.value=="" || inputNom.value=="")
    {
      btnAjouter.disabled=true;
      statutForm.style.backgroundColor="red";
    }
    else
    {
        btnAjouter.disabled=false;
        statutForm.style.backgroundColor="green";
        if(event.key=="Enter")
        {
          ajouterLigneTableau();
        }
    }
  }
}


function ajouterLigneTableau()
{

  idTab.innerHTML+= `<tr><td>${inputId.value}</td><td>${inputPrenom.value}</td><td>${inputNom.value}</td><td><input onClick="affichePersonne(${idTab.rows.length})" type="button" value="Afficher"></td></tr>`;
  inputId.value="";
  inputPrenom.value="";
  inputNom.value="";
  btnAjouter.disabled=true;
  statutForm.style.backgroundColor="red";
}
