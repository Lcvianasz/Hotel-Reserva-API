package com.example.hotel_reserva.repository;

import com.example.hotel_reserva.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByQuartoIdAndDataCheckInLessThanAndDataCheckOutGreaterThan(
            Long quartoId,
            LocalDate dataCheckIn,
            LocalDate dataCheckOut
    );

    @Query("""
        SELECT r FROM Reserva r
        WHERE r.quarto.id = :quartoId
        AND r.dataCheckIn < :dataCheckOut
        AND r.dataCheckOut > :dataCheckIn
    """)
    List<Reserva> BuscarConflitos(
            Long quartoId,
            LocalDate dataCheckIn,
            LocalDate dataCheckOut
    );
}
