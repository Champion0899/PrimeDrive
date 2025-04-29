import {inject, Inject, Injectable} from '@angular/core';
import * as http from 'node:http';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Fahrzeug} from './fahrzeug.model';

@Injectable({ providedIn: 'root' })
export class FahrzeugService{

  private fahrzeugUrl = 'api/fahrzeuge';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  private http = inject(HttpClient)

  getFahrzeuge(): Observable<Fahrzeug[]> {
    return this.http.get<Fahrzeug[]>(this.fahrzeugUrl)
  }
}
