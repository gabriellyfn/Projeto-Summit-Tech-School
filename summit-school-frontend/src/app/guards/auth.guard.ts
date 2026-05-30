import { inject } from '@angular/core';
import { Router, CanActivateFn } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // Verifica se o usuário está logado usando o AuthService
  if (authService.isLoggedIn()) {
    return true; // Permite o acesso à rota
  } else {
    // Redireciona para o login se não estiver autenticado
    router.navigate(['/login']);
    return false; // Bloqueia o acesso
  }
};
