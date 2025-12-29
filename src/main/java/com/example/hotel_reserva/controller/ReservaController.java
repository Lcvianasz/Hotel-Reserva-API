package com.example.hotel_reserva.controller;

import com.example.hotel_reserva.entity.Reserva;
import com.example.hotel_reserva.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserva")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> criar(@RequestBody Reserva reserva){
        return ResponseEntity.ok(reservaService.salvar(reserva));
    }
    @GetMapping
    public ResponseEntity<List<Reserva>> listar(){
        return ResponseEntity.ok(reservaService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscar(@PathVariable Long id){
        return ResponseEntity.ok(reservaService.buscarPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizar(
            @PathVariable Long id,
            @RequestBody Reserva reserva){

        reserva.setId(id);
        return ResponseEntity.ok(reservaService.salvar(reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        reservaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
