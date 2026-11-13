package io.github.sa.planeja.dominio.categoria;

import io.github.sa.planeja.common.exceptions.RegistroNaoEncontradoException;
import io.github.sa.planeja.common.exceptions.ValidationException;
import io.github.sa.planeja.common.validation.ValidationResult;
import io.github.sa.planeja.dominio.categoria.dto.CategoriaDetalhes;
import io.github.sa.planeja.dominio.categoria.dto.CategoriaForm;
import io.github.sa.planeja.dominio.categoria.mapper.CategoriaMapper;
import io.github.sa.planeja.dominio.categoria.model.CategoriaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaValidator validator;
    @Autowired
    private CategoriaMapper mapper;
    @Autowired
    private CategoriaRepository repository;

    public CategoriaDetalhes criar(CategoriaForm nova) {
        ValidationResult result = validator.validar(nova);

        if(result.isInvalido()){
            throw new ValidationException(result.getCampoInvalidos());
        }

        CategoriaEntity entity = mapper.toEntity(nova);
        repository.save(entity);

        return mapper.toDetalhes(entity);
    }

    public Page<CategoriaDetalhes> listar(PageRequest pageRequest) {
        return repository
                .findAll(pageRequest)
                .map(mapper::toDetalhes);
    }

    @Transactional
    public void mudarStatus(UUID id) {
        var categoria = repository.findById(id)
                .orElseThrow(RegistroNaoEncontradoException::new);

        categoria.setAtivo(!categoria.getAtivo());
    }

    public List<CategoriaDetalhes> listarAtivas() {
        return repository.findByAtivoTrue()
                .stream().map(mapper::toDetalhes).toList();
    }

    public List<CategoriaDetalhes> listarTodas() {
        return repository.findAll(Sort.by("nome"))
                .stream().map(mapper::toDetalhes).toList();
    }
}
