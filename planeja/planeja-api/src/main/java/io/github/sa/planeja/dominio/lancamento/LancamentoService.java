package io.github.sa.planeja.dominio.lancamento;

import io.github.sa.planeja.common.exceptions.RegistroNaoEncontradoException;
import io.github.sa.planeja.common.exceptions.ValidationException;
import io.github.sa.planeja.dominio.cartao.CartaoRepository;
import io.github.sa.planeja.dominio.cartao.model.CartaoEntity;
import io.github.sa.planeja.dominio.categoria.CategoriaRepository;
import io.github.sa.planeja.dominio.categoria.model.CategoriaEntity;
import io.github.sa.planeja.dominio.lancamento.dto.LancamentoDetalhes;
import io.github.sa.planeja.dominio.lancamento.dto.LancamentoForm;
import io.github.sa.planeja.dominio.lancamento.mapper.LacamentoMapper;
import io.github.sa.planeja.dominio.lancamento.model.LancamentoEntity;
import io.github.sa.planeja.dominio.lancamento.model.TipoLancamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.UUID;

import static io.github.sa.planeja.dominio.lancamento.LancamentoSpecs.*;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private LancamentoValidator validator;
    @Autowired
    private LacamentoMapper mapper;

    public LancamentoDetalhes criar(LancamentoForm form){
        CategoriaEntity categoria = categoriaRepository
                .findById(form.categoriaId())
                .orElse(null);
        CartaoEntity cartao = null;
        if(form.cartaoId() != null){
            cartao = cartaoRepository
                    .findById(form.cartaoId())
                    .orElse(null);
        }

        var result = validator.validar(form, categoria, cartao);
        if (result.isInvalido()){
            throw new ValidationException(result.getCampoInvalidos());
        }
        LancamentoEntity entity = mapper.toEntity(form, categoria, cartao);
        repository.save(entity);

        return mapper.toDetalhes(entity);
    }

    public Page<LancamentoDetalhes> listar(
            PageRequest pageRequest, YearMonth mes, TipoLancamento tipo, UUID categoriaId){

        Specification<LancamentoEntity> spec = Specification.unrestricted();

        if (tipo != null){
            spec = spec.and(tipoEqual(tipo));
        }

        if (categoriaId != null){
            spec = spec.and(categoriaEqual(categoriaId));
        }

        if (mes != null){
            spec = spec.and(mesEqual(mes));
        }

        var resultado = repository.findAll(spec, pageRequest);

        return resultado.map(mapper::toDetalhes);
    }

    public void deletar(UUID id){
        var lancamento = repository.findById(id)
                .orElseThrow(RegistroNaoEncontradoException::new);
        repository.delete(lancamento);
    }


}
