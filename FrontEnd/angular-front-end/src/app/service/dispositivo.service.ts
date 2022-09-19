import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DispositivoNuevo} from '../models/DispositivoNuevo';

@Injectable({
  providedIn: 'root'
})
export class DispositivoService {
  URL = 'http://localhost:8080/dispositivo';

  constructor(private httpClient: HttpClient) { }

  public nuevo(dispositivo: DispositivoNuevo): Observable<any> {
    return this.httpClient.post<any>(this.URL + '/nuevo', dispositivo);
  }

  public listarDispositivos(): Observable<DispositivoNuevo []> {
    return this.httpClient.get<DispositivoNuevo[]>(this.URL );
  }
  public listarDispositivosSinAsignar(): Observable<DispositivoNuevo []> {
    return this.httpClient.get<DispositivoNuevo[]>(this.URL + '/SAsignar');
  }
  public detail(id: number): Observable<DispositivoNuevo> {
    return this.httpClient.get<DispositivoNuevo>(this.URL + `/detail/${id}`);
  }
  public update(id: number, dispositivo: DispositivoNuevo): Observable<any> {
    return this.httpClient.put<any>(this.URL + `/update/${id}`, dispositivo);
  }
  public delete(id: number | undefined): Observable<any> {
    return this.httpClient.post(this.URL + '/delete', id);
  }
  public filtrarDispositivo(filtrarlist: string[]): Observable<DispositivoNuevo []> {
    return this.httpClient.post<DispositivoNuevo[]>(this.URL + '/filtrar', filtrarlist);
  }
  public deshabilitar(id: number | undefined,ejecutor: string):Observable<any> {
    return this.httpClient.post(this.URL + `/deshabilitar/${ejecutor}`,id )
  }
}
