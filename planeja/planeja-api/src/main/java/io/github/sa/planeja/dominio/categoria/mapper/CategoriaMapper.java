package io.github.sa.planeja.dominio.categoria.mapper;

import io.github.sa.planeja.dominio.categoria.dto.CategoriaDetalhes;
import io.github.sa.planeja.dominio.categoria.dto.CategoriaForm;
import io.github.sa.planeja.dominio.categoria.model.CategoriaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaEntity toEntity(CategoriaForm nova);

    CategoriaDetalhes toDetalhes(CategoriaEntity entity);
}
