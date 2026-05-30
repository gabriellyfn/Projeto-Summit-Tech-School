import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

export interface PagedOcorrencias {
  content: any[];
  totalPages: number;
  totalElements: number;
}

@Injectable({
  providedIn: 'root'
})
export class OcorrenciaService {

  private apiUrl = 'http://localhost:8080/ocorrencias';

  constructor(private http: HttpClient, private authService: AuthService) { }

  getOcorrencias(): Observable<PagedOcorrencias> {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<PagedOcorrencias>(`${this.apiUrl}?size=1000`, { headers });
  }

  salvarOcorrencia(dados: any): Observable<any> {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.post<any>(this.apiUrl, dados, { headers });
  }
}
