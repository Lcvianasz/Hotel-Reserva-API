package com.example.hotel_reserva.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarStatusReservaDTO {
    @NotNull
    private String status;
}
