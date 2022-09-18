import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PuestoService {

  URL = 'http://localhost:8080/puesto';

  constructor(private httpClient: HttpClient) { }

  public nuevo(puesto: string): Observable<any> {
    return this.httpClient.post<any>(this.URL + '/nuevo', puesto);
  }

  public listarPuestos(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.URL );
  }
}
