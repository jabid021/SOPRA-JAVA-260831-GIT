export interface LivreDto {
    id: string;
    nom: string;
    publication: string;
    auteurId?: string;
    auteurNom?: string;
    editeurId?: string;
    editeurNom?: string;
    collectionId?: string;
    collectionNom?: string;
}
