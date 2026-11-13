package io.github.sa.planeja.dominio.lancamento.dto;

import io.github.sa.planeja.dominio.lancamento.model.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoDetalhes(
        String id,
        LocalDate data,
        String descricao,
        BigDecimal valor,
        TipoLancamento tipo,
        String categoriaId,
        String categoriaNome,
        String cartaoId,
        String cartaoNome
) {
}
