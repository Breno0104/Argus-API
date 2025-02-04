package com.ProjetoWeb.ProjetoWeb.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoWeb.ProjetoWeb.domain.model.Condominio;
import com.ProjetoWeb.ProjetoWeb.dto.CondominioDTO;
import com.ProjetoWeb.ProjetoWeb.service.CondominioService;

@RestController
@RequestMapping("/condominio")
public class CondominioController {
    @Autowired
    private CondominioService condominioService;

    @PostMapping
    public ResponseEntity<String> createCondominio(@RequestBody CondominioDTO condominioDTO) {
        condominioService.createCondominio(condominioDTO);
        return ResponseEntity.ok(condominioDTO.nome() + " criado com sucesso!");
    }

    @GetMapping
    public List<Condominio> getAllCondominios() {
        return condominioService.getAllCondominios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Condominio> getCondominioById(@PathVariable Long id) throws Exception {
        Condominio condominio = condominioService.getCondominioById(id);
        return ResponseEntity.ok(condominio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Condominio> updateCondominio(@PathVariable Long id, @RequestBody CondominioDTO condominioDTO) {
        Condominio condominio = condominioService.updateCondominio(id, condominioDTO);
        return ResponseEntity.ok(condominio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCondominio(@PathVariable Long id) {
        condominioService.deleteCondominio(id);
        return ResponseEntity.ok("Condomínio " + id + " excluído.");
    }
}
