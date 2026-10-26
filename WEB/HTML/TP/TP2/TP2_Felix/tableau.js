const lignes = document.querySelectorAll("tr[data-carte]");

for (const ligne of lignes)
{
  ligne.addEventListener("mouseenter", function ()
  {
    document.body.style.backgroundImage = 'url("assets/images/carte/' + ligne.dataset.carte + '")';
  });

  ligne.addEventListener("mouseleave", function ()
  {
    document.body.style.backgroundImage = 'url("assets/images/carte/carte.png")';
  });
}

const musique = new Audio("assets/sounds/mexican_hat_dance.mp3");
const drapeauMexique = document.querySelector(".mexique .drapeau");

drapeauMexique.addEventListener("mouseenter", function ()
{
  musique.play();
});

drapeauMexique.addEventListener("mouseleave", function ()
{
  musique.pause();
  musique.currentTime = 0;
});
