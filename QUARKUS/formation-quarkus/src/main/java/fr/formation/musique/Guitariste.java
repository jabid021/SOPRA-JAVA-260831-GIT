package fr.formation.musique;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Guitariste {
    private String nom;

    @Inject // Injection de la dépendance
    private Guitare guitare;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Guitare getGuitare() {
        return guitare;
    }

    public void setGuitare(Guitare guitare) {
        this.guitare = guitare;
    }

    public void jouer() {
        System.out.println("Le guitariste joue " + this.guitare.son());
    }
}
