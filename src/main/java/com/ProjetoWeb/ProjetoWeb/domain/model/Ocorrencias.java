package com.ProjetoWeb.ProjetoWeb.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ocorrencias")
public class Ocorrencias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "descricao", columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoOcorrencia tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_aprovacao")
    private StatusAprovacao statusAprovacao = StatusAprovacao.AGUARDANDO;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_resolucao", nullable = false)
    private StatusResolucao statusResolucao = StatusResolucao.PENDENTE;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "id_area")
    private AreasComuns area;

    @Column(name = "feedback")
    private String feedback;

    public enum TipoOcorrencia {
        PROBLEMA_DE_INFRAESTRUTURA,
        DESENTENDIMENTO,
        SOLICITACAO_DE_MANUTENCAO,
        ASSEMBLEIA,
        DESVIO_DE_CONDUTA
    }

    public enum StatusAprovacao {
        APROVADO,
        REJEITADO,
        AGUARDANDO
    }

    public enum StatusResolucao {
        PENDENTE,
        EM_ANDAMENTO,
        CONCLUIDA
    }
}
