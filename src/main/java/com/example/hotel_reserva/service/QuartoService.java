package com.example.hotel_reserva.service;

import com.example.hotel_reserva.entity.Quarto;
import com.example.hotel_reserva.repository.QuartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuartoService {

    private final QuartoRepository quartoRepository;

    public Quarto salvar(Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    public List<Quarto> listarTodos() {
        return quartoRepository.findAll();
    }

    public Quarto buscarPorId(Long id) {
        return quartoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Quarto não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        if (!quartoRepository.existsById(id)) {
            throw new RuntimeException("Quarto não encontrado com id: " + id);
        }
        quartoRepository.deleteById(id);
    }
}
