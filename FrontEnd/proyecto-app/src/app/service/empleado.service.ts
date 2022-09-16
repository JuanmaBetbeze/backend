import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NuevoUsuario } from '../models/nuevo-usuario';
import { Observable } from 'rxjs';
import {Empleado} from '../models/empleado';
import {Dispositivo} from '../models/Dispositivo';


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
  public delete(id: number): Observable<any> {
    return this.httpClient.post(this.URL + '/delete', id);
  }
  public listarDispositivos(id: number): Observable<Dispositivo []> {
    return this.httpClient.get<Dispositivo []>(this.URL + `/dispositivos/${id}`);
  }
  public agregarDispositivos(id: number, idDispositivo: number, ejecutor: string): Observable<any> {
    return this.httpClient.post<any>(this.URL + `/dispositivos/asignar/${id}/${ejecutor}`, idDispositivo);
  }
  public quitarDispositivo(id: number, idDispositivo: number, ejecutor: string): Observable<any> {
    return this.httpClient.post<any>(this.URL + `/dispositivos/quitar/${id}/${ejecutor}`, idDispositivo);
  }
  public filtrarEmpleados(filtrarlist: string[]): Observable<Empleado []> {
    return this.httpClient.post<Empleado[]>(this.URL + '/filtrar', filtrarlist);
  }
}
