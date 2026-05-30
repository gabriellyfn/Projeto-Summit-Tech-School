import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { OcorrenciaService, PagedOcorrencias } from '../../services/ocorrencia.service';
import { DadosRankingAluno } from '../../models/dashboard.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrls: []
})
export class DashboardComponent implements OnInit {

  totalOcorrenciasMes: number = 0;
  topAlunos: DadosRankingAluno[] = [];

  constructor(
    private authService: AuthService,
    private router: Router,
    private ocorrenciaService: OcorrenciaService
  ) {}

  ngOnInit(): void {
    this.carregarOcorrencias();
  }

  carregarOcorrencias() {
    this.ocorrenciaService.getOcorrencias().subscribe({
      next: (response: PagedOcorrencias) => {
        const ocorrencias = response.content;
        console.log('Ocorrências recebidas:', ocorrencias);
        this.processarOcorrencias(ocorrencias);
      },
      error: (err) => {
        console.error('Erro ao buscar ocorrências:', err);
      }
    });
  }

  processarOcorrencias(ocorrencias: any[]) {
    this.totalOcorrenciasMes = ocorrencias.length;

    // Usando string (nome do aluno) como chave para o agrupamento
    const contagemAlunos: { [nome: string]: { id: number, nome: string, quantidade: number } } = {};

    // Vamos usar um id simulado para a tela, já que a API não retorna o ID do aluno
    let idSimulado = 1;

    ocorrencias.forEach(oc => {
      const nomeAluno = oc.aluno;

      if (nomeAluno) {
        if (!contagemAlunos[nomeAluno]) {
          contagemAlunos[nomeAluno] = { id: idSimulado++, nome: nomeAluno, quantidade: 0 };
        }
        contagemAlunos[nomeAluno].quantidade++;
      }
    });

    const listaRanking = Object.values(contagemAlunos);
    listaRanking.sort((a, b) => b.quantidade - a.quantidade);
    this.topAlunos = listaRanking.slice(0, 5);

    console.log('Top 5 Alunos Calculado:', this.topAlunos);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
