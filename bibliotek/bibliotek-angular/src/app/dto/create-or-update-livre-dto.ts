export interface CreateOrUpdateLivreDto {
    id: string;
    nom: string;
    publication: string;
    auteurId?: string;
    editeurId?: string;
    collectionId?: string;
}
