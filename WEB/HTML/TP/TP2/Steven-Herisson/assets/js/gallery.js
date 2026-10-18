async function chargerGalerie() {
    const response = await fetch("../assets/data/gallery.json");

    if (!response.ok) {
        throw new Error(`Erreur HTTP : ${response.status}`);
    }

    const images = await response.json();
    const galerie = document.getElementById("gallery");

    images.forEach(chemin => {
        const img = document.createElement("img");

        img.src = "../" + chemin;
        // img.alt = decodeURIComponent(chemin.split("/").pop()).replace(/\.[^.]+$/, "");
        img.loading = "lazy";
        img.classList.add("zoomable");

        galerie.appendChild(img);
    });
}

chargerGalerie().catch(console.error);