import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';
import {NuevoUsuario} from '../models/nuevo-usuario';
import {Usuario} from '../models/Usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  productoURL = 'http://localhost:8080/auth/usuario';

  constructor(private httpClient: HttpClient) { }

  public listarUsuarios(): Observable<Usuario[]> {
    return this.httpClient.get<Usuario[]>(this.productoURL );
  }
  public delete(usuario: string): Observable<any> {
    return this.httpClient.post<any>(this.productoURL + `/eliminar`, usuario);
  }
  public save(usuario: Usuario): Observable<any> {
    return this.httpClient.post<any>(this.productoURL + '/nuevo', usuario);
  }

  public detail(id: number): Observable<Producto> {
    return this.httpClient.get<Producto>(this.productoURL + `/detail/${id}`);
  }

  public detailName(nombre: string): Observable<Producto> {
    return this.httpClient.get<Producto>(this.productoURL + `/detailname/${nombre}`);
  }



  public update(id: number, producto: Producto): Observable<any> {
    return this.httpClient.put<any>(this.productoURL + `update/${id}`, producto);
  }


}
