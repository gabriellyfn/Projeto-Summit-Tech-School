import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DadosDashboard } from '../models/dashboard.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private apiUrl = 'http://localhost:8080/dashboard';

  constructor(private http: HttpClient, private authService: AuthService) { }

  getEstatisticas(inicio: string, fim: string): Observable<DadosDashboard> {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<DadosDashboard>(`${this.apiUrl}/estatisticas?inicio=${inicio}&fim=${fim}`, { headers });
  }
}
