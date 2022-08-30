import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NuevoUsuario } from '../models/nuevo-usuario';
import { Observable } from 'rxjs';
import {Empleado} from '../models/empleado';


@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  URL = 'http://localhost:8080/empleado';

  constructor(private httpClient: HttpClient) { }

  public nuevo(empleado: Empleado): Observable<any> {
    return this.httpClient.post<any>(this.URL + '/nuevo', empleado);
  }

  public listarSectores(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.URL );
  }
}
