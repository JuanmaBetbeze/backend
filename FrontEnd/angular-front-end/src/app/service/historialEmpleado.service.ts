import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HistorialEmpleado} from '../models/historialEmpleado';

@Injectable({
  providedIn: 'root'
})
export class HistorialEmpleadoService {
  URL = 'http://localhost:8080/historialEmpleado';
  constructor(private httpClient: HttpClient) { }

  public listarHistorialEmpleados(id: number | undefined): Observable<HistorialEmpleado[]> {
    return this.httpClient.get<HistorialEmpleado[]>(this.URL + `/listar/${id}`);
  }
}
