package com.example.hotel_reserva.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuartoDTO {

    private Long id;
    private String numero;
    private String tipo;
    private Double precoPorNoite;
    private Boolean disponivel;
}
