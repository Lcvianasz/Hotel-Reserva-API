package com.example.hotel_reserva.controller;

import com.example.hotel_reserva.entity.Hospede;
import com.example.hotel_reserva.service.HospedeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospedes")
@RequiredArgsConstructor
public class HospedeController {
    private final HospedeService hospedeService;

    @PostMapping
    public ResponseEntity<Hospede> criar(@RequestBody Hospede hospede) {
        return ResponseEntity.ok(hospedeService.salvar(hospede));
    }
    @GetMapping
    public ResponseEntity<List<Hospede>> listar(){
        return ResponseEntity.ok(hospedeService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Hospede> buscar(@PathVariable Long id){
        return ResponseEntity.ok(hospedeService.buscarPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Hospede> atualizar(
            @PathVariable Long id,
            @RequestBody Hospede hospede){
        hospede.setId(id);
        return ResponseEntity.ok(hospedeService.salvar(hospede));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        hospedeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
