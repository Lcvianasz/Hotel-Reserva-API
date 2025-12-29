package com.example.hotel_reserva.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String tipo;

    private Double precoPorNoite; // ← AQUI
    private Boolean disponivel;   // ← AQUI

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
