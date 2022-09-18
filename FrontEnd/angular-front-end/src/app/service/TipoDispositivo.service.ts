import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TipoDispositivoService {
  URL = 'http://localhost:8080/tipoDispositivo';

  constructor(private httpClient: HttpClient) { }

  public nuevo(tipo: string): Observable<any> {
    return this.httpClient.post<any>(this.URL + '/nuevo', tipo);
  }

  public listarTipos(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.URL);
  }
}
