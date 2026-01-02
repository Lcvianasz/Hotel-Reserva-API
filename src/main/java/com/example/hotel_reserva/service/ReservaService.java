package com.example.hotel_reserva.service;

import com.example.hotel_reserva.dto.ReservaRequestDTO;
import com.example.hotel_reserva.dto.ReservaResponseDTO;
import com.example.hotel_reserva.exception.ResourceNotFoundException;
import com.example.hotel_reserva.exception.BusinessException;
import com.example.hotel_reserva.entity.Hospede;
import com.example.hotel_reserva.entity.Quarto;
import com.example.hotel_reserva.entity.Reserva;
import com.example.hotel_reserva.entity.StatusReserva;
import com.example.hotel_reserva.repository.HospedeRepository;
import com.example.hotel_reserva.repository.QuartoRepository;
import com.example.hotel_reserva.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final HospedeRepository hospedeRepository;
    private final QuartoRepository quartoRepository;

    public ReservaResponseDTO salvar(ReservaRequestDTO dto){
        Hospede hospede = hospedeRepository.findById(dto.getHospedeId())
                .orElseThrow(() -> new ResourceNotFoundException("Hospede não encontrado"));
        Quarto quarto = quartoRepository.findById(dto.getQuartoId())
                .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado"));
        if (dto.getDataCheckOut().isBefore(dto.getDataCheckIn())){
            throw new BusinessException("Check-out não pode ser antes do Check-in");
        }

        boolean quartoIndisponivel = !reservaRepository
                .findByQuartoIdAndDataCheckInLessThanAndDataCheckOutGreaterThan(
                        quarto.getId(),
                        dto.getDataCheckIn(),
                        dto.getDataCheckOut()
                ).isEmpty();
        if (quartoIndisponivel) {
            throw new RuntimeException("Quarto ja está reservado para este periodo");
        }
        boolean existeConflito = !reservaRepository.BuscarConflitos(
                quarto.getId(),
                dto.getDataCheckIn(),
                dto.getDataCheckOut()
        ).isEmpty();
        if (existeConflito) {
            throw new BusinessException("Quarto indisponível para o período selecionado");
        }

        Reserva reserva = Reserva.builder()
                .dataCheckIn(dto.getDataCheckIn())
                .dataCheckOut(dto.getDataCheckOut())
                .hospede(hospede)
                .quarto(quarto)
                .status(StatusReserva.CRIADA)
                .build();

        Reserva salva = reservaRepository.save(reserva);
        return toResponseDTO(salva);
    }

    public List<ReservaResponseDTO> listarDTO(){
        return reservaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ReservaResponseDTO buscarPorDto(Long id){
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));
        return toResponseDTO(reserva);
    }
    public void deletar(Long id){
        if (!reservaRepository.existsById(id)){
            throw new ResourceNotFoundException("Reserva não encontrada");
        }
        reservaRepository.deleteById(id);
    }
    private ReservaResponseDTO toResponseDTO(Reserva reserva){
        return ReservaResponseDTO.builder()
                .id(reserva.getId())
                .dataCheckIn(reserva.getDataCheckIn())
                .dataCheckOut(reserva.getDataCheckOut())
                .nomeHospede(reserva.getHospede().getNome())
                .emailHospede(reserva.getHospede().getEmail())
                .numeroQuarto(reserva.getQuarto().getNumero())
                .tipoQuarto(reserva.getQuarto().getTipo())
                .status(reserva.getStatus().name())
                .build();
    }

    public ReservaResponseDTO atualizarStatus(Long id, String novoStatus){
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));
        reserva.setStatus(StatusReserva.valueOf(novoStatus.toUpperCase()));
        Reserva salva = reservaRepository.save(reserva);
        return toResponseDTO(salva);
    }
}
