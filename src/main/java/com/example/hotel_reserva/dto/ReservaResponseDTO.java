package com.example.hotel_reserva.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "2026-01-20")
    private LocalDate dataCheckIn;

    private LocalDate dataCheckOut;

    private String nomeHospede;
    private String emailHospede;

    private String numeroQuarto;
    private String tipoQuarto;
    private String status;
}
