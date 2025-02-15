package com.ProjetoWeb.ProjetoWeb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoWeb.ProjetoWeb.dto.ComunicadoDTO;
import com.ProjetoWeb.ProjetoWeb.service.ComunicadoService;

@RestController
@RequestMapping("/comunicado")
public class ComunicadoController {

    @Autowired
    private ComunicadoService comunicadoService;

    @PostMapping
    public ResponseEntity<?> enviarComunicado(@RequestBody ComunicadoDTO comunicadoDTO) {
        try {
            ComunicadoDTO comunicadoCriado = comunicadoService.enviarComunicado(comunicadoDTO);
            return new ResponseEntity<>(comunicadoCriado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ComunicadoDTO>> listarComunicados() {
        List<ComunicadoDTO> comunicados = comunicadoService.listarComunicados();
        return ResponseEntity.ok(comunicados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComunicadoDTO> atualizarComunicado(@PathVariable Long id, @RequestBody ComunicadoDTO comunicadoDTO) {
        try {

        	ComunicadoDTO comunicadoAtualizado = comunicadoService.atualizarComunicado(id, comunicadoDTO.mensagem(), comunicadoDTO.titulo());
            return new ResponseEntity<>(comunicadoAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> excluirComunicado(@PathVariable Long id) {
        try {
            comunicadoService.excluirComunicado(id);

            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Comunicado excluído com sucesso.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("erro", "Comunicado não encontrado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
