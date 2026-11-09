import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuteurDto } from '../dto/auteur-dto';
import { EntityCreatedOrUpdatedDto } from '../dto/entity-created-or-updated-dto';

@Injectable({
  providedIn: 'root',
})
export class AuteurService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = '/auteur';

  public findAll(): Observable<AuteurDto[]> {
      return this.http.get<AuteurDto[]>(this.apiUrl);
  }

  public findById(id: number): Observable<AuteurDto> {
    return this.http.get<AuteurDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(auteur: AuteurDto): Observable<EntityCreatedOrUpdatedDto> {
    if (!auteur.id) {
      return this.http.post<EntityCreatedOrUpdatedDto>(this.apiUrl, auteur);
    }

    return this.http.put<EntityCreatedOrUpdatedDto>(`${ this.apiUrl }/${ auteur.id }`, auteur);
  }

  public deleteById(id: string): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ id }`);
  }
}
