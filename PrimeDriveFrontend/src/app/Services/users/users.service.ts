import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../Models/vehicles/user.interface';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private apiUrl = 'https://localhost:8443/api/users';
  private httpClient = inject(HttpClient);

  public getCurrentUser(): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/current`, {
      withCredentials: true,
    });
  }

  public getUserById(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/${id}`, {
      withCredentials: true,
    });
  }
}
