package com.agenda_service_back.cliente;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
   

    @Mapping(source = "cliente_id", target = "cliente_id")
    ClienteDTO toDTO(Cliente cliente);

    @Mapping(source = "clienteDTO.cliente_id", target = "cliente_id")
    Cliente toEntity(ClienteDTO clienteDTO);
    
    List<ClienteDTO> toDTO(List<Cliente> clientes);
    @Mappings({
            @Mapping(source = "clienteDTO.cliente_id", target = "cliente_id"),
            @Mapping(source = "clienteDTO.cliente_nome", target = "cliente_nome"),
            @Mapping(source = "clienteDTO.cliente_cpf", target = "cliente_cpf"),
            @Mapping(source = "clienteDTO.email", target = "email"),
            @Mapping(source = "clienteDTO.cliente_senha", target = "cliente_senha"),
            @Mapping(source = "clienteDTO.cliente_dataNascimento", target = "cliente_dataNascimento"),
            @Mapping(source = "clienteDTO.endereco", target = "endereco"),
            @Mapping(source = "clienteDTO.telefone", target = "telefone"),
            @Mapping(source = "clienteDTO.agendamento", target = "agendamento")
    })
    void updateEntity(ClienteDTO clienteDTO, @MappingTarget Cliente cliente);
}
