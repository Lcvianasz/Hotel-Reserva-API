package com.example.hotel_reserva.controller;

import com.example.hotel_reserva.dto.AtualizarStatusReservaDTO;
import com.example.hotel_reserva.dto.ReservaRequestDTO;
import com.example.hotel_reserva.entity.Reserva;
import com.example.hotel_reserva.service.ReservaService;
import com.example.hotel_reserva.dto.ReservaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@Tag(name = "Reservas", description = "Endpoints para gerenciamento de reservas")
@RestController
@RequestMapping("/api/reserva")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService reservaService;

    @Operation(summary = "Criar uma nova reserva")
    @PostMapping
    public ResponseEntity<ReservaResponseDTO> criar(@RequestBody @Valid ReservaRequestDTO dto){
        return ResponseEntity.ok(reservaService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<ReservaResponseDTO>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(reservaService.listar(page, size));
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
