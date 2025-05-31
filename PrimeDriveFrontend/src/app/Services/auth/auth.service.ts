import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginResponse } from '../../Models/auth/loginResponse.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'https://localhost:8443/api/authentication';
  private httpClient = inject(HttpClient);

  public login(username: string, password: string): Observable<LoginResponse> {
    return this.httpClient.post<LoginResponse>(
      `${this.apiUrl}/login`,
      {
        username,
        password,
      },
      {
        withCredentials: true,
      }
    );
  }

  public logout(): Observable<void> {
    return this.httpClient.post<void>(
      `${this.apiUrl}/logout`,
      {},
      {
        withCredentials: true,
      }
    );
  }

  public isAuthenticated(): Observable<boolean> {
    return this.httpClient.get<boolean>(`${this.apiUrl}/check-session`, {
      withCredentials: true,
    });
  }
}
