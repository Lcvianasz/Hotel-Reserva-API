package com.example.hotel_reserva.controller;

import com.example.hotel_reserva.dto.AtualizarStatusReservaDTO;
import com.example.hotel_reserva.dto.ReservaRequestDTO;
import com.example.hotel_reserva.entity.Reserva;
import com.example.hotel_reserva.service.ReservaService;
import com.example.hotel_reserva.dto.ReservaResponseDTO;
import jakarta.validation.Valid;
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
    public ResponseEntity<ReservaResponseDTO> criar(@RequestBody @Valid ReservaRequestDTO dto){
        return ResponseEntity.ok(reservaService.salvar(dto));
    }
    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listar(){

        return ResponseEntity.ok(reservaService.listarDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(reservaService.buscarPorDto(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        reservaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ReservaResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestBody AtualizarStatusReservaDTO dto) {
        return ResponseEntity.ok(
                reservaService.atualizarStatus(id, dto.getStatus())
        );
    }
}
