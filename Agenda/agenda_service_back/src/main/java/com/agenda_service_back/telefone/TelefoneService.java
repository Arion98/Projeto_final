package com.agenda_service_back.telefone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelefoneService {
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private TelefoneMapper telefoneMapper;

    //buscando todos os telefones
    public List<TelefoneDTO> findAll(){
        List<Telefone> telefones = telefoneRepository.findAll();
        return telefones.stream().map(telefoneMapper::toDTO).collect(Collectors.toList());
    }
    // buscar pelo id
    public TelefoneDTO findById(Long id){
        Telefone telefone = telefoneRepository.findById(id).orElseThrow(()->new IllegalArgumentException("TELEFONE não encontrado"));
        return telefoneMapper.toDTO(telefone);
    }
    //criando um novo telefone
    public TelefoneDTO create(TelefoneDTO telefoneDTO){
        System.out.println("telefoneDTO:"+telefoneDTO);
        Telefone telefone = telefoneMapper.toEntity(telefoneDTO);
        telefone = telefoneRepository.save(telefone);
        return telefoneMapper.toDTO(telefone);
    }
    //update telefone
    public TelefoneDTO update(Long id, TelefoneDTO telefoneDTO) {
        Telefone telefone = telefoneRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Telefone não encontrado"));
        telefoneDTO.setTelefone_id(id);
        telefone = telefoneMapper.updateEntity(telefoneDTO, telefone);
        telefone = telefoneRepository.save(telefone);
        return telefoneMapper.toDTO(telefone);
    }
    public void deleteById(Long id){
        telefoneRepository.deleteById(id);
    }
}
