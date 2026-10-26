  const bandeau = document.querySelector(".img-pub");
  const son = document.querySelector("#audiopub");
  son.volume = 0.2 ;

  function jouerSon() {
    son.currentTime = 0;
    son.play().catch((erreur) => {
    console.log("Lecture audio impossible :", erreur);
  });
  }

  bandeau.addEventListener("click", jouerSon);