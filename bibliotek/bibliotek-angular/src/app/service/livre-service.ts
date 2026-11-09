import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { CreateOrUpdateLivreDto } from '../dto/create-or-update-livre-dto';
import { EntityCreatedOrUpdatedDto } from '../dto/entity-created-or-updated-dto';
import { LivreDto } from '../dto/livre-dto';

@Injectable({
  providedIn: 'root',
})
export class LivreService {
  private http: HttpClient = inject(HttpClient);

  private apiUrl: string = '/livre';
  private refresh$: Subject<void> = new Subject<void>();

  public findAll(): Observable<LivreDto[]> {
    return this.http.get<LivreDto[]>(this.apiUrl);
  }

  public findById(id: number): Observable<LivreDto> {
    return this.http.get<LivreDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(livre: CreateOrUpdateLivreDto): Observable<EntityCreatedOrUpdatedDto> {
    if (!livre.id) {
      return this.http.post<EntityCreatedOrUpdatedDto>(this.apiUrl, livre);
    }

    return this.http.put<EntityCreatedOrUpdatedDto>(`${ this.apiUrl }/${ livre.id }`, livre);
  }

  public deleteById(id: string): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ id }`);
  }
}
