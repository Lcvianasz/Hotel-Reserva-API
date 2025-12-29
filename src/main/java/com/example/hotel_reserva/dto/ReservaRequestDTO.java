package com.example.hotel_reserva.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservaRequestDTO {

    @NotNull
    private LocalDate dataCheckIn;
    @NotNull
    private LocalDate dataCheckOut;
    @NotNull
    private Long hospedeId;
    @NotNull
    private Long quartoId;
}
