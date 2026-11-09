import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CollectionDto } from '../dto/collection-dto';
import { EntityCreatedOrUpdatedDto } from '../dto/entity-created-or-updated-dto';

@Injectable({
  providedIn: 'root',
})
export class CollectionService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = '/collection';

  public findAll(): Observable<CollectionDto[]> {
    return this.http.get<CollectionDto[]>(this.apiUrl);
  }

  public findById(id: number): Observable<CollectionDto> {
    return this.http.get<CollectionDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(collection: CollectionDto): Observable<EntityCreatedOrUpdatedDto> {
    if (!collection.id) {
      return this.http.post<EntityCreatedOrUpdatedDto>(this.apiUrl, collection);
    }

    return this.http.put<EntityCreatedOrUpdatedDto>(`${ this.apiUrl }/${ collection.id }`, collection);
  }

  public deleteById(id: string): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ id }`);
  }
}
