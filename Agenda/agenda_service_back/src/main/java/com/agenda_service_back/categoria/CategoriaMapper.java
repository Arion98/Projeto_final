package com.agenda_service_back.categoria;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(source = "categoria_id", target = "categoria_id")
    CategoriaDTO toDTO(Categoria categoria);

    @Mapping(source = "categoriaDTO.categoria_id", target = "categoria_id")
    Categoria toEntity(CategoriaDTO categoriaDTO);

    List<CategoriaDTO> toDTO(List<Categoria> categorias);

    @Mappings({
            @Mapping(source = "categoriaDTO.categoria_id", target = "categoria_id"),
            @Mapping(source = "categoriaDTO.categoria_nome", target = "categoria_nome"),
            @Mapping(source = "categoriaDTO.categoria_descricao", target = "categoria_descricao"),
            @Mapping(source = "categoriaDTO.servico", target = "servico")
    })
    void updateEntityFromDto(CategoriaDTO categoriaDTO, @MappingTarget Categoria categoria);
}
