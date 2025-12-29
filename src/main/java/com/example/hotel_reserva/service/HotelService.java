package com.example.hotel_reserva.service;

import com.example.hotel_reserva.entity.Hotel;
import com.example.hotel_reserva.entity.Quarto;
import com.example.hotel_reserva.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public Hotel salvar(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> listarTodos() {
        return hotelRepository.findAll();
    }

    public Hotel buscarPorId(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Hotel não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new RuntimeException("Hotel não encontrado com id: " + id);
        }
        hotelRepository.deleteById(id);
    }
}
