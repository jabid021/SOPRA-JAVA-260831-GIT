package fr.formation.musique;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Guitariste {
    @Inject // Injection de la dépendance
    private Guitare guitare;

    public void jouer() {
        System.out.println("Le guitariste joue " + this.guitare.son());
    }
}
