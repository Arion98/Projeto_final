package com.agenda_service_back.servico;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> getAllServicos() {
        List<ServicoDTO> servicos = servicoService.getAllServicos();
        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> getServicoById(@PathVariable("id") Long id) {
        ServicoDTO servico = servicoService.getServicoById(id);
        return ResponseEntity.ok(servico);
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> createServico(@Valid @RequestBody ServicoDTO servicoDTO) {
        ServicoDTO createdServico = servicoService.createServico(servicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdServico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> updateServicos(@PathVariable Long id, @Valid @RequestBody ServicoDTO servicoDTO){
        ServicoDTO updateServicosDTO = servicoService.update(id, servicoDTO);
        return ResponseEntity.ok(updateServicosDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable("id") Long id) {
        servicoService.deleteServico(id);
        return ResponseEntity.noContent().build();
    }

}
