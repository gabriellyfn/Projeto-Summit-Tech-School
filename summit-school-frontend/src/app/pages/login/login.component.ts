import { Component } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class LoginComponent {

  loginForm: FormGroup;
  showFormError = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      login: ['', Validators.required],
      senha: ['', Validators.required]
    });
  }

  onSubmit() {
    // 1. Log para garantir que o método está sendo chamado
    console.log('Botão clicado! Validando formulário...');

    // 2. Verifica se é válido
    if (this.loginForm.valid) {
      this.showFormError = false;
      console.log('Formulário válido. Enviando dados para API:', this.loginForm.value);

      this.authService.login(this.loginForm.value).subscribe({
        next: (response) => {
          console.log('Resposta da API (Sucesso):', response);
          alert('Login efetuado com sucesso!'); // Feedback visual temporário
          // this.router.navigate(['/dashboard']);
        },
        error: (err) => {
          console.error('Resposta da API (Erro):', err);
          alert('Falha no login. Verifique suas credenciais.');
        }
      });
    } else {
      console.log('Formulário inválido. Exibindo mensagens de erro.');
      this.loginForm.markAllAsTouched();
      this.showFormError = true;
    }
  }
}
