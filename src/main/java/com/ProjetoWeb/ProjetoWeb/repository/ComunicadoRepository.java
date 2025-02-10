package com.ProjetoWeb.ProjetoWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoWeb.ProjetoWeb.domain.model.Comunicados;

@Repository
public interface ComunicadoRepository extends JpaRepository<Comunicados, Long> {
    Optional<Comunicados> findById(Long id);
}

