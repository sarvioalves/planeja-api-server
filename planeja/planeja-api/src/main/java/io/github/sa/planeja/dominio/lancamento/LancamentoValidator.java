package io.github.sa.planeja.dominio.lancamento;

import io.github.sa.planeja.common.validation.CampoInvalido;
import io.github.sa.planeja.common.validation.ValidationResult;
import io.github.sa.planeja.dominio.cartao.model.CartaoEntity;
import io.github.sa.planeja.dominio.categoria.model.CategoriaEntity;
import io.github.sa.planeja.dominio.lancamento.dto.LancamentoForm;
import io.github.sa.planeja.dominio.lancamento.model.TipoLancamento;
import org.springframework.stereotype.Component;

@Component
public class LancamentoValidator {

    public ValidationResult validar(
            LancamentoForm form, CategoriaEntity categoria, CartaoEntity cartao
    ){
        var result = ValidationResult.novo();
        if(categoria == null){
            result.add((new CampoInvalido("categoriaID", "Categoria não encontrada.")));
        } else if (Boolean.FALSE.equals(categoria.getAtivo())){
            result.add((new CampoInvalido("categoriaID", "Categoria inativa.")));
        }

        if(form.tipo() == TipoLancamento.RECEITA && cartao != null){
            result.add(new CampoInvalido("cartaoId", "Lançamento do tipo RECEITA não aceita cartão."));
        }

        if(form.tipo() == TipoLancamento.DESPESA && form.cartaoId() != null){
            if (cartao == null){
                result.add(new CampoInvalido("cartaoId", "Cartão não encontrado."));
            } else if (Boolean.FALSE.equals(cartao.getAtivo())) {
                result.add(new CampoInvalido("cartaoId", "Cartão inativo."));
            }
        }

        return result;
    }
}
