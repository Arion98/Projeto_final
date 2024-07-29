package com.agenda_service_back.telefone;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController //habilitar o REST
@RequestMapping("/telefone") // localhost:8080/telefone
public class TelefoneController {
    @Autowired
    private TelefoneService telefoneService;
    @GetMapping //retorna uma lista de telefones
    public ResponseEntity<List<TelefoneDTO>> getAllTelefones(){
        List<TelefoneDTO> telefonesDTO = telefoneService.findAll();
        return ResponseEntity.ok(telefonesDTO);
    }
    @GetMapping("/{id}") //localhost:8080/telefone/1 pegar por id
    public ResponseEntity<TelefoneDTO> getTelefoneById(@PathVariable Long id){
        TelefoneDTO telefoneDTO = telefoneService.findById(id);
        return ResponseEntity.ok(telefoneDTO);
    }
    @PostMapping //salvar um telefone no banco de dados
    public ResponseEntity<TelefoneDTO> createTelefone(@Valid @RequestBody TelefoneDTO telefoneDTO){
        System.out.println("telefoneDTO:"+telefoneDTO);
        TelefoneDTO createTelefoneDTO = telefoneService.create(telefoneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTelefoneDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneDTO> updateTelefone(@PathVariable Long id, @Valid @RequestBody TelefoneDTO telefoneDTO){
        TelefoneDTO updateTelefoneDTO = telefoneService.update(id, telefoneDTO);
        return ResponseEntity.ok(updateTelefoneDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletetelefone(@PathVariable Long id){
        telefoneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
