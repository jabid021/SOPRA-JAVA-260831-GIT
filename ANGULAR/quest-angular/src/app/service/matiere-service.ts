import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Matiere } from '../model/matiere';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class MatiereService {
  private apiUrl: string = "/matiere";
  private http: HttpClient = inject(HttpClient);

  public findAll(): Observable<Matiere[]> {
    return this.http.get<Matiere[]>(this.apiUrl);
  }

  public save(matiere: Matiere): Observable<Matiere> {
    if (!matiere.id) {
      return this.http.post<Matiere>(this.apiUrl, matiere);
    }

    return this.http.put<Matiere>(`${ this.apiUrl }/${ matiere.id }`, matiere);
  }

  public remove(matiere: Matiere): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ matiere.id }`);
  }
}
