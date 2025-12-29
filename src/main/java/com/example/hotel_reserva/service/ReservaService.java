package com.example.hotel_reserva.service;

import com.example.hotel_reserva.entity.Reserva;
import com.example.hotel_reserva.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public Reserva salvar(Reserva reserva) {

        if (reserva.getHospede() == null) {
            throw new RuntimeException("Reserva deve possuir um hóspede");
        }

        if (reserva.getQuarto() == null) {
            throw new RuntimeException("Reserva deve possuir um quarto");
        }

        if (reserva.getQuarto().getId() == null) {
            throw new RuntimeException("Quarto inválido (id não informado)");
        }

        if (reserva.getDataCheckIn() == null || reserva.getDataCheckOut() == null) {
            throw new RuntimeException("Datas de check-in e check-out são obrigatórias");
        }

        if (reserva.getDataCheckOut().isBefore(reserva.getDataCheckIn())) {
            throw new RuntimeException("Data de check-out não pode ser anterior ao check-in");
        }

        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarTodos() {
        return reservaRepository.findAll();
    }

    public Reserva buscarPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Reserva não encontrada com id: " + id));
    }

    public void deletar(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new RuntimeException("Reserva não encontrada com id: " + id);
        }
        reservaRepository.deleteById(id);
    }
}
