package com.example.hotel_reserva.controller;

import com.example.hotel_reserva.entity.Hotel;
import com.example.hotel_reserva.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoteis")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> criar(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.salvar(hotel));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> listar() {
        return ResponseEntity.ok(hotelService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> atualizar(
            @PathVariable Long id,
            @RequestBody Hotel hotel) {

        hotel.setId(id);
        return ResponseEntity.ok(hotelService.salvar(hotel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        hotelService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
