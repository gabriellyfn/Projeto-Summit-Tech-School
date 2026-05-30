import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { OcorrenciaService, PagedOcorrencias } from '../../services/ocorrencia.service';
import { DadosRankingAluno, DadosTotalCategoria, DadosVolumeAnual } from '../../models/dashboard.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.scss']
})
export class DashboardComponent implements OnInit {

  totalOcorrenciasMes: number = 0;
  topAlunos: DadosRankingAluno[] = [];
  totaisPorCategoria: DadosTotalCategoria[] = [];
  volumeAnual: DadosVolumeAnual[] = [];
  // valor máximo usado no cálculo do gráfico anual (contagem de ocorrências no ano com maior volume)
  volumeMax: number = 1;

  private mesesAbreviados = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];

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
        this.processarOcorrencias(ocorrencias);
      },
      error: (err) => {
        console.error('Erro ao buscar ocorrências:', err);
      }
    });
  }

  processarOcorrencias(ocorrencias: any[]) {
    this.totalOcorrenciasMes = ocorrencias.length;

    const contagemAlunos: { [nome: string]: { id: number, nome: string, quantidade: number } } = {};
    const contagemCategorias: { [categoria: string]: number } = {};
    const contagemMeses: { [mes: string]: number } = {};
    this.mesesAbreviados.forEach(mes => contagemMeses[mes] = 0);
    // Novo: contagem por ano para o gráfico de Volume Anual
    const contagemAnos: { [ano: string]: number } = {};

    let idSimulado = 1;

    ocorrencias.forEach(oc => {
      const nomeAluno = oc.aluno;
      if (nomeAluno) {
        if (!contagemAlunos[nomeAluno]) {
          contagemAlunos[nomeAluno] = { id: idSimulado++, nome: nomeAluno, quantidade: 0 };
        }
        contagemAlunos[nomeAluno].quantidade++;
      }

      const categoria = oc.categoriaOcorrencia || oc.tipoOcorrencia || 'NÃO DEFINIDA';
      if (!contagemCategorias[categoria]) {
        contagemCategorias[categoria] = 0;
      }
      contagemCategorias[categoria]++;

      if (oc.data) {
        const partesData = oc.data.split('-');
        if (partesData.length >= 2) {
          const mesNumerico = parseInt(partesData[1], 10) - 1;
          const nomeMes = this.mesesAbreviados[mesNumerico];
          if (nomeMes) {
            contagemMeses[nomeMes]++;
          }
        }
        // extrair ano (formato YYYY-mm-dd esperado)
        if (partesData.length >= 1) {
          const ano = partesData[0];
          if (ano) {
            contagemAnos[ano] = (contagemAnos[ano] || 0) + 1;
          }
        }
      }
    });

    const listaRanking = Object.values(contagemAlunos);
    listaRanking.sort((a, b) => b.quantidade - a.quantidade);
    this.topAlunos = listaRanking.slice(0, 5);

    this.totaisPorCategoria = Object.keys(contagemCategorias).map(cat => {
      return { categoria: cat, total: contagemCategorias[cat] };
    });
    this.totaisPorCategoria.sort((a, b) => b.total - a.total);

    // Preparar dados do gráfico mensal (mantido como antes)
    const maxOcorrenciasMes = Math.max(...Object.values(contagemMeses));
    const maxVolumeMes = maxOcorrenciasMes > 0 ? maxOcorrenciasMes : 1;

    // Preparar dados do gráfico anual: ordenar anos e calcular altura relativa
    const anos = Object.keys(contagemAnos).sort((a, b) => parseInt(a) - parseInt(b));
    const maxOcorrenciasAno = anos.length ? Math.max(...anos.map(a => contagemAnos[a])) : 1;
    const maxVolumeAno = maxOcorrenciasAno > 0 ? maxOcorrenciasAno : 1;

    // Gerar volumeAnual a partir de anos (label no campo 'mes' para manter compatibilidade com template)
    this.volumeMax = maxVolumeAno;
    this.volumeAnual = anos.map(ano => {
      const total = contagemAnos[ano];
      const altura = (total / this.volumeMax) * 100;
      return { mes: ano, total: total, height: altura } as any;
    });

    // Não criamos linhas de grade - o gráfico exibirá apenas anos (rótulos inferiores), totais (rótulos superiores) e barras.
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
