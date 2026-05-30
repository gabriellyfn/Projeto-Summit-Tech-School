import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

// Interface para a resposta paginada da API
export interface PagedOcorrencias {
  content: any[];
  totalPages: number;
  totalElements: number;
  // Adicione outras propriedades de paginação se necessário
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

    // A API de ocorrências é paginada, então pedimos a primeira página com um tamanho grande
    // para pegar todos os dados de uma vez (ajuste se tiver muitos dados)
    return this.http.get<PagedOcorrencias>(`${this.apiUrl}?size=1000`, { headers });
  }
}
