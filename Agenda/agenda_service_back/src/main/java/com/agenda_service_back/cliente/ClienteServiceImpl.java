package com.agenda_service_back.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda_service_back.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente savedCliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(savedCliente);
    }

    @Override
    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(clienteMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ClienteDTO getClienteById(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(clienteMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente not found with id " + clienteId));
    }

    @Override
    public ClienteDTO updateCliente(Long clienteId, ClienteDTO clienteDTO) {
        Optional<Cliente> existingClienteOpt = clienteRepository.findById(clienteId);
        if (existingClienteOpt.isPresent()) {
            Cliente existingCliente = existingClienteOpt.get();
            clienteMapper.updateEntity(clienteDTO, existingCliente);
            Cliente updatedCliente = clienteRepository.save(existingCliente);
            return clienteMapper.toDTO(updatedCliente);
        } else {
            throw new ResourceNotFoundException("Cliente not found with id " + clienteId);
        }
    }

    @Override
    public void deleteCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
