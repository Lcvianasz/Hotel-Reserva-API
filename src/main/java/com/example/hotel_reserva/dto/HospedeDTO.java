package com.example.hotel_reserva.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HospedeDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
}
