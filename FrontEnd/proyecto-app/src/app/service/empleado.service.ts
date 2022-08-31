import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NuevoUsuario } from '../models/nuevo-usuario';
import { Observable } from 'rxjs';
import {Empleado} from '../models/empleado';


@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  URL = 'http://localhost:8080/empleados';

  constructor(private httpClient: HttpClient) { }

  public nuevo(empleado: Empleado): Observable<any> {
    return this.httpClient.post<any>(this.URL + '/nuevo', empleado);
  }

  public listarEmpleados(): Observable<Empleado []> {
    return this.httpClient.get<Empleado[]>(this.URL );
  }
  public detail(id: number): Observable<Empleado> {
    return this.httpClient.get<Empleado>(this.URL + `/detail/${id}`);
  }
  public update(id: number, empleado: Empleado): Observable<any> {
    return this.httpClient.put<any>(this.URL + `/update/${id}`, empleado);
  }

}
