package com.example.hotel_reserva.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaResponseDTO {

    private Long id;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;

    private String nomeHospede;
    private String emailHospede;

    private String numeroQuarto;
    private String tipoQuarto;
}
