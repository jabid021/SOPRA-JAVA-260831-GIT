import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthResponse } from '../model/auth-response';
import { HttpClient } from '@angular/common/http';
import { Auth } from '../model/auth';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _token: string = "";

  public get token(): string {
    return this._token;
  }

  public set token(value: string) {
    this._token = value;
  }

  constructor(private http: HttpClient) {
  }

  public login(auth: Auth): Observable<AuthResponse> {
    return this.http.post<AuthResponse>('http://localhost:8080/api/user/auth', auth);
  }
}
