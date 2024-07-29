package com.agenda_service_back.cliente;

import java.util.List;

public interface ClienteService {
    ClienteDTO createCliente(ClienteDTO clienteDTO);
    List<ClienteDTO> getAllClientes();
    ClienteDTO getClienteById(Long clienteId); 
    ClienteDTO updateCliente(Long clienteId, ClienteDTO clienteDTO);
    void deleteCliente(Long clienteId);
}
