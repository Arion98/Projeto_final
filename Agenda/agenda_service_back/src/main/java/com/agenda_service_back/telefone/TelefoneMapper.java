package com.agenda_service_back.telefone;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TelefoneMapper {
    @Mapping(source = "telefone_id", target = "telefone_id")
    TelefoneDTO toDTO(Telefone telefone);
    @Mapping(source = "telefoneDTO.telefone_id", target = "telefone_id")
    Telefone toEntity(TelefoneDTO telefoneDTO);

    //recebendo lista de telefone do banco em DTO
    List<TelefoneDTO> toDTO(List<Telefone> telefones);

    @Mappings({
            @Mapping(source = "telefoneDTO.telefone_id", target = "telefone_id"),
            @Mapping(source = "telefoneDTO.telefone_numero", target = "telefone_numero"),
            @Mapping(source = "telefoneDTO.cliente", target = "cliente"),
            @Mapping(source = "telefoneDTO.prestador", target = "prestador"),
    })
    Telefone updateEntity(TelefoneDTO telefoneDTO, Telefone telefone);
}
