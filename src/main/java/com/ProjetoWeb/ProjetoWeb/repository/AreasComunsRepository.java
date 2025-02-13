package com.ProjetoWeb.ProjetoWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoWeb.ProjetoWeb.domain.model.AreasComuns;

@Repository
public interface AreasComunsRepository extends JpaRepository<AreasComuns, Long> {
    Optional<AreasComuns> findByNome(String nome);
}
