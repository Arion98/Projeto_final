package com.agenda_service_back.prestador;

import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.servico.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestadorService {
    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private PrestadorMapper prestadorMapper;

    @Autowired
    private ServicoRepository servicoRepository;


    public Prestador authenticate(String prestadorEmail, String prestadorSenha) {
        Prestador prestador = prestadorRepository.findByPrestadorEmail(prestadorEmail);
        System.out.println("prestador: " + prestador);
        return prestador;
    }

    public List<PrestadorDTO> findAll() {
        List<Prestador> prestador = prestadorRepository.findAll();
        return prestador.stream().map(prestadorMapper::toDTO).collect(Collectors.toList());
    }

    public PrestadorDTO findById(Long id) {
        Prestador prestador = prestadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prestador não encontrado"));
        return prestadorMapper.toDTO(prestador);
    }

    @Transactional
    public PrestadorDTO create(PrestadorDTO prestadorDTO) {
        Prestador prestador = prestadorMapper.toEntity(prestadorDTO);
        Prestador savedPrestador = prestadorRepository.save(prestador);

        return prestadorMapper.toDTO(savedPrestador);
    }

    public PrestadorDTO update(Long id, PrestadorDTO prestadorDTO) {
        Prestador prestador = prestadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prestador não encontrado"));
        prestadorDTO.setPrestador_id(id);
        prestadorMapper.updateEntity(prestadorDTO, prestador);
        prestador = prestadorRepository.save(prestador);
        return prestadorMapper.toDTO(prestador);
    }

    public void deleteById(Long id) {
        prestadorRepository.deleteById(id);
    }

    public List<PrestadorDTO> findByServicoNome(String nome) {
        List<Servico> servico = servicoRepository.findByServicoNome(nome);
        List<Prestador> prestador = servico.stream()
                .map(Servico::getPrestador)
                .distinct()
                .collect(Collectors.toList());
        return prestador.stream().map(prestadorMapper::toDTO).collect(Collectors.toList());
    }
}
