import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginResponse } from '../../Models/loginResponse.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/authentication';

  private httpClient = inject(HttpClient);

  public login(username: string, password: string): Observable<LoginResponse> {
    return this.httpClient.post<LoginResponse>(`${this.apiUrl}/login`, {
      username,
      password,
    });
  }

  public logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
  }

  public isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  public getToken(): string | null {
    return localStorage.getItem('token');
  }
}
