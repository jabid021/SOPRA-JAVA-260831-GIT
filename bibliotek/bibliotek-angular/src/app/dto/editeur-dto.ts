import { NationaliteEnum } from "../enumerator/nationalite-enum";

export interface EditeurDto {
    id: string;
    nom: string;
    pays: NationaliteEnum;
}
