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
  // Ano selecionado para exibir o gráfico mensal (por padrão o último ano com dados)
  selectedYear: string | null = null;
  // Dados mensais para o gráfico de linha do ano selecionado
  monthlyVolume: { mesIndex: number; mesLabel: string; total: number; percent: number }[] = [];
  // string com pontos SVG para a polyline (calculada a partir de monthlyVolume)
  monthlyPoints: string = '';

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

    // Preparar dados mensais para o ano selecionado (último ano disponível)
    this.selectedYear = anos.length ? anos[anos.length - 1] : new Date().getFullYear().toString();

    // inicializar contagem por mês para o ano selecionado
    const mesesCount = new Array(12).fill(0);
    ocorrencias.forEach(oc => {
      if (!oc.data) return;
      const partes = oc.data.split('-');
      const ano = partes[0];
      const mesNum = partes.length >= 2 ? parseInt(partes[1], 10) - 1 : NaN;
      if (ano === this.selectedYear && !isNaN(mesNum) && mesNum >= 0 && mesNum < 12) {
        mesesCount[mesNum]++;
      }
    });

    const maxMonthly = Math.max(...mesesCount) || 1;
    this.monthlyVolume = mesesCount.map((total, idx) => ({ mesIndex: idx, mesLabel: this.mesesAbreviados[idx], total, percent: (total / maxMonthly) * 100 }));

    // Calcular pontos para polyline SVG usando uma escala 0..100 (x em percent, y invertido)
    const n = this.monthlyVolume.length;
    const pointsArr: string[] = [];
    for (let i = 0; i < n; i++) {
      const x = n > 1 ? (i * (100 / (n - 1))) : 50;
      const y = 100 - this.monthlyVolume[i].percent; // inverter para coordenadas SVG (0 topo)
      pointsArr.push(`${x},${y}`);
    }
    this.monthlyPoints = pointsArr.join(' ');

    // Não criamos linhas de grade - o gráfico exibirá apenas anos (rótulos inferiores), totais (rótulos superiores) e barras.
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
