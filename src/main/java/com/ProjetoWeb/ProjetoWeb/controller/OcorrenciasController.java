package com.ProjetoWeb.ProjetoWeb.controller;

import java.util.List;

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

import com.ProjetoWeb.ProjetoWeb.dto.OcorrenciasDTO;
import com.ProjetoWeb.ProjetoWeb.service.OcorrenciasService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciasController {
    private final OcorrenciasService ocorrenciasService;

    @PostMapping
    public ResponseEntity<OcorrenciasDTO> criarOcorrencia(@RequestBody OcorrenciasDTO ocorrenciaDTO) {
        OcorrenciasDTO novaOcorrencia = ocorrenciasService.criarOcorrencia(ocorrenciaDTO);
        return new ResponseEntity<>(novaOcorrencia, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciasDTO>> listarOcorrencias() {
        List<OcorrenciasDTO> ocorrencias = ocorrenciasService.listarTodasOcorrencias();
        return ResponseEntity.ok(ocorrencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciasDTO> buscarOcorrencia(@PathVariable Long id) {
        OcorrenciasDTO ocorrencia = ocorrenciasService.buscarOcorrenciaPorId(id);
        return ResponseEntity.ok(ocorrencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciasDTO> atualizarOcorrencia(
            @PathVariable Long id,
            @RequestBody OcorrenciasDTO ocorrenciaDTO
    ) {
        OcorrenciasDTO ocorrenciaAtualizada = ocorrenciasService.atualizarOcorrencia(id, ocorrenciaDTO);
        return ResponseEntity.ok(ocorrenciaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOcorrencia(@PathVariable Long id) {
        ocorrenciasService.deletarOcorrencia(id);
        return ResponseEntity.noContent().build();
    }
}
