package io.github.sa.planeja.dominio.dashboard.dto;

import java.math.BigDecimal;

public record ResumoFinanceiro (
        BigDecimal receitas,
        BigDecimal despesa,
        BigDecimal saldo
){
}
