import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { DashboardService } from '../../services/dashboard.service';
import { DadosDashboard, DadosRankingAluno } from '../../models/dashboard.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrls: []
})
export class DashboardComponent implements OnInit {

  // Variáveis para guardar os dados que virão da API
  totalOcorrenciasMes: number = 0;
  topAlunos: DadosRankingAluno[] = [];

  constructor(
    private authService: AuthService,
    private router: Router,
    private dashboardService: DashboardService
  ) {}

  ngOnInit(): void {
    // Definimos as datas de início e fim. Por enquanto, fixas para pegar todo o ano atual
    const hoje = new Date();
    const inicio = `${hoje.getFullYear()}-01-01`;
    const fim = `${hoje.getFullYear()}-12-31`;

    this.carregarEstatisticas(inicio, fim);
  }

  carregarEstatisticas(inicio: string, fim: string) {
    this.dashboardService.getEstatisticas(inicio, fim).subscribe({
      next: (dados: DadosDashboard) => {
        console.log('Dados recebidos do dashboard:', dados);
        // Atualiza a lista de alunos com os dados da API (pegando apenas os 5 primeiros)
        this.topAlunos = dados.rankingReincidentes.slice(0, 5);

        // Calcula o total somando as quantidades por categoria (ou usando um valor específico se a API retornar diferente)
        this.totalOcorrenciasMes = dados.totalPorCategoria.reduce((acc, curr) => acc + curr.total, 0);
      },
      error: (err) => {
        console.error('Erro ao buscar estatísticas do dashboard:', err);
      }
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
