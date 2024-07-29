package com.agenda_service_back.endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    @Mapping(source = "endereco_id", target = "endereco_id")
    EnderecoDTO toDTO(Endereco endereco);
    @Mapping(source = "enderecoDTO.endereco_id", target = "endereco_id")
    Endereco toEntity(EnderecoDTO enderecoDTO);

    //recebendo lista de enderecos do banco em DTO
    List<EnderecoDTO> toDTO(List<Endereco> enderecos);
    @Mappings({
            @Mapping(source = "enderecoDTO.endereco_id", target = "endereco_id"),
            @Mapping(source = "enderecoDTO.endereco_rua", target = "endereco_rua"),
            @Mapping(source = "enderecoDTO.endereco_cep", target = "endereco_cep"),
            @Mapping(source = "enderecoDTO.endereco_numero", target = "endereco_numero"),
            @Mapping(source = "enderecoDTO.endereco_complemento", target = "endereco_complemento"),
            @Mapping(source = "enderecoDTO.endereco_cidade", target = "endereco_cidade"),
            @Mapping(source = "enderecoDTO.endereco_estado", target = "endereco_estado"),
            @Mapping(source = "enderecoDTO.endereco_bairro", target = "endereco_bairro"),
            @Mapping(source = "enderecoDTO.cliente", target = "cliente"),
            @Mapping(source = "enderecoDTO.prestador", target = "prestador")
    })
    Endereco updateEntity(EnderecoDTO enderecoDTO, Endereco endereco);
}
