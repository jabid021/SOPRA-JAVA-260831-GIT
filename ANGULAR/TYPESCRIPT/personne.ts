
// Interface Personne
interface Personne {
    nom: string;
    prenom: string;
    age?: number;
}

// Utilisation de l'interface
const maPersonne: Personne = {
    nom: "PERROUAULT",
    prenom: "Jérémy",
    age: 29
};

maPersonne.prenom = "Prenom 2";

console.log(`Le nom de la personne est ${ maPersonne.nom }`);
