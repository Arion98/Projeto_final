package com.agenda_service_back.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ServicoMapper servicoMapper;

    public ServicoDTO createServico(ServicoDTO servicoDTO) {
        Servico servico = servicoMapper.toEntity(servicoDTO);
        servico = servicoRepository.save(servico);
        return servicoMapper.toDTO(servico);
    }

    public List<ServicoDTO> getAllServicos() {
        return servicoRepository.findAll().stream()
                .map(servicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ServicoDTO getServicoById(Long id) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        return servicoMapper.toDTO(servico);
    }

    public ServicoDTO update(Long id, ServicoDTO servicoDTO) {
        Servico servico = servicoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Serviço não encontrado"));
        servicoDTO.setServico_id(id);
        servico = servicoMapper.updateEntity(servicoDTO, servico);
        servico = servicoRepository.save(servico);
        return servicoMapper.toDTO(servico);
    }

    public void deleteServico(Long id) {
        servicoRepository.deleteById(id);
    }

    public List<ServicoDTO> buscarServicosPorNome(String nome) {
        return servicoRepository.findByServicoNome(nome).stream()
                .map(servicoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
