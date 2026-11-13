package io.github.sa.planeja.dominio.lancamento.dto;

import io.github.sa.planeja.dominio.lancamento.model.TipoLancamento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LancamentoForm(
        @NotNull(message = "Campo obrigatorio.")
        UUID categoriaId,
        @NotBlank(message = "Campo obrigatório.")
        String descricao,
        @NotNull(message = "Campo obrigatorio.")
        TipoLancamento tipo,
        @NotNull(message = "Campo obrigatorio.")
        LocalDate data,
        @NotNull(message = "Campo obrigatorio.")
        @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero.")
        BigDecimal valor,
        UUID cartaoId
) {
}
