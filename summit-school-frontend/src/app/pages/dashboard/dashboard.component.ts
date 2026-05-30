import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { OcorrenciaService, PagedOcorrencias } from '../../services/ocorrencia.service';
import { DadosRankingAluno, DadosTotalCategoria } from '../../models/dashboard.model';

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
  totaisPorCategoria: DadosTotalCategoria[] = [];

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
    // 1. Total Geral
    this.totalOcorrenciasMes = ocorrencias.length;

    // 2. Variáveis para Agrupamento
    const contagemAlunos: { [nome: string]: { id: number, nome: string, quantidade: number } } = {};
    const contagemCategorias: { [categoria: string]: number } = {};

    let idSimulado = 1;

    // 3. Processar cada ocorrência
    ocorrencias.forEach(oc => {
      // --- Agrupamento por Aluno ---
      const nomeAluno = oc.aluno;
      if (nomeAluno) {
        if (!contagemAlunos[nomeAluno]) {
          contagemAlunos[nomeAluno] = { id: idSimulado++, nome: nomeAluno, quantidade: 0 };
        }
        contagemAlunos[nomeAluno].quantidade++;
      }

      // --- Agrupamento por Categoria ---
      // Pegamos o valor da categoria (pode vir como 'categoriaOcorrencia' ou 'tipoOcorrencia' dependendo da API)
      const categoria = oc.categoriaOcorrencia || oc.tipoOcorrencia || 'NÃO DEFINIDA';

      if (!contagemCategorias[categoria]) {
        contagemCategorias[categoria] = 0;
      }
      contagemCategorias[categoria]++;
    });

    // 4. Finalizar Top 5 Alunos
    const listaRanking = Object.values(contagemAlunos);
    listaRanking.sort((a, b) => b.quantidade - a.quantidade);
    this.topAlunos = listaRanking.slice(0, 5);

    // 5. Finalizar Totais por Categoria
    // Convertendo o objeto { "DISCIPLINAR": 2, "ATRASO": 1 } para o formato da interface DadosTotalCategoria[]
    this.totaisPorCategoria = Object.keys(contagemCategorias).map(cat => {
      return { categoria: cat, total: contagemCategorias[cat] };
    });

    // Ordenar categorias da maior para a menor
    this.totaisPorCategoria.sort((a, b) => b.total - a.total);

    console.log('Top 5 Alunos Calculado:', this.topAlunos);
    console.log('Totais por Categoria Calculados:', this.totaisPorCategoria);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
