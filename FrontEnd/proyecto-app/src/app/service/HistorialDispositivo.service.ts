import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HistorialDispositivo} from '../models/historialDispositivo';

@Injectable({
  providedIn: 'root'
})
export class HistorialDispositivoService {
  URL = 'http://localhost:8080/historialDispositivo';
  constructor(private httpClient: HttpClient) { }

  public listarHistorialDispositivo(id: number): Observable<HistorialDispositivo []> {
    return this.httpClient.get<HistorialDispositivo[]>(this.URL + `/listar/${id}`);
  }
}
