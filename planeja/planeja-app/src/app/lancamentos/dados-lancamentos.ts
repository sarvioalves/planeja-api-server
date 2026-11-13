export type tipoLancamento = 'RECEITA' | 'DESPESA'

export class DadosLancamentoForm {
    categoriaId!: string;
    data!: string;
    valor!: string;
    tipo!: tipoLancamento;
    cartaoId?: string | null;
}

export class DetalhesLancamento {
    id!: string;
    descricao!: string
    categoriaId!: string;
    categoriaNome!: string;
    data!: string;
    valor!: string;
    tipo!: tipoLancamento;
    cartaoId?: string | null;
    cartaoNome?: string;
}

export class FiltroLancamento {
    mes?: string;
    tipo?: tipoLancamento;
    categoriaId?: string;
    page?: number;
    size?: number;
}