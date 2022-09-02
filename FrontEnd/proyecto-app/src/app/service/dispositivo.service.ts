import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Dispositivo} from '../models/Dispositivo';

@Injectable({
  providedIn: 'root'
})
export class DispositivoService {
  URL = 'http://localhost:8080/dispositivo';

  constructor(private httpClient: HttpClient) { }

  public nuevo(dispositivo: Dispositivo): Observable<any> {
    return this.httpClient.post<any>(this.URL + '/nuevo', dispositivo);
  }

  public listarEmpleados(): Observable<Dispositivo []> {
    return this.httpClient.get<Dispositivo[]>(this.URL );
  }
}
