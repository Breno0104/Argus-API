package com.ProjetoWeb.ProjetoWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoWeb.ProjetoWeb.domain.model.Condominio;
import com.ProjetoWeb.ProjetoWeb.dto.CondominioDTO;
import com.ProjetoWeb.ProjetoWeb.repository.CondominioRepository;

@Service
public class CondominioService {
    @Autowired
    private CondominioRepository condominioRepository;

    public Condominio createCondominio(CondominioDTO condominioDTO) {
        Condominio condominio = new Condominio();
        condominio.setNome(condominioDTO.nome());
        condominio.setEndereco(condominioDTO.endereco());
        return condominioRepository.save(condominio);
    }

    public List<Condominio> getAllCondominios() {
        return condominioRepository.findAll();
    }

    public Condominio getCondominioById(Long id) throws Exception {
        return condominioRepository.findById(id).orElse(null);
    }

    public Condominio updateCondominio(Long id, CondominioDTO condominioDTO) {
        Condominio condominio = condominioRepository.findById(id).orElse(null);
        if (condominio != null) {
            condominio.setNome(condominioDTO.nome());
            condominio.setEndereco(condominioDTO.endereco());
            return condominioRepository.save(condominio);
        }
        return null;
    }

    public void deleteCondominio(Long id) {
        condominioRepository.deleteById(id);
    }
}
