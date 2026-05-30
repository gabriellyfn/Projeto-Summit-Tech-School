import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080'; // URL base da nossa API

  constructor(private http: HttpClient) { }

  login(credentials: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, credentials).pipe(
      tap(response => {
        // Se a resposta tiver um token, armazena no localStorage
        if (response && response.token) {
          localStorage.setItem('authToken', response.token);
        }
      })
    );
  }

  // Método para pegar o token armazenado
  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  // Método para verificar se o usuário está logado
  isLoggedIn(): boolean {
    return this.getToken() !== null;
  }

  // Método para fazer logout
  logout(): void {
    localStorage.removeItem('authToken');
  }
}
