package com.agenda_service_back.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoDTO> createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        EnderecoDTO createdEndereco = enderecoService.createEndereco(enderecoDTO);
        return new ResponseEntity<>(createdEndereco, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> getAllEnderecos() {
        List<EnderecoDTO> enderecos = enderecoService.getAllEnderecos();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> getEnderecoById(@PathVariable Long id) {
        EnderecoDTO endereco = enderecoService.getEnderecoById(id);
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> updateEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
        EnderecoDTO updatedEndereco = enderecoService.updateEndereco(id, enderecoDTO);
        return new ResponseEntity<>(updatedEndereco, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        enderecoService.deleteEndereco(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
