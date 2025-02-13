package com.ProjetoWeb.ProjetoWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoWeb.ProjetoWeb.domain.model.Ocorrencias;

@Repository
public interface OcorrenciasRepository extends JpaRepository<Ocorrencias, Long> {
    List<Ocorrencias> findByUsuarioId(Long usuarioId);
    List<Ocorrencias> findByTipo(Ocorrencias.TipoOcorrencia tipo);
    List<Ocorrencias> findByStatusAprovacao(Ocorrencias.StatusAprovacao statusAprovacao);
    List<Ocorrencias> findByStatusResolucao(Ocorrencias.StatusResolucao statusResolucao);
}