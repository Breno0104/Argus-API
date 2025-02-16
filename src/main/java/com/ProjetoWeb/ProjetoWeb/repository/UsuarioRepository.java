package com.ProjetoWeb.ProjetoWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ProjetoWeb.ProjetoWeb.domain.model.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findById(Long id);
    UserDetails findByCpf(String cpf);
    Optional<Usuarios> findUsuarioByCpf(String cpf);
}
