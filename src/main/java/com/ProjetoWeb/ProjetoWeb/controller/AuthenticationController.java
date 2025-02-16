package com.ProjetoWeb.ProjetoWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoWeb.ProjetoWeb.domain.model.Usuarios;
import com.ProjetoWeb.ProjetoWeb.dto.AuthenticationDTO;
import com.ProjetoWeb.ProjetoWeb.dto.LoginResponseDTO;
import com.ProjetoWeb.ProjetoWeb.dto.UsuarioDTO;
import com.ProjetoWeb.ProjetoWeb.infra.security.TokenService;
import com.ProjetoWeb.ProjetoWeb.repository.UsuarioRepository;
import com.ProjetoWeb.ProjetoWeb.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuarios) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioDTO> createUser(@RequestBody @Validated Usuarios usuarios) {
        UsuarioDTO usuarioDTO = usuarioService.createUser(usuarios);
        return ResponseEntity.ok(usuarioDTO);
    }
}
