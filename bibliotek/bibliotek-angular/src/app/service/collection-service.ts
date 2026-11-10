import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { fetchEventSource } from '@microsoft/fetch-event-source';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { CollectionDto } from '../dto/collection-dto';
import { EntityCreatedOrUpdatedDto } from '../dto/entity-created-or-updated-dto';
import { AuthService } from './auth-service';

@Injectable({
  providedIn: 'root',
})
export class CollectionService {
  private http: HttpClient = inject(HttpClient);
  private authService: AuthService = inject(AuthService);
  private apiUrl: string = '/collection';

  public findAll(): Observable<CollectionDto[]> {
    return this.http.get<CollectionDto[]>(this.apiUrl);
  }

  public changes(): Observable<void> {
    return new Observable<void>((subscriber) => {
      const controller = new AbortController();

      fetchEventSource(`${ environment.apiUrl }${ this.apiUrl }/events`, {
        headers: {
          Authorization: `Bearer ${ this.authService.token }`
        },

        signal: controller.signal,

        onmessage(event) {
          if (event.data === 'ready' || event.data === 'changed') {
            subscriber.next();
          }
        }
      });

      return () => controller.abort();
    });
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
