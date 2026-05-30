export interface DadosRankingAluno {
  id: number;
  nome: string;
  quantidade: number;
}

export interface DadosTotalCategoria {
  categoria: string;
  total: number;
}

export interface DadosDashboard {
  rankingReincidentes: DadosRankingAluno[];
  totalPorCategoria: DadosTotalCategoria[];
}

export interface DadosVolumeAnual {
  mes: string;
  total: number;
  height: number; // Nova propriedade para armazenar a altura calculada
}
