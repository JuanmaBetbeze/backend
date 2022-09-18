import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MarcaService {
  URL = 'http://localhost:8080/marca';

  constructor(private httpClient: HttpClient) { }

  public nuevo(marca: string): Observable<any> {
    return this.httpClient.post<any>(this.URL + '/nuevo', marca);
  }

  public listarMarcas(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.URL);
  }
}
