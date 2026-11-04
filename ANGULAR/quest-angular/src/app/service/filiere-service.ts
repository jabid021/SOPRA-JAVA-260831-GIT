import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Filiere } from '../model/filiere';

@Injectable({
  providedIn: 'root',
})
export class FiliereService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = "/filiere";

  public findAll(): Observable<Filiere[]> {
    return this.http.get<Filiere[]>(this.apiUrl);
  }
}
