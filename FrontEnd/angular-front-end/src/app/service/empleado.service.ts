import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NuevoUsuario } from '../models/NuevoUsuario';
import { Observable } from 'rxjs';
import {EmpleadoNuevo} from '../models/EmpleadoNuevo';
import {DispositivoNuevo} from '../models/DispositivoNuevo';


@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  URL = 'http://localhost:8080/empleados';

  constructor(private httpClient: HttpClient) { }

  public nuevo(empleado: EmpleadoNuevo): Observable<any> {
    return this.httpClient.post<any>(this.URL + '/nuevo', empleado);
  }

  public listarEmpleados(): Observable<EmpleadoNuevo []> {
    return this.httpClient.get<EmpleadoNuevo[]>(this.URL );
  }
  public detail(id: number): Observable<EmpleadoNuevo> {
    return this.httpClient.get<EmpleadoNuevo>(this.URL + `/detail/${id}`);
  }
  public update(id: number, empleado: EmpleadoNuevo): Observable<any> {
    return this.httpClient.put<any>(this.URL + `/update/${id}`, empleado);
  }
  public delete(id: number | undefined): Observable<any> {
    return this.httpClient.post(this.URL + '/delete', id);
  }
  public listarDispositivos(id: number): Observable<DispositivoNuevo []> {
    return this.httpClient.get<DispositivoNuevo []>(this.URL + `/dispositivos/${id}`);
  }
  public agregarDispositivos(id: number, idDispositivo: number | undefined, ejecutor: string): Observable<any> {
    return this.httpClient.post<any>(this.URL + `/dispositivos/asignar/${id}/${ejecutor}`, idDispositivo);
  }
  public quitarDispositivo(id: number, idDispositivo: number, ejecutor: string): Observable<any> {
    return this.httpClient.post<any>(this.URL + `/dispositivos/quitar/${id}/${ejecutor}`, idDispositivo);
  }
  public filtrarEmpleados(filtrarlist: string[]): Observable<EmpleadoNuevo []> {
    return this.httpClient.post<EmpleadoNuevo[]>(this.URL + '/filtrar', filtrarlist);
  }
}
