import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { EntityCreatedOrUpdatedDto } from '../dto/entity-created-or-updated-dto';
import { SubscribeRequestDto } from '../dto/subscribe-request-dto';

@Injectable({
  providedIn: 'root',
})
export class UtilisateurService {
  private http: HttpClient = inject(HttpClient);

  public subscribe(request: SubscribeRequestDto): Promise<void> {
    return new Promise((resolve, reject) => {
      this.http.post<EntityCreatedOrUpdatedDto>('/utilisateur/inscription', request).subscribe({
        // next => si la réponse est OK
        next: resp => {
          resolve();
        },

        // error => si la réponse est KO (30X, 40X, 50X)
        error: err => reject(err)
      });
    })
  }
}
