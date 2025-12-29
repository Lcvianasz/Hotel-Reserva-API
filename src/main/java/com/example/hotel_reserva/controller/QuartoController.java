package com.example.hotel_reserva.controller;

import com.example.hotel_reserva.entity.Quarto;
import com.example.hotel_reserva.service.QuartoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quartos")
@RequiredArgsConstructor
public class QuartoController {

    private final QuartoService quartoService;

    @PostMapping
    public ResponseEntity<Quarto> criar(@RequestBody Quarto quarto) {
        return ResponseEntity.ok(quartoService.salvar(quarto));
    }

    @GetMapping
    public ResponseEntity<List<Quarto>> listar() {
        return ResponseEntity.ok(quartoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quarto> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(quartoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quarto> atualizar(
            @PathVariable Long id,
            @RequestBody Quarto quarto) {

        quarto.setId(id);
        return ResponseEntity.ok(quartoService.salvar(quarto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        quartoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
