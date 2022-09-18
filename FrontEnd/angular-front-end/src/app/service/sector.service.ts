import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NuevoUsuario } from '../models/nuevo-usuario';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SectorService {

  URL = 'http://localhost:8080/sector';

  constructor(private httpClient: HttpClient) { }

  public nuevo(sector: string): Observable<any> {
    return this.httpClient.post<any>(this.URL + '/nuevo', sector);
  }

  public listarSectores(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.URL );
  }
}
