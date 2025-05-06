import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Fahrzeug} from './fahrzeug.model';

@Injectable({providedIn: 'root'})
export class FahrzeugService {

  private fahrzeugUrl = 'http://localhost:8080/api/fahrzeuge';
  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private http = inject(HttpClient)

  getFahrzeuge(): Observable<Fahrzeug[]> {
    return this.http.get<Fahrzeug[]>(this.fahrzeugUrl, this.httpOptions)
  }

  getFahrzeugById(id: string): Observable<Fahrzeug> {
    return this.http.get<Fahrzeug>(`${this.fahrzeugUrl}/${id}`);
  }

}
