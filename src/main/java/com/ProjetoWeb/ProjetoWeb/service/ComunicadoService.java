package com.ProjetoWeb.ProjetoWeb.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoWeb.ProjetoWeb.domain.model.Comunicados;
import com.ProjetoWeb.ProjetoWeb.domain.model.Condominio;
import com.ProjetoWeb.ProjetoWeb.domain.model.Usuarios;
import com.ProjetoWeb.ProjetoWeb.dto.ComunicadoDTO;
import com.ProjetoWeb.ProjetoWeb.repository.ComunicadoRepository;
import com.ProjetoWeb.ProjetoWeb.repository.CondominioRepository;
import com.ProjetoWeb.ProjetoWeb.repository.UsuarioRepository;

@Service
public class ComunicadoService {

    @Autowired
    private ComunicadoRepository comunicadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CondominioRepository condominioRepository;
    
    
    public ComunicadoDTO enviarComunicado(ComunicadoDTO comunicadoDTO) {

        Usuarios usuario = usuarioRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        Condominio condominio = condominioRepository.findByNome(comunicadoDTO.condominioNome())
                .orElseThrow(() -> new RuntimeException("Condomínio não encontrado."));

        Comunicados comunicado = new Comunicados();
        comunicado.setMensagem(comunicadoDTO.mensagem());
        comunicado.setTitulo(comunicadoDTO.titulo());  
        comunicado.setCondominio(condominio);
        comunicado.setUsuarios(usuario);

        Comunicados salvo = comunicadoRepository.save(comunicado);
        



        
         return converterParaDTO(salvo);
    }

    public List<ComunicadoDTO> listarComunicados() {
        List<Comunicados> comunicados = comunicadoRepository.findAll();

        return comunicados.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ComunicadoDTO atualizarComunicado(Long id, String novaMensagem, String novoTitulo) {

        Comunicados comunicado = comunicadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comunicado não encontrado."));

        comunicado.setMensagem(novaMensagem);
        comunicado.setTitulo(novoTitulo);  

        Comunicados comunicadoAtualizado = comunicadoRepository.save(comunicado);

        return converterParaDTO(comunicadoAtualizado);
    }

    public void excluirComunicado(Long id) {
        Comunicados comunicado = comunicadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comunicado não encontrado."));

        comunicadoRepository.delete(comunicado);
    }

    private ComunicadoDTO converterParaDTO(Comunicados comunicado) {
        return new ComunicadoDTO(
                comunicado.getId(),
                comunicado.getCondominio().getNome(),
                comunicado.getTitulo(),  
                comunicado.getMensagem()
        );
    }
}
