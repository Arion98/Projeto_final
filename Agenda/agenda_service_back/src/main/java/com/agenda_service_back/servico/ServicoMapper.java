package com.agenda_service_back.servico;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicoMapper {
    @Mapping(source = "servico_id", target = "servico_id")
    ServicoDTO toDTO(Servico servico);
    @Mapping(source = "servicoDTO.servico_id", target = "servico_id")
    Servico toEntity(ServicoDTO servicoDTO);

    //recebendo lista de servicos do banco em DTO
    List<ServicoDTO> toDTO(List<Servico> servico);

    @Mappings({
            @Mapping(source = "servicoDTO.servico_id", target = "servico_id"),
            @Mapping(source = "servicoDTO.servicoNome", target = "servicoNome"),
            @Mapping(source = "servicoDTO.servico_preco", target = "servico_preco"),
            @Mapping(source = "servicoDTO.servico_descricao", target = "servico_descricao"),
            @Mapping(source = "servicoDTO.servico_informacoesExtras", target = "servico_informacoesExtras"),
            @Mapping(source = "servicoDTO.categoria", target = "categoria"),
            @Mapping(source = "servicoDTO.prestador", target = "prestador"),
            @Mapping(source = "servicoDTO.servico_classificacao", target = "servico_classificacao"),
            @Mapping(source = "servicoDTO.agendamento", target = "agendamento"),
    })
    Servico updateEntity(ServicoDTO servicoDTO, Servico servico);
}
