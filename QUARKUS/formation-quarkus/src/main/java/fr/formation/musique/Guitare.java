package fr.formation.musique;

import jakarta.enterprise.context.ApplicationScoped;

// Instance managée par Quarkus
@ApplicationScoped
public class Guitare {
    public String son() {
        return "GLINK GLINK GLINK";
    }
}
