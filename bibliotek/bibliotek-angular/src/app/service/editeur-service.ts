import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EditeurDto } from '../dto/editeur-dto';
import { EntityCreatedOrUpdatedDto } from '../dto/entity-created-or-updated-dto';

@Injectable({
  providedIn: 'root',
})
export class EditeurService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = '/editeur';

  public findAll(): Observable<EditeurDto[]> {
      return this.http.get<EditeurDto[]>(this.apiUrl);
  }

  public findById(id: number): Observable<EditeurDto> {
    return this.http.get<EditeurDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(editeur: EditeurDto): Observable<EntityCreatedOrUpdatedDto> {
    if (!editeur.id) {
      return this.http.post<EntityCreatedOrUpdatedDto>(this.apiUrl, editeur);
    }

    return this.http.put<EntityCreatedOrUpdatedDto>(`${ this.apiUrl }/${ editeur.id }`, editeur);
  }

  public deleteById(id: string): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ id }`);
  }
}
