package com.ProjetoWeb.ProjetoWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoWeb.ProjetoWeb.domain.model.Condominio;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio, Long> {
    Optional<Condominio> findByNome(String nome);
    Optional<Condominio> findById(Long id);
}
