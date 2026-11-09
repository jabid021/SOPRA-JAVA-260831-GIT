import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AvisDto } from '../dto/avis-dto';
import { EntityCreatedOrUpdatedDto } from '../dto/entity-created-or-updated-dto';

@Injectable({
  providedIn: 'root',
})
export class AvisService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = '/avis';

  public findAll(): Observable<AvisDto[]> {
    return this.http.get<AvisDto[]>(this.apiUrl);
  }

  public findById(id: number): Observable<AvisDto> {
    return this.http.get<AvisDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(avis: AvisDto): Observable<EntityCreatedOrUpdatedDto> {
    if (!avis.id) {
      return this.http.post<EntityCreatedOrUpdatedDto>(this.apiUrl, avis);
    }

    return this.http.put<EntityCreatedOrUpdatedDto>(`${ this.apiUrl }/${ avis.id }`, avis);
  }

  public deleteById(id: string): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ id }`);
  }
}
