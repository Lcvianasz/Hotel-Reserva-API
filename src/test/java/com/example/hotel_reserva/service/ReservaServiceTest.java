package com.example.hotel_reserva.service;

import com.example.hotel_reserva.dto.ReservaRequestDTO;
import com.example.hotel_reserva.entity.Hospede;
import com.example.hotel_reserva.entity.Quarto;
import com.example.hotel_reserva.entity.Reserva;
import com.example.hotel_reserva.repository.HospedeRepository;
import com.example.hotel_reserva.repository.QuartoRepository;
import com.example.hotel_reserva.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private QuartoRepository quartoRepository;

    @Mock
    private HospedeRepository hospedeRepository;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void deveCriarReservaComSucesso() {
        Hospede hospede = Hospede.builder()
                .id(1L)
                .nome("Lucas")
                .email("lucas@email.com")
                .build();
        Quarto quarto = Quarto.builder()
                .id(1L)
                .numero("101")
                .tipo("STANDARD")
                .build();
        ReservaRequestDTO dto = ReservaRequestDTO.builder()
                .hospedeId(1L)
                .quartoId(1L)
                .dataCheckIn(LocalDate.now())
                .dataCheckOut(LocalDate.now().plusDays(2))
                .build();
        when (hospedeRepository.findById(1L)).thenReturn(Optional.of(hospede));
        when (quartoRepository.findById(1L)).thenReturn(Optional.of(quarto));
        when (reservaRepository.save(any(Reserva.class)))
                .thenAnswer(invocation -> {
                    Reserva r = invocation.getArgument(0);
                    r.setId(1L);
                    return r;
                });
        var response = reservaService.salvar(dto);

        assertNotNull(response);
        assertEquals("Lucas", response.getNomeHospede());
        assertEquals("101", response.getNumeroQuarto());
        verify(reservaRepository, times(1)).save(any());
    }

    @Test
    void deveLancaErroQuandoHospedeNaoExiste() {

        ReservaRequestDTO dto = ReservaRequestDTO.builder()
                .hospedeId(99L)
                .quartoId(1L)
                .dataCheckIn(LocalDate.now())
                .dataCheckOut(LocalDate.now().plusDays(1))
                .build();
        when (hospedeRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> reservaService.salvar(dto)
        );
        assertEquals("Hospede não encontrado", ex.getMessage());
    }

    @Test
    void deveLancarErroQuandoCheckoutAntesDoCheckin() {
        Hospede hospede = Hospede.builder()
                .id(1L)
                .nome("Lucas")
                .email("lucas@email.com")
                .build();

        Quarto quarto = Quarto.builder()
                .id(1L)
                .numero("101")
                .tipo("STANDARD")
                .build();

        ReservaRequestDTO dto = ReservaRequestDTO.builder()
                .hospedeId(1L)
                .quartoId(1L)
                .dataCheckIn(LocalDate.now())
                .dataCheckOut(LocalDate.now().minusDays(1))
                .build();

        when(hospedeRepository.findById(1L)).thenReturn(Optional.of(hospede));
        when(quartoRepository.findById(1L)).thenReturn(Optional.of(quarto));

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> reservaService.salvar(dto)
        );

        assertEquals("Check-out não pode ser antes do Check-in", ex.getMessage());
    }
}
