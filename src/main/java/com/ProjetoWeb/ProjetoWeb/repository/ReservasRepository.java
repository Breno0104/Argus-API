package com.ProjetoWeb.ProjetoWeb.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoWeb.ProjetoWeb.domain.model.AreasComuns;
import com.ProjetoWeb.ProjetoWeb.domain.model.Reservas;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas, Long> {
    Optional<Reservas> findByAreasComunsAndDataReserva(AreasComuns areasComuns, LocalDate dataReserva);

	Optional<Reservas> findByAreasComunsAndDataReservaAndHoraInicioBetween(AreasComuns areasComuns,
			LocalDate localDate, LocalTime horaInicio, LocalTime horaFim);
}
