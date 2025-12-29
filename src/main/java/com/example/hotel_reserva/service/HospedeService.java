package com.example.hotel_reserva.service;

import com.example.hotel_reserva.entity.Hospede;
import com.example.hotel_reserva.entity.Reserva;
import com.example.hotel_reserva.repository.HospedeRepository;
import com.example.hotel_reserva.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospedeService {

    private final HospedeRepository hospedeRepository;

    public Hospede salvar(Hospede hospede) {
        return hospedeRepository.save(hospede);
    }

    public List<Hospede> listarTodos() {
        return hospedeRepository.findAll();
    }

    public Hospede buscarPorId(Long id) {
        return hospedeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Hóspede não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        if (!hospedeRepository.existsById(id)) {
            throw new RuntimeException("Hóspede não encontrado com id: " + id);
        }
        hospedeRepository.deleteById(id);
    }
}

