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
    if (this.loginForm.valid) {
      this.showFormError = false;

      this.authService.login(this.loginForm.value).subscribe({
        next: (response) => {
          console.log('Evento NEXT do subscribe foi chamado!', response); // LOG DE DIAGNÓSTICO
          this.router.navigate(['/dashboard']);
        },
        error: (err) => {
          console.error('Evento ERROR do subscribe foi chamado!', err); // LOG DE DIAGNÓSTICO
          alert('Falha no login. Verifique suas credenciais.');
        },
        complete: () => {
          console.log('Evento COMPLETE do subscribe foi chamado!'); // LOG DE DIAGNÓSTICO
        }
      });
    } else {
      this.loginForm.markAllAsTouched();
      this.showFormError = true;
    }
  }
}
