package com.agenda_service_back.prestador;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/prestador")
public class PrestadorController {
    
    @Autowired
    private PrestadorService prestadorService;



    @GetMapping
    public ResponseEntity<List<PrestadorDTO>> getAllPrestadores(){
        List<PrestadorDTO> prestadorDTO = prestadorService.findAll();
        return ResponseEntity.ok(prestadorDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestadorDTO> getPrestadorById(@PathVariable Long id){
        PrestadorDTO prestadorDTO = prestadorService.findById(id);
        return ResponseEntity.ok(prestadorDTO);
    }

    @PostMapping
    public ResponseEntity<PrestadorDTO> createPrestador(@Valid @RequestBody PrestadorDTO prestadorDTO){
        System.out.println("prestadorDto:" + prestadorDTO);

        // Criação do Prestador
        PrestadorDTO createdPrestadorDTO = prestadorService.create(prestadorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrestadorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestadorDTO> updatePrestador(@PathVariable Long id, @Valid @RequestBody PrestadorDTO prestadorDTO){
        PrestadorDTO updatedPrestadorDTO = prestadorService.update(id, prestadorDTO);
        return ResponseEntity.ok(updatedPrestadorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestador(@PathVariable Long id){
        prestadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<PrestadorDTO>> getPrestadoresByServicoNome(@RequestParam String servicoNome) {
        System.out.println("servico_nome:" + servicoNome);
        if (servicoNome == null || servicoNome.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        try {
            List<PrestadorDTO> prestador = prestadorService.findByServicoNome(servicoNome);
            return ResponseEntity.ok(prestador);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
