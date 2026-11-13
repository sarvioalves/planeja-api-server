package io.github.sa.planeja.dominio.cartao.dto;

import io.github.sa.planeja.dominio.cartao.model.BandeiraCartao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Data Access Object
public record CartaoForm(
        @NotBlank(message = "Campo obrigatorio.")
        String nome,
        @NotNull(message = "Campo obrigatorio.")
        BandeiraCartao bandeira) {
}
