import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OcorrenciaService } from '../../services/ocorrencia.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './admin.html',
  styles: [`
    .admin-container { padding: 2rem; display: flex; justify-content: center; background-color: #121212; min-height: 100vh; color: #fff; }
    .admin-card { background: #1e1e1e; padding: 2rem; border-radius: 8px; width: 100%; max-width: 600px; box-shadow: 0 4px 6px rgba(0,0,0,0.3); }
    .admin-header h2 { margin-bottom: 0.5rem; color: #fff; }
    .admin-header p { color: #aaa; margin-bottom: 2rem; font-size: 0.9rem; }
    .form-group { margin-bottom: 1.5rem; display: flex; flex-direction: column; }
    .form-group label { margin-bottom: 0.5rem; color: #ccc; font-weight: 500; }
    .form-group input, .form-group select, .form-group textarea { padding: 0.75rem; background: #2a2a2a; border: 1px solid #333; border-radius: 4px; color: #fff; }
    .form-group input:focus, .form-group select:focus, .form-group textarea:focus { border-color: #007bff; outline: none; }
    .is-invalid { border-color: #dc3545 !important; }
    .error-text { color: #dc3545; font-size: 0.8rem; margin-top: 0.25rem; }
    .btn-submit { width: 100%; padding: 0.75rem; background: #007bff; color: white; border: none; border-radius: 4px; font-weight: bold; cursor: pointer; transition: background 0.2s; }
    .btn-submit:disabled { background: #555; cursor: not-allowed; }
    .alert { padding: 0.75rem; border-radius: 4px; margin-bottom: 1.5rem; font-size: 0.9rem; }
    .alert-success { background: #28a745; color: #fff; }
    .alert-danger { background: #dc3545; color: #fff; }
  `]
})
export class AdminComponent implements OnInit {
  ocorrenciaForm!: FormGroup;
  loading = false;
  successMessage = '';
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private ocorrenciaService: OcorrenciaService
  ) {}

  ngOnInit(): void {
    this.initForm();
  }

  private initForm(): void {
    this.ocorrenciaForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]],
      tipo: ['', [Validators.required]],
      descricao: ['', [Validators.required]]
    });
  }

  onSubmit(): void {
    if (this.ocorrenciaForm.invalid) {
      this.ocorrenciaForm.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.successMessage = '';
    this.errorMessage = '';

    this.ocorrenciaService.salvarOcorrencia(this.ocorrenciaForm.value).subscribe({
      next: (response: any) => {
        this.successMessage = 'Configuração salva com sucesso!';
        this.ocorrenciaForm.reset();
        this.loading = false;
      },
      error: (err: any) => {
        this.errorMessage = 'Erro ao salvar a configuração. Tente novamente.';
        this.loading = false;
      }
    });
  }
}
