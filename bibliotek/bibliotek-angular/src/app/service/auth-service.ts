import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthRequestDto } from '../dto/auth-request-dto';
import { AuthResponseDto } from '../dto/auth-response-dto';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http: HttpClient = inject(HttpClient);
  private _token: string = sessionStorage.getItem("token") ?? "";

  public get token(): string {
    return this._token;
  }

  public set token(value: string) {
    this._token = value;
    sessionStorage.setItem("token", value);
  }

  public auth(authRequest: AuthRequestDto): Observable<AuthResponseDto> {
    return this.http.post<AuthResponseDto>('/auth', authRequest);
  }

  public isLogged() {
    return !!this._token;
  }

  public resetAuth() {
    this._token = "";
    sessionStorage.removeItem("token");
  }
}
