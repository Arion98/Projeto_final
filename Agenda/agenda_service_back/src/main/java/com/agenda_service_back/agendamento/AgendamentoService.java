package com.agenda_service_back.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AgendamentoMapper agendamentoMapper;

    public AgendamentoDTO createAgendamento(AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = agendamentoMapper.toEntity(agendamentoDTO);
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.toDTO(agendamento);
    }

    public List<AgendamentoDTO> getAllAgendamentos() {
        return agendamentoRepository.findAll().stream()
                .map(agendamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AgendamentoDTO getAgendamentoById(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        return agendamentoMapper.toDTO(agendamento);
    }

    public AgendamentoDTO update(Long id, AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Agendamento não encontrado"));
        agendamentoDTO.setAgendamento_id(id);
        agendamento = agendamentoMapper.updateEntity(agendamentoDTO, agendamento);
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.toDTO(agendamento);
    }

    public void deleteAgendamento(Long id) {
        agendamentoRepository.deleteById(id);
    }
}
